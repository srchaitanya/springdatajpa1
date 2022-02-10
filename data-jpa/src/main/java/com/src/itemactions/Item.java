package com.src.itemactions;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@Entity
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "item_id")
	private Long id;
	
	private String itemName;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "item")
	private List<Action> actions; 
	
	
	protected Item() {}

	
	public Item(String itemName, List<Action> actions) {
		super();
		this.itemName = itemName;
		this.actions = actions;
	}
	
	

	public String getItemName() {
		return itemName;
	}

	public List<Action> getActions() {
		return actions;
	}


	@Override
	public String toString() {
		return "Item [Id=" + id + ", itemName=" + itemName + ", actions=" + actions + "]";
	}


	public Long getId() {
		return id;
	}


}

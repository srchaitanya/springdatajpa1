package com.src.itemactions;

import java.util.List;

import javax.persistence.CascadeType;
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
	private Long Id;
	
	private String itemName;
	
	@OneToMany(cascade = CascadeType.ALL)
	@OrderBy("insertDateTime DESC")
	private List<Action> actions; 
	
	
	protected Item() {}

	
	public Item(String itemName, List<Action> actions) {
		super();
		this.itemName = itemName;
		this.actions = actions;
	}
	
	public Long getId() {
		return Id;
	}

	public String getItemName() {
		return itemName;
	}

	public List<Action> getActions() {
		return actions;
	}


	@Override
	public String toString() {
		return "Item [Id=" + Id + ", itemName=" + itemName + ", actions=" + actions + "]";
	}


}

package com.src.itemactions;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Action {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "action_id")
	private Long id;
	
	private String actionName;
	private Timestamp insertDateTime;
	
	@ManyToOne
	@JoinColumn(name="item_id")
	private Item item;
	
	protected Action() {}
	
	public Action(String actionName, Timestamp date) {
		this.actionName=actionName;
		this.insertDateTime=date;
	}

	public Long getId() {
		return id;
	}

	public String getActionName() {
		return actionName;
	}

	public Item getItem() {
		return item;
	}

	@Override
	public String toString() {
		return "Action [id=" + id + ", actionName=" + actionName + ", insertDateTime=" + insertDateTime + ", item=" + item.getId()
				+ "]";
	}

	public Timestamp getInsertDateTime() {
		return insertDateTime;
	}

	public void setInsertDateTime(Timestamp insertDateTime) {
		this.insertDateTime = insertDateTime;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public void setItem(Item item) {
		this.item = item;
	}
	
}

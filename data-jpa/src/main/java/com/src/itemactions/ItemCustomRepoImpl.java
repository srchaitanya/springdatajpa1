package com.src.itemactions;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class ItemCustomRepoImpl {

	private EntityManager entityManager;

	public ItemCustomRepoImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}
	
	@Transactional
	public void printActionByItemId(Long itemId) {
		String queryStr = "select a from Item i join i.actions a where i.id =:itemId AND a.insertDateTime = (select max(action.insertDateTime) from Action action where action.item = i)";		
		Query query = this.entityManager.createQuery(queryStr);
		query.setParameter("itemId", itemId);
		System.out.println("actions with itemId :"+itemId);
		List list = query.getResultList();
		System.out.println(list);
	}

	@Transactional
	public void printItemById(Long id) {
		System.out.println("Item with id:" + id);
		List list = this.entityManager.createQuery("select i from Item i where i.id=:idval").setParameter("idval", id)
				.getResultList();
		System.out.println(list);
	}
	
	@Transactional
	public void saveItemAndActions(Item i,List<Action> actions) {
		this.entityManager.persist(i);
	}
	
	
	@Transactional
	public Action getLastAction(Long itemId) {
		Item i = this.entityManager.find(Item.class, itemId);
		Action a = null;
		if (i != null) {
			if (i.getActions() != null && !i.getActions().isEmpty()) {
				a = i.getActions().get(0);
			}
		}
		return a;
	}

}

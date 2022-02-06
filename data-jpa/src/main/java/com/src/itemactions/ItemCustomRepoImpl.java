package com.src.itemactions;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

@Component
public class ItemCustomRepoImpl {
	
    private EntityManager entityManager;
	
	public ItemCustomRepoImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Transactional
	public Action getLastAction(Long itemId) {
		Item i = this.entityManager.find(Item.class, itemId);
		Action a=null;
		if(i!=null) {
			if(i.getActions()!=null && !i.getActions().isEmpty()) {
				a = i.getActions().get(0);
			}
		}
		return a;		
	}

}

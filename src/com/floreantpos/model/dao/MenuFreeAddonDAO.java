package com.floreantpos.model.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

import com.floreantpos.model.MenuFreeAddon;

public class MenuFreeAddonDAO extends BaseMenuFreeAddonDAO {

	/**
	 * Default constructor.  Can be used in place of getInstance()
	 */
	public MenuFreeAddonDAO() {
	}

	@Override
	public List<MenuFreeAddon> findAll() {
		Session session = null;
		try {
			session = getSession();
			Criteria criteria = session.createCriteria(getReferenceClass());
			criteria.addOrder(Order.asc(MenuFreeAddon.PROP_SORT_ORDER));
			return criteria.list();
		} catch (Exception e) {
			throw e;
		} finally {
			closeSession(session);
		}
	}

	public void saveOrUpdateMenuFreeAddons(List<MenuFreeAddon> items) {
		Session session = null;
		Transaction tx = null;
		try {
			session = createNewSession();
			tx = session.beginTransaction();
			saveOrUpdateMenuFreeAddons(items, session);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	public void saveOrUpdateMenuFreeAddons(List<MenuFreeAddon> items, Session session) {
		for (Iterator iterator = items.iterator(); iterator.hasNext();) {
			MenuFreeAddon multiplier = (MenuFreeAddon) iterator.next();
			session.saveOrUpdate(multiplier);
		}
	}
}
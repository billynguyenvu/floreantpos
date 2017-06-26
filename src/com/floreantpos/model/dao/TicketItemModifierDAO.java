/**
 * ************************************************************************
 * * The contents of this file are subject to the MRPL 1.2
 * * (the  "License"),  being   the  Mozilla   Public  License
 * * Version 1.1  with a permitted attribution clause; you may not  use this
 * * file except in compliance with the License. You  may  obtain  a copy of
 * * the License at http://www.floreantpos.org/license.html
 * * Software distributed under the License  is  distributed  on  an "AS IS"
 * * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * * License for the specific  language  governing  rights  and  limitations
 * * under the License.
 * * The Original Code is FLOREANT POS.
 * * The Initial Developer of the Original Code is OROCUBE LLC
 * * All portions are Copyright (C) 2015 OROCUBE LLC
 * * All Rights Reserved.
 * ************************************************************************
 */
package com.floreantpos.model.dao;

import com.floreantpos.PosException;
import com.floreantpos.model.TicketItem;
import com.floreantpos.model.TicketItemModifier;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;



public class TicketItemModifierDAO extends BaseTicketItemModifierDAO {

	/**
	 * Default constructor.  Can be used in place of getInstance()
	 */
	public TicketItemModifierDAO () {}


	public List<TicketItemModifier> findByTicket(TicketItem ticketItem, Session session) throws PosException {
		try {
			Criteria criteria = session.createCriteria(getReferenceClass());
			criteria.add(Restrictions.eq(TicketItemModifier.PROP_TICKET_ITEM, ticketItem));

			return criteria.list();
		} catch (Exception e) {
			throw new PosException("Cannot get ticket item modifier object for ticket: " + ticketItem.getId()); //$NON-NLS-1$ //$NON-NLS-2$
		} 
	}


	public List<TicketItemModifier> findByTicket(TicketItem ticketItem) throws PosException {
		Session session = null;

		try {
			session = getSession();

			Criteria criteria = session.createCriteria(getReferenceClass());
			criteria.add(Restrictions.eq(TicketItemModifier.PROP_TICKET_ITEM, ticketItem));

			return criteria.list();
		} catch (Exception e) {
			throw new PosException("Cannot get ticket item modifier object for ticket: " + ticketItem.getId()); //$NON-NLS-1$ //$NON-NLS-2$
		} finally {
			closeSession(session);
		}
	}

}
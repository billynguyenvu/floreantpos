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

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.floreantpos.PosLog;
import com.floreantpos.model.MenuAddon;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

public class MenuAddonDAO extends BaseMenuAddonDAO {

	/**
	 * Default constructor.  Can be used in place of getInstance()
	 */
	public MenuAddonDAO() {
	}

	public List<MenuAddon> findMenuAddon(String name) {
		Session session = null;
		Criteria criteria = null;

		try {
			session = getSession();
			criteria = session.createCriteria(MenuAddon.class);
			if (StringUtils.isNotEmpty(name)) {
				criteria.add(Restrictions.ilike(MenuAddon.PROP_NAME, name + "%".trim(), MatchMode.ANYWHERE)); //$NON-NLS-1$
			}

			return criteria.list();
		} finally {

			session.close();
		}
	}
}
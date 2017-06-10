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
package com.floreantpos.model;

import java.awt.Color;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.StringUtils;

import com.floreantpos.config.TerminalConfig;
import com.floreantpos.model.base.BaseMenuFreeAddon;

@XmlRootElement(name = "menu-freeaddon")
public class MenuFreeAddon extends BaseMenuFreeAddon {
	private static final long serialVersionUID = 1L;

	/*[CONSTRUCTOR MARKER BEGIN]*/
	public MenuFreeAddon () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public MenuFreeAddon (java.lang.Integer id) {
		super(id);
	}

	/*[CONSTRUCTOR MARKER END]*/

	@Override
	public Integer getSortOrder() {
		return sortOrder == null ? 9999 : sortOrder;
	}

	@Override
	public Integer getButtonColor() {
		return buttonColor;
	}

	@Override
	public Integer getTextColor() {
		return textColor;
	}

	public String getDisplayName() {
		if (TerminalConfig.isUseTranslatedName() && StringUtils.isNotEmpty(getTranslatedName())) {
			return getTranslatedName();
		}

		return super.getName();
	}

	public String getPrintedName() {
		if (!TerminalConfig.isUseTranslatedName() && StringUtils.isNotEmpty(getTranslatedName())) {
			return getTranslatedName();
		}

		return super.getName();
	}

	@Override
	public String toString() {
		return getDisplayName();
	}

	public String getUniqueId() {
		return ("menu_freeaddon_" + getName() + "_" + getId()).replaceAll("\\s+", "_"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	}
}
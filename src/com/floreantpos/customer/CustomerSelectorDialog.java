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
package com.floreantpos.customer;

import java.awt.BorderLayout;
import java.awt.HeadlessException;

import com.floreantpos.Messages;
import com.floreantpos.main.Application;
import com.floreantpos.main.PosWindow;
import com.floreantpos.model.Customer;
import com.floreantpos.model.Ticket;
import com.floreantpos.ui.TitlePanel;
import com.floreantpos.ui.dialog.POSDialog;
import javax.swing.JTabbedPane;

public class CustomerSelectorDialog extends POSDialog {

	private final CustomerSelector todayCustomerSelector;
        private final CustomerSelector allCustomerSelector;
        private JTabbedPane tabbedPane = new JTabbedPane();

	public CustomerSelectorDialog(CustomerSelector todayCustomerSelector, CustomerSelector allCustomerSelector) throws HeadlessException {
		super(Application.getPosWindow(), true);
		this.todayCustomerSelector = todayCustomerSelector;
		this.allCustomerSelector = allCustomerSelector;

		TitlePanel titlePane = new TitlePanel();
		titlePane.setTitle(Messages.getString("CustomerSelectorDialog.0")); //$NON-NLS-1$

		getContentPane().add(titlePane, BorderLayout.NORTH);
                getContentPane().add(tabbedPane);
                tabbedPane.addTab("Today Customers", todayCustomerSelector);
                tabbedPane.addTab("All Customers", allCustomerSelector);
                tabbedPane.setSelectedComponent(todayCustomerSelector);
		this.todayCustomerSelector.setTabbedPane(tabbedPane);
		this.allCustomerSelector.setTabbedPane(tabbedPane);

		PosWindow window = Application.getPosWindow();
		setSize(window.getSize());
		setLocation(window.getLocation());
	}

	public void setCreateNewTicket(boolean createNewTicket) {
		todayCustomerSelector.setCreateNewTicket(createNewTicket);
		allCustomerSelector.setCreateNewTicket(createNewTicket);
	}

	public void updateView(boolean update) {
		todayCustomerSelector.updateView(update);
		allCustomerSelector.updateView(update);
	}

	public Customer getSelectedCustomer() {
		return ((CustomerSelector)tabbedPane.getSelectedComponent()).getSelectedCustomer();
	}

	public void setTicket(Ticket thisTicket) {
		todayCustomerSelector.setTicket(thisTicket);
		allCustomerSelector.setTicket(thisTicket);
	}

	public void setCustomer(Customer customer) {
		todayCustomerSelector.setCustomer(customer); 
		allCustomerSelector.setCustomer(customer); 
	}

	public void setCallerId(String callerId) {
		todayCustomerSelector.setCallerId(callerId); 
		allCustomerSelector.setCallerId(callerId); 
	}
}
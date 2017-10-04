package com.floreantpos.customer;

import com.floreantpos.extension.ExtensionManager;
import com.floreantpos.extension.OrderServiceExtension;
import com.floreantpos.model.OrderType;
import com.floreantpos.model.Ticket;

public class CustomerSelectorFactory {
	private static CustomerSelector customerSelector;

	public static CustomerSelectorDialog createCustomerSelectorDialog(OrderType orderType) {
		OrderServiceExtension orderServicePlugin = (OrderServiceExtension) ExtensionManager.getPlugin(OrderServiceExtension.class);
		if (customerSelector == null) {
			if (orderServicePlugin == null) {
				customerSelector = new DefaultCustomerListView();
			}
			else {
				customerSelector = orderServicePlugin.createNewCustomerSelector();
			}
		}
		customerSelector.setOrderType(orderType);
		customerSelector.redererCustomers();

		return new CustomerSelectorDialog(customerSelector);
	}

	public static CustomerSelectorDialog createCustomerSelectorDialog(OrderType orderType, Ticket ticket) {
		OrderServiceExtension orderServicePlugin = (OrderServiceExtension) ExtensionManager.getPlugin(OrderServiceExtension.class);
		if (customerSelector == null) {
			if (orderServicePlugin == null) {
				customerSelector = new DefaultCustomerListView(ticket);
			}
			else {
				customerSelector = orderServicePlugin.createNewCustomerSelector();
			}
		}
                else {
                    customerSelector.setTicket(ticket);
                    ((DefaultCustomerListView)customerSelector).loadCustomerFromTicket();
                }
		customerSelector.setOrderType(orderType);
		customerSelector.redererCustomers();

		return new CustomerSelectorDialog(customerSelector);
	}
}

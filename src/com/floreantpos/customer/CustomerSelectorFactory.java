package com.floreantpos.customer;

import com.floreantpos.extension.ExtensionManager;
import com.floreantpos.extension.OrderServiceExtension;
import com.floreantpos.model.OrderType;
import com.floreantpos.model.Ticket;

public class CustomerSelectorFactory {
	private static CustomerSelector todayCustomerSelector;
	private static CustomerSelector allCustomerSelector;

	public static CustomerSelectorDialog createCustomerSelectorDialog(OrderType orderType) {
		OrderServiceExtension orderServicePlugin = (OrderServiceExtension) ExtensionManager.getPlugin(OrderServiceExtension.class);
		if (todayCustomerSelector == null) {
			if (orderServicePlugin == null) {
				todayCustomerSelector = new DefaultCustomerListView();
			}
			else {
				todayCustomerSelector = orderServicePlugin.createNewCustomerSelector();
			}
		}
		todayCustomerSelector.setOrderType(orderType);
		todayCustomerSelector.redererCustomers();
		if (allCustomerSelector == null) {
			if (orderServicePlugin == null) {
				allCustomerSelector = new DefaultCustomerListView();
			}
			else {
				allCustomerSelector = orderServicePlugin.createNewCustomerSelector();
			}
		}
		allCustomerSelector.setOrderType(orderType);
		allCustomerSelector.redererCustomers();

		return new CustomerSelectorDialog(todayCustomerSelector, allCustomerSelector);
	}

	public static CustomerSelectorDialog createCustomerSelectorDialog(OrderType orderType, Ticket ticket) {
		OrderServiceExtension orderServicePlugin = (OrderServiceExtension) ExtensionManager.getPlugin(OrderServiceExtension.class);
		if (todayCustomerSelector == null) {
			if (orderServicePlugin == null) {
				todayCustomerSelector = new DefaultCustomerListView(ticket, true);
			}
			else {
				todayCustomerSelector = orderServicePlugin.createNewCustomerSelector();
			}
		}
                else {
                    todayCustomerSelector.setTicket(ticket);
                    ((DefaultCustomerListView)todayCustomerSelector).loadCustomerFromTicket();
                }
		todayCustomerSelector.setOrderType(orderType);
		todayCustomerSelector.redererCustomers();
		if (allCustomerSelector == null) {
			if (orderServicePlugin == null) {
				allCustomerSelector = new DefaultCustomerListView(ticket, false);
			}
			else {
				allCustomerSelector = orderServicePlugin.createNewCustomerSelector();
			}
		}
                else {
                    allCustomerSelector.setTicket(ticket);
                    ((DefaultCustomerListView)allCustomerSelector).loadCustomerFromTicket();
                }
		allCustomerSelector.setOrderType(orderType);
		allCustomerSelector.redererCustomers();

		return new CustomerSelectorDialog(todayCustomerSelector, allCustomerSelector);
	}
}

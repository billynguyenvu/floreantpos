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
/*
 * TicketView.java
 *
 * Created on August 4, 2006, 3:42 PM
 */

package com.floreantpos.ui.views.order;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.miginfocom.swing.MigLayout;

import com.floreantpos.IconFactory;
import com.floreantpos.Messages;
import com.floreantpos.POSConstants;
import com.floreantpos.PosException;
import com.floreantpos.config.TerminalConfig;
import com.floreantpos.main.Application;
import com.floreantpos.model.ITicketItem;
import com.floreantpos.model.MenuItem;
import com.floreantpos.model.OrderType;
import com.floreantpos.model.Ticket;
import com.floreantpos.model.TicketItem;
import com.floreantpos.model.TicketItemModifier;
import com.floreantpos.model.dao.MenuItemDAO;
import com.floreantpos.model.dao.TicketDAO;
import com.floreantpos.report.ReceiptPrintService;
import com.floreantpos.swing.PosButton;
import com.floreantpos.swing.PosScrollPane;
import com.floreantpos.swing.PosUIManager;
import com.floreantpos.ui.dialog.AutomatedWeightInputDialog;
import com.floreantpos.ui.dialog.BasicWeightInputDialog;
import com.floreantpos.ui.dialog.ItemSearchDialog;
import com.floreantpos.ui.dialog.NumberSelectionDialog2;
import com.floreantpos.ui.dialog.POSMessageDialog;
import com.floreantpos.ui.dialog.SeatSelectionDialog;
import com.floreantpos.ui.views.CashierSwitchBoardView;
import com.floreantpos.ui.views.order.actions.OrderListener;
import com.floreantpos.util.CurrencyUtil;
import com.floreantpos.util.DrawerUtil;
import com.floreantpos.util.NumberUtil;
import com.floreantpos.util.POSUtil;

/**
 * 
 * @author MShahriar
 */
public class TicketView extends JPanel {

	private java.util.Vector<OrderListener> orderListeners = new java.util.Vector<OrderListener>();
	private Ticket ticket;
	private com.floreantpos.swing.PosButton btnDecreaseAmount;
	private com.floreantpos.swing.PosButton btnDelete = new PosButton();
	private com.floreantpos.swing.PosButton btnIncreaseAmount = new PosButton();
	private com.floreantpos.swing.PosButton btnEdit = new PosButton("..."); //$NON-NLS-1$ //$NON-NLS-2$
	private com.floreantpos.swing.PosButton btnScrollDown;
	private com.floreantpos.swing.PosButton btnScrollUp = new PosButton();
	private com.floreantpos.swing.TransparentPanel ticketItemActionPanel;
	private javax.swing.JScrollPane ticketScrollPane;
	private PosButton btnTotal;
	private com.floreantpos.ui.ticket.TicketViewerTable ticketViewerTable;
	private JPanel itemSearchPanel;
	private JTextField txtSearchItem;
	private TitledBorder titledBorder = new TitledBorder(""); //$NON-NLS-1$
	private Border border = new CompoundBorder(titledBorder, new EmptyBorder(2, 2, 2, 2));
	private boolean cancelable;
	private boolean allowToLogOut;
	public final static String VIEW_NAME = "TICKET_VIEW"; //$NON-NLS-1$

	public TicketView() {
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed"
	// desc=" Generated Code ">//GEN-BEGIN:initComponents
	private void initComponents() {

		titledBorder.setTitleJustification(TitledBorder.CENTER);
		setBorder(border);
		setLayout(new java.awt.BorderLayout(5, 5));
		itemSearchPanel = new JPanel();

		ticketItemActionPanel = new com.floreantpos.swing.TransparentPanel();
		btnDecreaseAmount = new com.floreantpos.swing.PosButton();
		btnScrollDown = new com.floreantpos.swing.PosButton();
		ticketViewerTable = new com.floreantpos.ui.ticket.TicketViewerTable();
		ticketScrollPane = new PosScrollPane(ticketViewerTable);
		ticketScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		ticketScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		ticketScrollPane.setPreferredSize(PosUIManager.getSize(180, 200));

		btnEdit.setEnabled(false);

		createPayButton();

		createTicketItemControlPanel();
		createItemSearchPanel();

		JPanel centerPanel = new JPanel(new BorderLayout(5, 5));
		centerPanel.add(ticketScrollPane);

		add(itemSearchPanel, BorderLayout.NORTH);
		add(centerPanel);
		add(ticketItemActionPanel, BorderLayout.EAST);
		ticketViewerTable.getRenderer().setInTicketScreen(true);
		ticketViewerTable.getSelectionModel().addListSelectionListener(new TicketItemSelectionListener());
		setPreferredSize(PosUIManager.getSize(360, 463));
	}// </editor-fold>//GEN-END:initComponents

	private void createItemSearchPanel() {

		itemSearchPanel.setLayout(new MigLayout("insets 0", "grow", ""));
		PosButton btnSearch = new PosButton("...");

		txtSearchItem = new JTextField();

		txtSearchItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (txtSearchItem.getText().equals("")) {
					POSMessageDialog.showMessage("Please enter item number or barcode ");
					return;
				}

				if (!addMenuItemByBarcode(txtSearchItem.getText())) {
					addMenuItemBySortOrder(txtSearchItem.getText());
//					addMenuItemByItemId(txtSearchItem.getText());
				}
				txtSearchItem.setText("");
			}
		});

		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ItemSearchDialog dialog = new ItemSearchDialog(Application.getPosWindow());
				dialog.setTitle("Search item");
				dialog.pack();
				dialog.open();
				if (dialog.isCanceled()) {
					return;
				}

				txtSearchItem.requestFocus();

				if (!addMenuItemByBarcode(dialog.getValue())) {
					if (!addMenuItemBySortOrder(dialog.getValue())) {
						POSMessageDialog.showError(Application.getPosWindow(), "Item not found");
					}
//					if (!addMenuItemByItemId(dialog.getValue())) {
//						POSMessageDialog.showError(Application.getPosWindow(), "Item not found");
//					}
				}
			}
		});
		itemSearchPanel.add(txtSearchItem, "split 2, grow,span");
		itemSearchPanel.add(btnSearch, "grow, span, width " + PosUIManager.getSize(60) + "!, height " + PosUIManager.getSize(40) + "!");
	}

	private static boolean isParsable(String input) {
		boolean parsable = true;
		try {
			Integer.parseInt(input);
		} catch (NumberFormatException e) {
			parsable = false;
		}
		return parsable;
	}

	private boolean addMenuItemByItemId(String id) {

		if (!isParsable(id)) {
			return false;
		}

		Integer itemId = Integer.parseInt(id);

		MenuItem menuItem = MenuItemDAO.getInstance().get(itemId);

		if (menuItem == null) {
			return false;
		}

		if (!filterByOrderType(menuItem)) {
			return false;
		}

		if (!filterByStockAmount(menuItem)) {
			return false;
		}

		OrderView.getInstance().getOrderController().itemSelected(menuItem);
		return true;
	}

	private boolean addMenuItemByBarcode(String barcode) {

		MenuItemDAO dao = new MenuItemDAO();

		MenuItem menuItem = dao.getMenuItemByBarcode(barcode);

		if (menuItem == null) {
			return false;
		}

		if (!filterByOrderType(menuItem)) {
			return false;
		}

		if (!filterByStockAmount(menuItem)) {
			return false;
		}

		OrderView.getInstance().getOrderController().itemSelected(menuItem);
		return true;
	}

	private boolean addMenuItemBySortOrder(String sortOrder) {

		if (!isParsable(sortOrder)) {
			return false;
		}

		Integer itemSortOrder = Integer.parseInt(sortOrder);
		MenuItemDAO dao = new MenuItemDAO();

		MenuItem menuItem = dao.getMenuItemBySortOrder(itemSortOrder);

		if (menuItem == null) {
			return false;
		}

		if (!filterByOrderType(menuItem)) {
			return false;
		}

		if (!filterByStockAmount(menuItem)) {
			return false;
		}

		OrderView.getInstance().getOrderController().itemSelected(menuItem);
		return true;
	}

	private boolean filterByOrderType(MenuItem menuItem) {

		List<OrderType> orderTypeList = menuItem.getOrderTypeList();

		if (orderTypeList == null || orderTypeList.size() == 0) {
			return true;
		}

		if (orderTypeList.contains(ticket.getOrderType())) {
			return true;
		}
		return false;
	}

	private boolean filterByStockAmount(MenuItem menuItem) {
		if (menuItem.isDisableWhenStockAmountIsZero() && menuItem.getStockAmount() <= 0) {
			POSMessageDialog.showError("Items are not available in stock");
			return false;
		}
		return true;
	}

	private void createPayButton() {
		btnTotal = new PosButton(POSConstants.TOTAL.toUpperCase());
		btnTotal.setFont(btnTotal.getFont().deriveFont(Font.BOLD));

		if (!Application.getInstance().getTerminal().isHasCashDrawer()) {
			btnTotal.setEnabled(false);
		}

		btnTotal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (ticket.getOrderType().isHasForHereAndToGo()) {
					OrderTypeSelectionDialog2 dialog = new OrderTypeSelectionDialog2(ticket);
					dialog.open();

					if (dialog.isCanceled()) {
						return;
					}
					String orderType = dialog.getSelectedOrderType();
					if (orderType != null) {
						ticket.updateTicketItemPriceByOrderType(orderType);
						updateModel();
						updateView();
					}
				}
				doPayNow();
			}
		});

		add(btnTotal, BorderLayout.SOUTH);
	}

	private void createTicketItemControlPanel() {
		GridLayout gridLayout = new GridLayout(0, 1, 1, 3);
		ticketItemActionPanel.setLayout(gridLayout);
		//		ticketItemActionPanel.setLayout(new MigLayout("fill, wrap 1, ins 0", "[fill, grow 100, shrink 100]", ""));
		Dimension size = PosUIManager.getSize(40, 40);
		btnScrollUp.setIcon(IconFactory.getIcon("/ui_icons/", "up.png", size)); //$NON-NLS-1$ //$NON-NLS-2$
		btnScrollUp.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				doScrollUp();
			}
		});

		btnIncreaseAmount.setIcon(IconFactory.getIcon("/ui_icons/", "add_user.png", size)); //$NON-NLS-1$ //$NON-NLS-2$
		btnIncreaseAmount.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {

				ITicketItem selectedTicketItem = ticketViewerTable.getSelected();

				if (selectedTicketItem == null) {
					return;
				}

				if (isFractionalUnit()) {
					doIncreaseFractionalUnit();
				}
				else {
					doIncreaseAmount();
				}
			}
		});

		btnDecreaseAmount.setIcon(IconFactory.getIcon("/ui_icons/", "minus.png", size)); //$NON-NLS-1$ //$NON-NLS-2$
		btnDecreaseAmount.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				doDecreaseAmount();
			}
		});

		btnScrollDown.setIcon(IconFactory.getIcon("/ui_icons/", "down.png", size)); //$NON-NLS-1$ //$NON-NLS-2$
		btnScrollDown.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				doScrollDown();
			}
		});

		btnDelete.setIcon(IconFactory.getIcon("/ui_icons/", "delete.png", size)); //$NON-NLS-1$ //$NON-NLS-2$
		btnDelete.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				doDeleteSelection();
			}
		});

		btnEdit.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				doEditSelection();
			}
		});

		ticketItemActionPanel.add(btnScrollUp);
		ticketItemActionPanel.add(btnIncreaseAmount);
		ticketItemActionPanel.add(btnDecreaseAmount);
		ticketItemActionPanel.add(btnDelete);
		ticketItemActionPanel.add(btnEdit);
		ticketItemActionPanel.add(btnScrollDown);

		ticketItemActionPanel.setPreferredSize(PosUIManager.getSize(60, 270));
	}

	public synchronized void doFinishOrder() {// GEN-FIRST:event_doFinishOrder
		sendTicketToKitchen();
		closeView(false);
	}// GEN-LAST:event_doFinishOrder

	public synchronized void sendTicketToKitchen() {// GEN-FIRST:event_doFinishOrder
		saveTicketIfNeeded();
		if (ticket.getOrderType().isShouldPrintToKitchen()) {
			if (ticket.needsKitchenPrint()) {
				ReceiptPrintService.printToKitchen(ticket);
				TicketDAO.getInstance().refresh(ticket);
				setCancelable(false);
				setAllowToLogOut(false);
			}
		}
		OrderController.saveOrder(ticket);
	}

	public synchronized void doHoldOrder() {// GEN-FIRST:event_doFinishOrder
		updateModel();

		TicketDAO ticketDAO = TicketDAO.getInstance();
		OrderController.saveOrder(ticket);
		ticketDAO.refresh(ticket);

		closeView(false);
	}// GEN-LAST:event_doFinishOrder

	public void saveTicketIfNeeded() {
		updateModel();

		TicketDAO ticketDAO = TicketDAO.getInstance();

		//if (ticket.getId() == null) {
		// save ticket first. ticket needs to save so that it
		// contains an id.
		OrderController.saveOrder(ticket);
		ticketDAO.refresh(ticket);
		//}
	}

	private void closeView(boolean orderCanceled) {
		ticketViewerTable.setRowHeight(PosUIManager.getSize(60));
		if (TerminalConfig.isCashierMode()) {
			RootView.getInstance().showView(CashierSwitchBoardView.VIEW_NAME);
		}
		else {
			RootView.getInstance().showDefaultView();
		}
	}

	public void doCancelOrder() {// GEN-FIRST:event_doCancelOrder
		closeView(true);
	}// GEN-LAST:event_doCancelOrder

	private synchronized void updateModel() {
		if (!ticket.isBarTab() && (ticket.getTicketItems() == null || ticket.getTicketItems().size() == 0)) {
			throw new PosException(com.floreantpos.POSConstants.TICKET_IS_EMPTY_);
		}

		ticket.calculatePrice();
	}

	private void doPayNow() {// GEN-FIRST:event_doPayNow
		try {
			if (!POSUtil.checkDrawerAssignment()) {
				return;
			}

			updateModel();

			OrderController.saveOrder(ticket);

			firePayOrderSelected();
		} catch (PosException e) {
			POSMessageDialog.showError(e.getMessage());
		}
	}// GEN-LAST:event_doPayNow

	private void doDeleteSelection() {// GEN-FIRST:event_doDeleteSelection
		ticketViewerTable.deleteSelectedItem();
		updateView();

	}// GEN-LAST:event_doDeleteSelection

	private void doEditSelection() {// GEN-FIRST:event_doDeleteSelection
		Object object = ticketViewerTable.getSelected();

		if (object == null) {
			return;
		}
		if (object instanceof TicketItem && ((TicketItem) object).isTreatAsSeat()) {
			TicketItem ticketItem = (TicketItem) object;

			SeatSelectionDialog seatDialog = new SeatSelectionDialog(ticket.getTableNumbers(), getSeatNumbers());
			seatDialog.setTitle("Select Seat");
			seatDialog.pack();
			seatDialog.open();

			if (seatDialog.isCanceled()) {
				return;
			}
			int seatNumber = seatDialog.getSeatNumber();
			if (seatNumber == -1) {
				NumberSelectionDialog2 dialog = new NumberSelectionDialog2();
				dialog.setTitle("Enter seat number");
				dialog.setValue(ticketItem.getSeatNumber());
				dialog.pack();
				dialog.open();

				if (dialog.isCanceled()) {
					return;
				}
				seatNumber = (int) dialog.getValue();
			}

			ticketItem.setName("Seat** " + seatNumber);
                        ticketItem.setNameToPrinting("Seat** " + seatNumber);
			ticketItem.setSeatNumber(seatNumber);
			updateTicketItemsSeatNumber(ticketItem);
		}
		else
			OrderController.openModifierDialog((ITicketItem) object);

		updateView();

	}// GEN-LAST:event_doDeleteSelection

	protected List<Integer> getSeatNumbers() {
		List<Integer> seatNumbers = new ArrayList<>();

		for (TicketItem ticketItem : ticket.getTicketItems()) {
			if (ticketItem.isTreatAsSeat() && !seatNumbers.contains(ticketItem.getSeatNumber())) {
				seatNumbers.add(ticketItem.getSeatNumber());
			}
		}
		return seatNumbers;
	}

	private void updateTicketItemsSeatNumber(TicketItem ticketItem) {
		boolean updateSeatNumber = false;
		for (TicketItem item : ticket.getTicketItems()) {
			if (item == ticketItem) {
				updateSeatNumber = true;
				continue;
			}
			if (updateSeatNumber) {
				if (!item.isTreatAsSeat())
					item.setSeatNumber(ticketItem.getSeatNumber());
				else
					break;
			}
		}
	}

	private void doIncreaseAmount() {// GEN-FIRST:event_doIncreaseAmount
		if (!checkStock(-1)) {
			POSMessageDialog.showError("Items are not available in stock");
			return;
		}

		if (ticketViewerTable.increaseItemAmount()) {
			updateView();
		}

	}// GEN-LAST:event_doIncreaseAmount

	private void doIncreaseFractionalUnit() {

		double selectedQuantity = getNewItemQuantity();

		if (selectedQuantity == -1) {
			return;
		}

		if (!checkStock(selectedQuantity)) {
			POSMessageDialog.showError("Items are not available in stock");
			return;
		}

		if (ticketViewerTable.increaseFractionalUnit(selectedQuantity)) {
			updateView();
		}
	}

	private void doDecreaseAmount() {// GEN-FIRST:event_doDecreaseAmount
		if (ticketViewerTable.decreaseItemAmount()) {
			updateView();
		}
	}// GEN-LAST:event_doDecreaseAmount

	private void doScrollDown() {// GEN-FIRST:event_doScrollDown
		ticketViewerTable.scrollDown();
	}// GEN-LAST:event_doScrollDown

	private void doScrollUp() {// GEN-FIRST:event_doScrollUp
		ticketViewerTable.scrollUp();
	}// GEN-LAST:event_doScrollUp

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket _ticket) {
		this.ticket = _ticket;

		ticketViewerTable.setTicket(_ticket);
		updateView();
		setCancelable(true);
		setAllowToLogOut(true);
	}

	public void addTicketItem(TicketItem ticketItem) {
		ticketViewerTable.addTicketItem(ticketItem);
		updateView();
	}

	public void removeModifier(TicketItem parent, TicketItemModifier modifier) {
		modifier.setItemCount(0);
		//modifier.setModifierType(TicketItemModifier.MODIFIER_NOT_INITIALIZED);
		ticketViewerTable.removeModifier(parent, modifier);
	}

	public void selectRow(int rowIndex) {
		ticketViewerTable.selectRow(rowIndex);
	}

	public void updateView() {
		if (ticket == null) {
			btnTotal.setText(POSConstants.TOTAL.toUpperCase() + " " + CurrencyUtil.getCurrencySymbol() + "0.00");
			titledBorder.setTitle(ticket.getTicketType().toString() + "[New Ticket]"); //$NON-NLS-1$
			return;
		}
		ticket.calculatePrice();

		ITicketItem selectedItem = (ITicketItem) ticketViewerTable.getSelected();

		if (selectedItem != null) {
			if (TerminalConfig.isActiveCustomerDisplay()) {
				String sendMessageToCustomerDisplay = getDisplayMessage(selectedItem, ticket.getTotalAmount().toString());
				System.out.println("Send total value to customer display with text: " + sendMessageToCustomerDisplay);
                                DrawerUtil.setItemDisplay(TerminalConfig.getCustomerDisplayPort(), sendMessageToCustomerDisplay);
			}
		}

		btnTotal.setText(POSConstants.TOTAL.toUpperCase() + " " + CurrencyUtil.getCurrencySymbol() + NumberUtil.formatNumber(ticket.getTotalAmount()));

		/*if (ticket.getTotalAmount() > 0) {
			//btnTotal.setText("<html><h2>Total " + Application.getCurrencySymbol() + NumberUtil.formatNumber(ticket.getTotalAmount()) + "</h2></html>");
			btnTotal.setText("Total " +Application.getCurrencySymbol() + NumberUtil.formatNumber(ticket.getTotalAmount()));
		}
		else {
			//btnTotal.setText("<html><h2>Total</h2></html>");
			btnTotal.setText("<html><b>Total</b></html>");
		}*/

		if (ticket.getId() == null) {
			titledBorder.setTitle(ticket.getTicketType() + " [New Ticket]"); //$NON-NLS-1$
		}
		else {
			titledBorder.setTitle(ticket.getTicketType()
					+ " " + Messages.getString("TicketView.37") + ticket.getId() + " Table# " + getTableNumbers(ticket.getTableNumbers())); //$NON-NLS-1$ //$NON-NLS-2$

			/*	titledBorder.setTitle(ticket.getTicketType()
						+ " Ticket ["+ ticket.getId()+"]," + "Table [" + getTableNumbers(ticket.getTableNumbers())+"]"); //$NON-NLS-1$ //$NON-NLS-2$	
			*/}

		ticketViewerTable.updateView();
	}

	public void addOrderListener(OrderListener listenre) {
		orderListeners.add(listenre);
	}

	public void removeOrderListener(OrderListener listenre) {
		orderListeners.remove(listenre);
	}

	public void firePayOrderSelected() {
		for (OrderListener listener : orderListeners) {
			listener.payOrderSelected(getTicket());
		}
	}

	public void setControlsVisible(boolean visible) {
		if (visible) {
			btnIncreaseAmount.setEnabled(true);
			btnDecreaseAmount.setEnabled(true);
			btnDelete.setEnabled(true);
		}
		else {
			btnIncreaseAmount.setEnabled(false);
			btnDecreaseAmount.setEnabled(false);
			btnDelete.setEnabled(false);
		}
	}

	public com.floreantpos.ui.ticket.TicketViewerTable getTicketViewerTable() {
		return ticketViewerTable;
	}

	private class TicketItemSelectionListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			Object selected = ticketViewerTable.getSelected();
			if (!(selected instanceof ITicketItem)) {
				return;
			}

			ITicketItem iTicketItem = (ITicketItem) selected;
			if (iTicketItem.isPrintedToKitchen()) {
				btnIncreaseAmount.setEnabled(false);
				btnDecreaseAmount.setEnabled(false);
			}

			if (selected instanceof TicketItemModifier) {
				btnIncreaseAmount.setEnabled(false);
				btnDecreaseAmount.setEnabled(false);
				btnEdit.setEnabled(true);
				btnDelete.setEnabled(false);
			}
			else {
				btnIncreaseAmount.setEnabled(true);
				btnDecreaseAmount.setEnabled(true);
				btnDelete.setEnabled(true);
				btnEdit.setEnabled(false);

				if (selected instanceof TicketItem) {
					TicketItem ticketItem = (TicketItem) selected;
					if (ticketItem.isPrintedToKitchen()) {
						btnIncreaseAmount.setEnabled(false);
						btnDecreaseAmount.setEnabled(false);
						if (TerminalConfig.isAllowedToDeletePrintedTicketItem()) {
							btnDelete.setEnabled(true);
						}
						else {
							btnDelete.setEnabled(false);
						}
					}
					if (ticketItem.isTreatAsSeat()) {
						btnEdit.setEnabled(!ticketItem.isPrintedToKitchen());
					}
					else if (ticketItem.isHasModifiers()) {
						btnIncreaseAmount.setEnabled(false);
						btnDecreaseAmount.setEnabled(false);
						btnEdit.setEnabled(true);
					}
					else if (ticketItem.isFractionalUnit()) {
						btnIncreaseAmount.setEnabled(true);
						btnDecreaseAmount.setEnabled(false);
						btnDelete.setEnabled(true);
					}
				}
			}
		}
	}

	/**
	 * @return the txtSearchItem
	 */
	public JTextField getTxtSearchItem() {
		return txtSearchItem;
	}

	private String getDisplayMessage(ITicketItem item, String totalPrice) {

		int currentItemLenth = item.getNamePrinting().length();
		String ticketItems;
		if (currentItemLenth > 12) {
			ticketItems = item.getNamePrinting().substring(0, 12);
		}
		else {
			ticketItems = item.getNamePrinting();
		}
		int index = ticketItems.indexOf("\n");
		if (index > 0) ticketItems = ticketItems.substring(0, index);
                
		String quantity = item.getItemQuantityDisplay();
		double itemPrice = item.getUnitPriceDisplay();

		String line = String.format("%-1s %-12s %5s", quantity, ticketItems, itemPrice);
                System.out.println("line:\n" + line);

		String total = "TOTAL";
		String line2 = String.format("%-5s %14s", total, CurrencyUtil.getCurrencySymbol() + totalPrice);

		return line + line2;
//		return line + "\r" + line2;
	}

	/**
	 * @return the cancelable
	 */
	public boolean isCancelable() {
		return cancelable;
	}

	/**
	 * @param cancelable the cancelable to set
	 */
	public void setCancelable(boolean cancelable) {
		this.cancelable = cancelable;
	}

	/**
	 * @return the allowToLogOut
	 */
	public boolean isAllowToLogOut() {
		return allowToLogOut;
	}

	/**
	 * @param allowToLogOut the allowToLogOut to set
	 */
	public void setAllowToLogOut(boolean allowToLogOut) {
		this.allowToLogOut = allowToLogOut;
	}

	private String getTableNumbers(List<Integer> numbers) {

		String tableNumbers = "";
		if (numbers != null && !numbers.isEmpty()) {
			for (Iterator iterator = numbers.iterator(); iterator.hasNext();) {
				Integer n = (Integer) iterator.next();
				tableNumbers += n;

				if (iterator.hasNext()) {
					tableNumbers += ", ";
				}
			}
			return tableNumbers;
		}
		return tableNumbers;
	}

	private double getNewItemQuantity() {
		ITicketItem selectedTicketItem = ticketViewerTable.getSelected();
		double selectedQuantity = 0;
		if (TerminalConfig.getScaleActivationValue().equals("cas10")) {
			selectedQuantity = AutomatedWeightInputDialog.takeDoubleInput(selectedTicketItem.getNameDisplay(), 1);
		}
		else {
			selectedQuantity = BasicWeightInputDialog.takeDoubleInput("Please enter item weight or quantity.", 1);
		}
		if (selectedQuantity <= -1) {
			return -1;
		}

		if (selectedQuantity == 0) {
			POSMessageDialog.showError("Unit can not be zero");
			return -1;
		}
		return selectedQuantity;
	}

	private boolean isFractionalUnit() {
		Object object = ticketViewerTable.getSelected();

		if (object instanceof TicketItem) {
			TicketItem ticketItem = (TicketItem) object;
			return ticketItem.isFractionalUnit();
		}
		return false;
	}

	private boolean checkStock(double selectedItemQuantity) {

		TicketItem selectedTicketItem = (TicketItem) ticketViewerTable.getSelected();

		MenuItemDAO dao = new MenuItemDAO();
		MenuItem menuItem = dao.get(selectedTicketItem.getItemId());

		return isStockAvailable(menuItem, selectedTicketItem, selectedItemQuantity);
	}

	public boolean isStockAvailable(MenuItem menuItem, TicketItem selectedTicketItem, double selectedItemQuantity) {

		if (!menuItem.isDisableWhenStockAmountIsZero()) {
			return true;
		}

		List<TicketItem> ticketItems = ticketViewerTable.getTicketItems();

		if (menuItem.isFractionalUnit()) {// fractional unit start here

			if (ticketItems == null || ticketItems.isEmpty()) {
				if (menuItem.getStockAmount() < selectedTicketItem.getItemQuantity()) {
					return false;
				}
				return true;
			}

			double totalItemQuantity = 0;

			for (TicketItem tItem : ticketItems) {

				if (menuItem.getName().equals(tItem.getName())) {

					totalItemQuantity += tItem.getItemQuantity();

					if (menuItem.getStockAmount() < totalItemQuantity) {
						return false;
					}
				}
			}

			if (selectedItemQuantity != -1) {

				totalItemQuantity -= selectedTicketItem.getItemQuantity();

				totalItemQuantity += selectedItemQuantity;
			}
			else {
				totalItemQuantity += selectedTicketItem.getItemQuantity();
			}

			if (menuItem.getStockAmount() < totalItemQuantity) {
				return false;
			}
			return true;
		}//fractional Unit end here

		if (ticketItems == null || ticketItems.isEmpty()) {
			if (menuItem.getStockAmount() < selectedTicketItem.getItemCount()) {
				return false;
			}
			return true;
		}

		int totalItemCount = 0;

		for (TicketItem tItem : ticketItems) {

			if (tItem.getName().equals(menuItem.getName())) {

				totalItemCount += tItem.getItemCount();

				if (menuItem.getStockAmount() <= totalItemCount) {
					return false;
				}
			}
		}
		return true;
	}
}

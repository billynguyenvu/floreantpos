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
 * SplitTicketView.java
 *
 * Created on September 3, 2006, 11:11 PM
 */

package com.floreantpos.ui.views;


import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JSeparator;

import net.miginfocom.swing.MigLayout;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.floreantpos.Messages;
import com.floreantpos.POSConstants;
import com.floreantpos.main.Application;
import com.floreantpos.model.ActionHistory;
import com.floreantpos.model.ShopTable;
import com.floreantpos.model.Ticket;
import com.floreantpos.model.TicketItem;
import com.floreantpos.model.dao.ActionHistoryDAO;
import com.floreantpos.model.dao.ShopTableDAO;
import com.floreantpos.model.dao.TicketDAO;
import com.floreantpos.swing.PosButton;
import com.floreantpos.swing.TransparentPanel;
import com.floreantpos.ui.dialog.POSDialog;
import com.floreantpos.ui.dialog.POSMessageDialog;
import com.floreantpos.ui.views.order.TicketForSplitView;

/**
 *
 * @author  MShahriar
 */
public class SplitTicketDialog extends POSDialog {
	private Ticket ticket;

	private com.floreantpos.swing.POSToggleButton btnSplitBySeat;
	/** Creates new form SplitTicketView */
	public SplitTicketDialog() {
		super();

		initComponents();

		mainTicketView.setViewNumber(1);
		ticketView2.setViewNumber(2);
		ticketView3.setViewNumber(3);
		ticketView4.setViewNumber(4);

		mainTicketView.setTicketView1(ticketView2);
		mainTicketView.setTicketView2(ticketView3);
		mainTicketView.setTicketView3(ticketView4);

		ticketView2.setTicketView1(mainTicketView);
		ticketView2.setTicketView2(ticketView3);
		ticketView2.setTicketView3(ticketView4);

		ticketView3.setTicketView1(mainTicketView);
		ticketView3.setTicketView2(ticketView2);
		ticketView3.setTicketView3(ticketView4);

		ticketView4.setTicketView1(mainTicketView);
		ticketView4.setTicketView2(ticketView2);
		ticketView4.setTicketView3(ticketView3);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(true);
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {

        createTitlePanel();
        createActionButtonPanel();
        createToolbarPanel();
        createTicketViewPanel();
        
        centerPanel = new TransparentPanel(new java.awt.BorderLayout());
        centerPanel.add(toolbarPanel, java.awt.BorderLayout.NORTH);
        centerPanel.add(ticketPanel, java.awt.BorderLayout.CENTER);

        getContentPane().add(centerPanel, java.awt.BorderLayout.CENTER);

    }// </editor-fold>//GEN-END:initComponents

	private void createToolbarPanel() {
		toolbarPanel = new TransparentPanel();
		
		ButtonGroup buttonGroup = new javax.swing.ButtonGroup();

		btnNumSplit2 = new com.floreantpos.swing.POSToggleButton();
		btnNumSplit3 = new com.floreantpos.swing.POSToggleButton();
		btnNumSplit4 = new com.floreantpos.swing.POSToggleButton();
		btnSplitBySeat = new com.floreantpos.swing.POSToggleButton();
		lblTicketId = new com.floreantpos.swing.POSTitleLabel();

		lblTicketId.setText(Messages.getString("SplitTicketDialog.0")); //$NON-NLS-1$
		toolbarPanel.add(lblTicketId);
		JSeparator separator = new JSeparator(JSeparator.VERTICAL);
		separator.setPreferredSize(new Dimension(5, 20));
		toolbarPanel.add(separator);
        toolbarPanel.add(new JLabel(com.floreantpos.POSConstants.NUMBER_OF_SPLITS));

        buttonGroup.add(btnNumSplit2);
        btnNumSplit2.setSelected(true);
        btnNumSplit2.setText("2"); //$NON-NLS-1$
        btnNumSplit2.setPreferredSize(new java.awt.Dimension(60, 40));
        btnNumSplit2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNumSplit2ActionPerformed(evt);
            }
        });

        toolbarPanel.add(btnNumSplit2);

        buttonGroup.add(btnNumSplit3);
        btnNumSplit3.setText("3"); //$NON-NLS-1$
        btnNumSplit3.setPreferredSize(new java.awt.Dimension(60, 40));
        btnNumSplit3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNumSplit3ActionPerformed(evt);
            }
        });

        toolbarPanel.add(btnNumSplit3);

        buttonGroup.add(btnNumSplit4);
        btnNumSplit4.setText("4"); //$NON-NLS-1$
        btnNumSplit4.setPreferredSize(new java.awt.Dimension(60, 40));
        btnNumSplit4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNumSplit4ActionPerformed(evt);
            }
        });

        toolbarPanel.add(btnNumSplit4);
        
        buttonGroup.add(btnSplitBySeat);
		btnSplitBySeat.setText("Split by seat number"); //$NON-NLS-1$
		btnSplitBySeat.setPreferredSize(new java.awt.Dimension(120, 40));
		btnSplitBySeat.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				doSplitBySeatNumber(evt);
			}
		});

		toolbarPanel.add(btnSplitBySeat);
	}

	private void createTicketViewPanel() {
		ticketPanel = new TransparentPanel(new MigLayout("fill, hidemode 3")); //$NON-NLS-1$
        
		mainTicketView = new TicketForSplitView(this);
        ticketView2 = new TicketForSplitView(this);
        ticketView3 = new TicketForSplitView(this);
        ticketView4 = new TicketForSplitView(this);
        
        ticketView3.setVisible(false);
		ticketView4.setVisible(false);

        ticketPanel.add(mainTicketView, "grow"); //$NON-NLS-1$
        ticketPanel.add(ticketView2, "grow"); //$NON-NLS-1$
        ticketPanel.add(ticketView3, "grow"); //$NON-NLS-1$
        ticketPanel.add(ticketView4, "grow"); //$NON-NLS-1$
        
	}

	private void createActionButtonPanel() {
		actionButtonPanel = new TransparentPanel();
        
		btnFinish = new PosButton();
        btnFinish.setText(com.floreantpos.POSConstants.FINISH);
        btnFinish.setPreferredSize(new java.awt.Dimension(140, 50));
        btnFinish.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinishActionPerformed(evt);
            }
        });

        actionButtonPanel.add(btnFinish);

        btnCancel = new PosButton();
        btnCancel.setText(com.floreantpos.POSConstants.CANCEL);
        btnCancel.setPreferredSize(new java.awt.Dimension(140, 50));
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        actionButtonPanel.add(btnCancel);

        getContentPane().add(actionButtonPanel, java.awt.BorderLayout.SOUTH);
	}

	private void createTitlePanel() {
		titlePanel = new com.floreantpos.ui.TitlePanel();
        titlePanel.setTitle(com.floreantpos.POSConstants.SPLIT_TICKET);
        getContentPane().add(titlePanel, java.awt.BorderLayout.NORTH);
	}

	private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
		setTicket(null);
		dispose();
	}//GEN-LAST:event_btnCancelActionPerformed

	private synchronized void btnFinishActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinishActionPerformed
		doFinishSplit();
	}//GEN-LAST:event_btnFinishActionPerformed

        public synchronized void doFinishSplit() {
            Session session = null;
		Transaction tx = null;

		try {
			TicketDAO dao = new TicketDAO();
			session = dao.createNewSession();
			tx = session.beginTransaction();

			saveTicket(mainTicketView, session);
			saveTicket(ticketView2, session);
			saveTicket(ticketView3, session);
			saveTicket(ticketView4, session);

			tx.commit();
			
			//save the action
			ActionHistoryDAO.getInstance().saveHistory(Application.getCurrentUser(), ActionHistory.SPLIT_CHECK, com.floreantpos.POSConstants.RECEIPT_REPORT_TICKET_NO_LABEL + ":"+mainTicketView.getTicket().getId()); //$NON-NLS-1$
			
			dispose();
		} catch (Exception e) {
			try {
				tx.rollback();
			} catch (Exception x) {
			}
			POSMessageDialog.showError(Application.getPosWindow(), POSConstants.ERROR_MESSAGE, e);
		} finally {
			try {
				session.close();
			} catch (Exception x) {
			}
		}
        }
	private void doSplitBySeatNumber(java.awt.event.ActionEvent evt) {
		List<TicketItem> ticketItems = ticket.getTicketItems();
		Map<Integer, List<TicketItem>> itemMap = new HashMap<Integer, List<TicketItem>>();
		for (TicketItem ticketItem : ticketItems) {
			List<TicketItem> ticketItemList = itemMap.get(ticketItem.getSeatNumber());
			if (ticketItemList == null) {
				ticketItemList = new ArrayList<>();
				ticketItemList.add(ticketItem);
				itemMap.put(ticketItem.getSeatNumber(), ticketItemList);
			}
			else {
				ticketItemList.add(ticketItem);
			}
		}

		int splitViewNumber = 1;

		for (Integer seatNumber : itemMap.keySet()) {
			List<TicketItem> splitTicketItems = itemMap.get(seatNumber);
			transferTicketItemsToSplitView(splitTicketItems, splitViewNumber);
			splitViewNumber++;
		}
	}

	private void transferTicketItemsToSplitView(List<TicketItem> splitTicketItems, int splitViewNumber) {
		if (splitViewNumber == 2) {
			for (TicketItem ticketItem : splitTicketItems) {
				mainTicketView.transferTicketItem(ticketItem, ticketView2, true);
			}
		}
		else if (splitViewNumber == 3) {
			ticketView3.setVisible(true);
			for (TicketItem ticketItem : splitTicketItems) {
				mainTicketView.transferTicketItem(ticketItem, ticketView3, true);
			}
		}
		else if (splitViewNumber == 4) {
			ticketView4.setVisible(true);
			for (TicketItem ticketItem : splitTicketItems) {
				mainTicketView.transferTicketItem(ticketItem, ticketView4, true);
			}
		}
	}

	private void btnNumSplit4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNumSplit4ActionPerformed
		//if(ticketView3.remove)
		ticketView3.setVisible(true);
		ticketView4.setVisible(true);
	}//GEN-LAST:event_btnNumSplit4ActionPerformed

	private void btnNumSplit3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNumSplit3ActionPerformed
		ticketView3.setVisible(true);

		if (ticketView4.isVisible()) {
			Ticket ticket4 = ticketView4.getTicket();

			List<TicketItem> ticketItems = ticket4.getTicketItems();
			for (TicketItem item : ticketItems) {
				ticketView4.transferAllTicketItem(item, mainTicketView);
			}
			ticketView4.setVisible(false);
		}
	}//GEN-LAST:event_btnNumSplit3ActionPerformed

	private void btnNumSplit2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNumSplit2ActionPerformed
		if (ticketView3.isVisible()) {
			Ticket ticket3 = ticketView3.getTicket();

			List<TicketItem> ticketItems = new ArrayList<TicketItem>(ticket3.getTicketItems());
			for (TicketItem item : ticketItems) {
				ticketView3.transferAllTicketItem(item, mainTicketView);
			}
			ticketView3.setVisible(false);
		}

		if (ticketView4.isVisible()) {
			Ticket ticket4 = ticketView4.getTicket();

			List<TicketItem> ticketItems = ticket4.getTicketItems();
			for (TicketItem item : ticketItems) {
				ticketView4.transferAllTicketItem(item, mainTicketView);
			}
			ticketView4.setVisible(false);
		}
	}//GEN-LAST:event_btnNumSplit2ActionPerformed

	public void saveTicket(TicketForSplitView view, Session session) {
		if (!view.isVisible())
			return;

		view.getTicket().setOrderType(mainTicketView.getTicket().getOrderType());
		view.updateModel();

		Ticket ticket = view.getTicket();
		if (ticket.getTicketItems().size() <= 0)
			return;
		
		List<ShopTable> tables = ShopTableDAO.getInstance().getTables(mainTicketView.getTicket());
		if(tables != null) {
			for (ShopTable shopTable : tables) {
				ticket.addTable(shopTable.getTableNumber());
			}
		}
		
		TicketDAO.getInstance().saveOrUpdate(ticket, session);
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private PosButton btnCancel;
    private PosButton btnFinish;
    private com.floreantpos.swing.POSToggleButton btnNumSplit2;
    private com.floreantpos.swing.POSToggleButton btnNumSplit3;
    private com.floreantpos.swing.POSToggleButton btnNumSplit4;
    private com.floreantpos.swing.POSTitleLabel lblTicketId;
    private com.floreantpos.ui.views.order.TicketForSplitView mainTicketView;
    private com.floreantpos.ui.views.order.TicketForSplitView ticketView2;
    private com.floreantpos.ui.views.order.TicketForSplitView ticketView3;
    private com.floreantpos.ui.views.order.TicketForSplitView ticketView4;
    private com.floreantpos.ui.TitlePanel titlePanel;
    private TransparentPanel actionButtonPanel;
    private TransparentPanel centerPanel;
    private TransparentPanel toolbarPanel;
    private TransparentPanel ticketPanel;
    // End of variables declaration//GEN-END:variables

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
		if (ticket != null) {
			lblTicketId.setText(com.floreantpos.POSConstants.ORIGINAL_TICKET_ID + ": " + ticket.getId()); //$NON-NLS-1$
			mainTicketView.setTicket(ticket);
			btnSplitBySeat.setVisible(ticket.getOrderType().isAllowSeatBasedOrder());
		}
	}
}

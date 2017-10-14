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
package com.floreantpos.bo.ui.explorer;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import net.miginfocom.swing.MigLayout;

import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.JXTable;

import com.floreantpos.Messages;
import com.floreantpos.POSConstants;
import com.floreantpos.bo.ui.BOMessageDialog;
import com.floreantpos.model.PaymentType;
import com.floreantpos.model.PosTransaction;
import com.floreantpos.model.Ticket;
import com.floreantpos.model.TransactionType;
import com.floreantpos.model.User;
import com.floreantpos.model.dao.TicketDAO;
import com.floreantpos.model.dao.UserDAO;
import com.floreantpos.swing.ListTableModel;
import com.floreantpos.swing.TransparentPanel;
import com.floreantpos.ui.PosTableRenderer;
import com.floreantpos.ui.dialog.ComboItemSelectionDialog;
import com.floreantpos.ui.dialog.POSMessageDialog;
import com.floreantpos.ui.util.UiUtil;
import java.util.ArrayList;
import java.util.Arrays;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ChangePaymentMethod extends TransparentPanel {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("MMM,dd  hh:mm a"); //$NON-NLS-1$
    private JXDatePicker fromDatePicker = UiUtil.getCurrentMonthStart();
    private JXDatePicker toDatePicker = UiUtil.getCurrentMonthEnd();
    private JButton btnGo = new JButton(com.floreantpos.POSConstants.GO);
    private JButton btnChangePayment = new JButton("Change Payment Method"); //$NON-NLS-1$
    private JXTable table;
    private JComboBox cbUserType;

    public ChangePaymentMethod() {
        super(new BorderLayout());
        add(new JScrollPane(table = new JXTable(new TicketTableModel(new ArrayList<Ticket>()))));
        table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setDefaultRenderer(Object.class, new PosTableRenderer());
        JPanel topPanel = new JPanel(new MigLayout());

        cbUserType = new JComboBox();

        UserDAO dao = new UserDAO();
        List<User> userTypes = dao.findAll();

        Vector list = new Vector();
        list.add(POSConstants.ALL);
        list.addAll(userTypes);

        cbUserType.setModel(new DefaultComboBoxModel(list));

        topPanel.add(new JLabel(com.floreantpos.POSConstants.START_DATE), "grow"); //$NON-NLS-1$
        topPanel.add(fromDatePicker); //$NON-NLS-1$
        topPanel.add(new JLabel(com.floreantpos.POSConstants.END_DATE), "grow"); //$NON-NLS-1$
        topPanel.add(toDatePicker); //$NON-NLS-1$
        topPanel.add(new JLabel(POSConstants.USER + ":")); //$NON-NLS-1$
        topPanel.add(cbUserType);
        topPanel.add(btnGo, "skip 1, al right"); //$NON-NLS-1$
        add(topPanel, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(btnChangePayment);
        //bottomPanel.add(btnPrint);
        add(bottomPanel, BorderLayout.SOUTH);

        /*btnPrint.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow < 0) {
					BOMessageDialog.showError(AttendanceHistoryExplorer.this, "Please select a row to print");
					return;
				}
				TicketTableModel model = (TicketTableModel) table.getModel();
				Ticket report = (Ticket) model.getRowData(selectedRow);

				//PosPrintService.printDrawerPullReport(report, report.getTerminal());
			}
		});*/
        btnGo.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    viewReport();
                    //resizeColumnWidth(table);
                } catch (Exception e1) {
                    BOMessageDialog.showError(ChangePaymentMethod.this, POSConstants.ERROR_MESSAGE, e1);
                }
            }

        });

        btnChangePayment.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow < 0) {
                    BOMessageDialog.showError(ChangePaymentMethod.this, Messages.getString("AttendanceHistoryExplorer.4")); //$NON-NLS-1$
                    return;
                }
                TicketTableModel model = (TicketTableModel) table.getModel();
                Ticket ticket = (Ticket) model.getRowData(selectedRow);
                PosTransaction tx = ticket.getTransactions().iterator().next();
                
                ComboItemSelectionDialog dialog = new ComboItemSelectionDialog("SELECT PAYMENT METHOD", "Payment Method", PaymentType.getPaymentTypes(), false);
                dialog.setSelectedItem(PaymentType.getPaymentType(tx.getPaymentType()));
                dialog.setVisibleNewButton(false);
                dialog.pack();
                dialog.open();

                if (dialog.isCanceled()) {
                    return;
                }
                
                PaymentType paymentType = (PaymentType)dialog.getSelectedItem();
                tx.setPaymentType(paymentType.toString());
                String paymentTransactionType = "CASH";
                if (paymentType.equals(PaymentType.CASH)) {
                    tx.setTransactionType(TransactionType.CREDIT.name());
                    tx.setCardType(null);
                    paymentTransactionType = "CASH";
                }
                else {
                    if (paymentType.toString().startsWith("DEBIT")) {
                        tx.setTransactionType(TransactionType.DEBIT.name());
                        paymentTransactionType = "DEBIT_CARD";
                    } else {
                        tx.setTransactionType(TransactionType.CREDIT.name());
                        paymentTransactionType = "CREDIT_CARD";
                    }
                    tx.setCardType(dialog.getSelectedItem().toString());
                }

                TicketDAO dao = new TicketDAO();
                dao.saveOrUpdate(ticket);
                
                // Update payment type transaction
                Session s = dao.createNewSession();
                try {
                    Transaction txDB = s.beginTransaction();
                    SQLQuery q = s.createSQLQuery("UPDATE transactions SET PAYMENT_TYPE = :txType WHERE TICKET_ID = :ticketId");
                    q.setParameter("txType", paymentTransactionType);
                    q.setParameter("ticketId", ticket.getId());
                    q.executeUpdate();
                    txDB.commit();
                    s.close();
                    System.out.println("UPDATE PAYMENT_TYPE field success: " + paymentTransactionType);
                }
                catch (HibernateException ex) {
                    System.out.println("CANNOT UPDATE PAYMENT_TYPE field: " + ex.getMessage());
                }
                    
                viewReport();
                model.updateItem(selectedRow);
                System.out.println("UPDATE PAYMENT TYPE SUCCESS: " + ticket.getId());
            }
        });
        
        viewReport();
    }

    private void viewReport() {
        try {
            Date fromDate = fromDatePicker.getDate();
            Date toDate = toDatePicker.getDate();

            if (fromDate.after(toDate)) {
                POSMessageDialog.showError(com.floreantpos.util.POSUtil.getFocusedWindow(),
                        com.floreantpos.POSConstants.FROM_DATE_CANNOT_BE_GREATER_THAN_TO_DATE_);
                return;
            }

            Calendar calendar = Calendar.getInstance();
            calendar.clear();

            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(fromDate);

            calendar.set(Calendar.YEAR, calendar2.get(Calendar.YEAR));
            calendar.set(Calendar.MONTH, calendar2.get(Calendar.MONTH));
            calendar.set(Calendar.DATE, calendar2.get(Calendar.DATE));
            calendar.set(Calendar.HOUR, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            fromDate = calendar.getTime();

            calendar.clear();
            calendar2.setTime(toDate);
            calendar.set(Calendar.YEAR, calendar2.get(Calendar.YEAR));
            calendar.set(Calendar.MONTH, calendar2.get(Calendar.MONTH));
            calendar.set(Calendar.DATE, calendar2.get(Calendar.DATE));
            calendar.set(Calendar.HOUR, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            toDate = calendar.getTime();

            User user = null;
            if (!cbUserType.getSelectedItem().equals(POSConstants.ALL)) {
                user = (User) cbUserType.getSelectedItem();
            }

            TicketDAO dao = new TicketDAO();
            List<Ticket> ticketList = dao.findClosedTickets(fromDate, toDate, user);
            TicketTableModel model = (TicketTableModel) table.getModel();
            model.setRows(ticketList);
        } catch (Exception e) {
            BOMessageDialog.showError(this, POSConstants.ERROR_MESSAGE, e);
        }
    }

    class TicketTableModel extends ListTableModel {

        String[] columnNames = {POSConstants.TICKET_LIST_COLUMN_ID, "TABLES", POSConstants.TICKET_LIST_COLUMN_SERVER,
            POSConstants.TICKET_LIST_COLUMN_CREATE_DATE, POSConstants.TICKET_LIST_COLUMN_CUSTOMER,
            POSConstants.TICKET_LIST_COLUMN_PAYMENT_TYPE, POSConstants.TICKET_LIST_COLUMN_TOTAL};

        TicketTableModel(List<Ticket> list) {
            setRows(list);
            setColumnNames(columnNames);
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            Ticket ticket = (Ticket) rows.get(rowIndex);

            switch (columnIndex) {
                case 0:
                    return Integer.valueOf(ticket.getId());

                case 1:
                    return ticket.getTableNumbers();

                case 2:
                    User owner = ticket.getOwner();
                    return owner.getFirstName();

                case 3:
                    return ticket.getCreateDate();

                case 4:
                    String customerName = ticket.getProperty(Ticket.CUSTOMER_NAME);

                    if (customerName != null && !customerName.equals("")) { //$NON-NLS-1$
                        return customerName;
                    }

                    String customerMobile = ticket.getProperty(Ticket.CUSTOMER_MOBILE);

                    if (customerMobile != null) {
                        return customerMobile;
                    }

                    return Messages.getString("TicketListView.6"); //$NON-NLS-1$

                case 5:

                    return ticket.getTransactions().iterator().next().getPaymentType();

                case 6:

                    return ticket.getTotalAmount();
            }

            return null;
        }
    }
}

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
package com.floreantpos.main;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;

import net.miginfocom.swing.MigLayout;

import com.floreantpos.Messages;
import com.floreantpos.PosLog;
import com.floreantpos.model.Gratuity;
import com.floreantpos.model.KitchenTicket;
import com.floreantpos.model.KitchenTicketItem;
import com.floreantpos.model.PaymentType;
import com.floreantpos.model.PosTransaction;
import com.floreantpos.model.ShopTable;
import com.floreantpos.model.Ticket;
import com.floreantpos.model.TicketDiscount;
import com.floreantpos.model.TicketItem;
import com.floreantpos.model.TicketItemDiscount;
import com.floreantpos.model.TicketItemModifier;
import com.floreantpos.model.dao.GratuityDAO;
import com.floreantpos.model.dao.KitchenTicketDAO;
import com.floreantpos.model.dao.KitchenTicketItemDAO;
import com.floreantpos.model.dao.PosTransactionDAO;
import com.floreantpos.model.dao.ShopTableDAO;
import com.floreantpos.model.dao.TicketDAO;
import com.floreantpos.model.dao.TicketDiscountDAO;
import com.floreantpos.model.dao.TicketItemDAO;
import com.floreantpos.model.dao.TicketItemDiscountDAO;
import com.floreantpos.model.dao.TicketItemModifierDAO;
import com.floreantpos.swing.POSTextField;
import com.floreantpos.swing.PosButton;
import com.floreantpos.swing.PosUIManager;
import com.floreantpos.ui.dialog.POSMessageDialog;
import com.floreantpos.util.DatabaseUtil;
import com.jgoodies.looks.plastic.PlasticXPLookAndFeel;
import com.jgoodies.looks.plastic.theme.ExperienceBlue;
import com.jidesoft.swing.JideScrollPane;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import org.apache.commons.lang3.math.NumberUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class UpdateTicketsWindow extends JFrame implements ActionListener {

    private static final String SAVE = "DELETE"; //$NON-NLS-1$
    private static final String CANCEL = "cancel"; //$NON-NLS-1$
    private POSTextField tfPercentThreshold;
    private POSTextField tfAmountOrPercent;
    private JDatePickerImpl tfStartDate;
    private JDatePickerImpl tfEndDate;
    private PosButton btnExit;
    private PosButton btnSave;

    private JLabel lblPercentThreshold;
    private JLabel lblAmountOrPercent;
    private JLabel lblStartDate;
    private JLabel lblEndDate;
    private JLabel lblSymbol;
    private JCheckBox chkDeleteOnAmount; //$NON-NLS-1$
    private JLabel lblStatus;

    private boolean connectionSuccess;
    private boolean isDeleteOnAmount = false;

    public UpdateTicketsWindow() throws HeadlessException {
        setLookAndFeel();
        ImageIcon applicationIcon = new ImageIcon(getClass().getResource("/icons/icon.png")); //$NON-NLS-1$
        setIconImage(applicationIcon.getImage());
        initUI();
        addUIListeners();
    }

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
        if (b) {
            setupSizeAndLocation();
        }
    }

    private void setLookAndFeel() {
        try {
            PlasticXPLookAndFeel.setPlasticTheme(new ExperienceBlue());
            UIManager.setLookAndFeel(new PlasticXPLookAndFeel());
            initializeFont();
        } catch (Exception ignored) {
        }
    }

    public void setupSizeAndLocation() {
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(PosUIManager.getSize(450, 300));
    }

    protected void initUI() {
        getContentPane().setLayout(new BorderLayout()); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

        JPanel transactionsPanel = new JPanel(new MigLayout("fill,hidemode 3", "[170px][fill, grow]", "")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        transactionsPanel.setBorder(new TitledBorder(Messages.getString("UpdateTicketsWindow.3"))); //$NON-NLS-1$

        chkDeleteOnAmount = new JCheckBox("Update by amount"); //$NON-NLS-1$
        chkDeleteOnAmount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (chkDeleteOnAmount.isSelected()) {
                    isDeleteOnAmount = true;
                    lblAmountOrPercent.setText("Amount ($):");
                } else {
                    isDeleteOnAmount = false;
                    lblAmountOrPercent.setText("Percentage (%):");
                }
            }
        });
        transactionsPanel.add(chkDeleteOnAmount, "wrap");
        lblPercentThreshold = new JLabel("Threshold (%, ex: 10, 20, 100):");
        transactionsPanel.add(lblPercentThreshold);
        tfPercentThreshold = new POSTextField("10");
        tfPercentThreshold.setHorizontalAlignment(JTextField.RIGHT);
        transactionsPanel.add(tfPercentThreshold, "wrap");
        tfAmountOrPercent = new POSTextField("10");
        tfAmountOrPercent.setHorizontalAlignment(JTextField.RIGHT);
        lblAmountOrPercent = new JLabel("Percentage (%, ex: 10, 20, 100):"); //$NON-NLS-1$ //$NON-NLS-2$
        transactionsPanel.add(lblAmountOrPercent);
        transactionsPanel.add(tfAmountOrPercent, "wrap"); //$NON-NLS-1$

        UtilDateModel model1 = new UtilDateModel();
        model1.setValue(new Date());
        UtilDateModel model2 = new UtilDateModel();
        model2.setValue(new Date());
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl startDatePanel = new JDatePanelImpl(model1, p);
        JDatePanelImpl endDatePanel = new JDatePanelImpl(model2, p);
        tfStartDate = new JDatePickerImpl(startDatePanel, new DateComponentFormatter());
        tfEndDate = new JDatePickerImpl(endDatePanel, new DateComponentFormatter());
        lblStartDate = new JLabel("Start Date" + ":"); //$NON-NLS-1$ //$NON-NLS-2$
        transactionsPanel.add(lblStartDate);
        transactionsPanel.add(tfStartDate, "wrap");
        lblEndDate = new JLabel("End Date" + ":"); //$NON-NLS-1$ //$NON-NLS-2$
        transactionsPanel.add(lblEndDate);
        transactionsPanel.add(tfEndDate, "wrap");
        lblStatus = new JLabel("Please select data to delete ...");
        lblStatus.setForeground(Color.GREEN);
        transactionsPanel.add(lblStatus, "wrap"); //$NON-NLS-1$

        btnSave = new PosButton("DELETE"); //$NON-NLS-1$
        btnSave.setActionCommand(SAVE);
        btnExit = new PosButton(Messages.getString("DatabaseConfigurationDialog.28").toUpperCase()); //$NON-NLS-1$
        btnExit.setActionCommand(CANCEL);

        JPanel buttonPanel = new JPanel(new MigLayout("fillx,right")); //$NON-NLS-1$

        buttonPanel.add(btnSave, "h 40!,split 2,right"); //$NON-NLS-1$
        buttonPanel.add(btnExit, "h 40!"); //$NON-NLS-1$

        JPanel contentPanel = new JPanel(new MigLayout("fillx")); //$NON-NLS-1$
        contentPanel.add(transactionsPanel, "grow,wrap"); //$NON-NLS-1$
        getContentPane().add(new JideScrollPane(contentPanel), BorderLayout.CENTER); //$NON-NLS-1$
        getContentPane().add(buttonPanel, BorderLayout.SOUTH); //$NON-NLS-1$

        getContentPane().setBackground(transactionsPanel.getBackground());
    }

    public void setTitle(String title) {
        super.setTitle("Update transactions"); //$NON-NLS-1$
    }

    public static UpdateTicketsWindow open() {
        UpdateTicketsWindow window = new UpdateTicketsWindow();
        window.setTitle(Messages.getString("DatabaseConfigurationDialog.38")); //$NON-NLS-1$
        window.pack();
        window.setVisible(true);

        return window;
    }

    public static void main(String[] args) throws Exception {
        UpdateTicketsWindow.open();
    }

    private void initializeFont() {
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);

            if (value != null && value instanceof FontUIResource) {
                FontUIResource f = (FontUIResource) value;
                String fontName = f.getFontName();

                Font font = new Font(fontName, f.getStyle(), PosUIManager.getDefaultFontSize());
                UIManager.put(key, new FontUIResource(font));
            }
        }
    }

    private void addUIListeners() {
        btnSave.addActionListener(this);
        btnExit.addActionListener(this);
    }

    private void deleteTransactions(Date startDate, Date endDate, Float percentToDelete, Double amountToDelete, Double threshold) {
        try {
            DatabaseUtil.initialize();

            Double totalAmount = 0.0d;
            List<Ticket> deleteTickets = new ArrayList<Ticket>();
            Float realPercentToDelete = 0.00f;
            Double realAmountToDelete = 0.00d;

            // Get list tickets
            List<Ticket> tickets = new ArrayList<Ticket>();
            List<Ticket> dbTickets = TicketDAO.getInstance().findClosedTickets(startDate, endDate);

            // Calculate total amount of tickets
            lblStatus.setText("Calculating ticket to delete ... ");
            for (Ticket ticket : dbTickets) {
                if (ticket.getTransactions().isEmpty()) continue;
//                if (ticket.getTransactions().iterator().next().getTransactionTime())
                totalAmount += ticket.getTotalAmount();
                System.out.println("printed customer copy: " + ticket.getPrintedCustomerCopy());
                if ((ticket.getPrintedCustomerCopy() == null || !ticket.getPrintedCustomerCopy())
                        && ticket.getTransactions().size() == 1
                        && ticket.getTransactions().iterator().next().getPaymentType().equals(PaymentType.CASH.name())) {
                    tickets.add(ticket);
                }
            }

            // Sort tickets for later use
            tickets.sort(new Comparator<Ticket>() {
                public int compare(Ticket ticket1, Ticket ticket2) {
                    return ticket2.getTotalAmount().compareTo(ticket1.getTotalAmount());
                }
            });

            // Translate delete percent to delete amount
            if (amountToDelete == null) {
                amountToDelete = totalAmount * percentToDelete;
            }
            Double threshAmount = amountToDelete + totalAmount * threshold;
            System.out.println("threshAmount = " + threshAmount);

            // Get tickets need to be deleted
            MatchData finalData = getMatchTickets(amountToDelete, tickets, 0, threshAmount);
            for (int i = 1; i < tickets.size(); i++) {
                lblStatus.setText("Calculating ticket to delete. Round "+i+" ... ");
                MatchData matchData = getMatchTickets(amountToDelete, tickets, i, threshAmount);
                if (matchData.diff < finalData.diff || finalData.recentTickets.isEmpty()) finalData = matchData;
            }
            realAmountToDelete = finalData.realAmountToDelete;
            deleteTickets = finalData.recentTickets;
            realPercentToDelete = (float) Math.round(realAmountToDelete * 10000 / totalAmount) / 100;
            SimpleDateFormat df = new SimpleDateFormat("EEE dd");
            int yesNo = JOptionPane.showConfirmDialog(this, "Number of invoice to be updated: " + deleteTickets.size()
                    + "\nTotal amount will be corrected: $" + (double) Math.round(100 * realAmountToDelete) / 100
                    + "\nPercentage of transactions to be correct: " + realPercentToDelete + " %"
                    + "\nYour current transaction amount from " + df.format(startDate) + " to " + df.format(endDate) + " is: $" + (double) Math.round(100 * totalAmount) / 100
                    + "\n\nAre you sure to update these transactions?", "Update transactions", JOptionPane.YES_NO_OPTION);
            if (yesNo == JOptionPane.YES_OPTION) {
                // Delete all ticket's related data
                Session session = null;
                Transaction tx = null;

                try {
                    
                    session = TicketDAO.getInstance().createNewSession();
                    tx = session.beginTransaction();
                    for (Ticket ticket : deleteTickets) {
                        System.out.println("Deleting ticket " + ticket.getId());
                        lblStatus.setText("Deleting ticket " + ticket.getId());
                        // Delete kitchen data
                        List<KitchenTicket> kts = KitchenTicketDAO.getInstance().findByParentId(ticket.getId());
                        for (KitchenTicket kt : kts) {
                            lblStatus.setText("Deleting kitchen ticket ... ");
                        lblStatus.repaint();
                            for (KitchenTicketItem kti : kt.getTicketItems()) {
                                KitchenTicketItemDAO.getInstance().delete(kti, session);
                            }
                            KitchenTicketDAO.getInstance().delete(kt, session);
                        }

                        for (TicketDiscount td : ticket.getDiscounts()) {
                            lblStatus.setText("Deleting discount ticket ... ");
                            TicketDiscountDAO.getInstance().delete(td, session);
                        }
                        for (PosTransaction postx : ticket.getTransactions()) {
                            lblStatus.setText("Deleting transaction ... ");
                            PosTransactionDAO.getInstance().delete(postx, session);
                        }
                        // Delete ticket items
                        for (TicketItem ti : ticket.getTicketItems()) {
                            lblStatus.setText("Deleting ticket item ... ");
                            System.out.println("Deleting ticket item id: " + ti.getId());
                            
                            List<TicketItemModifier> tims = TicketItemModifierDAO.getInstance().findByTicket(ti);                            
                            if (tims.size() > 0) {
                                for (TicketItemModifier tim : tims) {
                                    boolean needDelete = true;
                                    if (ti.getSizeModifier() != null) {
                                        System.out.println("Ticket item size modifier id: " + ti.getSizeModifier().getId());
                                    }
                                    if (ti.getSizeModifier() != null && tim.getId().equals(ti.getSizeModifier().getId())) needDelete = false;
                                    else {
                                        for (TicketItemModifier tim2 : ti.getTicketItemModifiers())
                                            if (tim.getId().equals(tim2.getId())) {
                                                needDelete = false;
                                                break;
                                            }
                                        if (needDelete) {
                                            for (TicketItemModifier tim2 : ti.getAddOns())
                                                if (tim.getId().equals(tim2.getId())) {
                                                    needDelete = false;
                                                    break;
                                                }
                                        }
                                    }
                                    if (needDelete) {
                                        System.out.println("Deleting orphan ticket item modifier: " + tim.getId());
                                        TicketItemModifierDAO.getInstance().delete(tim, session);
                                    }
                                }
                            }
                            
                            if (ti.getSizeModifier() != null) {
                                System.out.println("Deleting ticket item size modifier id: " + ti.getSizeModifier().getId());
                                TicketItemModifier tim = ti.getSizeModifier();
                                TicketItemModifierDAO.getInstance().delete(tim, session);
                            }
                            
                            if (ti.getTicketItemModifiers().size() > 0) {
                                for (TicketItemModifier tim : ti.getTicketItemModifiers()) {
                                    System.out.println("Deleting ticket item modifier: " + tim.getId());
                                    TicketItemModifierDAO.getInstance().delete(tim, session);
                                }
                            }
                            
                            if (ti.getAddOns().size() > 0) {
                                for (TicketItemModifier tim : ti.getAddOns()) {
                                    System.out.println("Deleting ticket item addon: " + tim.getId());
                                    TicketItemModifierDAO.getInstance().delete(tim, session);
                                }
                            }
                            
                            for (TicketItemDiscount tid : ti.getDiscounts()) {
                                TicketItemDiscountDAO.getInstance().delete(tid, session);
                            }
                            TicketItemDAO.getInstance().delete(ti, session);
                        }
                        
                        // Set shop table un-server
                        List<ShopTable> shopTables = ShopTableDAO.getInstance().getByNumbers(ticket.getTableNumbers());
                        for (ShopTable shopTable: shopTables) {
                            shopTable.setServing(false);
                            ShopTableDAO.getInstance().update(shopTable);
                        }
                        
                        // Gratuity deletion
                        List<Gratuity> gratuities = GratuityDAO.getInstance().findByTicket(ticket);
                        if (gratuities != null) {
                            for (Gratuity gratuity: gratuities) {
                                if (ticket.getGratuity() == null || !ticket.getGratuity().getId().equals(gratuity.getId())) {
                                    System.out.println("Deleting orphan gratuity id: " + gratuity.getId());
                                    GratuityDAO.getInstance().delete(gratuity, session);
                                }
                            }
                        }

                        if (ticket.getGratuity() != null) {
                            System.out.println("Deleting gratuity id: " + ticket.getGratuity().getId());
                            GratuityDAO.getInstance().delete(ticket.getGratuity(), session);
                        }
                        
                        // Delete ticket
                        TicketDAO.getInstance().delete(ticket, session);
                    }
                    tx.commit();
                } catch (Exception e) {
                    if (tx != null) {
                        tx.rollback();
                    }

                    throw e;

                } finally {
                    if (session != null) {
                        session.close();
                    }
                }
                POSMessageDialog.showMessage("The transactions have been corected with total amount: $" + (double) Math.round(100 * realAmountToDelete) / 100 + " (based on " + realPercentToDelete + "% transactions).");
            }
            lblStatus.setText("Please select data to delete ...");
        } catch (Exception ex) {
            ex.printStackTrace();
            PosLog.error(getClass(), ex);
            POSMessageDialog.showMessage(this, ex.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }
    
    private class MatchData {
        List<Ticket> recentTickets = null;
        Double diff = null;
        Double realAmountToDelete = 0.00d;
    }
    
    private MatchData getMatchTickets(Double amountToDelete, List<Ticket> tickets, int index, Double threshAmount) {
        List<Ticket> deleteTickets = new ArrayList<>();
        Double realAmountToDelete = 0.00d;
        Double diff = amountToDelete;
        for (int i = index; i < tickets.size(); i++) {
            Ticket ticket = tickets.get(i);
            if (realAmountToDelete + ticket.getTotalAmount() > amountToDelete) {
                System.out.println("startDiff = " + diff);
                List<Ticket> recentTickets = new ArrayList<>();
                for (int j = i; j< tickets.size(); j++) {
                    MatchData matchData = getOtherTickets(amountToDelete, realAmountToDelete, tickets.get(j).getTotalAmount(), tickets, j, threshAmount);
                    System.out.println("index: " + j + ", newDiff = " + matchData.diff + ", size = " + matchData.recentTickets.size());
                    if (matchData.diff < diff && !matchData.recentTickets.isEmpty()) {
                        diff = matchData.diff;
                        recentTickets = matchData.recentTickets;
                        System.out.println("recentTickets size=" + recentTickets.size());
                    }
                }
                System.out.println("final recentTickets size=" + recentTickets.size());
                deleteTickets.addAll(recentTickets);
                for (Ticket t: recentTickets) realAmountToDelete += t.getTotalAmount();
                break;
            }
            else {
                diff = amountToDelete - realAmountToDelete - ticket.getTotalAmount();
            }
            deleteTickets.add(ticket);
            realAmountToDelete += ticket.getTotalAmount();
        }
        MatchData data = new MatchData();
        data.diff = diff;
        data.recentTickets = deleteTickets;
        data.realAmountToDelete = realAmountToDelete;
        
        return data;
    }
    
    private MatchData getOtherTickets(Double amountToDelete, Double totalAmountToDelete, Double currentAmount, List<Ticket> tickets, int index, Double threshAmount) {
        MatchData data = new MatchData();
        List<Ticket> recentTickets = new ArrayList<Ticket>();
        Double otherAmountToDelete = totalAmountToDelete;
        Double diff = Math.abs(totalAmountToDelete - amountToDelete);
        
        for (int i = tickets.size() - 1; i > index; i--) {
            Double newDiff = otherAmountToDelete + tickets.get(i).getTotalAmount() - amountToDelete;
            if (Math.abs(newDiff) < diff) {
                if (newDiff <= 0 && (otherAmountToDelete + tickets.get(i).getTotalAmount() < threshAmount)) {
                    otherAmountToDelete += tickets.get(i).getTotalAmount();
                    recentTickets.add(tickets.get(i));
                    diff = Math.abs(newDiff);
                }
            }
            if (newDiff > 0) break;
        }
        
            System.out.println("diff 1 = " + diff);
        if ((totalAmountToDelete + currentAmount < threshAmount) && (Math.abs(totalAmountToDelete + currentAmount - amountToDelete) < diff)) {
            diff = Math.abs(totalAmountToDelete + currentAmount - amountToDelete);
            recentTickets.clear();
            recentTickets.add(tickets.get(index));
            System.out.println("diff 2 = " + diff);
            System.out.println("recentTickets size = " + recentTickets.size() + ", data: " + recentTickets.get(0).getTotalAmount());
        }
        
        for (int i = tickets.size() - 1; i > index; i--) {
            Double newDiff = totalAmountToDelete + tickets.get(i).getTotalAmount() - amountToDelete;
            if (Math.abs(newDiff) < diff && (totalAmountToDelete + tickets.get(i).getTotalAmount() < threshAmount)) {
                recentTickets.clear();
                recentTickets.add(tickets.get(i));
                diff = Math.abs(newDiff);
            System.out.println("diff 3 = " + diff);
            }
            if (newDiff > 0) break;
        }
        
        data.recentTickets = recentTickets;
        data.diff = diff;
        return data;
    }

    private Date getDate(JDatePickerImpl date, Boolean isStart) {
        Calendar cal = Calendar.getInstance();
        if (isStart) {
            cal.set(date.getModel().getYear(), date.getModel().getMonth(), date.getModel().getDay(), 0, 0, 0);
        } else {
            cal.set(date.getModel().getYear(), date.getModel().getMonth(), date.getModel().getDay(), 23, 59, 59);
        }
        return cal.getTime();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String command = e.getActionCommand();
            if (CANCEL.equalsIgnoreCase(command)) {
                System.exit(1);
                return;
            }

            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            if (SAVE.equals(command)) {
                if (!NumberUtils.isNumber(tfAmountOrPercent.getText())) {
                    POSMessageDialog.showMessage(this, "Please correct input value to a number.");
                } else {
                    Double threshold = 0.00d;
                    if (!NumberUtils.isNumber(tfPercentThreshold.getText())) {
                        tfPercentThreshold.setText("10.0");
                    } else {
                        threshold = Double.parseDouble(tfPercentThreshold.getText());
                    }
                    Double value = Double.parseDouble(tfAmountOrPercent.getText());
                    if (!(value.equals(0.00d) || value.equals(0d))) {
                        if (chkDeleteOnAmount.isSelected()) {
                            deleteTransactions(getDate(tfStartDate, true), getDate(tfEndDate, false), null, value, threshold/100);
                        } else {
                            deleteTransactions(getDate(tfStartDate, true), getDate(tfEndDate, false), value.floatValue()/100, null, threshold/100);
                        }
                    } else {
                        POSMessageDialog.showMessage(this, "The input number must greater than zero.");
                    }
                }
            }
        } catch (Exception e2) {
            PosLog.error(getClass(), e2);
            POSMessageDialog.showMessage(this, e2.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }
}

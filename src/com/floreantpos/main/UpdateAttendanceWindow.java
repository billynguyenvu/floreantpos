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
import com.floreantpos.model.AttendenceHistory;
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
import com.floreantpos.model.User;
import com.floreantpos.model.dao.AttendenceHistoryDAO;
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
import com.floreantpos.model.dao.UserDAO;
import com.floreantpos.report.AttendanceReportData;
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
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import org.apache.commons.lang3.math.NumberUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class UpdateAttendanceWindow extends JFrame implements ActionListener {

    private static final String CALCULATE = "CALCULATE"; //$NON-NLS-1$
    private static final String SAVE = "DELETE"; //$NON-NLS-1$
    private static final String CANCEL = "cancel"; //$NON-NLS-1$
    private JDatePickerImpl tfStartDate;
    private JDatePickerImpl tfEndDate;
    private PosButton btnExit;
    private PosButton btnSave;
    private PosButton btnCalculate;

    private JLabel lblCurrentTotalWorkingTime;
    private JLabel lblCurrentTotalSalary;
    private POSTextField tfCurrentTotalWorkingTime;
    private POSTextField tfCurrentTotalSalary;

    private JLabel lblTotalWorkingTime;
    private JLabel lblTotalSalary;
    private POSTextField tfTotalWorkingTime;
    private POSTextField tfTotalSalary;

    private JLabel lblStartDate;
    private JLabel lblEndDate;
    private JLabel lblSymbol;
    private JCheckBox chkTimeOrSalary; //$NON-NLS-1$
    private JLabel lblStatus;

    private boolean connectionSuccess;
    private boolean isTimeOrSalary = false;
    private JComboBox cbUserType;

    public UpdateAttendanceWindow() throws HeadlessException {
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
        setSize(PosUIManager.getSize(500, 410));
    }

    protected void initUI() {
        DatabaseUtil.initialize();
        getContentPane().setLayout(new BorderLayout()); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

        JPanel transactionsPanel = new JPanel(new MigLayout("fill,hidemode 3", "[220px][fill, grow]", "")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        transactionsPanel.setBorder(new TitledBorder("Current Employee Attendance")); //$NON-NLS-1$

        JPanel transactionsPanel2 = new JPanel(new MigLayout("fill,hidemode 3", "[220px][fill, grow]", "")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        transactionsPanel2.setBorder(new TitledBorder("New Employee Attendance")); //$NON-NLS-1$

        JLabel lblUsers = new JLabel("Selected user:");
        transactionsPanel.add(lblUsers);
        cbUserType = new JComboBox();

        UserDAO dao = new UserDAO();
        List<User> userTypes = dao.findAll();

        Vector list = new Vector();
        list.addAll(userTypes);

        cbUserType.setModel(new DefaultComboBoxModel(list));
        cbUserType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateAttendance(getDate(tfStartDate, true), getDate(tfEndDate, false));
            }
        });
        transactionsPanel.add(cbUserType, "wrap");

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
        tfStartDate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateAttendance(getDate(tfStartDate, true), getDate(tfEndDate, false));
            }
        });
        tfEndDate = new JDatePickerImpl(endDatePanel, new DateComponentFormatter());
        tfEndDate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateAttendance(getDate(tfStartDate, true), getDate(tfEndDate, false));
            }
        });
        lblStartDate = new JLabel("From Date" + ":"); //$NON-NLS-1$ //$NON-NLS-2$
        transactionsPanel.add(lblStartDate);
        transactionsPanel.add(tfStartDate, "wrap");
        lblEndDate = new JLabel("To Date" + ":"); //$NON-NLS-1$ //$NON-NLS-2$
        transactionsPanel.add(lblEndDate);
        transactionsPanel.add(tfEndDate, "wrap");

        lblCurrentTotalWorkingTime = new JLabel("Current working time (hrs):");
        transactionsPanel.add(lblCurrentTotalWorkingTime);
        tfCurrentTotalWorkingTime = new POSTextField("0");
        tfCurrentTotalWorkingTime.setHorizontalAlignment(JTextField.RIGHT);
        tfCurrentTotalWorkingTime.setEnabled(false);
        transactionsPanel.add(tfCurrentTotalWorkingTime, "wrap");
        tfCurrentTotalSalary = new POSTextField("0");
        tfCurrentTotalSalary.setHorizontalAlignment(JTextField.RIGHT);
        tfCurrentTotalSalary.setEnabled(false);
        lblCurrentTotalSalary = new JLabel("Current salary ($):"); //$NON-NLS-1$ //$NON-NLS-2$
        transactionsPanel.add(lblCurrentTotalSalary);
        transactionsPanel.add(tfCurrentTotalSalary, "wrap"); //$NON-NLS-1$

        chkTimeOrSalary = new JCheckBox("Update by working time"); //$NON-NLS-1$
        chkTimeOrSalary.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (chkTimeOrSalary.isSelected()) {
                    isTimeOrSalary = true;
                    tfTotalWorkingTime.setEnabled(true);
                    tfTotalSalary.setEnabled(false);
                } else {
                    isTimeOrSalary = false;
                    tfTotalWorkingTime.setEnabled(false);
                    tfTotalSalary.setEnabled(true);
                }
            }
        });
        transactionsPanel2.add(chkTimeOrSalary, "wrap"); //$NON-NLS-1$

        lblTotalWorkingTime = new JLabel("New working time (hrs):");
        transactionsPanel2.add(lblTotalWorkingTime);
        tfTotalWorkingTime = new POSTextField("0");
        tfTotalWorkingTime.setHorizontalAlignment(JTextField.RIGHT);
        transactionsPanel2.add(tfTotalWorkingTime, "wrap");
        tfTotalSalary = new POSTextField("0");
        tfTotalSalary.setHorizontalAlignment(JTextField.RIGHT);
        lblTotalSalary = new JLabel("New salary ($):"); //$NON-NLS-1$ //$NON-NLS-2$
        transactionsPanel2.add(lblTotalSalary);
        transactionsPanel2.add(tfTotalSalary, "wrap"); //$NON-NLS-1$
        tfTotalWorkingTime.setEnabled(false);
        tfTotalSalary.setEnabled(true);
//        lblStatus = new JLabel("Please select data to delete ...");
//        lblStatus.setForeground(Color.GREEN);
//        transactionsPanel2.add(lblStatus, "wrap"); //$NON-NLS-1$

        btnCalculate = new PosButton("RECALCULATE"); //$NON-NLS-1$
        btnCalculate.setActionCommand(CALCULATE);
        btnCalculate.setEnabled(false);
        btnSave = new PosButton("SAVE"); //$NON-NLS-1$
        btnSave.setActionCommand(SAVE);
        btnExit = new PosButton(Messages.getString("DatabaseConfigurationDialog.28").toUpperCase()); //$NON-NLS-1$
        btnExit.setActionCommand(CANCEL);

        JPanel buttonPanel = new JPanel(new MigLayout("fillx,right")); //$NON-NLS-1$

        buttonPanel.add(btnCalculate, "h 40!,split 3,right"); //$NON-NLS-1$
        buttonPanel.add(btnSave, "h 40!"); //$NON-NLS-1$
        buttonPanel.add(btnExit, "h 40!"); //$NON-NLS-1$

        JPanel contentPanel = new JPanel(new MigLayout("fillx")); //$NON-NLS-1$
        contentPanel.add(transactionsPanel, "grow,wrap"); //$NON-NLS-1$
        contentPanel.add(transactionsPanel2, "grow,wrap"); //$NON-NLS-1$
        getContentPane().add(new JideScrollPane(contentPanel), BorderLayout.CENTER); //$NON-NLS-1$
        getContentPane().add(buttonPanel, BorderLayout.SOUTH); //$NON-NLS-1$

        getContentPane().setBackground(transactionsPanel.getBackground());
    }

    public void setTitle(String title) {
        super.setTitle("Update Employee Attendance"); //$NON-NLS-1$
    }

    public static UpdateAttendanceWindow open() {
        UpdateAttendanceWindow window = new UpdateAttendanceWindow();
        window.setTitle(Messages.getString("DatabaseConfigurationDialog.38")); //$NON-NLS-1$
        window.pack();
        window.setVisible(true);

        return window;
    }

    public static void main(String[] args) throws Exception {
        UpdateAttendanceWindow.open();
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
        btnCalculate.addActionListener(this);
        btnSave.addActionListener(this);
        btnExit.addActionListener(this);
    }

    public double calculateWorkTime(AttendenceHistory attendance) {
        long cin = attendance.getClockInTime().getTime();
        long cout = attendance.getClockOutTime().getTime();

        long milliseconds = cout - cin;
        if (milliseconds < 0) {
            return 0;
        }

        double seconds = milliseconds / 1000.0;
        double minutes = seconds / 60.0;
        double hours = minutes / 60.0;

        return hours;
    }

    private void calculateAttendance(Date fromDate, Date toDate) {
        try {
            User user = (User) cbUserType.getSelectedItem();
            AttendenceHistoryDAO dao = new AttendenceHistoryDAO();
            List<AttendenceHistory> attendanceList = dao.findHistory(fromDate, toDate, user);
            Double totalTime = 0.0d;
            Double totalSalary = 0.0d;
            System.out.println("Number of attendance: " + attendanceList.size());
            for (AttendenceHistory attendance : attendanceList) {
                totalTime += calculateWorkTime(attendance);
            }
            totalSalary = totalTime * user.getCostPerHour();
            System.out.println("totalTime: " + totalTime);

            // Update view
            tfCurrentTotalSalary.setText("" + (double) Math.round(100 * totalSalary) / 100);
            tfCurrentTotalWorkingTime.setText("" + (double) Math.round(100 * totalTime) / 100);
        } catch (Exception ex) {
            ex.printStackTrace();
            PosLog.error(getClass(), ex);
            POSMessageDialog.showMessage(this, ex.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }

    private void updateAttendanceToDB(List<AttendenceHistory> attendanceList) {
        Session session = null;
        Transaction tx = null;

        try {

            session = AttendenceHistoryDAO.getInstance().createNewSession();
            tx = session.beginTransaction();
            for (AttendenceHistory attendenceHistory : attendanceList) {
                AttendenceHistoryDAO.getInstance().saveOrUpdate(attendenceHistory, session);
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
        POSMessageDialog.showMessage("Update employee attendance completed.");

    }

    private void updateAttendance(Date fromDate, Date toDate) {
        try {
            Double totalTimeOld = (double) Math.round(100 * Double.parseDouble(tfCurrentTotalWorkingTime.getText())) / 100;
            Double totalSalaryOld = (double) Math.round(100 * Double.parseDouble(tfCurrentTotalSalary.getText())) / 100;
            Double totalTimeNew = (double) Math.round(100 * Double.parseDouble(tfTotalWorkingTime.getText())) / 100;
            Double totalSalaryNew = (double) Math.round(100 * Double.parseDouble(tfTotalSalary.getText())) / 100;
            boolean updateTime = isTimeOrSalary;
            boolean updateSalary = !isTimeOrSalary;
            if (totalTimeNew == totalTimeOld) {
                updateTime = false;
            } else if (totalSalaryNew == totalSalaryOld) {
                updateSalary = false;
            }

            if (!updateTime && !updateSalary) {
                POSMessageDialog.showError("Please set value for new working time or new salary.");
                return;
            }

            User user = (User) cbUserType.getSelectedItem();
            AttendenceHistoryDAO dao = new AttendenceHistoryDAO();
            List<AttendenceHistory> attendanceList = dao.findHistory(fromDate, toDate, user);

            if (attendanceList.isEmpty()) {
                POSMessageDialog.showError("There is not any attendance record for this user !!!");
                return;
            }

            if (updateTime) {
                SimpleDateFormat df = new SimpleDateFormat("EEE dd");
                int yesNo = JOptionPane.showConfirmDialog(this, "The total working time from " + df.format(fromDate) + " to " + df.format(toDate) + " will be set to: " + totalTimeNew
                        + "\n\nAre you sure to update total working time for user: " + user + "?", "Update Employee Attendance", JOptionPane.YES_NO_OPTION);
                if (yesNo == JOptionPane.YES_OPTION) {
                    long totalTime = Math.round(totalTimeNew * 60 * 60 * 1000);
                    long avgTime = Math.round(totalTime / attendanceList.size());
                    System.out.println("Total time: " + totalTime);

                    for (AttendenceHistory attendance : attendanceList) {
                        long adjustTime = Math.min(avgTime, totalTime);
                        Calendar clockOut = Calendar.getInstance();
                        clockOut.setTime(attendance.getClockInTime());
                        clockOut.add(Calendar.MILLISECOND, (int) adjustTime);
                        attendance.setClockOutTime(clockOut.getTime());
                        attendance.setClockOutHour((short) clockOut.get(Calendar.HOUR_OF_DAY));
                        totalTime -= adjustTime;
                        System.out.println("Total time: " + totalTime);
                    }

                    updateAttendanceToDB(attendanceList);
                    totalSalaryNew = (double) (Math.round(totalTimeNew * user.getCostPerHour() * 100) / 100);
                    tfTotalSalary.setText("" + totalSalaryNew);
                }
            } else if (updateSalary) {
                SimpleDateFormat df = new SimpleDateFormat("EEE dd");
                int yesNo = JOptionPane.showConfirmDialog(this, "The total salary from " + df.format(fromDate) + " to " + df.format(toDate) + " will be set to: " + totalSalaryNew
                        + "\n\nAre you sure to update total salary for user: " + user + "?", "Update Employee Attendance", JOptionPane.YES_NO_OPTION);
                if (yesNo == JOptionPane.YES_OPTION) {
                    totalTimeNew = (double) Math.round(totalSalaryNew * 100 / user.getCostPerHour()) / 100;
                    long totalTime = Math.round(totalTimeNew * 60 * 60 * 1000);
                    long avgTime = Math.round(totalTime / attendanceList.size());
                    System.out.println("Total time: " + totalTime);

                    for (AttendenceHistory attendance : attendanceList) {
                        long adjustTime = Math.min(avgTime, totalTime);
                        Calendar clockOut = Calendar.getInstance();
                        clockOut.setTime(attendance.getClockInTime());
                        clockOut.add(Calendar.MILLISECOND, (int) adjustTime);
                        attendance.setClockOutTime(clockOut.getTime());
                        attendance.setClockOutHour((short) clockOut.get(Calendar.HOUR_OF_DAY));
                        totalTime -= adjustTime;
                        System.out.println("Total time: " + totalTime);
                    }

                    updateAttendanceToDB(attendanceList);
                    tfTotalWorkingTime.setText("" + totalTimeNew);
                }
            }
            tfCurrentTotalSalary.setText("" + totalSalaryNew);
            tfCurrentTotalWorkingTime.setText("" + totalTimeNew);
        } catch (Exception ex) {
            ex.printStackTrace();
            PosLog.error(getClass(), ex);
            POSMessageDialog.showMessage(this, ex.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
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

            if (SAVE.equals(command)) {
                if (!NumberUtils.isNumber(tfTotalSalary.getText())) {
                    POSMessageDialog.showMessage(this, "Please correct input value to a number.");
                    return;
                }
                if (!NumberUtils.isNumber(tfTotalWorkingTime.getText())) {
                    POSMessageDialog.showMessage(this, "Please correct input value to a number.");
                    return;
                } else {
                    if (!NumberUtils.isNumber(tfTotalWorkingTime.getText())) {
                        tfTotalWorkingTime.setText("10.0");
                    }
                    if (!NumberUtils.isNumber(tfTotalSalary.getText())) {
                        tfTotalSalary.setText("10.0");
                    }
                    System.out.println("New salary: " + tfTotalSalary.getText());
                    setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    updateAttendance(getDate(tfStartDate, true), getDate(tfEndDate, false));
                }
            }
            if (CALCULATE.equals(command)) {
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                calculateAttendance(getDate(tfStartDate, true), getDate(tfEndDate, false));
            }
        } catch (Exception e2) {
            PosLog.error(getClass(), e2);
            POSMessageDialog.showMessage(this, e2.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }
}

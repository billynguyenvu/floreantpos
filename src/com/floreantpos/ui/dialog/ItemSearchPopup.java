/**
 * ************************************************************************
 * * The contents of this file are subject to the MRPL 1.2 * (the "License"),
 * being the Mozilla Public License * Version 1.1 with a permitted attribution
 * clause; you may not use this * file except in compliance with the License.
 * You may obtain a copy of * the License at
 * http://www.floreantpos.org/license.html * Software distributed under the
 * License is distributed on an "AS IS" * basis, WITHOUT WARRANTY OF ANY KIND,
 * either express or implied. See the * License for the specific language
 * governing rights and limitations * under the License. * The Original Code is
 * FLOREANT POS. * The Initial Developer of the Original Code is OROCUBE LLC *
 * All portions are Copyright (C) 2015 OROCUBE LLC * All Rights Reserved.
 * ************************************************************************
 */
package com.floreantpos.ui.dialog;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import org.apache.commons.lang.StringUtils;

import com.floreantpos.Messages;
import com.floreantpos.POSConstants;
import com.floreantpos.main.Application;
import com.floreantpos.swing.PosButton;
import com.floreantpos.swing.PosUIManager;
import javax.swing.JTextField;

public class ItemSearchPopup extends OkCancelOptionDialog implements ActionListener {

    private String value;

    private PosButton btnClear;
    private PosButton btnClearAll;
    private JTextField tfSearchItem;

    public ItemSearchPopup() {
        super(Application.getPosWindow(), true);
        init();
    }

    public ItemSearchPopup(Frame parent) {
        super(parent, true);
        init();
    }

    public ItemSearchPopup(JTextField tfSearchItem) {
        super(Application.getPosWindow(), true);
        this.tfSearchItem = tfSearchItem;
        init();
    }

    public ItemSearchPopup(Frame parent, JTextField tfSearchItem) {
        super(parent, true);
        this.tfSearchItem = tfSearchItem;
        init();
    }

    private void init() {
        //setResizable(false);
        btnClear = new PosButton();
        btnClear.setText(Messages.getString("PasswordEntryDialog.11"));

        btnClearAll = new PosButton();
        btnClearAll.setText(Messages.getString("PasswordEntryDialog.12"));

        JPanel contentPane = new JPanel(new BorderLayout(10, 10));
        getContentPanel().add(contentPane);

        JPanel keyboardPanel = createKeyboardPanel();
        contentPane.add(keyboardPanel);
    }

    private JPanel createKeyboardPanel() {
        JPanel buttonPanel = new JPanel(new GridLayout(0, 3, 5, 5));

        String[][] numbers = {{"7", "8", "9"}, {"4", "5", "6"}, {"1", "2", "3"}, {"0"}}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$
        String[][] iconNames = new String[][]{{"7.png", "8.png", "9.png"}, {"4.png", "5.png", "6.png"}, {"1.png", "2.png", "3.png"}, //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$
        {"0.png"}}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

        Dimension size = PosUIManager.getSize(70, 40);

        Action goAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                String actionCommand = e.getActionCommand();

                if (POSConstants.CANCEL.equalsIgnoreCase(actionCommand)) {
                    doCancel();
                } else if (POSConstants.OK.equalsIgnoreCase(actionCommand)) {
                    doOk();
                } else {
                    if (StringUtils.isNotEmpty(actionCommand)) {
                        tfSearchItem.setText(getString() + actionCommand);
                    }
                }
            }
        };

        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers[i].length; j++) {
                String buttonText = String.valueOf(numbers[i][j]);

                PosButton posButton = new PosButton();
                posButton.setAction(goAction);
                ImageIcon icon = com.floreantpos.IconFactory.getIcon("/ui_icons/", iconNames[i][j]); //$NON-NLS-1$
                if (icon != null) {
                    posButton.setIcon(icon);
                } else {
                    posButton.setText(buttonText);
                }

                posButton.setPreferredSize(size);
                posButton.setIconTextGap(0);
                posButton.setActionCommand(buttonText);
                buttonPanel.add(posButton);
            }
        }
        ImageIcon clearIcon = com.floreantpos.IconFactory.getIcon("/ui_icons/", "clear.png"); //$NON-NLS-1$
        btnClear.setIcon(clearIcon);
        btnClear.setIconTextGap(0);

        ImageIcon clearAllIcon = com.floreantpos.IconFactory.getIcon("/ui_icons/", "clear.png"); //$NON-NLS-1$
        btnClearAll.setIcon(clearAllIcon);
        btnClearAll.setIconTextGap(0);

        buttonPanel.add(btnClear);
        buttonPanel.add(btnClearAll);

        btnClear.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                doClear();
            }
        });

        btnClearAll.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                doClearAll();
            }
        });

        return buttonPanel;
    }

    @Override
    public void doOk() {
        setCanceled(false);
        dispose();
    }

    @Override
    public void doCancel() {
        setCanceled(true);
        dispose();
    }

    private void doClearAll() {
        tfSearchItem.setText(""); //$NON-NLS-1$
    }

    private void doClear() {
        String passwordAsString = getString();
        if (StringUtils.isNotEmpty(passwordAsString)) {
            passwordAsString = passwordAsString.substring(0, passwordAsString.length() - 1);
        }
        tfSearchItem.setText(passwordAsString);
    }

    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        if (POSConstants.CANCEL.equalsIgnoreCase(actionCommand)) {
            doCancel();
        } else if (POSConstants.OK.equalsIgnoreCase(actionCommand)) {
            doOk();
        } else {
            if (StringUtils.isNotEmpty(actionCommand)) {
                tfSearchItem.setText(getString() + actionCommand);
            }
        }
    }

    public void setTitle(String title) {
        super.setTitlePaneText(title);

        super.setTitle(title);
    }

    public void setDialogTitle(String title) {
        super.setTitle(title);
    }

    private String getString() {
        return tfSearchItem.getText();
    }

    public static void main(String[] args) {
        ItemSearchPopup dialog2 = new ItemSearchPopup();
        dialog2.pack();
        dialog2.setVisible(true);
    }

    public static String show(Component parent, String title) {
        ItemSearchPopup dialog2 = new ItemSearchPopup();
        dialog2.setTitle(title);
        dialog2.pack();
        dialog2.setLocationRelativeTo(parent);
        dialog2.setVisible(true);

        if (dialog2.isCanceled()) {
            return null;
        }

        return dialog2.getString();
    }
}

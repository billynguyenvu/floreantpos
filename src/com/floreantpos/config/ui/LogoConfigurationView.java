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
package com.floreantpos.config.ui;

import com.floreantpos.IconFactory;
import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

import org.apache.commons.lang.StringUtils;

import com.floreantpos.Messages;
import com.floreantpos.main.Application;
import com.floreantpos.model.Restaurant;
import com.floreantpos.model.dao.RestaurantDAO;
import com.floreantpos.swing.FixedLengthTextField;
import com.floreantpos.swing.POSFileChooser;
import com.floreantpos.swing.POSTextField;
import com.floreantpos.ui.dialog.POSMessageDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import org.apache.commons.io.FileUtils;

public class LogoConfigurationView extends ConfigurationView {
    JPanel contentPanel;
    JButton btnFile1;
    JButton btnFile2;
    JButton btnFile3;

    public LogoConfigurationView() {
        setLayout(new BorderLayout());
        contentPanel = new JPanel(new MigLayout("fillx", "[][grow][][grow]")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

        btnFile1 = new JButton("");
        btnFile1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String fileName = "";
                POSFileChooser fileChooser = new POSFileChooser();
                int ret = fileChooser.showOpenDialog(btnFile1);
                if (ret == POSFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    fileName = file.getName();
                    System.out.println("New file 1: " + fileName);
                    try {
                        FileUtils.copyFile(file, new File("config/ui_icons/", "" + "header-logo-orig.png"));
                        btnFile1.setIcon(IconFactory.getIcon("/ui_icons/", "" + "header-logo-orig.png", true));
                        POSMessageDialog.showMessage("This change will be applied after the application restarted.");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        btnFile1.setIcon(IconFactory.getIcon("/ui_icons/", "" + "header-logo-orig.png"));
        btnFile1.setBorder(BorderFactory.createEmptyBorder());
        btnFile1.setContentAreaFilled(false);
        btnFile1.setFocusable(false);
        contentPanel.add(btnFile1, "cell 0 0,growx");
        
        btnFile2 = new JButton("");
        btnFile2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String fileName = "";
                POSFileChooser fileChooser = new POSFileChooser();
                int ret = fileChooser.showOpenDialog(btnFile1);
                if (ret == POSFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    fileName = file.getName();
                    System.out.println("New file 2: " + fileName);
                    try {
                        FileUtils.copyFile(file, new File("config/ui_icons/", "" + "header-logo.png"));
                        btnFile2.setIcon(IconFactory.getIcon("/ui_icons/", "" + "header-logo.png", true));
                        POSMessageDialog.showMessage("This change will be applied after the application restarted.");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        btnFile2.setIcon(IconFactory.getIcon("/ui_icons/", "" + "header-logo.png"));
        btnFile2.setBorder(BorderFactory.createEmptyBorder());
        btnFile2.setContentAreaFilled(false);
        btnFile2.setFocusable(false);
        contentPanel.add(btnFile2, "cell 0 1,growx");
        
        btnFile3 = new JButton("");
        btnFile3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String fileName = "";
                POSFileChooser fileChooser = new POSFileChooser();
                int ret = fileChooser.showOpenDialog(btnFile1);
                if (ret == POSFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    fileName = file.getName();
                    System.out.println("New file 3: " + fileName);
                    try {
                        FileUtils.copyFile(file, new File("config/ui_icons/", "" + "title.png"));
                        btnFile3.setIcon(IconFactory.getIcon("/ui_icons/", "" + "title.png", true));
                        POSMessageDialog.showMessage("This change will be applied after the application restarted.");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        btnFile3.setIcon(IconFactory.getIcon("/ui_icons/", "" + "title.png"));
        btnFile3.setBorder(BorderFactory.createEmptyBorder());
        btnFile3.setContentAreaFilled(false);
        btnFile3.setFocusable(false);
        contentPanel.add(btnFile3, "cell 0 2,growx");

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(null);
        add(scrollPane);
    }

    @Override
    public boolean save() throws Exception {
        return true;
    }

    @Override
    public void initialize() throws Exception {
        
    }

    @Override
    public String getName() {
        return "Logo";
    }
}

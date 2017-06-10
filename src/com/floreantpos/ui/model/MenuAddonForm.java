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
 * MenuAddonEditor.java
 *
 * Created on August 4, 2006, 12:03 AM
 */

package com.floreantpos.ui.model;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import net.miginfocom.swing.MigLayout;

import com.floreantpos.Messages;
import com.floreantpos.model.MenuAddon;
import com.floreantpos.model.Tax;
import com.floreantpos.model.dao.MenuAddonDAO;
import com.floreantpos.model.dao.TaxDAO;
import com.floreantpos.swing.ComboBoxModel;
import com.floreantpos.swing.DoubleTextField;
import com.floreantpos.swing.FixedLengthTextField;
import com.floreantpos.swing.IntegerTextField;
import com.floreantpos.swing.MessageDialog;
import com.floreantpos.swing.TransparentPanel;
import com.floreantpos.ui.BeanEditor;
import com.floreantpos.ui.dialog.BeanEditorDialog;
import com.floreantpos.util.POSUtil;

/**
 *
 * @author  MShahriar
 */
public class MenuAddonForm extends BeanEditor {

	private MenuAddon menuAddon;

	private JCheckBox chkPrintToKitchen;
	private JComboBox cbTaxes;

	private JFormattedTextField tfName;
	private FixedLengthTextField tfTranslatedName;
	private DoubleTextField tfNormalPrice;
	private IntegerTextField tfSortOrder;

	private JButton btnButtonColor;
	private JButton btnTextColor;

	private JTabbedPane jTabbedPane1;

	public MenuAddonForm() throws Exception {
		this(new MenuAddon());
	}

	public MenuAddonForm(MenuAddon menuAddon) throws Exception {
		this.menuAddon = menuAddon;

		initComponents();

		TaxDAO taxDAO = new TaxDAO();
		List<Tax> taxes = taxDAO.findAll();
		cbTaxes.setModel(new ComboBoxModel(taxes));

		add(jTabbedPane1);

		setBean(menuAddon);
	}

	private void initComponents() {
		setLayout(new BorderLayout(0, 0));

		jTabbedPane1 = new javax.swing.JTabbedPane();

		tfName = new javax.swing.JFormattedTextField();
		tfTranslatedName = new FixedLengthTextField();
		tfNormalPrice = new DoubleTextField();
		tfSortOrder = new IntegerTextField();
		cbTaxes = new javax.swing.JComboBox();
		JButton btnNewTax = new javax.swing.JButton();
		chkPrintToKitchen = new javax.swing.JCheckBox();

		JScrollPane jScrollPane3 = new javax.swing.JScrollPane();

		JLabel lblName = new javax.swing.JLabel(com.floreantpos.POSConstants.NAME + ":");
		JLabel lblTranslatedName = new JLabel(Messages.getString("MenuAddonForm.0")); //$NON-NLS-1$
		JLabel lblPrice = new javax.swing.JLabel("Price" + ":");
		JLabel lblSortOrder = new JLabel(Messages.getString("MenuAddonForm.15")); //$NON-NLS-1$
		JLabel lblTaxRate = new javax.swing.JLabel(com.floreantpos.POSConstants.TAX_RATE + ":");
		JLabel lblPercentage = new javax.swing.JLabel();

		lblPercentage.setText("%"); //$NON-NLS-1$
		tfNormalPrice.setText("0"); //$NON-NLS-1$

		btnNewTax.setText("..."); //$NON-NLS-1$
		btnNewTax.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnNewTaxActionPerformed(evt);
			}
		});

		chkPrintToKitchen.setText(com.floreantpos.POSConstants.PRINT_TO_KITCHEN);
		chkPrintToKitchen.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
		chkPrintToKitchen.setMargin(new java.awt.Insets(0, 0, 0, 0));

		JPanel generalTabPanel = new JPanel(new BorderLayout());
		jTabbedPane1.addTab(com.floreantpos.POSConstants.GENERAL, generalTabPanel);

		TransparentPanel lelfInputPanel = new TransparentPanel();
		lelfInputPanel.setLayout(new MigLayout("wrap 2,hidemode 3", "[90px][grow]", "")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		lelfInputPanel.add(lblName, "alignx left,aligny center"); //$NON-NLS-1$
		lelfInputPanel.add(tfName, "growx,aligny top"); //$NON-NLS-1$

		lelfInputPanel.add(lblTranslatedName, "alignx left,aligny center"); //$NON-NLS-1$
		lelfInputPanel.add(tfTranslatedName, "growx"); //$NON-NLS-1$

                lelfInputPanel.add(lblPrice, "alignx left,aligny center"); //$NON-NLS-1$
                lelfInputPanel.add(tfNormalPrice, "growx,aligny top"); //$NON-NLS-1$

		JPanel rightInputPanel = new JPanel(new MigLayout("wrap 2", "[86px][grow]"));

		rightInputPanel.add(lblTaxRate, "alignx left,aligny center,split 2"); //$NON-NLS-1$
		rightInputPanel.add(lblPercentage, "alignx left,aligny center"); //$NON-NLS-1$
		rightInputPanel.add(cbTaxes, "growx,aligny top,split 2"); //$NON-NLS-1$
		rightInputPanel.add(btnNewTax, "alignx left,aligny top"); //$NON-NLS-1$

		rightInputPanel.add(lblSortOrder, "alignx left,aligny center"); //$NON-NLS-1$
		rightInputPanel.add(tfSortOrder, "growx,aligny top"); //$NON-NLS-1$

		rightInputPanel.add(chkPrintToKitchen, "skip 1,alignx left,aligny top"); //$NON-NLS-1$

		generalTabPanel.add(lelfInputPanel);
		generalTabPanel.add(rightInputPanel, BorderLayout.EAST);

		JLabel lblButtonColor = new JLabel(Messages.getString("MenuAddonForm.1")); //$NON-NLS-1$
		btnButtonColor = new JButton(""); //$NON-NLS-1$
		btnButtonColor.setPreferredSize(new Dimension(140, 40));

		JLabel lblTextColor = new JLabel(Messages.getString("MenuAddonForm.27")); //$NON-NLS-1$
		btnTextColor = new JButton(Messages.getString("MenuAddonForm.29")); //$NON-NLS-1$
		btnTextColor.setPreferredSize(new Dimension(140, 40));

		JPanel tabButtonStyle = new JPanel(new MigLayout("hidemode 3,wrap 2"));
		tabButtonStyle.add(lblButtonColor); //$NON-NLS-1$
		tabButtonStyle.add(btnButtonColor); //$NON-NLS-1$
		tabButtonStyle.add(lblTextColor); //$NON-NLS-1$
		tabButtonStyle.add(btnTextColor); //$NON-NLS-1$

		jTabbedPane1.addTab("Button Style", tabButtonStyle); //$NON-NLS-1$

		btnButtonColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Color color = JColorChooser.showDialog(MenuAddonForm.this, Messages.getString("MenuAddonForm.39"), btnButtonColor.getBackground()); //$NON-NLS-1$
				btnButtonColor.setBackground(color);
				btnTextColor.setBackground(color);
			}
		});

		btnTextColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Color color = JColorChooser.showDialog(MenuAddonForm.this, Messages.getString("MenuAddonForm.40"), btnTextColor.getForeground()); //$NON-NLS-1$
				btnTextColor.setForeground(color);
			}
		});
	}

	private void btnNewTaxActionPerformed(java.awt.event.ActionEvent evt) {
		try {
			TaxForm editor = new TaxForm();
			BeanEditorDialog dialog = new BeanEditorDialog(POSUtil.getBackOfficeWindow(), editor);
			dialog.open();
			if (!dialog.isCanceled()) {
				Tax tax = (Tax) editor.getBean();
				ComboBoxModel model = (ComboBoxModel) cbTaxes.getModel();
				model.addElement(tax);
				model.setSelectedItem(tax);
			}
		} catch (Exception x) {
			MessageDialog.showError(com.floreantpos.POSConstants.ERROR_MESSAGE, x);
		}
	}

	@Override
	public boolean save() {
		try {
			if (!updateModel())
				return false;

			MenuAddon menuAddon = (MenuAddon) getBean();
			MenuAddonDAO dao = new MenuAddonDAO();
			dao.saveOrUpdate(menuAddon);
		} catch (Exception e) {
			MessageDialog.showError(com.floreantpos.POSConstants.SAVE_ERROR, e);
			return false;
		}
		return true;
	}

	@Override
	protected void updateView() {
		MenuAddon menuAddon = (MenuAddon) getBean();

		if (menuAddon == null) {
			tfName.setText(""); //$NON-NLS-1$
			tfNormalPrice.setText("0"); //$NON-NLS-1$
			return;
		}

		tfName.setText(menuAddon.getName());
		tfTranslatedName.setText(menuAddon.getTranslatedName());
		tfNormalPrice.setText(String.valueOf(menuAddon.getPrice()));
		chkPrintToKitchen.setSelected(menuAddon.isShouldPrintToKitchen());

		if (menuAddon.getSortOrder() != null) {
			tfSortOrder.setText(menuAddon.getSortOrder().toString());
		}

		if (menuAddon.getButtonColor() != null) {
			Color color = new Color(menuAddon.getButtonColor());
			btnButtonColor.setBackground(color);
			btnTextColor.setBackground(color);
		}

		if (menuAddon.getTextColor() != null) {
			Color color = new Color(menuAddon.getTextColor());
			btnTextColor.setForeground(color);
		}

		if (menuAddon.getTax() != null) {
			cbTaxes.setSelectedItem(menuAddon.getTax());
		}
	}

	@Override
	protected boolean updateModel() {
		MenuAddon menuAddon = (MenuAddon) getBean();

		String name = tfName.getText();
		if (POSUtil.isBlankOrNull(name)) {
			MessageDialog.showError(Messages.getString("MenuAddonForm.44")); //$NON-NLS-1$
			return false;
		}

		menuAddon.setName(name);
		menuAddon.setTax((Tax) cbTaxes.getSelectedItem());
		menuAddon.setShouldPrintToKitchen(Boolean.valueOf(chkPrintToKitchen.isSelected()));

		menuAddon.setTranslatedName(tfTranslatedName.getText());
		menuAddon.setButtonColor(btnButtonColor.getBackground().getRGB());
		menuAddon.setTextColor(btnTextColor.getForeground().getRGB());
		menuAddon.setSortOrder(tfSortOrder.getInteger());

		menuAddon.setPrice(tfNormalPrice.getDouble());
		return true;
	}

	public String getDisplayText() {
		MenuAddon menuAddon = (MenuAddon) getBean();
		if (menuAddon.getId() == null) {
			return Messages.getString("MenuAddonForm.45"); //$NON-NLS-1$
		}
		return Messages.getString("MenuAddonForm.46"); //$NON-NLS-1$
	}
}

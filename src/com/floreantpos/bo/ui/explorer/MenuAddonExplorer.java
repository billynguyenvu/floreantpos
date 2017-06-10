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
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.jdesktop.swingx.JXTable;

import com.floreantpos.Messages;
import com.floreantpos.POSConstants;
import com.floreantpos.bo.ui.BOMessageDialog;
import com.floreantpos.bo.ui.CustomCellRenderer;
import com.floreantpos.model.MenuAddon;
import com.floreantpos.model.dao.MenuAddonDAO;
import com.floreantpos.swing.ListTableModel;
import com.floreantpos.swing.TransparentPanel;
import com.floreantpos.ui.dialog.BeanEditorDialog;
import com.floreantpos.ui.dialog.ConfirmDeleteDialog;
import com.floreantpos.ui.model.MenuAddonForm;
import com.floreantpos.util.CurrencyUtil;
import com.floreantpos.util.POSUtil;

public class MenuAddonExplorer extends TransparentPanel {

	private String currencySymbol;
	private JXTable table;
	private MenuAddonExplorerModel tableModel;

	public MenuAddonExplorer() {
		setLayout(new BorderLayout(5, 5));

		currencySymbol = CurrencyUtil.getCurrencySymbol();
		tableModel = new MenuAddonExplorerModel();
		table = new JXTable(tableModel);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setDefaultRenderer(Object.class, new CustomCellRenderer());
		add(new JScrollPane(table));

		createActionButtons();
		add(buildSearchForm(), BorderLayout.NORTH);

		updateMenuAddonList();

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				if (me.getClickCount() == 2) {
					doEditSelectedMenuAddon();
				}
			}
		});
	}

	private void createActionButtons() {
		ExplorerButtonPanel explorerButtonPanel = new ExplorerButtonPanel();
		JButton editButton = explorerButtonPanel.getEditButton();
		JButton addButton = explorerButtonPanel.getAddButton();
		JButton deleteButton = explorerButtonPanel.getDeleteButton();
		JButton duplicateButton = new JButton(POSConstants.DUPLICATE);
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MenuAddonForm editor = new MenuAddonForm();
					BeanEditorDialog dialog = new BeanEditorDialog(POSUtil.getBackOfficeWindow(), editor);
					dialog.open();
					if (dialog.isCanceled())
						return;
					MenuAddon menuAddon = (MenuAddon) editor.getBean();
					tableModel.addMenuAddon(menuAddon);
				} catch (Throwable x) {
					BOMessageDialog.showError(com.floreantpos.POSConstants.ERROR_MESSAGE, x);
				}
			}

		});
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doEditSelectedMenuAddon();
			}
		});

		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int index = table.getSelectedRow();
					if (index < 0)
						return;

					index = table.convertRowIndexToModel(index);

					if (ConfirmDeleteDialog
							.showMessage(MenuAddonExplorer.this, com.floreantpos.POSConstants.CONFIRM_DELETE, com.floreantpos.POSConstants.DELETE) != ConfirmDeleteDialog.NO) {
						MenuAddon category = (MenuAddon) tableModel.getRowData(index);
						MenuAddonDAO menuAddonDAO = new MenuAddonDAO();
						menuAddonDAO.delete(category);
						tableModel.deleteMenuAddon(category, index);
					}
				} catch (Throwable x) {
					BOMessageDialog.showError(com.floreantpos.POSConstants.ERROR_MESSAGE, x);
				}

			}

		});

		duplicateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int index = table.getSelectedRow();
					if (index < 0)
						return;

					index = table.convertRowIndexToModel(index);

					MenuAddon existingMenuAddon = (MenuAddon) tableModel.getRowData(index);

					MenuAddon newMenuAddon = new MenuAddon();
					PropertyUtils.copyProperties(newMenuAddon, existingMenuAddon);
					newMenuAddon.setId(null);
					String newName = doDuplicateName(existingMenuAddon);
					newMenuAddon.setName(newName);
					newMenuAddon.setSortOrder(existingMenuAddon.getSortOrder());
					newMenuAddon.setTax(existingMenuAddon.getTax());
					newMenuAddon.setButtonColor(existingMenuAddon.getButtonColor());
					newMenuAddon.setTextColor(existingMenuAddon.getTextColor());
					newMenuAddon.setShouldPrintToKitchen(existingMenuAddon.isShouldPrintToKitchen());

					MenuAddonForm editor = new MenuAddonForm(newMenuAddon);
					BeanEditorDialog dialog = new BeanEditorDialog(POSUtil.getBackOfficeWindow(), editor);
					dialog.open();
					if (dialog.isCanceled())
						return;

					MenuAddon menuMenuAddon = (MenuAddon) editor.getBean();
					tableModel.addMenuAddon(menuMenuAddon);
					table.getSelectionModel().addSelectionInterval(tableModel.getRowCount() - 1, tableModel.getRowCount() - 1);
					table.scrollRowToVisible(tableModel.getRowCount() - 1);
				} catch (Throwable x) {
					BOMessageDialog.showError(POSConstants.ERROR_MESSAGE, x);
				}
			}
		});
		TransparentPanel panel = new TransparentPanel();
		panel.add(addButton);
		panel.add(editButton);
		panel.add(deleteButton);
		panel.add(duplicateButton);

		add(panel, BorderLayout.SOUTH);
	}

	private void doEditSelectedMenuAddon() {
		try {
			int index = table.getSelectedRow();
			if (index < 0)
				return;

			index = table.convertRowIndexToModel(index);
			MenuAddon menuAddon = (MenuAddon) tableModel.getRowData(index);

			MenuAddonForm editor = new MenuAddonForm(menuAddon);
			BeanEditorDialog dialog = new BeanEditorDialog(POSUtil.getBackOfficeWindow(), editor);
			dialog.open();
			if (dialog.isCanceled())
				return;

			table.repaint();
		} catch (Throwable x) {
			BOMessageDialog.showError(com.floreantpos.POSConstants.ERROR_MESSAGE, x);
		}
	}

	private JPanel buildSearchForm() {
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout("", "[][]30[][]30[]", "[]20[]")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		JLabel nameLabel = new JLabel(Messages.getString("MenuAddonExplorer.3")); //$NON-NLS-1$
		final JTextField nameField = new JTextField(15);

		JButton searchBttn = new JButton(Messages.getString("MenuAddonExplorer.6")); //$NON-NLS-1$
		panel.add(nameLabel, "align label"); //$NON-NLS-1$
		panel.add(nameField);
		panel.add(searchBttn);

		TitledBorder title;
		Border loweredetched;
		loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		title = BorderFactory.createTitledBorder(loweredetched, Messages.getString("MenuAddonExplorer.8")); //$NON-NLS-1$
		title.setTitleJustification(TitledBorder.LEFT);
		panel.setBorder(title);
		searchBttn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				List<MenuAddon> menuAddonList;
				String txName = nameField.getText();
                                menuAddonList = MenuAddonDAO.getInstance().findMenuAddon(txName);

				setMenuAddonList(menuAddonList);
			}
		});
		return panel;
	}

	public synchronized void updateMenuAddonList() {
		setMenuAddonList(MenuAddonDAO.getInstance().findAll());

	}

	public void setMenuAddonList(List<MenuAddon> menuAddonList) {
		tableModel.setRows(menuAddonList);

	}

	private class MenuAddonExplorerModel extends ListTableModel {

		public MenuAddonExplorerModel() {

			super(new String[] { com.floreantpos.POSConstants.ID, com.floreantpos.POSConstants.NAME, POSConstants.TRANSLATED_NAME,
					com.floreantpos.POSConstants.PRICE + " (" + currencySymbol + ")", //$NON-NLS-1$ //$NON-NLS-2$
					com.floreantpos.POSConstants.TAX + "(%)", POSConstants.BUTTON_COLOR, POSConstants.SORT_ORDER }); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		}

		public Object getValueAt(int rowIndex, int columnIndex) {
			List<MenuAddon> menuAddonList = getRows();

			MenuAddon menuAddon = menuAddonList.get(rowIndex);

			switch (columnIndex) {
				case 0:
					return String.valueOf(menuAddon.getId());

				case 1:
					return menuAddon.getName();

				case 2:
					return menuAddon.getTranslatedName();

				case 3:
					return Double.valueOf(menuAddon.getPrice());

				case 4:
					if (menuAddon.getTax() == null) {
						return ""; //$NON-NLS-1$
					}
					return Double.valueOf(menuAddon.getTax().getRate());

				case 5:
					if (menuAddon.getButtonColor() != null) {
						return new Color(menuAddon.getButtonColor());
					}

					return null;

				case 6:
					return menuAddon.getSortOrder();
			}
			return null;
		}

		public void addMenuAddon(MenuAddon category) {
			int size = getRows().size();
			getRows().add(category);
			fireTableRowsInserted(size, size);

		}

		public void deleteMenuAddon(MenuAddon category, int index) {
			getRows().remove(category);
			fireTableRowsDeleted(index, index);
		}

	}

	private String doDuplicateName(MenuAddon existingMenuAddon) {
		String existingName = existingMenuAddon.getName();
		String newName = new String();
		int lastIndexOf = existingName.lastIndexOf(" ");
		if (lastIndexOf == -1) {
			newName = existingName + " 1";
		}
		else {
			String processName = existingName.substring(lastIndexOf + 1, existingName.length());
			if (StringUtils.isNumeric(processName)) {
				Integer count = Integer.valueOf(processName);
				count += 1;
				newName = existingName.replace(processName, String.valueOf(count));
			}
			else {
				newName = existingName + " 1";
			}
		}
		return newName;
	}

}
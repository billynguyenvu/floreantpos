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
import java.util.List;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import com.floreantpos.POSConstants;
import com.floreantpos.bo.ui.BOMessageDialog;
import com.floreantpos.bo.ui.CustomCellRenderer;
import com.floreantpos.model.MenuFreeAddon;
import com.floreantpos.model.dao.MenuFreeAddonDAO;
import com.floreantpos.swing.TransparentPanel;
import com.floreantpos.ui.PosTableRenderer;
import com.floreantpos.ui.dialog.BeanEditorDialog;
import com.floreantpos.ui.dialog.ConfirmDeleteDialog;
import com.floreantpos.ui.model.MenuFreeAddonForm;
import com.floreantpos.util.POSUtil;

public class MenuFreeAddonExplorer extends TransparentPanel {
	private List<MenuFreeAddon> menuFreeAddonList;
	private JTable table;
	private MenuFreeAddonExplorerTableModel tableModel;
	private JButton editButton;
	private JButton deleteButton;

	public MenuFreeAddonExplorer() {
		menuFreeAddonList = MenuFreeAddonDAO.getInstance().findAll();

		tableModel = new MenuFreeAddonExplorerTableModel();
		table = new JTable(tableModel);
		table.setDefaultRenderer(Object.class, new CustomCellRenderer());
		table.getColumnModel().getColumn(6).setCellRenderer(new PosTableRenderer());
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				int index = table.getSelectedRow();
				if (index < 0)
					return;

				MenuFreeAddon menuFreeAddon = menuFreeAddonList.get(index);
			}
		});

		setLayout(new BorderLayout(5, 5));
		add(new JScrollPane(table));

		JButton addButton = new JButton(com.floreantpos.POSConstants.ADD);
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MenuFreeAddonForm editor = new MenuFreeAddonForm();
					BeanEditorDialog dialog = new BeanEditorDialog(POSUtil.getBackOfficeWindow(), editor);
					dialog.open();
					if (dialog.isCanceled())
						return;

					tableModel.addMenuFreeAddon((MenuFreeAddon) editor.getBean());
				} catch (Exception x) {
					BOMessageDialog.showError(com.floreantpos.POSConstants.ERROR_MESSAGE, x);
				}
			}

		});

		editButton = new JButton(com.floreantpos.POSConstants.EDIT);
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int index = table.getSelectedRow();
					if (index < 0)
						return;

					MenuFreeAddon menuFreeAddon = menuFreeAddonList.get(index);

					MenuFreeAddonForm menuFreeAddonForm = new MenuFreeAddonForm(menuFreeAddon);
					BeanEditorDialog dialog = new BeanEditorDialog(POSUtil.getBackOfficeWindow(), menuFreeAddonForm);
					dialog.open();
					if (dialog.isCanceled())
						return;

					table.repaint();
				} catch (Throwable x) {
					BOMessageDialog.showError(com.floreantpos.POSConstants.ERROR_MESSAGE, x);
				}
			}

		});
		deleteButton = new JButton(com.floreantpos.POSConstants.DELETE);
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int index = table.getSelectedRow();
					if (index < 0)
						return;
					MenuFreeAddon menuFreeAddon = menuFreeAddonList.get(index);
					if (ConfirmDeleteDialog.showMessage(MenuFreeAddonExplorer.this, com.floreantpos.POSConstants.CONFIRM_DELETE,
							com.floreantpos.POSConstants.DELETE) == ConfirmDeleteDialog.YES) {
						MenuFreeAddonDAO.getInstance().delete(menuFreeAddon);
						tableModel.deleteMenuFreeAddon(menuFreeAddon, index);
					}
				} catch (Exception x) {
					BOMessageDialog.showError(com.floreantpos.POSConstants.ERROR_MESSAGE, x);
				}
			}
		});

		JButton btnSetAsDefault = new JButton("Set default");
		btnSetAsDefault.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int index = table.getSelectedRow();
					if (index < 0)
						return;
					MenuFreeAddon selectedMenuFreeAddon = menuFreeAddonList.get(index);
					MenuFreeAddonDAO.getInstance().saveOrUpdateMenuFreeAddons(menuFreeAddonList);
					tableModel.fireTableDataChanged();
				} catch (Exception x) {
					BOMessageDialog.showError(com.floreantpos.POSConstants.ERROR_MESSAGE, x);
				}
			}

		});

		TransparentPanel panel = new TransparentPanel();
		panel.add(addButton);
		panel.add(editButton);
		panel.add(deleteButton);
		panel.add(btnSetAsDefault);
		add(panel, BorderLayout.SOUTH);
	}

	class MenuFreeAddonExplorerTableModel extends AbstractTableModel {
		String[] columnNames = { POSConstants.NAME, "Prefix", POSConstants.RATE, POSConstants.SORT_ORDER, POSConstants.BUTTON_COLOR, POSConstants.TEXT_COLOR,
				"Default" };

		public int getRowCount() {
			if (menuFreeAddonList == null) {
				return 0;
			}
			return menuFreeAddonList.size();
		}

		public int getColumnCount() {
			return columnNames.length;
		}

		@Override
		public String getColumnName(int column) {
			return columnNames[column];
		}

		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false;
		}

		public Object getValueAt(int rowIndex, int columnIndex) {
			if (menuFreeAddonList == null)
				return ""; //$NON-NLS-1$

			MenuFreeAddon menuFreeAddon = menuFreeAddonList.get(rowIndex);

			switch (columnIndex) {
				case 0:
					return menuFreeAddon.getName();
				case 1:
					return menuFreeAddon.getSortOrder();
				case 2:
					if (menuFreeAddon.getButtonColor() != null) {
						return menuFreeAddon.getButtonColor();
					}

					return null;
				case 3:
					if (menuFreeAddon.getTextColor() != null) {
						return menuFreeAddon.getTextColor();
					}

					return null;

			}
			return null;
		}

		public void addMenuFreeAddon(MenuFreeAddon menuFreeAddon) {
			int size = menuFreeAddonList.size();
			menuFreeAddonList.add(menuFreeAddon);
			fireTableRowsInserted(size, size);
		}

		public void deleteMenuFreeAddon(MenuFreeAddon menuFreeAddon, int index) {
			menuFreeAddonList.remove(menuFreeAddon);
			fireTableRowsDeleted(index, index);
		}
	}
}

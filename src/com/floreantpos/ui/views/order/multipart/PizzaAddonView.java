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
 * GroupView.java
 *
 * Created on August 5, 2006, 9:29 PM
 */

package com.floreantpos.ui.views.order.multipart;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

import com.floreantpos.POSConstants;
import com.floreantpos.model.MenuItem;
import com.floreantpos.model.MenuItemSize;
import com.floreantpos.model.MenuAddon;
import com.floreantpos.model.Multiplier;
import com.floreantpos.model.TicketItem;
import com.floreantpos.model.dao.MultiplierDAO;
import com.floreantpos.swing.POSToggleButton;
import com.floreantpos.swing.PosButton;
import com.floreantpos.swing.PosUIManager;
import com.floreantpos.swing.ScrollableFlowPanel;
import com.floreantpos.ui.views.order.modifier.AddonSelectionListener;
import com.floreantpos.ui.views.order.modifier.AddonSelectionModel;

/**
 * 
 * @author MShahriar
 */
public class PizzaAddonView extends JPanel {
	private AddonSelectionListener addonSelectionListener;

	//private AddonSelectionModel addonSelectionModel;

	private PosButton btnClear = new PosButton(POSConstants.CLEAR);

	private HashMap<String, AddonButton> buttonMap = new HashMap<String, AddonButton>();
	private Multiplier selectedMultiplier;
	private MultiplierButton defaultMultiplierButton;

	private JPanel mainPanel;
                  private JPanel multiplierPanel;

	private JPanel contentPanel;
	private PizzaModifierSelectionDialog pizzaAddonSelectionDialog;
	private ScrollableFlowPanel groupPanel;

	public PizzaAddonView(TicketItem ticketItem, MenuItem menuItem, PizzaModifierSelectionDialog pizzaAddonSelectionDialog) {
		AddonSelectionModel addonSelectionModel = new AddonSelectionModel(ticketItem, menuItem);
		//this.addonSelectionModel = addonSelectionModel;
		this.pizzaAddonSelectionDialog = pizzaAddonSelectionDialog;
		setLayout(new BorderLayout());
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBorder(new TitledBorder(null, "ADDONS", TitledBorder.CENTER, TitledBorder.CENTER));
		contentPanel = new JPanel();
		contentPanel.setLayout(new MigLayout("fillx, aligny top"));
		add(mainPanel, BorderLayout.CENTER);
		addMultiplierButtons();
	}

	private void addActionButtons() {
		//actionButtonPanel.add(btnClear);
		btnClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
	}

	private void addMultiplierButtons() {
		multiplierPanel = new JPanel(new MigLayout("fillx,center"));
		List<Multiplier> multiplierList = MultiplierDAO.getInstance().findAll();
		ButtonGroup group = new ButtonGroup();
		if (multiplierList != null) {
			for (Multiplier multiplier : multiplierList) {
				MultiplierButton btnMultiplier = new MultiplierButton(multiplier);
				if (multiplier.isDefaultMultiplier()) {
					selectedMultiplier = multiplier;
					defaultMultiplierButton = btnMultiplier;
					btnMultiplier.setSelected(true);
				}
				multiplierPanel.add(btnMultiplier, "grow");
				group.add(btnMultiplier);
			}
		}
		mainPanel.add(multiplierPanel, BorderLayout.SOUTH);
//                multiplierPanel.setVisible(false);
	}

	//	public void setAddons(Collection<MenuAddon> addons) {
	//		buttonMap.clear();
	//
	//		try {
	//
	//			List itemList = new ArrayList();
	//
	//			for (MenuAddon addon : addons) {
	//				itemList.add(addon);
	//			}
	//
	//			setItems(itemList);
	//		} catch (PosException e) {
	//			POSMessageDialog.showError(this, com.floreantpos.POSConstants.ERROR_MESSAGE, e);
	//		}
	//	}
	//
	//	@Override
	//	protected void renderItems() {
	//		super.renderItems();
	//		updateView();
	//	}

	protected AbstractButton createItemButton(Object item) {
		MenuAddon addon = (MenuAddon) item;
		AddonButton addonButton = new AddonButton(addon, null, null);
//		String key = addon.getId() + "_" + addon.getAddonGroup().getId(); //$NON-NLS-1$
//		buttonMap.put(key, addonButton);

		return addonButton;
	}

	public void addAddonSelectionListener(AddonSelectionListener listener) {
		this.addonSelectionListener = listener;
	}

	public void removeAddonSelectionListener(AddonSelectionListener listener) {
		this.addonSelectionListener = null;
	}

	public void updateView() {
		contentPanel.removeAll();
		groupPanel = new ScrollableFlowPanel();
		groupPanel.setPreferredSize(new Dimension(PosUIManager.getSize(500, 0)));
		JScrollPane js = new JScrollPane(groupPanel, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//		js.setBorder(null);
//
//                if (menuAddonGroup != null) {
//		Set<MenuAddon> addons = menuAddonGroup.getAddons();
//		for (MenuAddon menuAddon : addons) {
//			if (!menuAddon.isPizzaAddon()) {
//				continue;
//			}
//			menuAddon.setMenuItemAddonGroup(menuAddonGroup.getMenuItemAddonGroup());
//			groupPanel.getContentPane().add(new AddonButton(menuAddon, selectedMultiplier, pizzaAddonSelectionDialog.getSelectedSize()));
//		}
//                if(addons.size() > 0) multiplierPanel.setVisible(true);
//                }
		contentPanel.add(js, "newline,top,center");
		mainPanel.add(contentPanel, BorderLayout.CENTER);
		contentPanel.repaint();
		mainPanel.repaint();

	}

	private class AddonButton extends PosButton implements ActionListener {
		private MenuAddon menuAddon;

		public AddonButton(MenuAddon addon, Multiplier multiplier, MenuItemSize menuItemSize) {
			this.menuAddon = addon;

			setText("<html><center>" + addon.getDisplayName() + "<br/>" + addon.getPriceForSizeAndMultiplier(menuItemSize, true, multiplier) + "</center></html>"); //$NON-NLS-1$ //$NON-NLS-2$

			if (addon.getButtonColor() != null) {
				setBackground(new Color(addon.getButtonColor()));
			}

			if (addon.getTextColor() != null) {
				setForeground(new Color(addon.getTextColor()));
			}

			setFocusable(true);
			setFocusPainted(true);
			addActionListener(this);
		}

		public void actionPerformed(ActionEvent e) {
			addonSelectionListener.addonSelected(menuAddon, selectedMultiplier);

		//	defaultMultiplierButton.setSelected(true);
		//	selectedMultiplier = defaultMultiplierButton.getMultiplier();
			
			groupPanel.getContentPane().removeAll();
//			Set<MenuAddon> addons = menuAddonGroup.getAddons();
//			for (MenuAddon menuAddon : addons) {
//				if (!menuAddon.isPizzaAddon()) {
//					continue;
//				}
//				menuAddon.setMenuItemAddonGroup(menuAddonGroup.getMenuItemAddonGroup());
//				groupPanel.getContentPane().add(new AddonButton(menuAddon, selectedMultiplier, pizzaAddonSelectionDialog.getSelectedSize()));
//			}
//                        if(addons.size() > 0) multiplierPanel.setVisible(true);
			contentPanel.repaint();
			mainPanel.repaint();
		}
	}

	public void setActionButtonsVisible(boolean b) {
		btnClear.setVisible(b);
	}

	private class MultiplierButton extends POSToggleButton implements ActionListener {
		private Multiplier multiplier;

		public MultiplierButton(Multiplier multiplier) {
			this.multiplier = multiplier;
			setText(multiplier.getName());
			Integer buttonColor = multiplier.getButtonColor();
			if (buttonColor != null) {
				setBackground(new Color(buttonColor));
			}
			Integer textColor = multiplier.getTextColor();
			if (textColor != null) {
				setForeground(new Color(textColor));
			}
			setBorder(BorderFactory.createLineBorder(Color.GRAY));
			addActionListener(this);
		}

		public Multiplier getMultiplier() {
			return multiplier;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			selectedMultiplier = multiplier;
			updateAddonPrice();
		}

		private void updateAddonPrice() {
			groupPanel.getContentPane().removeAll();
//			Set<MenuAddon> addons = menuAddonGroup.getAddons();
//			for (MenuAddon menuAddon : addons) {
//				if (!menuAddon.isPizzaAddon()) {
//					continue;
//				}
//				menuAddon.setMenuItemAddonGroup(menuAddonGroup.getMenuItemAddonGroup());
//				groupPanel.getContentPane().add(new AddonButton(menuAddon, selectedMultiplier, pizzaAddonSelectionDialog.getSelectedSize()));
//			}
//                        if(addons.size() > 0) multiplierPanel.setVisible(true);
			contentPanel.repaint();
			mainPanel.repaint();
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			if (isSelected())
				setBorder(BorderFactory.createLineBorder(new Color(255, 128, 0), 1));
			else
				setBorder(BorderFactory.createLineBorder(Color.GRAY));

		}
	}

}

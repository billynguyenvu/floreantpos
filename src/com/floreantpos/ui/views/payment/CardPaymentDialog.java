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
package com.floreantpos.ui.views.payment;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

import com.floreantpos.IconFactory;
import com.floreantpos.Messages;
import com.floreantpos.main.Application;
import com.floreantpos.model.PaymentType;
import com.floreantpos.swing.PosButton;
import com.floreantpos.swing.PosUIManager;
import com.floreantpos.swing.QwertyKeyPad;
import com.floreantpos.ui.TitlePanel;
import com.floreantpos.ui.dialog.POSDialog;
import com.floreantpos.ui.dialog.POSMessageDialog;
import javax.swing.JButton;

public class CardPaymentDialog extends POSDialog implements CardInputProcessor {

    private CardInputListener cardInputListener;

    private JButton btnVisaCard;
    private JButton btnMasterCard;
    private JButton btnAmericanExpress;
    private JButton btnDiscoverCard;
    private JButton btnDebitVisaCard;

    public CardPaymentDialog(CardInputListener cardInputListener) {
        super(Application.getPosWindow(), "Quach Enterprise", true);

        this.cardInputListener = cardInputListener;

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        createUI();
    }

    private void createUI() {

        setPreferredSize(new Dimension(PosUIManager.getSize(380), PosUIManager.getSize(300)));
        btnVisaCard = new JButton();
        btnVisaCard.setIcon(IconFactory.getIcon("/ui_icons/", "" + "visa_card.png")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        btnVisaCard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setCanceled(false);
                dispose();
                cardInputListener.cardInputted(CardPaymentDialog.this, PaymentType.CREDIT_VISA);
            }
        });

        btnMasterCard = new JButton(""); //$NON-NLS-1$
        btnMasterCard.setIcon(IconFactory.getIcon("/ui_icons/", "" + "master_card.png")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        btnMasterCard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setCanceled(false);
                dispose();
                cardInputListener.cardInputted(CardPaymentDialog.this, PaymentType.CREDIT_MASTER_CARD);
            }
        });

        btnAmericanExpress = new JButton();
        btnAmericanExpress.setIcon(IconFactory.getIcon("/ui_icons/", "" + "am_ex_card.png")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        btnAmericanExpress.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setCanceled(false);
                dispose();
                cardInputListener.cardInputted(CardPaymentDialog.this, PaymentType.CREDIT_AMEX);
            }
        });

        btnDiscoverCard = new JButton();
        btnDiscoverCard.setIcon(IconFactory.getIcon("/ui_icons/", "" + "discover_card.png")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        btnDiscoverCard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setCanceled(false);
                dispose();
                cardInputListener.cardInputted(CardPaymentDialog.this, PaymentType.CREDIT_DISCOVERY);
            }
        });

        ButtonGroup group = new ButtonGroup();
        group.add(btnVisaCard);
        group.add(btnMasterCard);
        group.add(btnAmericanExpress);
        group.add(btnDiscoverCard);

        JPanel creditCardPanel = new JPanel(new GridLayout(1, 0, 10, 10));

        creditCardPanel.add(btnVisaCard);
        creditCardPanel.add(btnMasterCard);
        creditCardPanel.add(btnAmericanExpress);
        creditCardPanel.add(btnDiscoverCard);

        creditCardPanel.setBorder(new CompoundBorder(new TitledBorder(Messages.getString("PaymentTypeSelectionDialog.4")), new EmptyBorder(10, 10, 10, 10))); //$NON-NLS-1$

        JPanel debitCardPanel = new JPanel(new GridLayout(1, 0, 10, 10));

        btnDebitVisaCard = new JButton();
        btnDebitVisaCard.setIcon(IconFactory.getIcon("/ui_icons/", "" + "debit_card.png")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        btnDebitVisaCard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setCanceled(false);
                dispose();
                cardInputListener.cardInputted(CardPaymentDialog.this, PaymentType.DEBIT_CARD);
            }
        });

        group.add(btnDebitVisaCard);
        debitCardPanel.add(btnDebitVisaCard);

        debitCardPanel.setBorder(new CompoundBorder(new TitledBorder(Messages.getString("PaymentTypeSelectionDialog.6")), new EmptyBorder(10, 10, 10, 10))); //$NON-NLS-1$

//        JPanel panel = new JPanel();

//        JPanel centralPanel = new JPanel(new BorderLayout());
//        centralPanel.add(panel, BorderLayout.CENTER);

        JPanel cardPanel = new JPanel(new MigLayout("fill,hidemode 3", "[150px][fill, grow]", "")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        cardPanel.add(creditCardPanel, "wrap");
        cardPanel.add(debitCardPanel);

        getContentPane().add(cardPanel, BorderLayout.CENTER);
//        panel.setLayout(new MigLayout("", "[][grow]", "[50][grow]")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }
}

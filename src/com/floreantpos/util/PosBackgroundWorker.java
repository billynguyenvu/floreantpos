/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.floreantpos.util;

import com.floreantpos.model.Customer;
import com.floreantpos.model.Ticket;
import com.floreantpos.model.dao.CustomerDAO;
import com.floreantpos.model.dao.TicketDAO;
import java.util.Calendar;
import java.util.List;
import java.util.TimerTask;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author TuanViet
 */
public class PosBackgroundWorker extends TimerTask {

    @Override
    public void run() {
        Calendar gateDate = Calendar.getInstance();
        gateDate.add(Calendar.HOUR_OF_DAY, -12);
//        gateDate.add(Calendar.SECOND, -12);
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.DAY_OF_YEAR, -2);
        Calendar endDate = Calendar.getInstance();
        List<Ticket> tickets = TicketDAO.getInstance().findClosedTickets(startDate.getTime(), endDate.getTime());
        System.out.println("tickets size: " + tickets.size());

        // Delete all ticket's related data
        Session session = null;
        Transaction tx = null;

        try {
            session = TicketDAO.getInstance().createNewSession();
            tx = session.beginTransaction();
            for (Ticket ticket : tickets) {
                if (ticket.getCreateDate().before(gateDate.getTime()) && (ticket.getSavedCustomer() == null || !ticket.getSavedCustomer())) {
                    Integer customerId = ticket.getCustomerId();
                    if (customerId != null && customerId != 0) {
                        Customer customer = CustomerDAO.getInstance().findById(customerId);
                        if (customer != null) {
                            System.out.println("Deleting customer: " + customer.getName());
                            ticket.setCustomer(null);
                            ticket.setCustomerId(null);
                            TicketDAO.getInstance().saveOrUpdate(ticket, session);
                            CustomerDAO.getInstance().delete(customer, session);
                            System.out.println("Deleted customer: " + customer.getName());
                        }
                    }
                }
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Exception during deleting customer: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}

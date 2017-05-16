/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.floreantpos.util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;

/**
 *
 * @author TuanViet
 */
public class FloreantIdGenerator implements org.hibernate.id.IdentifierGenerator {

    @Override
    public Serializable generate(SessionImplementor si, Object o) throws HibernateException {
        int MAX_LOOP = 1000;
        int id = Math.abs(UUID.randomUUID().hashCode());
        try {
            Connection con = si.connection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select id from TICKET where id=" + id);
            boolean dup = rs.next();
            int loop = 0;
            while (dup && loop++ < MAX_LOOP) {
                id = Math.abs(UUID.randomUUID().hashCode());
                rs = stmt.executeQuery("select id from TICKET where id=" + id);
                dup = rs.next();
            }

            if (dup) {
                loop = 0;
                while (dup && loop++ < MAX_LOOP) {
                    id -= 1;
                    rs = stmt.executeQuery("select id from TICKET where id=" + id);
                    dup = rs.next();
                }
            }
            
            if (!dup) return id;
        } catch (SQLException ex) {
            System.out.println("Error occurred during generate identity, using default.");
        }
        return id - 1;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nfc.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 *
 * @author admin
 */
public class HibernateUtil {

    private static final SessionFactory readOnlyFusionSessionFactory;
    private static final SessionFactory ISOsessionFactory;

    static {
        try {
            readOnlyFusionSessionFactory = new AnnotationConfiguration().configure("readOnlyFusion.cfg.xml").buildSessionFactory();
            ISOsessionFactory = new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation Faild." + ex);
            throw new ExceptionInInitializerError();
        }
    }
    
    public static SessionFactory getROFusionSessionFactory() {
        return readOnlyFusionSessionFactory;
    }

	public static SessionFactory getIsosessionfactory() {
		return ISOsessionFactory;
	}
    
    
}

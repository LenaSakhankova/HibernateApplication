package org.example;

import org.example.model.Item;
import org.hibernate.Session;
import org.example.model.Person;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class).addAnnotatedClass(Item.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
        session.beginTransaction();

        Person person = session.get(Person.class, 3);
        List<Item> itemList = person.getItemList();
        for (Item item:itemList){
            //пораждает sql
            session.remove(item);
        }
            //не пораждает sgl
        person.getItemList().clear();//так как всё кешируется, если Hibernate не обновит данные, то в кеше останется

        session.getTransaction().commit();
        }

        finally{
        sessionFactory.close();
        }

    }


}

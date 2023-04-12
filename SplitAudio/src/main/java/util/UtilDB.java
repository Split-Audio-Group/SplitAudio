package util;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import datamodel.*;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @since JavaSE-1.8
 */
public class UtilDB {
   static SessionFactory sessionFactory = null;
   static SessionLog session = null;

   public static SessionFactory getSessionFactory() {
      if (sessionFactory != null) {
         return sessionFactory;
      }
      Configuration configuration = new Configuration().configure();
      StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
      sessionFactory = configuration.buildSessionFactory(builder.build());
      return sessionFactory;
   }
   
   public static SessionLog getSession() {
	      if (session != null) {
	         return session;
	      }
	      session = new SessionLog();
	      return session;
	   }
   
   
   public static List<User> listUsers() {
	      List<User> resultList = new ArrayList<User>();

	      Session session = getSessionFactory().openSession();
	      Transaction tx = null;

	      try {
	         tx = session.beginTransaction();
	         List<?> users = session.createQuery("FROM User").list();
	         for (Iterator<?> iterator = users.iterator(); iterator.hasNext();) {
	            User user = (User) iterator.next();
	            resultList.add(user);
	         }
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx != null)
	            tx.rollback();
	         e.printStackTrace();
	      } finally {
	         session.close();
	      }
	      return resultList;
	   }
   
   public static List<User> listUsers(String keyword) {
	      List<User> resultList = new ArrayList<User>();

	      Session session = getSessionFactory().openSession();
	      Transaction tx = null;

	      try {
	         tx = session.beginTransaction();
	         List<?> users = session.createQuery("FROM User").list();
	         for (Iterator<?> iterator = users.iterator(); iterator.hasNext();) {
	        	 User user = (User) iterator.next();
	            if (user.getName().startsWith(keyword)) {
	               resultList.add(user);
	            }
	         }
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx != null)
	            tx.rollback();
	         e.printStackTrace();
	      } finally {
	         session.close();
	      }
	      return resultList;
	   }
   
   
   public static void createUser(String userName, String displayName, String email, String password) {
	      Session session = getSessionFactory().openSession();
	      Transaction tx = null;
	      try {
	         tx = session.beginTransaction();
	         session.save(new User(userName, displayName, email, password));
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx != null)
	            tx.rollback();
	         e.printStackTrace();
	      } finally {
	         session.close();
	      }
   }
   public static List<User> logInUser(String username, String password) {
	      List<User> resultList = new ArrayList<User>();

	      Session session = getSessionFactory().openSession();
	      Transaction tx = null;

	      try {
	         tx = session.beginTransaction();
	         List<?> users = session.createQuery("FROM User").list();
	         for (Iterator<?> iterator = users.iterator(); iterator.hasNext();) {
	            User user = (User) iterator.next();
	            if ((user.getName().equals(username)||user.getEmail().equals(username))&&user.getPassword().equals(password)) {
	            		resultList.add(user);
	            }
	         }
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx != null)
	            tx.rollback();
	         e.printStackTrace();
	      } finally {
	         session.close();
	      }
	      return resultList;
	   }
   
   public static void deleteUser(int id) {
	   Session session = getSessionFactory().openSession();
	   Transaction tx = null;
	   try {
		   tx = session.beginTransaction();
		   Object user = session.load(User.class, id);
		   if(user != null) {
			   session.delete(user);
			   session.getTransaction().commit();
		   }
		   
		   
	   } catch (HibernateException e) {
		   if(tx != null)
			   tx.rollback();
		   e.printStackTrace();
	   } finally {
		   session.close();
	   }
   }
   
   public static List<Audio_Files> listFiles() {
	      List<Audio_Files> resultList = new ArrayList<Audio_Files>();

	      Session session = getSessionFactory().openSession();
	      Transaction tx = null;

	      try {
	         tx = session.beginTransaction();
	         List<?> users = session.createQuery("FROM Audio_Files").list();
	         for (Iterator<?> iterator = users.iterator(); iterator.hasNext();) {
	        	 Audio_Files file = (Audio_Files) iterator.next();
	        	 if (file.getpub()) {
		               resultList.add(file);
		         }
	         }
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx != null)
	            tx.rollback();
	         e.printStackTrace();
	      } finally {
	         session.close();
	      }
	      return resultList;
	   }

	public static List<Audio_Files> listFiles(String keyword) {
		      List<Audio_Files> resultList = new ArrayList<Audio_Files>();
	
		      Session session = getSessionFactory().openSession();
		      Transaction tx = null;
	
		      try {
		         tx = session.beginTransaction();
		         List<?> users = session.createQuery("FROM Audio_Files").list();
		         for (Iterator<?> iterator = users.iterator(); iterator.hasNext();) {
		        	 Audio_Files file = (Audio_Files) iterator.next();
		            if (file.getName().startsWith(keyword)) {
		               resultList.add(file);
		            }
		         }
		         tx.commit();
		      } catch (HibernateException e) {
		         if (tx != null)
		            tx.rollback();
		         e.printStackTrace();
		      } finally {
		         session.close();
		      }
		      return resultList;
		   }
	public static List<Audio_Files> listFilesByUser(User user) {
	      List<Audio_Files> resultList = new ArrayList<Audio_Files>();

	      Session session = getSessionFactory().openSession();
	      Transaction tx = null;

	      try {
	         tx = session.beginTransaction();
	         List<?> messages = session.createQuery("FROM Messages").list();
	         for (Iterator<?> iterator = messages.iterator(); iterator.hasNext();) {
	        	 Audio_Files file = (Audio_Files) iterator.next();
	            if (file.getuid().equals(user.getId())||file.getpub()){
	               resultList.add(file);
	            }
	         }
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx != null)
	            tx.rollback();
	         e.printStackTrace();
	      } finally {
	         session.close();
	      }
	      return resultList;
	   }
	
	
	public static void createFile(Integer uid, String name, String path, Double size, Boolean pub) {
		      Session session = getSessionFactory().openSession();
		      Transaction tx = null;
		      try {
		         tx = session.beginTransaction();
		         session.save(new Audio_Files(uid, name, path, size, pub));
		         tx.commit();
		      } catch (HibernateException e) {
		         if (tx != null)
		            tx.rollback();
		         e.printStackTrace();
		      } finally {
		         session.close();
		      }
	}
	
	public static void deleteFile(int id) {
		   Session session = getSessionFactory().openSession();
		   Transaction tx = null;
		   try {
			   tx = session.beginTransaction();
			   Object user = session.load(Audio_Files.class, id);
			   if(user != null) {
				   session.delete(user);
				   session.getTransaction().commit();
			   }
			   
			   
		   } catch (HibernateException e) {
			   if(tx != null)
				   tx.rollback();
			   e.printStackTrace();
		   } finally {
			   session.close();
		   }
	}

	public static String userNameById(Integer userId) {
		// TODO Auto-generated method stub
		String userName = "";
	      Session session = getSessionFactory().openSession();
	      Transaction tx = null;

	      try {
	         tx = session.beginTransaction();
	         List<?> users = session.createQuery("FROM Users").list();
	         for (Iterator<?> iterator = users.iterator(); iterator.hasNext();) {
	            User user = (User) iterator.next();
	            if (user.getId().equals(userId)){
	               userName = user.getName();
	            }
	         }
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx != null)
	            tx.rollback();
	         e.printStackTrace();
	      } finally {
	         session.close();
	      }
	      return userName;
	}
   
}
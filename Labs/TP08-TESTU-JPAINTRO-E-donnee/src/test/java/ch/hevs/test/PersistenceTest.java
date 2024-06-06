package ch.hevs.test;

import org.junit.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class PersistenceTest {

	@Test
	public void test() {
		
		EntityTransaction tx = null;
		
		try {
			
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("bankPU_unitTest");
			EntityManager em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			// To complete ...

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				tx.rollback();
			} catch (IllegalStateException e1) {
				e1.printStackTrace();
			} catch (SecurityException e1) {
				e1.printStackTrace();
			}
		}

	}

}

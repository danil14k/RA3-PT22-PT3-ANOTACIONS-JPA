package es.ilerna.M0486.ra3.pt22.pt3.anotacions.jpa.main;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;

import es.ilerna.M0486.ra3.pt22.pt3.anotacions.jpa.domain.Person;
import es.ilerna.M0486.ra3.pt22.pt3.anotacions.jpa.domain.Student;

// CLASE - Main - Punto de entrada principal de la aplicación
// KEYWORD: HIBERNATE_SESSION, TRANSACTION_MANAGEMENT, MAIN_METHOD
public class Main {

	// MÉTODO - main - Método principal que abre una sesión de Hibernate
	// KEYWORD: SESSION, TRANSACTION, TRY_CATCH_FINALLY, RESOURCE_MANAGEMENT
	public static void main(String[] args) {

		// VARIABLE - session - Sesión de Hibernate para operaciones con BD KEYWORD: DATABASE_SESSION
		Session session = null;
		// VARIABLE - transaction - Transacción de Hibernate para control de cambios
		Transaction transaction = null;
		// VARIABLE - sc - Scanner para entrada de usuario
		Scanner sc = new Scanner(System.in);

		try {
			// PASO 1: Obtener sesión del SessionFactory KEYWORD: SESSION_CREATION
			session = HibernateSession.getSessionFactory().openSession();
			// PASO 2: Iniciar transacción KEYWORD: TRANSACTION_BEGIN
			transaction = session.beginTransaction();

			// TODO: Aquí iría la lógica de negocio con BD

		} catch (Exception ex) {
			// MANEJO DE EXCEPCIONES: Si hay error, hacer rollback
			if (transaction != null) {
				transaction.rollback(); // KEYWORD: ROLLBACK
			}

			// VALIDACIÓN: Mostrar traza de error
			ex.printStackTrace();
		} finally {
			// LIMPIEZA - Cerrar sesión y recursos KEYWORD: CLEANUP, RESOURCE_CLOSING
			if (session != null) {
				session.close();
			}

			// Cerrar SessionFactory y Scanner KEYWORD: SHUTDOWN
			HibernateSession.getSessionFactory().close();
			sc.close();
		}
	}
}

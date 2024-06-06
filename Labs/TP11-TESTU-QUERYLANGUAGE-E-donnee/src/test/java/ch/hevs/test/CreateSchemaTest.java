package ch.hevs.test;

import java.util.EnumSet;

import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;
import org.junit.Test;

import ch.hevs.businessobject.Account;
import ch.hevs.businessobject.Address;
import ch.hevs.businessobject.Agency;
import ch.hevs.businessobject.Banker;
import ch.hevs.businessobject.Client;
import ch.hevs.businessobject.ExternalAccount;
import ch.hevs.businessobject.InternalAccount;
import ch.hevs.businessobject.Operation;
import ch.hevs.businessobject.Person;
import junit.framework.TestCase;

public class CreateSchemaTest extends TestCase {

	@Test
	public void test() {

		MetadataSources metadataSources = new MetadataSources(
			    new StandardServiceRegistryBuilder()
		        .applySetting("hibernate.dialect", "org.hibernate.dialect.HSQLDialect")
		        .applySetting("hibernate.connection.driver_class", "org.hsqldb.jdbcDriver")
		        .applySetting("hibernate.connection.url", "jdbc:hsqldb:hsql://localhost/DB")
		        .applySetting("hibernate.connection.username", "sa")
		        .applySetting("hibernate.connection.password", "")
		        .build());
		metadataSources.addAnnotatedClass(Account.class);
		metadataSources.addAnnotatedClass(Address.class);
		metadataSources.addAnnotatedClass(Client.class);
		metadataSources.addAnnotatedClass(Person.class);
		metadataSources.addAnnotatedClass(InternalAccount.class);
		metadataSources.addAnnotatedClass(ExternalAccount.class);
		metadataSources.addAnnotatedClass(Operation.class);
		metadataSources.addAnnotatedClass(Agency.class);
		metadataSources.addAnnotatedClass(Banker.class);
		Metadata metadata = metadataSources.buildMetadata();

		SchemaExport schemaExport = new SchemaExport();
		schemaExport.setFormat(true);
		schemaExport.setOutputFile("schema.ddl");
		schemaExport.create(EnumSet.of(TargetType.DATABASE), metadata);
	}
}

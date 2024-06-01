# Spring Transaction Propagation Types

This repository demonstrates the use of different transaction propagation types in a Spring Boot application. It includes examples of various transaction propagation behaviors using a simple `Record` entity and corresponding repository, service, and controller layers.

## Prerequisites

- Java 17 or higher
- Maven
- Spring Boot 3.3 or higher

## Dependencies

Ensure you have the following dependencies in your `pom.xml`:

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>
    
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
    
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
    
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
        <version>RELEASE</version>
        <scope>compile</scope>
    </dependency>
 </dependencies>
```
## Database Configuration

Configure the H2 database in `src/main/resources/application.properties`:

```properties
spring.application.name=transaction-test
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.datasource.url = jdbc:h2:mem:testdb
```

## Testing the Application

1. Start the application using your preferred method (e.g., `mvn spring-boot:run`).
2. Open your browser and navigate to:
   - `http://localhost:8080/api/testRequired` to test for Propagation.REQUIRED transaction.
   - `http://localhost:8080/api/testRequiresNew` to test for Propagation.REQUIRES_NEW transaction.
   - `http://localhost:8080/api/testNested` to test for Propagation.NESTED transaction.
   - `http://localhost:8080/api/testSupports` to test with an existing transaction for Propagation.SUPPORTS.
   - `http://localhost:8080/api/testSupportsNoTransaction` to test without an existing transaction for Propagation.SUPPORTS.
   - `http://localhost:8080/api/testNotSupportedWithTransaction` to test with an existing transaction for Propagation.NOT_SUPPORTED.
   - `http://localhost:8080/api/testNotSupportedNoTransaction` to test without an existing transaction for Propagation.NOT_SUPPORTED.
   - `http://localhost:8080/api/testNeverWithTransaction` to test with an existing transaction for Propagation.NEVER.
   - `http://localhost:8080/api/testNeverNoTransaction` to test without an existing transaction for Propagation.NEVER.
   - `http://localhost:8080/api/testMandatoryWithTransaction` to test with an existing transaction for Propagation.MANDATORY.
   - `http://localhost:8080/api/testMandatoryNoTransaction` to test without an existing transaction for Propagation.MANDATORY.
3. Use the H2 console at `http://localhost:8080/h2-console` to inspect the `Record` table. Login with JDBC URL `jdbc:h2:mem:testdb`

### Example Queries

To view the records in the `Record` table, execute the following query in the H2 console:

```sql
SELECT * FROM RECORD;
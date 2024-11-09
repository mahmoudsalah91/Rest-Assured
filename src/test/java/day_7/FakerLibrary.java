package day_7;

import com.github.javafaker.Faker;
import org.testng.annotations.Test;

/*
java faker library :- library in java use to generate faker data to use ex: in automation script
-for use  >> https://github.com/DiUS/java-faker
-need to put discrepancy of  Java Faker in POM.xml file
-create object from faker >> Faker faker = new Faker();
then use it
-String name = faker.name().fullName(); // Miss Samanta Schmidt
-String firstName = faker.name().firstName(); // Emory
-String lastName = faker.name().lastName(); // Barton

String streetAddress = faker.address().streetAddress(); // 60018 Sawayn Brooks Suite 449
 */
public class FakerLibrary {
    @Test
       void fakerData() {
        Faker faker = new Faker();
        String fullName = faker.name().fullName();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String userName = faker.name().username();
        String password = faker.internet().password();
        String phone = faker.phoneNumber().cellPhone();
        String emil = faker.internet().safeEmailAddress();



        System.out.println(fullName);
        System.out.println(firstName);
        System.out.println(lastName);
        System.out.println(userName);
        System.out.println(password);
        System.out.println(phone);
        System.out.println(emil);

    }

}

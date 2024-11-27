package framework;

import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class DataGenerators {

    Faker fake = new Faker();

    /**
     *
     * @return first name without apostrophe symbol
     */
    public String fakeFirstName() {
        return fake.name().firstName().replace("'", "");
    }

    /**
     *
     * @return last name without apostrophe symbol
     */
    public String fakeLastName() {
        return fake.name().lastName().replace("'", "");
    }

    /**
     *
     * @return fake email
     */
    public String fakeEmail() {
        return fake.internet().emailAddress();
    }

    /**
     *
     * @param localPart
     * @return local part of email address
     */
    public String fakeEmail(String localPart) {
        return fake.internet().safeEmailAddress(localPart);
    }

    /**
     *
     * @return fake full name
     */
    public String fakeFullName() {
        return fake.name().fullName();
    }

    /**
     *
     * @return street address
     */
    public String fakeStreetAddress() {
        return fake.address().streetAddress();
    }

    /**
     *
     * @return fake city name
     */
    public String fakeCity() {
        return fake.address().city();
    }

    /**
     *
     * @return random zip code (!might not be real)
     */
    public String fakeZipCode() {
        return fake.numerify("#####");
    }

    /**
     *
     * @return random boolean
     */
    public boolean fakeBoolean() {
        return fake.bool().bool();
    }

    /**
     *
     * @return
     */
    public String fakeSymbols(int count) {
        return RandomStringUtils.random(count, true, true);
    }

    /**
     *
     * @param lettersCount str length
     * @return random String
     */
    public String fakeFixedString(int lettersCount) {
        return fake.lorem().characters(lettersCount);
    }

    /**
     *
     * @return random word
     */
    public String fakeRandomWord() {
        return fake.lorem().word();
    }

    /**
     *
     * @return date of birth
     */
    public String fakeDateOfBirth() {
        return fake.date().past(15000, TimeUnit.DAYS).toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime().format(DateTimeFormatter.ofPattern("MM-dd-yyyy'T'HH:mm:ss.SSS[xxx][xx][X]"));
    }

    /**
     *
     * @param from 0
     * @param to MAX Int
     * @return random Int
     */
    public int fakeRandomIntFromTo(int from, int to) {
        return fake.number().numberBetween(from, to);
    }

    /**
     *
     * @return fake id in UUID format
     */
    public String fakeUuid() {
        return fake.internet().uuid().toUpperCase();
    }

}

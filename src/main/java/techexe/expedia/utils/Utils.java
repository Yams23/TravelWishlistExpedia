package techexe.expedia.utils;

import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;

import java.util.Random;
import java.util.UUID;

/**
 *  Class to store utility methods for this framework
 */
public class Utils {

    /**
     * Gets unique id.
     *
     * @return the unique id
     */
    public static String getUniqueId() {
        return UUID.randomUUID().toString();
    }

    /**
     * Gets random alpha numeric string.
     *
     * @param length
     * @return  random string
     */
    public static String getRandomString(int length) {
        RandomStringGenerator randomStringGenerator =
                new RandomStringGenerator.Builder()
                        .withinRange('0', 'z')
                        .filteredBy(CharacterPredicates.LETTERS, CharacterPredicates.DIGITS)
                        .build();
        return randomStringGenerator.generate(length);
    }

    /**
     * Gets randon email id.
     *
     * @return the randon email id
     */
    public static String getRandonEmailId() {
        return "user_" + getRandomString(3) + "@" + getRandomString(3) + ".com";
    }

    /**
     * Get random integer within given bound.
     *
     * @param bound
     * @return the integer
     */
    public static Integer getRandomNo(int bound){
        Random random = new Random();
        return random.nextInt(bound);


    }
}

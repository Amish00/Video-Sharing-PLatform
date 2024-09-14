import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;
import java.util.Random;

public class ValidationTest {

    // Method to validate email
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(email).matches();
    }

    // Method to validate password
    private boolean isValidPassword(String password) {
        return password.length() >= 8 && password.matches(".*[A-Za-z].*") && password.matches(".*[0-9].*");
    }

    // Generate random email
    private String generateRandomEmail() {
        String[] domains = {"example.com", "test.com", "email.org"};
        String username = generateRandomString(8);
        String domain = domains[new Random().nextInt(domains.length)];
        return username + "@" + domain;
    }

    // Generate random password with letters and digits
    private String generateRandomPassword() {
        return generateRandomString(5) + new Random().nextInt(10000); // 5 chars + 4 digits
    }

    // Helper method to generate random strings
    private String generateRandomString(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(rand.nextInt(chars.length())));
        }
        return sb.toString();
    }

    @Test
    public void testValidEmails() {
        assertTrue(isValidEmail("test@example.com"));
        assertTrue(isValidEmail("user.name@example.co.uk"));
        assertTrue(isValidEmail("user+123@example.com"));

        // Test with random valid email
        String randomEmail = generateRandomEmail();
        assertTrue(isValidEmail(randomEmail), "Random email: " + randomEmail);
    }

    @Test
    public void testInvalidEmails() {
        assertFalse(isValidEmail("invalid-email"));
        assertFalse(isValidEmail("user@.com"));
        assertFalse(isValidEmail("user@com"));
        assertFalse(isValidEmail("user@domain.c"));

        // Test with random invalid email
        String randomInvalidEmail = generateRandomString(5);  // No domain part
        assertFalse(isValidEmail(randomInvalidEmail), "Random invalid email: " + randomInvalidEmail);
    }

    @Test
    public void testValidPasswords() {
        assertTrue(isValidPassword("Password1"));
        assertTrue(isValidPassword("Test1234"));
        assertTrue(isValidPassword("SecurePass99"));

        // Test with random valid password
        String randomPassword = generateRandomPassword();
        assertTrue(isValidPassword(randomPassword), "Random password: " + randomPassword);
    }

    @Test
    public void testInvalidPasswords() {
        assertFalse(isValidPassword("short1"));  // Too short
        assertFalse(isValidPassword("NoDigitsHere"));  // No digits
        assertFalse(isValidPassword("12345678"));  // No letters
        assertFalse(isValidPassword("!@#$%^&"));  // Only special characters

        // Test with random invalid password (less than 8 chars)
        String randomInvalidPassword = generateRandomString(6);  // Too short
        assertFalse(isValidPassword(randomInvalidPassword), "Random invalid password: " + randomInvalidPassword);
    }
}

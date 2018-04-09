package com.nearshoretechnology.focalpoint.common.util;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptorUtil {

  @Autowired
  private BCryptPasswordEncoder cryptPasswordEncoder;

  private static EncryptorUtil sharedInstance;

  private EncryptorUtil() {

    if (cryptPasswordEncoder == null)
      cryptPasswordEncoder = new BCryptPasswordEncoder();
  }

  public static EncryptorUtil getSharedInstance() {

    if (sharedInstance == null)
      sharedInstance = new EncryptorUtil();

    return sharedInstance;
  }

  public String encrypt(String inputString) {

    return cryptPasswordEncoder.encode(inputString);
  }

  public boolean match(String rawPassword, String encodedPassword) {

    return cryptPasswordEncoder.matches(rawPassword, encodedPassword);
  }
  
  /**
   * Generate a random String suitable for use as a
   * temporary password.
   *
   * @return String suitable for use as a temporary password
   * @since 2.4
   * @see <a
   *      href="http://www.java2s.com/Code/Java/Security/GeneratearandomStringsuitableforuseasatemporarypassword.htm">Use
   *      as a temporary password</a>
   */
  public static String generateRandomPassword() {

    // Pick from some letters that won't be easily mistaken
    // for each other. So, for example, omit o O and 0, 1 l and L.
    String letters = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ23456789+@<>=";
    Random random = new SecureRandom();

    String pw = "";
    for (int i = 0; i < PASSWORD_LENGTH; i++) {
      int index = (int) (random.nextDouble() * letters.length());
      pw += letters.substring(index, index + 1);
    }
    
    return pw;
  }
  
  public static final int PASSWORD_LENGTH = 8;
}

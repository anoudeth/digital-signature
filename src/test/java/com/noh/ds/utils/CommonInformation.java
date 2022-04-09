package com.noh.ds.utils;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Component;

@Component
public class CommonInformation {
    public CommonInformation() {
    }

    public String currentDate() {
        String date = null;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
        date = dtf.format(now).toString();
        return date.toUpperCase();
    }

    public String currentDateTime() {
        String date = null;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
        date = dtf.format(now).toString();
        return date.toUpperCase();
    }

    public String getCustomDate(String datestring) {
        DateFormat parser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        Date convertedDate = null;

        try {
            convertedDate = parser.parse(datestring);
        } catch (Exception var6) {
            System.out.println("==> Exception while converting date : " + var6.getMessage());
            return datestring;
        }

        String output = formatter.format(convertedDate);
        System.out.println(datestring + "=> " + output);
        return output.toUpperCase();
    }

    public String generateProfileID() {
        String date = null;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyyhhmmss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
        date = dtf.format(now).toString();
        return "PR" + date.toUpperCase();
    }

    public String generateMenuID() {
        String date = null;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyyhhmmss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
        date = dtf.format(now).toString();
        return "PFM" + date.toUpperCase();
    }

    public String getDDMMYYYYHHMMSS() {
        String date = null;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyyhhmmss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
        date = dtf.format(now).toString();
        return date.toUpperCase();
    }

    public String generateUserID() {
        String date = null;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyyhhmmss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
        date = dtf.format(now).toString();
        return "USR" + date.toUpperCase();
    }

    public String generateMerchantID() {
        String date = null;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyyhhmmss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
        date = dtf.format(now).toString();
        return "MER" + date.toUpperCase();
    }

    public String generateAuditID() {
        String date = null;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyyhhmmss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
        date = dtf.format(now).toString();
        return "ADT" + date.toUpperCase();
    }

    public String generateLimitID() {
        String date = null;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyyhhmmss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
        date = dtf.format(now).toString();
        return "LMT" + date.toUpperCase();
    }

    public String generateID() {
        String date = null;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyyhhmmss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
        date = dtf.format(now).toString();
        return date.toUpperCase();
    }

    public String generateTempPass() {
        String pass = null;
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        SecureRandom rnd = new SecureRandom();

        while(salt.length() < 8) {
            int index = (int)(rnd.nextFloat() * (float)SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }

        pass = salt.toString();
        System.out.println("Generate Pass: " + pass);
        return pass;
    }

    public static void main(String[] args) {
        System.out.println((new CommonInformation()).generateSaltKey());
        System.out.println((new CommonInformation()).generateCheckSum());
        System.out.println((new CommonInformation()).generateCheckSum());
    }

    public String generateSaltKey() {
        String pass = null;
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        SecureRandom rnd = new SecureRandom();

        while(salt.length() < 32) {
            int index = (int)(rnd.nextFloat() * (float)SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }

        pass = salt.toString();
        System.out.println("Salt  Pass: " + pass);
        return pass;
    }

    /**
     * generate key and send to merchant
     * @return
     */
    public String generateCheckSum() {
        KeyGenerator gen = null;

        try {
            gen = KeyGenerator.getInstance("AES");
        } catch (NoSuchAlgorithmException var5) {
            var5.printStackTrace();
        }

        gen.init(128);
        SecretKey secret = gen.generateKey();
        byte[] binary = secret.getEncoded();
        String text = String.format("%02X", new BigInteger(1, binary));
        return text.substring(0, 32);
    }

    private static byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }

    public static String generateHashedSalt(String passwordToHash) throws NoSuchAlgorithmException {
        byte[] salt = getSalt();
        System.out.println("Salt Key: " + salt);
        String securePassword = get_SHA_1_SecurePassword(passwordToHash, salt);
        return securePassword;
    }

    private static String get_SHA_1_SecurePassword(String passwordToHash, byte[] salt) {
        String generatedPassword = null;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();

            for(int i = 0; i < bytes.length; ++i) {
                sb.append(Integer.toString((bytes[i] & 255) + 256, 16).substring(1));
            }

            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException var7) {
            var7.printStackTrace();
        }

        return generatedPassword;
    }

    public String removeZero(String str) {
        System.out.println("Amount : " + str);
        int i = 0;

        byte isNonZero;
        for(isNonZero = 0; i < str.length(); ++i) {
            if (str.charAt(i) != '0') {
                isNonZero = 1;
                break;
            }

            isNonZero = 0;
        }

        StringBuffer sb = new StringBuffer(str);
        if (isNonZero >= 1) {
            sb.replace(0, i, "");
            BigInteger d = new BigInteger(sb.toString());
            BigDecimal two = new BigDecimal(d, 2);
            System.out.println("Truncated Amount : " + two.toString());
            return two.toString();
        } else {
            return str;
        }
    }

    public String formatNumber(String number) {
        System.out.println("number : " + number);
        double amount = Double.parseDouble(number);
        DecimalFormat formatter = new DecimalFormat("#,###.00");
        System.out.println("Formatted: " + formatter.format(amount));
        return formatter.format(amount);
    }

    public String generateToken(Integer length) {
        String token = null;
        String allowedChars = "1234567890";
        StringBuilder salt = new StringBuilder();
        SecureRandom rnd = new SecureRandom();

        while(salt.length() < length) {
            int index = (int)(rnd.nextFloat() * (float)allowedChars.length());
            salt.append(allowedChars.charAt(index));
        }

        token = salt.toString();
        System.out.println("Generate token: " + token);
        return token;
    }

    public String generateReferenceId() {
        String pass = null;
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        SecureRandom rnd = new SecureRandom();

        while(salt.length() < 10) {
            int index = (int)(rnd.nextFloat() * (float)SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }

        pass = salt.toString();
        System.out.println("Generate Pass: " + pass);
        return pass;
    }

    public String generateRandomString(Integer length) {
        String pass = null;
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        SecureRandom rnd = new SecureRandom();

        while(salt.length() < length) {
            int index = (int)(rnd.nextFloat() * (float)SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }

        pass = salt.toString();
        System.out.println("Generate String: " + pass);
        return pass;
    }

    public String getCurrencyText(String currency) {
        String cur = "LAK";
        if (!currency.equals("LAK") && !currency.equals("418")) {
            if (!currency.equals("THB") && !currency.equals("764")) {
                if (!currency.equals("USD") && !currency.equals("840")) {
                    return currency;
                } else {
                    cur = "USD";
                    return cur;
                }
            } else {
                cur = "THB";
                return cur;
            }
        } else {
            cur = "LAK";
            return cur;
        }
    }

    public String getCurrencyCode(String currency) {
        String cur = "418";
        if (!currency.equals("LAK") && !currency.equals("418")) {
            if (!currency.equals("THB") && !currency.equals("764")) {
                if (!currency.equals("USD") && !currency.equals("840")) {
                    return currency;
                } else {
                    cur = "840";
                    return cur;
                }
            } else {
                cur = "764";
                return cur;
            }
        } else {
            cur = "418";
            return cur;
        }
    }

    public String getTxnType(String code) {
        if (code == null && code == "") {
            return "NA";
        } else if (!code.equals("CRD") && code != "CRD") {
            if (!code.equals("IB_WLT") && code != "IB_WLT") {
                if (!code.equals("LMM_WLT") && code != "LMM_WLT") {
                    return !code.equals("ACCT") && code != "ACCT" ? code : "ACCT Txn";
                } else {
                    return "LMM Txn";
                }
            } else {
                return "IB WLT Txn";
            }
        } else {
            return "Card Txn";
        }
    }

    public static boolean isPasswordExpired(String lastPasswordChangedDate) {
        Date today = new Date(System.currentTimeMillis());
        System.out.println("Current Day " + today);

        try {
            Date lastPasswordChanged = (new SimpleDateFormat("yyyy-MM-dd")).parse(lastPasswordChangedDate);
            System.out.println("Last password changed Date " + lastPasswordChanged);
            Calendar lastpasscal = GregorianCalendar.getInstance();
            lastpasscal.setTime(lastPasswordChanged);
            lastpasscal.add(5, 90);
            System.out.println("Password Expire Date " + lastpasscal.getTime());
            if (today.after(lastpasscal.getTime())) {
                System.out.println("password expired true");
                return true;
            }
        } catch (ParseException var4) {
            System.out.println(var4.getMessage());
            return false;
        }

        System.out.println("password expired false");
        return false;
    }
}
package vn.siten.base.common.util;

import javax.xml.bind.DatatypeConverter;
import java.time.format.DateTimeParseException;

public class PrimitiveUtils {
    public static boolean isInteger(Object object) {
        try {
            String number = object == null ? null : object.toString();
            Integer.parseInt(number);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public static boolean isDouble(Object object) {
        try {
            String number = object == null ? null : object.toString();
            Double.parseDouble(number);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public static boolean isLong(Object object) {
        try {
            String number = object == null ? null : object.toString();
            Long.parseLong(number);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public static boolean isDate(Object object) {
        try {
            DatatypeConverter.parseDateTime(object.toString());
            return true;
        } catch (DateTimeParseException ex) {
            return false;
        }
    }

    public static Integer parseIntSilently(String intStr) {
        try {
            return Integer.valueOf(intStr);
        } catch (Exception e) {
            return null;
        }
    }

    public static Long parseLongSilently(String intStr) {
        try {
            return Long.valueOf(intStr);
        } catch (Exception e) {
            return null;
        }
    }

    public static Double parseDoubleSilently(String intStr) {
        try {
            return Double.valueOf(intStr);
        } catch (Exception e) {
            return null;
        }
    }
}

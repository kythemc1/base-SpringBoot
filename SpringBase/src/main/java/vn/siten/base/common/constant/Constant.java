package vn.siten.base.common.constant;

public class Constant {
    public static final String LOGIN_URL = "/cms/login";
    public static final String [] DATE_FORMATS = new String[] {"dd-MM-yy", "dd-MM-yyyy", "MM-dd-yyyy", "yyyy-MM-dd",
            "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss.SSS", "yyyy-MM-dd HH:mm:ss.SSSZ", "yyyy-MM-ddTHH:mm:ss.SSSZ", "EEEEE MMMMM yyyy HH:mm:ss.SSSZ"};
    public static final String AUTH_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_SECRET = "secret";
    public static final long TOKEN_EXPIRATION_TIME = 7*24*60*60*1000;

    public static final String[] PUBLIC_URLs = new String[]{
            "/", LOGIN_URL
    };
}

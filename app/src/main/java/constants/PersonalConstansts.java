package constants;

/**
 * Created by Constantine on 2016/4/20.
 */
public class PersonalConstansts {

    public static String AUTYPE = "0";
    public static String userName = "0";
    public static String token = "0";
    public static String identityType = "0";
    public static final String FILENAME = "myfile";
    public static final String REMEBERACCOUNT = "myfile";
    public static final String ACCOUNTKEY = "account";
    public static final String USERNAMEKEY = "username";
    public static final String PASSWORDKEY = "password";
    public static final String TOKENKEY = "token";

    public static final int PAGESIZE = 5;
    public static int versionCode;
    public static String versionName;

    public static final String YYT = "yyt";
    public static final String OA = "oa";
    public static final String DW = "dw";

    public static boolean isReporter(){
        return AUTYPE.equals(YYT) || AUTYPE.equals(OA);
    }

    public static boolean isWorker(){
        return AUTYPE.equals(DW);
    }

}

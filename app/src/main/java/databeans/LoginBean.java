package databeans;

/**
 * Created by Constantine on 2016/5/25.
 */
public class LoginBean {

    public String message;
    public int code;
    public DatumBean datum;

    public static class DatumBean {

        public String username;
        public String token;
        public String type;
    }
}

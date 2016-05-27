package databeans;

/**
 * Created by Constantine on 2016/5/27.
 */
public class FlagCountBean {

    public String message;
    public int code;
    public DatumBean datum;

    public static class DatumBean {

        public int repiring;
        public int toRepir;
        public int repired;
    }
}

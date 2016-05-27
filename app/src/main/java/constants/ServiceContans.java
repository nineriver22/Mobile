package constants;

/**
 * Created by Constantine on 2016/5/25.
 */
public class ServiceContans {

    public static final String baseURL = "http://10.120.147.119:80";

    public static final String testToken = baseURL + "/api/test?";
    public static final String checkToken = baseURL + "/api/signIn?";
    public static final String getCode = baseURL + "/api/login/getCode?";
    public static final String login = baseURL + "/api/login/check?";
    public static final String getFlagCount = baseURL + "/api/index/count?";
    public static final String searchBills = baseURL + "/api/maintain/getByCode?";
    public static final String todoBills = baseURL + "/api/maintain/getTodo?";
    public static final String doneBills = baseURL + "/api/maintain/getDone?";
}

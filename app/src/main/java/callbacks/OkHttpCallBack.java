package callbacks;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by cii on 2016/3/25.
 */
public abstract class OkHttpCallBack {

    public abstract void onError(Call call, Exception e);

    public abstract void onResponse(Object response);

    public String parseNetworkResponse(Response response) throws Exception {
        return response.body().string();
    }

}

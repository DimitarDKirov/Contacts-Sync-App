package teamwork.contacts_sync_app.views.dataServices;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import io.reactivex.Observable;
import mitko.data.Constants;
import mitko.data.HttpDataTransfer;
import mitko.data.contracts.IHttpDataTransfer;
import mitko.data.models.ServerResponse;
import mitko.data.models.UserDb;

/**
 * Created by dimki on 03.03.2017 г..
 */

public class UserDataService {
    private final Context context;
    public String UserUrl;
    private IHttpDataTransfer<UserDb> httpdata;

    public UserDataService(IHttpDataTransfer<UserDb> httpdata, Context context) {
        this.context = context;
        this.httpdata = httpdata;
    }

    public UserDataService(Context context) {
        this.UserUrl = Constants.serverUrl + "/user/";
        this.httpdata = new HttpDataTransfer<UserDb>(this.UserUrl, UserDb.class, UserDb[].class);
        this.context = context;
    }

    public Observable<Boolean> register(String username, String password) {
        UserDb user = new UserDb(username, password, null);
        this.httpdata.setUrl(this.UserUrl + "register");
        return this.httpdata.post(user)
                .switchMap(userDbServerResponse -> {
                    if (userDbServerResponse.getSuccess()) {
                        this.httpdata.setUrl(this.UserUrl + "login");
                        return this.login(username, password);
                    }

                    return Observable.just(userDbServerResponse.getSuccess());
                });
    }

    public Observable<Boolean> login(String username, String password) {
        UserDb user = new UserDb(username, password, null);
        this.httpdata.setUrl(this.UserUrl + "login");
        return this.httpdata.post(user)
                .map(userDbServerResponse -> {
                    this.saveToken(userDbServerResponse.getToken());
                    return userDbServerResponse.getSuccess();
                });
    }

    private void saveToken(String token) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(Constants.TOKEN_KEY, token);
        editor.commit();
    }
}

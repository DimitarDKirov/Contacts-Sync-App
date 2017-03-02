package mitko.data.dataServices;

import android.provider.SyncStateContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mitko.data.Constants;
import mitko.data.HttpTransfer;
import mitko.data.contracts.IHttpDataTransfer;
import mitko.data.models.ContactDb;
import mitko.data.models.ServerResponse;

/**
 * Created by dimki on 02.03.2017 г..
 */

public class ContactDataService {
    private static final String contactUrl = "/api/contact";
    private IHttpDataTransfer<ContactDb> httpData;

    public ContactDataService() {
        String url = Constants.serverUrl + contactUrl;
        String token = "eyJhbGciOiJIUzI1NiJ9.NThiMzMxNDc3YzMzYTA0YzQ0NjQyMWM0.ueJM7-qohXPPczXg_J5qDKb-gLZSZXXMR8zYf4vFN-g";
        this.httpData = new HttpTransfer<ContactDb>(url, token, ContactDb.class, ContactDb[].class);
    }

    public Observable<ContactDb[]> getContacts() {
        return this.httpData.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}

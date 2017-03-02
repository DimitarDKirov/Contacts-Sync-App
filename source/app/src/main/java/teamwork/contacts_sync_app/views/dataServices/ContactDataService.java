package teamwork.contacts_sync_app.views.dataServices;

import android.provider.SyncStateContract;

import java.util.Arrays;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import mitko.data.Constants;
import mitko.data.HttpTransfer;
import mitko.data.contracts.IHttpDataTransfer;
import mitko.data.models.ContactDb;
import mitko.data.models.ServerResponse;
import teamwork.contacts_sync_app.views.models.Contact;

/**
 * Created by dimki on 02.03.2017 г..
 */

public class ContactDataService {
    private static final String contactUrl = "/api/contact";
    private IHttpDataTransfer<ContactDb> httpData;

    public ContactDataService() {
        String url = Constants.serverUrl + contactUrl;
        String token = "eyJhbGciOiJIUzI1NiJ9.NThiMDM2MTYyYzI5Y2IxMWNjMzlmMGQz.e1COer6A7GofTffAl1gZpzGEzVUR5JcqPtBjQ_aEhjs";
        this.httpData = new HttpTransfer<ContactDb>(url, token, ContactDb.class, ContactDb[].class);
    }

    public Observable<Contact[]> getContacts() {
        return this.httpData.getAll()
                .map(contactDbs -> {
                    Contact[] contacts = new Contact[contactDbs.length];
                    for (int i = 0; i < contactDbs.length; i++) {
                        contacts[i] = new Contact(contactDbs[i].getName(), contactDbs[i].getPhoneNumber(), contactDbs[i].getCompany());
                    }

                    return contacts;
                });
    }

    public Observable<Contact> getContactById(String id) {
        return this.httpData.getById(id)
                .map(contactDb -> new Contact(contactDb.getName(), contactDb.getPhoneNumber(), contactDb.getCompany()));
    }

    public Observable<Boolean> createContacts(Contact[] contacts) {
        ContactDb[] contactsDb = new ContactDb[contacts.length];
        for (int i = 0; i < contacts.length; i++) {
            contactsDb[i] = new ContactDb(contacts[i].getName(), contacts[i].getNumber(), contacts[i].getCompany());
        }

        return this.httpData.add(contactsDb)
                .map(contactDbServerResponse -> contactDbServerResponse.getSuccess() == true);
    }

    public Observable<Contact> updateContact(String id, Contact contact) {
        ContactDb contactDb = new ContactDb(contact.getName(), contact.getNumber(), contact.getCompany());
        return this.httpData.update(id, contactDb)
                .map(contactDbServerResponse -> {
                    ContactDb responseContact = contactDbServerResponse.getObject();
                    return new Contact(responseContact.getName(), responseContact.getPhoneNumber(), responseContact.getCompany());
                });
    }

    public Observable<Boolean> deleteContact(String id) {
        return this.httpData.delete(id)
                .map(contactDbServerResponse -> contactDbServerResponse.getSuccess() == true);
    }
}

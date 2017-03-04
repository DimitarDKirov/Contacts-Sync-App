package teamwork.contacts_sync_app.views.dataServices;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import io.reactivex.Observable;
import mitko.data.Constants;
import mitko.data.HttpDataTransfer;
import mitko.data.contracts.IHttpDataTransfer;
import mitko.data.models.ContactDb;
import teamwork.contacts_sync_app.views.models.Contact;

/**
 * Created by dimki on 02.03.2017 г..
 */

public class ContactDataService {
    private static final String contactUrl = "/api/contact";
    private final Context context;
    private IHttpDataTransfer<ContactDb> httpData;

    public ContactDataService(Context context) {
        String url = Constants.serverUrl + contactUrl;

        this.httpData = new HttpDataTransfer<ContactDb>(url, ContactDb.class, ContactDb[].class);
        this.context=context;
    }

    public Observable<Contact[]> getContacts() {
        this.setToken();
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
        this.setToken();
        return this.httpData.getById(id)
                .map(contactDb -> new Contact(contactDb.getName(), contactDb.getPhoneNumber(), contactDb.getCompany()));
    }

    public Observable<Boolean> createContacts(Contact[] contacts) {
        this.setToken();
        ContactDb[] contactsDb = new ContactDb[contacts.length];
        for (int i = 0; i < contacts.length; i++) {
            contactsDb[i] = new ContactDb(contacts[i].getName(), contacts[i].getNumber(), contacts[i].getCompany());
        }

        return this.httpData.add(contactsDb)
                .map(contactDbServerResponse -> contactDbServerResponse.getSuccess());
    }

    public Observable<Contact> updateContact(String id, Contact contact) {
        this.setToken();
        ContactDb contactDb = new ContactDb(contact.getName(), contact.getNumber(), contact.getCompany());
        return this.httpData.update(id, contactDb)
                .map(contactDbServerResponse -> {
                    ContactDb responseContact = contactDbServerResponse.getObject();
                    return new Contact(responseContact.getName(), responseContact.getPhoneNumber(), responseContact.getCompany());
                });
    }

    public Observable<Boolean> deleteContact(String id) {
        return this.httpData.delete(id)
                .map(contactDbServerResponse -> contactDbServerResponse.getSuccess());
    }

    private void setToken(){
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        String token = settings.getString(Constants.TOKEN_KEY, "");
        this.httpData.setToken(token);
    }
}

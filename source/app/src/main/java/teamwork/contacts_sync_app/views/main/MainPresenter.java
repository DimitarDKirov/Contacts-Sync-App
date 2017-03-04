package teamwork.contacts_sync_app.views.main;

import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import teamwork.contacts_sync_app.views.dataServices.ContactDataService;
import teamwork.contacts_sync_app.views.dataServices.UserDataService;
import teamwork.contacts_sync_app.views.models.Contact;
import teamwork.contacts_sync_app.views.ui.ModalFactory;
import teamwork.contacts_sync_app.views.ui.Notifier;

public class MainPresenter
        implements MainContracts.Presenter {
    private final MainContracts.View view;
    private final Context context;
    Contact[] contacts;


    public MainPresenter(MainContracts.View view, ModalFactory modalFactory, Notifier notifier, Context context) {
        this.view = view;
        this.context=context;

        this.getView().setPresenter(this);
        this.getView().setModalFactory(modalFactory);
        this.getView().setNotifier(notifier);

        this.contacts = new Contact[]{
                new Contact("Angel", "0888888888", "Home"),
                new Contact("Dimitar", "0889000000", "Home"),
                new Contact("Samuil", "0888666666", "Home"),
                new Contact("Peter", "0888666666", "Home")
        };
    }

    public Observable<Boolean> start() {
        UserDataService userService = new UserDataService(this.context);
        return userService.register("mitko2", "123456")
                .switchMap(aBoolean -> {
                    ContactDataService data = new ContactDataService(this.context);
                    return data.getContacts()
                            .map(new Function<Contact[], Boolean>() {
                                @Override
                                public Boolean apply(Contact[] contactsDb) throws Exception {
                                    contacts = contactsDb;
                                    getView().setContacts(contactsDb);
                                    return true;
                                }
                            });
                    //this.getView().setContacts(this.contacts);
                });
    }

    @Override
    public MainContracts.View getView() {
        return this.view;
    }

    public void selectName(int position) {
        this.getView().navigateWith(this.contacts[position]);
        this.getView().notifyText(
                "Selected \"" + this.contacts[position].getName() + "\""
        );
    }

    @Override
    public void add() {
        this.getView()
                .showAddView();
    }
}
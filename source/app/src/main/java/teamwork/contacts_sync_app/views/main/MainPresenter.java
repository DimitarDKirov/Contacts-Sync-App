package teamwork.contacts_sync_app.views.main;

import java.util.Arrays;

import mitko.data.dataServices.ContactDataService;
import teamwork.contacts_sync_app.views.models.Contact;
import teamwork.contacts_sync_app.views.ui.ModalFactory;
import teamwork.contacts_sync_app.views.ui.Notifier;

public class MainPresenter
        implements MainContracts.Presenter {
    private final MainContracts.View view;
    Contact[] contacts;


    public MainPresenter(MainContracts.View view, ModalFactory modalFactory, Notifier notifier) {
        this.view = view;

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

    public void start() {
//        ContactDataService data = new ContactDataService();
//        data.getContacts()
//                .map(contactDbs -> {
//                    return (Contact[]) Arrays.stream(contactDbs).map(contact -> {
//                        return new Contact(contact.getName(), contact.getPhoneNumber(), contact.getCompany());
//                    })
//                            .toArray(Contact[]::new);
//                })
//                .subscribe(contactsDb -> {
//                    this.contacts = contactsDb;
//                    this.getView().setContacts(this.contacts);
//                });
      this.getView().setContacts(this.contacts);
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
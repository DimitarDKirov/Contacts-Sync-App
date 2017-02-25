package teamwork.contacts_sync_app.views.main;

import teamwork.contacts_sync_app.views.models.Contact;
import teamwork.contacts_sync_app.views.ui.ModalFactory;
import teamwork.contacts_sync_app.views.ui.Notifier;

public class MainContracts {
    public interface View {
        void setContacts(Contact[] items);

        void notifyText(String text);

        void setPresenter(MainContracts.Presenter presenter);

        void navigateWith(Contact contact);

        void showAddView();

        void setModalFactory(ModalFactory modalFactory);

        void setNotifier(Notifier notifier);
    }

    public interface Presenter {
        void start();

        MainContracts.View getView();

        void selectName(int index);

        void add();
    }
}

package teamwork.contacts_sync_app.views.details;

import teamwork.contacts_sync_app.views.models.Contact;

public class DetailsContracts {
    public interface View {
        void setDetails(Contact contact);

        void setPresenter(DetailsContracts.Presenter presenter);
    }

    public interface Presenter {
        DetailsContracts.View getView();

        void start();

        void setContact(Contact contact);
    }
}

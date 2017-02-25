package teamwork.contacts_sync_app.views.details;

import teamwork.contacts_sync_app.views.models.Contact;

public class DetailsPresenter implements DetailsContracts.Presenter {
    private final DetailsContracts.View view;
    private Contact contact;

    public DetailsPresenter(DetailsContracts.View view) {
        this.view = view;
        this.getView().setPresenter(this);
    }

    @Override
    public DetailsContracts.View getView() {
        return view;
    }

    @Override
    public void start() {
        this.getView().setDetails(this.contact);
    }

    @Override
    public void setContact(Contact contact) {
        this.contact = contact;
    }
}

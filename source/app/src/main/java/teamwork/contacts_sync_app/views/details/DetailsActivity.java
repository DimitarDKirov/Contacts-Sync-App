package teamwork.contacts_sync_app.views.details;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import teamwork.contacts_sync_app.R;
import teamwork.contacts_sync_app.views.models.Contact;

public class DetailsActivity extends AppCompatActivity {
    private DetailsContracts.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Contact contact = (Contact) this.getIntent().getSerializableExtra("details");

        DetailsContracts.View detailsView = new DetailsView();

        this.presenter = new DetailsPresenter(detailsView);
        this.presenter.setContact(contact);

        this.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_content,
                        (Fragment) this.presenter.getView())
                .commit();
    }
}

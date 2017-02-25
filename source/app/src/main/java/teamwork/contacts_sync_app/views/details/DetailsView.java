package teamwork.contacts_sync_app.views.details;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import teamwork.contacts_sync_app.R;
import teamwork.contacts_sync_app.views.models.Contact;

public class DetailsView extends Fragment implements DetailsContracts.View {
    private TextView tvName;
    private TextView tvNumber;
    private TextView tvCompany;
    private DetailsContracts.Presenter presenter;

    public DetailsView() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_details_view, container, false);

        this.tvName = (TextView) root.findViewById(R.id.tv_name);
        this.tvNumber = (TextView) root.findViewById(R.id.tv_number);
        this.tvCompany = (TextView) root.findViewById(R.id.tv_company);

        this.presenter.start();

        return root;
    }

    @Override
    public void setDetails(Contact contact) {
        String name = "Name: " + contact.getName();
        String number = "Number: " + contact.getNumber();
        String company = "Company: " + contact.getCompany();

        this.tvName.setText(name);
        this.tvNumber.setText(number);
        this.tvCompany.setText(company);
    }

    @Override
    public void setPresenter(DetailsContracts.Presenter presenter) {
        this.presenter = presenter;
    }
}

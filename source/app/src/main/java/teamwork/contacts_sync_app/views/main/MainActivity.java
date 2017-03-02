package teamwork.contacts_sync_app.views.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import teamwork.contacts_sync_app.R;
import teamwork.contacts_sync_app.views.navigations.DrawerActivity;
import teamwork.contacts_sync_app.views.ui.ModalFactory;
import teamwork.contacts_sync_app.views.ui.Notifier;

public class MainActivity extends DrawerActivity {
    public MainContracts.Presenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainView mainView = new MainView();
        ModalFactory modalFactory = new ModalFactory();
        Notifier notifier = new Notifier();
        this.mainPresenter = new MainPresenter(mainView, modalFactory, notifier);

        this.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_content,
                        (Fragment) this.mainPresenter.getView()
                )
                .commit();
    }
}
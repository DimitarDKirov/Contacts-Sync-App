package teamwork.contacts_sync_app.views.navigations;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;

import teamwork.contacts_sync_app.R;
import teamwork.contacts_sync_app.views.main.MainActivity;

public class DrawerActivity extends AppCompatActivity {

    @Override
    protected void onStart() {
        super.onStart();

        this.setupDrawer();
    }

    protected void setupDrawer() {
        View drawerContainer = this.findViewById(R.id.container_drawer);
        if (drawerContainer == null) {
            throw new UnsupportedOperationException("The activity must have an element with id \"container_drawer\"");
        }

        ArrayList<DrawerItemInfo> items = new ArrayList<>();

        items.add(new DrawerItemInfo(1, "Home"));
        items.add(new DrawerItemInfo(2, "Contacts"));

        Fragment drawerFragment =
                DrawerFragment.createFragment(items, (view, position, drawerItem) -> {
                    switch ((int) drawerItem.getIdentifier()) {
                        case 1:
                        case 2:
                            Intent intent = new Intent(this, MainActivity.class);
                            this.startActivity(intent);
                            break;
                    }

                    return true;
                });

        this.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_drawer, drawerFragment)
                .commit();
    }
}

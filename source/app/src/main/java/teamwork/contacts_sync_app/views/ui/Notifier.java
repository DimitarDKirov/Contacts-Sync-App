package teamwork.contacts_sync_app.views.ui;

import android.content.Context;
import android.graphics.Color;

import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperActivityToast;

public class Notifier {
    public void notifySuccess(Context context, String text) {
        SuperActivityToast.create(context, new Style(), Style.TYPE_BUTTON)
                .setText(text)
                .setDuration(Style.DURATION_LONG)
                .setTextColor(Color.BLACK)
                .setColor(Color.GREEN)
                .setHeight(200)
                .setFrame(Style.FRAME_LOLLIPOP)
                .setAnimations(Style.ANIMATIONS_POP)
                .show();
    }
}

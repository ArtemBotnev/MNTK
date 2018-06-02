package ru.artembotnev.mntk;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.artembotnev.mntk.model.net.ApiProvider;
import ru.artembotnev.mntk.model.net.pojo.ConferenceRoot;

/**
 * Intent service's subclass for handle net queries
 * <p>
 * Created by Artem Botnev on 02.06.2018
 */
public class mntkIntentService extends IntentService {
    private static final String MNTK_NET_SERVISE = "net_service";
    private static final String CONFERENCE_ROOT = "conference_root";

    public static void obtainConferenceRoot(Context context) {
        Intent intent = new Intent(context, mntkIntentService.class);
        intent.setAction(CONFERENCE_ROOT);

        context.startService(intent);
    }

    public mntkIntentService() {
        super(MNTK_NET_SERVISE);
    }

    @Override
    protected void onHandleIntent(@NonNull Intent intent) {
        String action = intent.getAction();

        if (action == null) {
            return;
        }

        if (action.equals(CONFERENCE_ROOT)) {
            ApiProvider.getSectionApiInstance()
                    .getConferenceRoot()
                    .enqueue(new Callback<ConferenceRoot>() {
                        @Override
                        public void onResponse(Call<ConferenceRoot> call, Response<ConferenceRoot> response) {
                            // check response
                            if (response.code() == 200 && response.body() != null) {
                                // TODO: add event to event bus
                            } else {
                                // TODO: push error to event bus
                            }
                        }

                        @Override
                        public void onFailure(Call<ConferenceRoot> call, Throwable t) {
                            // TODO: push error to event bus
                        }
                    });
        }
    }
}

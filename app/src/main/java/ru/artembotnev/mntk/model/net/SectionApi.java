package ru.artembotnev.mntk.model.net;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.artembotnev.mntk.model.net.pojo.ConferenceRoot;
import ru.artembotnev.mntk.model.net.pojo.Participant;

/**
 * Contains REST methods for sections
 * <p>
 * Created by Artem Botnev on 20.05.18
 */
public interface SectionApi {
    /**
     * for getting ConferenceRoot call from net
     *
     * @return retrofit Call
     */
    @GET("mobile/get/conference/5adce40ae98b0504c10dded7")
    Call<ConferenceRoot> getConferenceRoot();

    /**
     * for getting all participants of definite group from net
     *
     * @param group of participants
     * @return list of participants
     */
    @GET("read/conference/participants/{id}")
    Call<List<Participant>> getParticipants(@Path("id") String group);
}

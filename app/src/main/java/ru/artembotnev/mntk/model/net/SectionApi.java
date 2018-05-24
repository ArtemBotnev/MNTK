package ru.artembotnev.mntk.model.net;


import retrofit2.Call;
import retrofit2.http.GET;
import ru.artembotnev.mntk.model.net.pojo.ConferenceRoot;

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
}

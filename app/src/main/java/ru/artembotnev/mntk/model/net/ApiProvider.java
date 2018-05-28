package ru.artembotnev.mntk.model.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Provides methods for getting REST API
 * <p>
 * Created by Artem Botnev on 20.05.18
 */
public class ApiProvider {
    private static final String URL_ROOT = "http://em-http.rosenergoatom.ru/";
    private static SectionApi sectionApi;

    public static SectionApi getSectionApiInstance() {
        if (sectionApi == null) {
            // create gson converter
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm")
                    .create();

            // create retrofit
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(URL_ROOT)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            sectionApi = retrofit.create(SectionApi.class);
        }

        return sectionApi;
    }
}

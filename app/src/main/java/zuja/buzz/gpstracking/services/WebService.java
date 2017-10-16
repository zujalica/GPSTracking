package zuja.buzz.gpstracking.services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import zuja.buzz.gpstracking.models.DriverModel;

/**
 * TODO: Retrofit web service
 */
public interface WebService {

    @POST("/json={json}")
    Call<String> sendGPSData(@Path("json") String gpsData);

    @GET("/users/{driver}")
    Call<DriverModel> getDriver(@Path("driver") String driverId);
}

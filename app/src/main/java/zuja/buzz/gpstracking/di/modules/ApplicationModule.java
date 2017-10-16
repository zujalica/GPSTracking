package zuja.buzz.gpstracking.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import zuja.buzz.gpstracking.GPSTrackingApplication;

@Module
public class ApplicationModule {

    GPSTrackingApplication mApplication;

    public ApplicationModule(GPSTrackingApplication application) {
        this.mApplication = application;
    }

    @Provides
    @Singleton
    GPSTrackingApplication application(){
        return mApplication;
    }
}

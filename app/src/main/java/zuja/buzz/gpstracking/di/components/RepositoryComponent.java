package zuja.buzz.gpstracking.di.components;

import javax.inject.Singleton;

import dagger.Component;
import zuja.buzz.gpstracking.GPSTrackingApplication;
import zuja.buzz.gpstracking.activities.MainActivity;
import zuja.buzz.gpstracking.di.modules.ApplicationModule;
import zuja.buzz.gpstracking.di.modules.ExecutorModule;
import zuja.buzz.gpstracking.di.modules.RepositoryModule;
import zuja.buzz.gpstracking.di.modules.RoomModule;
import zuja.buzz.gpstracking.fragments.DriverProfileFragment;
import zuja.buzz.gpstracking.fragments.NewRideFragment;

@Singleton
@Component(modules = {RepositoryModule.class, ApplicationModule.class, RoomModule.class, ExecutorModule.class})
public interface RepositoryComponent {
    void inject(MainActivity activity);
    void inject(DriverProfileFragment fragment);
    void inject(GPSTrackingApplication application);
    void inject(NewRideFragment fragment);
}

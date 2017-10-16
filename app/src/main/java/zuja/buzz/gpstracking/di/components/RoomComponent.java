package zuja.buzz.gpstracking.di.components;

import javax.inject.Singleton;

import dagger.Component;
import zuja.buzz.gpstracking.GPSTrackingApplication;
import zuja.buzz.gpstracking.dao.RideDAO;
import zuja.buzz.gpstracking.dao.RideSegmentDAO;
import zuja.buzz.gpstracking.dao.DriverDAO;
import zuja.buzz.gpstracking.dao.LocationPointDAO;
import zuja.buzz.gpstracking.dao.SettingsDAO;
import zuja.buzz.gpstracking.di.modules.ApplicationModule;
import zuja.buzz.gpstracking.di.modules.RoomModule;

@Singleton
@Component(modules = { RoomModule.class, ApplicationModule.class})
public interface RoomComponent {
    DriverDAO driverDAO();
    SettingsDAO settingsDAO();
    RideSegmentDAO rideSegmentDAO();
    LocationPointDAO locationPointDAO();
    RideDAO rideDAO();

    GPSTrackingApplication gpsTrackingApplication();
}

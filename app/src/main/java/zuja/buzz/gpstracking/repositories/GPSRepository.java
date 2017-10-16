package zuja.buzz.gpstracking.repositories;

import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

import zuja.buzz.gpstracking.dao.LocationPointDAO;
import zuja.buzz.gpstracking.dao.RideDAO;
import zuja.buzz.gpstracking.dao.RideSegmentDAO;
/**
 * GPS repository. Used for communication with the database and web services.
 */
@Singleton
public class GPSRepository {

    private RideDAO rideDAO;
    private RideSegmentDAO rideSegmentDAO;
    private LocationPointDAO locationPointDAO;
    private Executor executor;

    @Inject
    public GPSRepository(RideDAO rideDAO, RideSegmentDAO rideSegmentDAO, LocationPointDAO locationPointDAO, Executor executor) {
        this.rideDAO = rideDAO;
        this.rideSegmentDAO = rideSegmentDAO;
        this.locationPointDAO = locationPointDAO;
        this.executor = executor;
    }

}

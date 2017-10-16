package zuja.buzz.gpstracking.repositories;

import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

import zuja.buzz.gpstracking.dao.DriverDAO;
import zuja.buzz.gpstracking.models.DriverModel;

/**
 * Driver repository. Used for communication with the database and web services.
 */
@Singleton
public class DriverRepository {

    public enum UpdateDriverData{
        NAME, LAST_NAME, AGE, PLATE_NUMBER, PROFILE_PICTURE;
    }

    private DriverDAO driverDAO;
    private Executor executor;

    @Inject
    public DriverRepository(DriverDAO driverDAO, Executor executor){
        this.driverDAO = driverDAO;
        this.executor = executor;
    }

    public DriverModel getDriver(int driverId){
        return driverDAO.load(driverId);
    }

    public void updateDriver(final DriverModel driverModel, final UpdateDriverData updateDriverData){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                switch (updateDriverData){
                    case NAME:
                        driverDAO.updateName(driverModel.getName(), driverModel.getId());
                        break;
                    case LAST_NAME:
                        driverDAO.updateLastName(driverModel.getLastName(), driverModel.getId());
                        break;
                    case AGE:
                        driverDAO.updateAge(driverModel.getAge(), driverModel.getId());
                        break;
                    case PLATE_NUMBER:
                        driverDAO.updatePlateNumber(driverModel.getPlateNumber(), driverModel.getId());
                        break;
                    case PROFILE_PICTURE:
                        driverDAO.updateProfilePicture(driverModel.getProfilePicture(), driverModel.getId());
                        break;
                }
            }
        });
    }

    public long insertDriver(final DriverModel driverModel){
        return driverDAO.save(driverModel);
    }

    public void updateDriver(final DriverModel driverModel){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                driverDAO.updateDriver(driverModel);
            }
        });
    }
}

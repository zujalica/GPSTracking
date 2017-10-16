package zuja.buzz.gpstracking;

import android.app.Application;

import zuja.buzz.gpstracking.di.components.DaggerExecutorComponent;
import zuja.buzz.gpstracking.di.components.DaggerFactoryComponent;
import zuja.buzz.gpstracking.di.components.DaggerRepositoryComponent;
import zuja.buzz.gpstracking.di.components.DaggerRoomComponent;
import zuja.buzz.gpstracking.di.components.DaggerWebComponent;
import zuja.buzz.gpstracking.di.components.ExecutorComponent;
import zuja.buzz.gpstracking.di.components.FactoryComponent;
import zuja.buzz.gpstracking.di.components.RepositoryComponent;
import zuja.buzz.gpstracking.di.components.RoomComponent;
import zuja.buzz.gpstracking.di.components.WebComponent;
import zuja.buzz.gpstracking.di.modules.ApplicationModule;
import zuja.buzz.gpstracking.di.modules.ExecutorModule;
import zuja.buzz.gpstracking.di.modules.FactoryModule;
import zuja.buzz.gpstracking.di.modules.RepositoryModule;
import zuja.buzz.gpstracking.di.modules.RoomModule;
import zuja.buzz.gpstracking.di.modules.WebModule;

/**
 * Main application class. Used for initialization and starting setup. (mainly used for DI)
 */
public class GPSTrackingApplication extends Application {

    private WebComponent mWebComponent;
    private FactoryComponent mFactoryComponent;
    private RepositoryComponent mRepositoryComponent;
    private RoomComponent mRoomComponent;
    private ExecutorComponent mExecutorComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationModule applicationModule = new ApplicationModule(this);
        RepositoryModule repositoryModule = new RepositoryModule();
        FactoryModule factoryModule = new FactoryModule();
        RoomModule roomModule = new RoomModule(this);
        ExecutorModule executorModule = new ExecutorModule();

        mRoomComponent = DaggerRoomComponent.builder()
                .roomModule(roomModule)
                .applicationModule(applicationModule)
                .build();
        mWebComponent = DaggerWebComponent.builder()
                .webModule(new WebModule(Constants.WEB_URL))
                .applicationModule(applicationModule)
                .roomModule(roomModule)
                .executorModule(executorModule)
                .build();
        mFactoryComponent = DaggerFactoryComponent.builder()
                .factoryModule(factoryModule)
                .applicationModule(applicationModule)
                .repositoryModule(repositoryModule)
                .roomModule(roomModule)
                .executorModule(executorModule)
                .build();
        mRepositoryComponent = DaggerRepositoryComponent.builder()
                .repositoryModule(repositoryModule)
                .applicationModule(applicationModule)
                .roomModule(roomModule)
                .executorModule(executorModule)
                .build();
        mExecutorComponent = DaggerExecutorComponent.builder()
                .executorModule(executorModule)
                .build();
    }

    public WebComponent getWebComponent() {
        return mWebComponent;
    }

    public FactoryComponent getFactoryComponent() {
        return mFactoryComponent;
    }

    public RepositoryComponent getRepositoryComponent() {
        return mRepositoryComponent;
    }

    public RoomComponent getRoomComponent() {
        return mRoomComponent;
    }

    public ExecutorComponent getExecutorComponent() {
        return mExecutorComponent;
    }
}

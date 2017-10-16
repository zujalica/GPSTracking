package zuja.buzz.gpstracking.di.components;

import java.util.concurrent.Executor;

import javax.inject.Singleton;

import dagger.Component;
import zuja.buzz.gpstracking.activities.MainActivity;
import zuja.buzz.gpstracking.di.modules.ApplicationModule;
import zuja.buzz.gpstracking.di.modules.ExecutorModule;
import zuja.buzz.gpstracking.di.modules.RoomModule;
import zuja.buzz.gpstracking.di.modules.WebModule;

@Singleton
@Component(modules = {ApplicationModule.class, WebModule.class, RoomModule.class, ExecutorModule.class})
public interface WebComponent {
    void inject(MainActivity activity);
}

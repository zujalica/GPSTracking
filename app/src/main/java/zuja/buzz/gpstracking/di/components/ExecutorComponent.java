package zuja.buzz.gpstracking.di.components;

import javax.inject.Singleton;

import dagger.Component;
import zuja.buzz.gpstracking.di.modules.ExecutorModule;

@Singleton
@Component(modules = { ExecutorModule.class })
public interface ExecutorComponent {
}

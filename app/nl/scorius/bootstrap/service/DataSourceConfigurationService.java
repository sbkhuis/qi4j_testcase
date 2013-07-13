package nl.scorius.bootstrap.service;

import nl.scorius.bootstrap.InfraLayerAssembler;

import org.qi4j.api.activation.ActivatorAdapter;
import org.qi4j.api.activation.Activators;
import org.qi4j.api.entity.EntityBuilder;
import org.qi4j.api.injection.scope.Structure;
import org.qi4j.api.mixin.Mixins;
import org.qi4j.api.service.ServiceComposite;
import org.qi4j.api.service.ServiceReference;
import org.qi4j.api.structure.Module;
import org.qi4j.api.unitofwork.UnitOfWork;
import org.qi4j.api.unitofwork.UnitOfWorkCompletionException;
import org.qi4j.library.sql.datasource.DataSourceConfiguration;

import play.Logger;

@Mixins (DataSourceConfigurationService.Mixin.class)
@Activators (DataSourceConfigurationService.Activator.class)
public interface DataSourceConfigurationService extends ServiceComposite {

	public void createConfiguration ();

	public class Activator extends
			ActivatorAdapter<ServiceReference<DataSourceConfigurationService>>

	{

		@Override
		public void afterActivation (
				ServiceReference<DataSourceConfigurationService> aReference)
				throws Exception {
			aReference.get().createConfiguration();
		}
	}

	public abstract class Mixin implements DataSourceConfigurationService {

		@Structure
		private Module	module;

		@Override
		public void createConfiguration () {
			UnitOfWork _UoW = this.module.newUnitOfWork();
			try {
				createDataSourceConfiguration();
				createDataSourceIndexConfiguration();
				_UoW.complete();
			}
			catch (UnitOfWorkCompletionException e) {
				Logger.error("Can not create database configuration. Reason: "
						+ e.getMessage());
			}
			finally {
				_UoW.discard();
			}
		}

		private DataSourceConfiguration createDataSourceConfiguration () {
			EntityBuilder<DataSourceConfiguration> _Builder = this.module
					.currentUnitOfWork().newEntityBuilder(
							DataSourceConfiguration.class);
			DataSourceConfiguration _DSConfigurationPrototype = _Builder
					.instance();

			_DSConfigurationPrototype.enabled().set(true);
			_DSConfigurationPrototype.identity().set(InfraLayerAssembler.DS_ID);
			_DSConfigurationPrototype.driver().set("org.postgresql.Driver");
			_DSConfigurationPrototype.url().set(
					"jdbc:postgresql://192.168.56.101/qi4j");
			_DSConfigurationPrototype.username().set("postgres");
			_DSConfigurationPrototype.password().set("postgres");
			return _Builder.newInstance();

		}

		private DataSourceConfiguration createDataSourceIndexConfiguration () {
			EntityBuilder<DataSourceConfiguration> _Builder = this.module
					.currentUnitOfWork().newEntityBuilder(
							DataSourceConfiguration.class);
			DataSourceConfiguration _DSConfigurationPrototype = _Builder
					.instance();

			_DSConfigurationPrototype.enabled().set(true);
			_DSConfigurationPrototype.identity().set(
					InfraLayerAssembler.DS_INDEX_ID);
			_DSConfigurationPrototype.driver().set("org.postgresql.Driver");
			_DSConfigurationPrototype.url().set(
					"jdbc:postgresql://192.168.56.101/qi4j");
			_DSConfigurationPrototype.username().set("postgres");
			_DSConfigurationPrototype.password().set("postgres");
			return _Builder.newInstance();
		}
	}
}

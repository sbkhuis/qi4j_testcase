package nl.scorius.bootstrap;

import org.qi4j.api.activation.Activator;
import org.qi4j.api.entity.EntityBuilder;
import org.qi4j.api.structure.Module;
import org.qi4j.api.unitofwork.UnitOfWork;
import org.qi4j.library.sql.datasource.DataSourceConfiguration;

public class ConfigModuleActivator implements Activator<Module>
{
	private void setDataSourceConfiguration( DataSourceConfiguration aConfig )
	{
		aConfig.enabled().set(true);
		aConfig.identity().set(InfraLayerAssembler.DS_ID);
		aConfig.driver().set("org.postgresql.Driver");
		aConfig.url().set("jdbc:postgresql://10.0.0.86/scorius6");
		aConfig.username().set("postgres");
		aConfig.password().set("postgres");
	}

	private void setDataSourceIndexConfiguration(
			DataSourceConfiguration aConfig )
	{
		aConfig.enabled().set(true);
		aConfig.identity().set(InfraLayerAssembler.DS_INDEX_ID);
		aConfig.driver().set("org.postgresql.Driver");
		aConfig.url().set("jdbc:postgresql://10.0.0.86/scorius6");
		aConfig.username().set("postgres");
		aConfig.password().set("postgres");
	}

	private DataSourceConfiguration createDataSourceConfiguration(
			UnitOfWork aUoW )
	{
		EntityBuilder<DataSourceConfiguration> _Builder = aUoW
				.newEntityBuilder(DataSourceConfiguration.class);
		DataSourceConfiguration _DSConfigurationPrototype = _Builder.instance();

		setDataSourceConfiguration(_DSConfigurationPrototype);

		return _Builder.newInstance();
	}

	private DataSourceConfiguration createDataSourceIndexConfiguration(
			UnitOfWork aUoW )
	{
		EntityBuilder<DataSourceConfiguration> _Builder = aUoW
				.newEntityBuilder(DataSourceConfiguration.class);
		DataSourceConfiguration _DSConfigurationPrototype = _Builder.instance();

		setDataSourceIndexConfiguration(_DSConfigurationPrototype);

		return _Builder.newInstance();
	}

	@Override
	public void afterActivation( Module aModule ) throws Exception
	{
		UnitOfWork _UoW = aModule.newUnitOfWork();
		try
		{
			createDataSourceConfiguration(_UoW);
			createDataSourceIndexConfiguration(_UoW);
			_UoW.complete();
		}
		finally
		{
			_UoW.discard();
		}
	}

	@Override
	public void afterPassivation( Module arg0 ) throws Exception
	{
	}

	@Override
	public void beforeActivation( Module arg0 ) throws Exception
	{
	}

	@Override
	public void beforePassivation( Module arg0 ) throws Exception
	{
	}
}

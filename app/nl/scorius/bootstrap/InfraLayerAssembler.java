package nl.scorius.bootstrap;

import org.qi4j.api.common.Visibility;
import org.qi4j.api.value.ValueSerialization;
import org.qi4j.bootstrap.ApplicationAssembly;
import org.qi4j.bootstrap.AssemblyException;
import org.qi4j.bootstrap.AssemblyVisitorAdapter;
import org.qi4j.bootstrap.ModuleAssembly;
import org.qi4j.entitystore.sql.assembly.PostgreSQLEntityStoreAssembler;
import org.qi4j.index.sql.assembly.PostgreSQLIndexQueryAssembler;
import org.qi4j.library.sql.assembly.DataSourceAssembler;
import org.qi4j.library.sql.datasource.DataSources;
import org.qi4j.library.sql.dbcp.DBCPDataSourceServiceAssembler;
import org.qi4j.valueserialization.orgjson.OrgJsonValueSerializationService;

public class InfraLayerAssembler extends
		AssemblyVisitorAdapter<AssemblyException>
{
	public static final String	CommonIndexingModule	= "Common-Indexing-Module";
	public static final String	CommonDatabaseModule	= "Common-Database-Module";

	public static final String	DS_SERVICE_ID			= "postgresql-datasource-service";
	public static final String	DS_ID					= "postgresql-datasource";
	public static final String	DS_INDEX_ID				= "postgresql-index-datasource";

	@Override
	public void visitApplication( ApplicationAssembly anAssembly )
			throws AssemblyException
	{
		ModuleAssembly _ConfigModule = anAssembly.layer(
				ScoriusApplicationAssembler.CONFIG_LAYER).module(
				ConfigLayerAssembler.CommonConfigModule);
		ModuleAssembly _DatabaseModule = anAssembly.layer(
				ScoriusApplicationAssembler.INFRASTRUCTURE_LAYER).module(
				CommonDatabaseModule);

		_DatabaseModule.services(OrgJsonValueSerializationService.class)
				.taggedWith(ValueSerialization.Formats.JSON)
				.visibleIn(Visibility.module);

		new DBCPDataSourceServiceAssembler().identifiedBy(DS_SERVICE_ID)
				.visibleIn(Visibility.module).withConfig(_ConfigModule)
				.withConfigVisibility(Visibility.application)
				.assemble(_DatabaseModule);

		new DataSourceAssembler().withDataSourceServiceIdentity(DS_SERVICE_ID)
				.identifiedBy(DS_ID).visibleIn(Visibility.module)
				.withCircuitBreaker(DataSources.newDataSourceCircuitBreaker())
				.assemble(_DatabaseModule);

		new PostgreSQLEntityStoreAssembler().visibleIn(Visibility.application)
				.withConfig(_ConfigModule)
				.withConfigVisibility(Visibility.application)
				.assemble(_DatabaseModule);

		new DataSourceAssembler().withDataSourceServiceIdentity(DS_SERVICE_ID)
				.identifiedBy(DS_INDEX_ID).visibleIn(Visibility.module)
				.withCircuitBreaker().assemble(_DatabaseModule);
		new PostgreSQLIndexQueryAssembler().visibleIn(Visibility.application)
				.withConfig(_ConfigModule)
				.withConfigVisibility(Visibility.application)
				.assemble(_DatabaseModule);

	}
}

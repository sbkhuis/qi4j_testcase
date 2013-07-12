package nl.scorius.bootstrap;

import org.qi4j.api.common.Visibility;
import org.qi4j.api.value.ValueSerialization;
import org.qi4j.bootstrap.ApplicationAssembly;
import org.qi4j.bootstrap.AssemblyException;
import org.qi4j.bootstrap.AssemblyVisitorAdapter;
import org.qi4j.bootstrap.ModuleAssembly;
import org.qi4j.entitystore.memory.MemoryEntityStoreService;
import org.qi4j.library.rdf.repository.MemoryRepositoryService;
import org.qi4j.spi.uuid.UuidIdentityGeneratorService;
import org.qi4j.valueserialization.orgjson.OrgJsonValueSerializationService;

public class ConfigLayerAssembler extends
		AssemblyVisitorAdapter<AssemblyException>
{
	public static final String	CommonConfigModule	= "Common-Config-Module";

	@SuppressWarnings("unchecked")
	@Override
	public void visitApplication( ApplicationAssembly anAssembly )
			throws AssemblyException
	{
		ModuleAssembly _ConfigModule = anAssembly.layer(
				ScoriusApplicationAssembler.CONFIG_LAYER).module(
				CommonConfigModule);

		_ConfigModule
				.services(MemoryEntityStoreService.class,
						MemoryRepositoryService.class,
						UuidIdentityGeneratorService.class)
				.instantiateOnStartup().visibleIn(Visibility.module);
		_ConfigModule.services(OrgJsonValueSerializationService.class)
				.taggedWith(ValueSerialization.Formats.JSON)
				.visibleIn(Visibility.module);

		_ConfigModule.withActivators(ConfigModuleActivator.class);
	}
}

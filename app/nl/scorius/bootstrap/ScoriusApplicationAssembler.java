package nl.scorius.bootstrap;

//import nl.scorius.common.database.PostgreSQLDataBaseAssembler;
//import nl.scorius.common.qi4j.ScoriusQi;

import org.qi4j.api.structure.Application.Mode;
import org.qi4j.bootstrap.ApplicationAssembler;
import org.qi4j.bootstrap.ApplicationAssembly;
import org.qi4j.bootstrap.ApplicationAssemblyFactory;
import org.qi4j.bootstrap.AssemblyException;
import org.qi4j.bootstrap.LayerAssembly;

public class ScoriusApplicationAssembler implements ApplicationAssembler
{
	public static final String	CONFIG_LAYER			= "CONFIG";
	public static final String	INFRASTRUCTURE_LAYER	= "INFRASTRUCTURE";
	public static final String	DOMAIN_LAYER			= "DOMAIN";
	public static final String	APPLICATION_LAYER		= "APPLICATION";
	public static final String	PRESENTATION_LAYER		= "PRESENTATION";

	public static final String	APPLICATION_NAME		= "Scorius";

	@Override
	public ApplicationAssembly assemble( ApplicationAssemblyFactory aFactory )
			throws AssemblyException
	{
		ApplicationAssembly _Assembly = aFactory.newApplicationAssembly();

		_Assembly.setName(APPLICATION_NAME);
		_Assembly.setMode(Mode.development);

		LayerAssembly _ConfigLayer = _Assembly.layer(CONFIG_LAYER);
		LayerAssembly _InfraLayer = _Assembly.layer(INFRASTRUCTURE_LAYER);
		LayerAssembly _DomainLayer = _Assembly.layer(DOMAIN_LAYER);
		LayerAssembly _PresentationLayer = _Assembly.layer(PRESENTATION_LAYER);

		_InfraLayer.uses(_ConfigLayer);

		_DomainLayer.uses(_InfraLayer);

		_PresentationLayer.uses(_InfraLayer);
		_PresentationLayer.uses(_DomainLayer);

		_Assembly.visit(new ConfigLayerAssembler());
		_Assembly.visit(new InfraLayerAssembler());
		_Assembly.visit(new DomainLayerAssembler());
		_Assembly.visit(new PresentationLayerAssembler());

		return _Assembly;
	}
}
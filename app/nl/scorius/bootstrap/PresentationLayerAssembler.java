package nl.scorius.bootstrap;

import nl.scorius.presentation.controllers.SubscriptionController;

import org.qi4j.api.common.Visibility;
import org.qi4j.bootstrap.ApplicationAssembly;
import org.qi4j.bootstrap.AssemblyException;
import org.qi4j.bootstrap.AssemblyVisitorAdapter;
import org.qi4j.bootstrap.ModuleAssembly;

public class PresentationLayerAssembler extends
		AssemblyVisitorAdapter<AssemblyException>
{
	public static final String	SubscriptionControllersModule	= "Subscription-Controllers-Module";

	@Override
	public void visitApplication( ApplicationAssembly anAssembly )
			throws AssemblyException
	{
		ModuleAssembly _ControllersModule = anAssembly.layer(
				ScoriusApplicationAssembler.PRESENTATION_LAYER).module(
				SubscriptionControllersModule);

		_ControllersModule.objects(SubscriptionController.class).visibleIn(
				Visibility.layer);
	}
}
package nl.scorius.bootstrap;

import nl.scorius.domain.subscription.entity.SubscriptionEntity;
import nl.scorius.domain.subscription.service.factory.SubscriptionEntityFactoryService;
import nl.scorius.domain.subscription.service.repository.SubscriptionRepositoryService;

import org.qi4j.api.common.Visibility;
import org.qi4j.bootstrap.ApplicationAssembly;
import org.qi4j.bootstrap.AssemblyException;
import org.qi4j.bootstrap.AssemblyVisitorAdapter;
import org.qi4j.bootstrap.ModuleAssembly;

public class DomainLayerAssembler extends
		AssemblyVisitorAdapter<AssemblyException>
{
	public static final String	SubscriptionDomainModule	= "Subscription-Domain-Module";

	@Override
	public void visitApplication( ApplicationAssembly anAssembly )
			throws AssemblyException
	{
		ModuleAssembly _DomainModule = anAssembly.layer(
				ScoriusApplicationAssembler.DOMAIN_LAYER).module(
				SubscriptionDomainModule);

		_DomainModule.entities(SubscriptionEntity.class);
		_DomainModule
				.services(SubscriptionRepositoryService.class,
						SubscriptionEntityFactoryService.class)
				.instantiateOnStartup().visibleIn(Visibility.application);
	}
}
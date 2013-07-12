package nl.scorius.domain.subscription.service.factory;

import org.qi4j.api.mixin.Mixins;
import org.qi4j.api.service.ServiceComposite;

@Mixins(SubscriptionEntityFactoryMixin.class)
public interface SubscriptionEntityFactoryService extends
		SubscriptionEntityFactory, ServiceComposite
{

}

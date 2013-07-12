package nl.scorius.domain.subscription.service.factory;

import nl.scorius.domain.subscription.entity.SubscriptionEntity;

public interface SubscriptionEntityFactory
{
	public SubscriptionEntity create( String aName );
}

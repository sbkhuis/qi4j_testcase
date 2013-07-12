package nl.scorius.domain.subscription.service.repository;

import java.util.Collection;

import nl.scorius.domain.subscription.entity.SubscriptionEntity;

import org.qi4j.api.unitofwork.NoSuchEntityException;

public interface SubscriptionRepository
{
	SubscriptionEntity findById( String anId ) throws NoSuchEntityException;

	SubscriptionEntity findByName( String aName );

	Collection<SubscriptionEntity> all();
}

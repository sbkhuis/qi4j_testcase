package nl.scorius.domain.subscription.service.factory;

import nl.scorius.domain.subscription.entity.Subscription;
import nl.scorius.domain.subscription.entity.SubscriptionEntity;

import org.qi4j.api.entity.EntityBuilder;
import org.qi4j.api.injection.scope.Structure;
import org.qi4j.api.unitofwork.UnitOfWork;
import org.qi4j.api.unitofwork.UnitOfWorkFactory;

public class SubscriptionEntityFactoryMixin implements
		SubscriptionEntityFactory
{
	@Structure
	UnitOfWorkFactory	unitOfWorkFactory;

	@Override
	public SubscriptionEntity create( String aName )
	{
		UnitOfWork _UnitOfWork = this.unitOfWorkFactory.currentUnitOfWork();

		EntityBuilder<SubscriptionEntity> _Builder = _UnitOfWork
				.newEntityBuilder(SubscriptionEntity.class);

		Subscription _Subscription = _Builder.instance();
		_Subscription.name().set(aName);

		return _Builder.newInstance();
	}
}

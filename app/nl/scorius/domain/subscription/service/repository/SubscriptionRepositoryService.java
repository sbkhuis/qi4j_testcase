package nl.scorius.domain.subscription.service.repository;

import static org.qi4j.api.query.QueryExpressions.eq;
import static org.qi4j.api.query.QueryExpressions.templateFor;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import nl.scorius.domain.subscription.entity.SubscriptionEntity;

import org.qi4j.api.injection.scope.Structure;
import org.qi4j.api.mixin.Mixins;
import org.qi4j.api.query.QueryBuilderFactory;
import org.qi4j.api.service.ServiceComposite;
import org.qi4j.api.unitofwork.NoSuchEntityException;
import org.qi4j.api.unitofwork.UnitOfWorkFactory;

@Mixins({ SubscriptionRepositoryService.Mixin.class })
public interface SubscriptionRepositoryService extends SubscriptionRepository,
		ServiceComposite
{
	public class Mixin implements SubscriptionRepository
	{
		@Structure
		private UnitOfWorkFactory	unitOfWorkFactory;

		@Structure
		private QueryBuilderFactory	queryBuilderFactory;

		@Override
		public SubscriptionEntity findById( String anId )
				throws NoSuchEntityException
		{
			return this.unitOfWorkFactory.currentUnitOfWork().get(
					SubscriptionEntity.class, anId);
		}

		@Override
		public SubscriptionEntity findByName( String aName )
		{
			return this.unitOfWorkFactory
					.currentUnitOfWork()
					.newQuery(
							this.queryBuilderFactory.newQueryBuilder(
									SubscriptionEntity.class).where(
									eq(templateFor(SubscriptionEntity.class)
											.name(), aName)))

					.find();
		}

		@Override
		public Collection<SubscriptionEntity> all()
		{
			Iterator<SubscriptionEntity> _Iterator = this.unitOfWorkFactory
					.currentUnitOfWork()
					.newQuery(
							this.queryBuilderFactory
									.newQueryBuilder(SubscriptionEntity.class))
					.iterator();

			Collection<SubscriptionEntity> _All = new HashSet<SubscriptionEntity>();
			while (_Iterator.hasNext())
			{
				_All.add(_Iterator.next());
			}

			return _All;
		}

	}
}

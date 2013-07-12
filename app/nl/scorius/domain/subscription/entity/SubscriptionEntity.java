package nl.scorius.domain.subscription.entity;

import org.qi4j.api.entity.EntityComposite;
import org.qi4j.api.mixin.Mixins;

@Mixins(SubscriptionStateMixin.class)
public interface SubscriptionEntity extends EntityComposite, Subscription
{

}

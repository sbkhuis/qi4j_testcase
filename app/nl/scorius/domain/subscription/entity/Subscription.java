package nl.scorius.domain.subscription.entity;

import org.qi4j.api.property.Property;
import org.qi4j.library.constraints.annotation.NotEmpty;

public interface Subscription extends SubscriptionState
{
	@NotEmpty
	public Property<String> name();

}

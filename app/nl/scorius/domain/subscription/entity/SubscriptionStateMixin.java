package nl.scorius.domain.subscription.entity;

import org.qi4j.api.injection.scope.This;

public class SubscriptionStateMixin implements SubscriptionState
{
	@This
	private ActiveState	state;

	@Override
	public void deactivate()
	{
		this.state.active().set(false);
	}

	@Override
	public void activate()
	{
		this.state.active().set(true);
	}

	@Override
	public boolean isActive()
	{
		return this.state.active().get();
	}

}

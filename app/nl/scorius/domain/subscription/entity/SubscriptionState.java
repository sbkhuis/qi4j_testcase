package nl.scorius.domain.subscription.entity;

public interface SubscriptionState
{
	public void deactivate();

	public void activate();

	public boolean isActive();
}

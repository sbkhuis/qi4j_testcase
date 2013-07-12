package nl.scorius.domain.subscription.entity;

import org.qi4j.api.common.UseDefaults;
import org.qi4j.api.property.Property;

public interface ActiveState
{
	@UseDefaults
	Property<Boolean> active();
}

package nl.scorius;

import play.GlobalSettings;

import org.codeartisans.playqi.PlayQi;

public class Global extends GlobalSettings
{
	@Override
	public <T> T getControllerInstance( Class<T> clazz )
	{
		return PlayQi.controllersModule().newObject(clazz);
	}
}

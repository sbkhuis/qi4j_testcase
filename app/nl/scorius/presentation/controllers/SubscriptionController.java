package nl.scorius.presentation.controllers;

import nl.scorius.domain.subscription.entity.SubscriptionEntity;
import nl.scorius.domain.subscription.service.factory.SubscriptionEntityFactory;
import nl.scorius.domain.subscription.service.repository.SubscriptionRepository;

import org.qi4j.api.injection.scope.Service;
import org.qi4j.api.injection.scope.Structure;
import org.qi4j.api.structure.Module;
import org.qi4j.api.unitofwork.NoSuchEntityException;
import org.qi4j.api.unitofwork.UnitOfWork;
import org.qi4j.api.unitofwork.UnitOfWorkCompletionException;

import play.mvc.Controller;
import play.mvc.Result;

public class SubscriptionController extends Controller
{
	@Structure
	private Module						module;

	@Service
	private SubscriptionRepository		repostitory;

	@Service
	private SubscriptionEntityFactory	factory;

	public Result create( String aName )
	{
		UnitOfWork _UoW = module.newUnitOfWork();

		String _Id = null;
		try
		{
			SubscriptionEntity _Subscription = this.factory.create(aName);
			_Id = _Subscription.identity().get();
			_UoW.complete();
		}
		catch ( UnitOfWorkCompletionException e )
		{
			_UoW.discard();
			return internalServerError();
		}
		finally
		{
			_UoW.discard();
		}

		return ok(_Id);
	}

	public Result get( String anId )
	{
		UnitOfWork _UoW = module.newUnitOfWork();

		String _Name = null;
		try
		{
			_Name = this.repostitory.findById(anId).name().get();
			_UoW.complete();
		}
		catch ( NoSuchEntityException e )
		{
			_UoW.discard();
			return ok("not found");
		}
		catch ( UnitOfWorkCompletionException e )
		{
			_UoW.discard();
			return internalServerError();
		}
		finally
		{
			_UoW.discard();
		}

		return ok("found: " + _Name);
	}

	public Result findByName( String aName )
	{
		UnitOfWork _UoW = module.newUnitOfWork();

		String _Id = null;
		try
		{
			SubscriptionEntity _Subscription = this.repostitory
					.findByName(aName);
			if ( _Subscription != null )
				_Id = _Subscription.identity().get();
			_UoW.complete();
		}
		catch ( NoSuchEntityException e )
		{
			_UoW.discard();
			return ok("not found");
		}
		catch ( UnitOfWorkCompletionException e )
		{
			_UoW.discard();
			return internalServerError();
		}
		finally
		{
			_UoW.discard();
		}

		return ok(_Id instanceof String ? "found" : "not found");
	}
}

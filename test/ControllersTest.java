import static org.fest.assertions.Assertions.assertThat;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.callAction;
import static play.test.Helpers.contentAsString;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;
import static play.test.Helpers.status;

import org.junit.Test;

import play.mvc.Result;

public class ControllersTest
{
	@Test
	public void testCreateAndGet()
	{
		running(fakeApplication(), new Runnable()
		{
			@Override
			public void run()
			{
				String _Name = "test_db";

				Result resultCreate = callAction(nl.scorius.presentation.controllers.routes.ref.SubscriptionController
						.create(_Name));
				assertThat(status(resultCreate)).isEqualTo(OK);

				String _ResultId = contentAsString(resultCreate);
				assertThat(_ResultId).isNotNull();

				Result resultGet = callAction(nl.scorius.presentation.controllers.routes.ref.SubscriptionController
						.get(_ResultId));
				assertThat(status(resultCreate)).isEqualTo(OK);
				assertThat(contentAsString(resultGet)).isEqualTo(
						"found: test_db");
			}
		});
	}

	@Test
	public void testFindByName()
	{
		running(fakeApplication(), new Runnable()
		{
			@Override
			public void run()
			{
				String _Name = "test_db";

				Result _Result = callAction(nl.scorius.presentation.controllers.routes.ref.SubscriptionController
						.findByName(_Name));
				assertThat(status(_Result)).isEqualTo(OK);
				assertThat(contentAsString(_Result)).isEqualTo("found");
			}
		});
	}
}

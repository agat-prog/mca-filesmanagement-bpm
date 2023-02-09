package mca.filesmanagement.bpm.domain;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import mca.filesmanagement.bpm.domain.process.FactoryProcessAggregate;

@ExtendWith(SpringExtension.class)
@Tag("UnitTest")
@DisplayName("Factory tests")
public class FactoryProviderTest {

	/** Test factory providers. */
	@Test
	@DisplayName("Test factory providers")
	public void givenAFactoryProvidersWhenCreateThenReturnProcessAggregate() {
		IFactoryProcessAggregate factoryProcessAggregate = FactoryProvider.getFactoryProcessAggregate();
		assertNotNull(factoryProcessAggregate);
		assertTrue(FactoryProcessAggregate.class.isInstance(factoryProcessAggregate));
	}
}

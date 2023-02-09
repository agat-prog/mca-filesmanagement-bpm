package mca.filesmanagement.bpm.infraestructure.model;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import mca.filesmanagement.bpm.commons.PhaseCodeEnum;
import net.sf.beanrunner.BeanRunner;

public class ModelTest {

	/** Getters y setters. */
	@Test
    public void testGettersAndSetters() throws Exception {
		BeanRunner beanRunner = new BeanRunner();
		beanRunner.addTestValue(PhaseCodeEnum.class, PhaseCodeEnum.INICIAL);
		beanRunner.addTestValue(PhaseEntity.class, new PhaseEntity());
		beanRunner.testBean(new PhaseEntity());

		beanRunner = new BeanRunner();
		beanRunner.addTestValue(PhaseInstanceEntity.class, new PhaseInstanceEntity());
		beanRunner.testBean(new PhaseInstanceEntity());

		PhaseInstanceEntity entity = new PhaseInstanceEntity();
		PhaseEntity phaseEntity = new PhaseEntity();
		phaseEntity.setDescription("Descripcion");
		assertEquals(entity.getPhaseCode(), PhaseCodeEnum.NULL);
		assertNull(entity.getPhaseDescription());

		entity.setPhase(phaseEntity);
		assertNotNull(entity.getPhaseCode());
		assertNotNull(entity.getPhaseDescription());

		beanRunner = new BeanRunner();
		beanRunner.addTestValue(ProcesEntity.class, new ProcesEntity());
		beanRunner.testBean(new ProcesEntity());
	}
}

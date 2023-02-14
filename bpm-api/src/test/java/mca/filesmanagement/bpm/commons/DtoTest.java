package mca.filesmanagement.bpm.commons;

import org.junit.jupiter.api.Test;

import net.sf.beanrunner.BeanRunner;

public class DtoTest {

	/** Getters y setters. */
	@Test
    public void testGettersAndSetters() throws Exception {
		BeanRunner beanRunner = new BeanRunner();
		beanRunner.addTestValue(PhaseCodeEnum.class, PhaseCodeEnum.ANALISIS_TECNICO);
		beanRunner.addTestValue(PhaseDto.class, new PhaseDto());
		beanRunner.testBean(new PhaseDto());

		beanRunner = new BeanRunner();
		beanRunner.addTestValue(PhaseCodeEnum.class, PhaseCodeEnum.ANALISIS_TECNICO);
		beanRunner.addTestValue(PhaseInstanceDto.class, new PhaseInstanceDto());
		beanRunner.testBean(new PhaseInstanceDto());
	}
}

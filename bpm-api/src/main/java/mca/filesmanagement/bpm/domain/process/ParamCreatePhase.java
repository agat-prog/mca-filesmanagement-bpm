package mca.filesmanagement.bpm.domain.process;

import java.util.Date;

import mca.filesmanagement.bpm.commons.PHASE_CODE;

public record ParamCreatePhase(Long Id, 
								PHASE_CODE code, 
								String description, 
								Date date,
								Date dateFinished,
								String user, 
								String userFinished) {
}

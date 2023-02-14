package mca.filesmanagement.bpm.domain.process;

import java.util.Date;

import mca.filesmanagement.bpm.commons.PhaseCodeEnum;

/**
 * DTO para la creación de una fase.
 * @param Id identificador único.
 * @param code código externo único.
 * @param description descripción de la fase.
 * @param date Fecha de creación de la fase.
 * @param dateFinished Fecha de finalización de la fase (not null).
 * @param user usuario que inicia la fase.
 * @param userFinished usuario que finaliza la fase (not null).
 * @author agat
 */
public record ParamCreatePhase(Long Id, PhaseCodeEnum code, String description,
		Date date, Date dateFinished, String user, String userFinished) {
}

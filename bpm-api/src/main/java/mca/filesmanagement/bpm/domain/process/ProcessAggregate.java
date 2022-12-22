package mca.filesmanagement.bpm.domain.process;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bpm.api.messaging.events.ProcessUpdatedEvent;
import mca.filesmanagement.bpm.commons.BpmException;
import mca.filesmanagement.bpm.commons.PHASE_CODE;
import mca.filesmanagement.bpm.port.out.IProcesRepository;
import mca.filesmanagement.bpm.port.out.IPublicationService;

public class ProcessAggregate {

	private String id;
	private boolean active = true;
	private Date date = new Date();
	private List<Phase> phases = new ArrayList<>();
	private IPublicationService publisher;
	private IProcesRepository procesRepository;
	
	public ProcessAggregate(String id) {
		super();
		
		this.id = id;
	}
	
	public void afterCreated() {
		this.publishProcessUpdateEvent();
	}
	
	public void next(String user, PHASE_CODE toPhase) throws BpmException {
		Phase newPhase = FactoryPhase.create(toPhase);
		newPhase.setUser(user);
		newPhase.setDescription(this.procesRepository.findByCode(newPhase.getCode()).getDescription());
		newPhase.setHasChanged(true);
		
		this.getActualPhase().next(newPhase);
		this.phases.add(newPhase);
		
		if (newPhase.isFinish()) {
			this.deactivate();
		}
		
		this.publishProcessUpdateEvent();
	}
	
	private void publishProcessUpdateEvent() {
		ProcessUpdatedEvent event = new ProcessUpdatedEvent();
		event.setCode(this.id);
		event.setDate(this.date);
		event.setPhaseCode(getActualPhase().getCode().name());
		event.setPhaseName(getActualPhase().getDescription());
		this.publisher.notify(this, event);
	}
	
	public void addPhase(ParamCreatePhase param) {
		Phase phase = FactoryPhase.create(param.code());
		phase.setDate(param.date());
		phase.setDateFinished(param.dateFinished());
		phase.setDescription(param.description());
		phase.setId(param.Id());
		phase.setUser(param.user());
		phase.setUserFinished(param.userFinished());
		
		// La fase introducida debe tener fecha posterior a la última introducida
		if (!this.isDateAfter(phase)) {
			throw new IllegalArgumentException();
		}
		
		this.phases.add(phase);
	}
	
	public Phase getActualPhase() {
		if (!this.phases.isEmpty()) {
			return this.phases.get(this.phases.size() - 1);
		}
		else {
			throw new IllegalArgumentException();
		}
	}
	
	private boolean isDateAfter(Phase phase) {
		boolean question = true;
		if (!this.phases.isEmpty()
				&& phase.getDate().before(this.phases.get(0).getDate())) {
			question = false;
		}
		return question;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	protected void deactivate() {
		this.active = false;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the phases
	 */
	public List<Phase> getPhasesChanged() {
		return phases.stream().filter(phase -> phase.isHasChanged()).toList();
	}

	/**
	 * @param publisher the publisher to set
	 */
	public void setPublisher(IPublicationService publisher) {
		this.publisher = publisher;
	}

	/**
	 * @param procesRepository the procesRepository to set
	 */
	public void setProcesRepository(IProcesRepository procesRepository) {
		this.procesRepository = procesRepository;
	}
}

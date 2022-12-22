package mca.filesmanagement.bpm.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mca.filesmanagement.bpm.commons.BpmException;
import mca.filesmanagement.bpm.commons.PhaseDto;
import mca.filesmanagement.bpm.commons.ProcesDto;
import mca.filesmanagement.bpm.service.BpmService;

@RestController
@RequestMapping("/api/bpm")
public class BpmController extends AbstractController {

	private static Logger LOGGER = LoggerFactory.getLogger(BpmController.class);
	
	@Autowired
	private BpmService bpmService;
	
	public BpmController() {
		super();
	}
	
	@PostMapping
	public ResponseEntity<?> next(@RequestParam (required = true , value = "procesCode") String procesCode
								, @RequestParam (required = true , value = "phaseCode") String phaseCode){
		String user = this.getUserName();
		LOGGER.info(String.format("El usuario '%s' está ejecutando BpmController.next (%s, %s)", user, procesCode, phaseCode));
		
		try {
			this.bpmService.nextPhase(user, procesCode, phaseCode);
		} 
		catch (BpmException e) {
			e.printStackTrace();
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok().build();
	}
	
	@GetMapping(path = "/{processCode}")
	public ResponseEntity<ProcesDto> findByCode(@PathVariable(name = "processCode", required = true) String processCode){
		LOGGER.info(String.format("BpmController.findByCode: processCode -> %s", processCode));
		return ResponseEntity.ok(this.bpmService.findByCode(processCode));
	}
	
	@GetMapping(path = "/availablePhases/{phaseCode}")
	public ResponseEntity<List<PhaseDto>> availablePhase(@PathVariable(name = "phaseCode", required = true) String phaseCode){
		return ResponseEntity.ok(this.bpmService.availablePhases(phaseCode));
	}
}

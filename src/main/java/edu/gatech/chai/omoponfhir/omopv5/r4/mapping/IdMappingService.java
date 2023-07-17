/*******************************************************************************
 * Copyright (c) 2019 Georgia Tech Research Institute
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package edu.gatech.chai.omoponfhir.omopv5.r4.mapping;

import edu.gatech.chai.omopv5.dba.service.IdPairService;
import edu.gatech.chai.omopv5.model.entity.IdPair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * ID Mapping Class to manage the IDs between FHIR and OMOP.
 * 
 * @author mc142
 *
 */
@Service
public class IdMappingService {
	static final Logger logger = LoggerFactory.getLogger(IdMappingService.class);

	@Autowired
	private IdPairService idPairService;

	public String getFHIRfromOMOP(Long omopId, String resourceName) {
		return idPairService.findByOmopId(omopId)
				.map(IdPair::getFhirId)
				.orElse(null);
	}

	/**
	 * What is OMOP ID for the long part of FHIR ID
	 * @param fhirId
	 * @return
	 */
	public Long getOMOPfromFHIR(String fhirId, String resourceName) {
		Optional<IdPair> mapping = idPairService.findByFhirId(fhirId);

		if (mapping.isEmpty()) {
			return writeMapping(fhirId, resourceName).getOmopId();
		}

		return mapping.get().getOmopId();
	}

	private IdPair writeMapping(String fhirId, String resourceName) {
		logger.info("Creating mapping for a {} with FHIR id {}", resourceName, fhirId);
		return idPairService.create(new IdPair(fhirId));
	}
}

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ID Mapping Class to manage the IDs between FHIR and OMOP.
 * 
 * @author mc142
 *
 */
public class IdMapping {
	static final Logger logger = LoggerFactory.getLogger(IdMapping.class);

	public static String getFHIRfromOMOP(Long omopId, String resourceName) {
		// We use the same ID for now.
		// TODO: Develop the ID mapping so that we do not reveal native
		//       OMOP ID. If the mapping exists, send it. If not, create a new
		//       mapping.
		
		return omopId.toString();
	}

	/**
	 * What is OMOP ID for the long part of FHIR ID
	 * @param fhirId
	 * @return
	 */
	public static Long getOMOPfromFHIR(String fhirId, String resourceName) {
		// We use the same ID now.
		// TODO: Develop the ID mapping so that we do not reveal native
		//       OMOP ID.
		
		return Long.valueOf(fhirId);
	}

	public static void writeMapping(String fhirId, String resourceName, Long omopId) {
		logger.info("Creating mapping for a {} with FHIR id {} and OMOP id {}", resourceName, fhirId, omopId);
	}
	
	public static void writeOMOPfromFHIR(String fhirId) {
		// Placeholder for later to use to store OMOP ID mapping info.
		// This information will be used by getFHIRfromOMOP
		// TODO: Develop mapping creation here.
	}
}

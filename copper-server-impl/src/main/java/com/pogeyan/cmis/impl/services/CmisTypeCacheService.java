/**
 * Copyright 2017 Pogeyan Technologies
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.pogeyan.cmis.impl.services;

import java.util.Arrays;
import java.util.List;
import org.apache.chemistry.opencmis.commons.definitions.PropertyDefinition;
import org.apache.chemistry.opencmis.commons.definitions.TypeDefinition;
import org.apache.chemistry.opencmis.commons.impl.TypeCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pogeyan.cmis.api.data.IBaseObject;
import com.pogeyan.cmis.impl.utils.DBUtils;

public class CmisTypeCacheService implements TypeCache {
	private static final Logger LOG = LoggerFactory.getLogger(CmisTypeCacheService.class);
	private final String repositoryId;

	CmisTypeCacheService(String repositoryId) {
		this.repositoryId = repositoryId;
	}

	@Override
	public TypeDefinition getTypeDefinition(String typeId) {
		List<? extends TypeDefinition> typeDef = DBUtils.TypeServiceDAO.getById(this.repositoryId,
				Arrays.asList(typeId), null);
		return typeDef != null ? typeDef.get(0) : null;
	}

	@Override
	public TypeDefinition reloadTypeDefinition(String typeId) {
		List<? extends TypeDefinition> typeDef = DBUtils.TypeServiceDAO.getById(this.repositoryId,
				Arrays.asList(typeId), null);
		return typeDef != null ? typeDef.get(0) : null;
	}

	@Override
	public TypeDefinition getTypeDefinitionForObject(String objectId) {
		IBaseObject object = DBUtils.BaseDAO.getByObjectId(repositoryId, null, false, objectId, null, null);
		if (object != null) {
			List<? extends TypeDefinition> typeDef = DBUtils.TypeServiceDAO.getById(this.repositoryId,
					Arrays.asList(object.getTypeId()), null);
			return typeDef != null ? typeDef.get(0) : null;
		}
		return null;
	}

	@Override
	public PropertyDefinition<?> getPropertyDefinition(String propId) {
		Long startTime = System.currentTimeMillis();
		PropertyDefinition<?> property = DBUtils.TypeServiceDAO.getAllPropertyById(this.repositoryId, propId, null);
		Long totalTime = System.currentTimeMillis() - startTime;
		if (LOG.isDebugEnabled()) {	
			LOG.debug("Method Name: getPropertyDefinition, TotalTime: {} ms", totalTime);
		}
		return property != null ? property : null;
	}

	public static TypeCache get(String repositoryId) {
		return new CmisTypeCacheService(repositoryId);
	}
}

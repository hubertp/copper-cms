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
package com.pogeyan.cmis.repo.local;

import javax.management.modelmbean.InvalidTargetObjectTypeException;

import com.pogeyan.cmis.api.auth.IAuthFactory;
import com.pogeyan.cmis.api.auth.IAuthService;
import com.pogeyan.cmis.api.auth.IAuthStoreSettings;

public class LocalRepoAuthFactory implements IAuthFactory {

	@Override
	public IAuthStoreSettings getStoreSetting() {
		IAuthStoreSettings authStoreSetting = new LocalStoreSetting();
		return authStoreSetting;
	}

	@Override
	public IAuthService getAuthService(IAuthStoreSettings authStoreSetting) throws InvalidTargetObjectTypeException {
		LocalAuthService authService = new LocalAuthService();
		authService.setAuthStoreSettings(authStoreSetting);
		return authService;
	}

}

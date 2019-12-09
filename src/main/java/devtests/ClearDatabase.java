/*
 * Copyright 2019 TopicQuests
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 */
package devtests;

import org.topicquests.asr.general.GeneralDatabaseEnvironment;
import org.topicquests.asr.sentence.api.ISentenceClient;

/**
 * @author jackpark
 *
 */
public class ClearDatabase {
	private GeneralDatabaseEnvironment environment;
	private ISentenceClient client;

	/**
	 * 
	 */
	public ClearDatabase() {
		String schemaName = "tqos_asr";
		environment = new GeneralDatabaseEnvironment(schemaName);
		
		environment.clearDatabase();
		System.out.println("Did "+client);
		environment.shutDown();
		System.exit(0);
	}

}

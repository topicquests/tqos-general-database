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
import org.topicquests.asr.general.document.api.IDocumentClient;
import org.topicquests.support.api.IResult;

/**
 * @author jackpark
 *
 */
public class ListTest {
	private GeneralDatabaseEnvironment environment;
	private IDocumentClient client;

	/**
	 * 
	 */
	public ListTest() {
		String schemaName = "tqos_asr";
		environment = new GeneralDatabaseEnvironment(schemaName);
		client = environment.getDocumentClient();
		environment.logDebug("StartListTest");
		IResult r = client.listDocuments(0, 5);
		System.out.println("ERX "+r.getErrorString());
		System.out.println("DID "+r.getResultObject());
		
		environment.shutDown();
		System.exit(0);
	}

}

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

import net.minidev.json.JSONObject;

/**
 * @author jackpark
 *
 */
public class DocumentTest {
	private GeneralDatabaseEnvironment environment;
	private IDocumentClient client;
	private final String
		DOC1	= "FirstDoc",
		DOC2	= "SecondDoc";

	/**
	 * 
	 */
	public DocumentTest() {
		String schemaName = "tqos_asr";
		environment = new GeneralDatabaseEnvironment(schemaName);
		client = environment.getDocumentClient();
		JSONObject doc = new JSONObject();
		doc.put("id", DOC1);
		IResult r = client.put(DOC1, DOC1, doc);
		System.out.println("A "+r.getErrorString()+" | "+r.getResultObject());
		//A  | 1
		doc = new JSONObject();
		doc.put("id", DOC2);
		r = client.put(DOC2, DOC2, doc);
		System.out.println("B "+r.getErrorString()+" | "+r.getResultObject());
		//B  | 1
		
		r = client.get(DOC1);
		System.out.println("C "+r.getErrorString()+" | "+r.getResultObject());
		//E  | {"id":"BastTa"}
		r = client.listDocuments(0, 1);
		System.out.println("D "+r.getErrorString()+" | "+r.getResultObject());
		r = client.listDocuments(1, 1);
		System.out.println("DD "+r.getErrorString()+" | "+r.getResultObject());
		r = client.size();
		System.out.println("E "+r.getErrorString()+" | "+r.getResultObject());
		environment.shutDown();
		System.exit(0);	
	}

}

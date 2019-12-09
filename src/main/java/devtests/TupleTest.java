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
import org.topicquests.asr.general.tuple.api.ITupleClient;
import org.topicquests.support.api.IResult;

import net.minidev.json.JSONObject;

/**
 * @author jackpark
 *
 */
public class TupleTest {
	private GeneralDatabaseEnvironment environment;
	private ITupleClient client;
	private final String
		TUP1	= "FirstTuple",
		TUP2	= "SecondTuple";

	/**
	 * 
	 */
	public TupleTest() {
		String schemaName = "tqos_asr";
		environment = new GeneralDatabaseEnvironment(schemaName);
		client = environment.getTupleClient();
		JSONObject doc = new JSONObject();
		doc.put("id", TUP1);
		IResult r = client.put(TUP1, doc);
		System.out.println("A "+r.getErrorString()+" | "+r.getResultObject());
		//A  | 1
		doc = new JSONObject();
		doc.put("id", TUP2);
		r = client.put(TUP2, doc);
		System.out.println("B "+r.getErrorString()+" | "+r.getResultObject());
		//B  | 1
		
		r = client.get(TUP1);
		System.out.println("C "+r.getErrorString()+" | "+r.getResultObject());
		//E  | {"id":"BastTa"}
		r = client.listTuples(0, -1);
		System.out.println("D "+r.getErrorString()+" | "+r.getResultObject());
		r = client.listTuples(0, 1);
		System.out.println("DD "+r.getErrorString()+" | "+r.getResultObject());
		r = client.listTuples(1, 1);
		System.out.println("DDD "+r.getErrorString()+" | "+r.getResultObject());
		r = client.size();
		System.out.println("E "+r.getErrorString()+" | "+r.getResultObject());
		environment.shutDown();
		System.exit(0);	
	}

}

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
package org.topicquests.asr.paragraph.api;

import org.topicquests.support.api.IResult;

import net.minidev.json.JSONObject;

/**
 * @author jackpark
 *
 */
public interface IParagraphClient {

	IResult put(String paraId, JSONObject paragraph);
	
	IResult update(String paraId, JSONObject paragraph);
	
	IResult get(String paraId);
	
	IResult remove(String paraId);
	
	/**
	 * count = -1 means ALL
	 * @param start
	 * @param count
	 * @return
	 */
	IResult listparagraphs(int start, int count);
	
	/**
	 * Dangerous: empties the table
	 * @return
	 */
	IResult clearTable();

	IResult size();

	void shutDown();
}

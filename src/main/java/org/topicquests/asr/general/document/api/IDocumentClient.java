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
package org.topicquests.asr.general.document.api;

import org.topicquests.support.api.IResult;

import net.minidev.json.JSONObject;

/**
 * @author jackpark
 *
 */
public interface IDocumentClient {
	
	/**
	 * 
	 * @param docId
	 * @param pmid
	 * @param pmcid
	 * @param url
	 * @param label can not be <code>null</code>
	 * @param document
	 * @return 
	 */
	IResult put(String docId, String pmid, String pmcid, String url, String label, JSONObject document);
	
	IResult update(String docId, JSONObject document);
	
	IResult get(String docId);
	
	/**
	 * Can return nothing or a single document, or a list of documents
	 * @param label
	 * @return returns a List of JSONObjects or empty
	 */
	IResult findByLabel(String label);
	
	/**
	 * Should only return one doc if any
	 * @param url
	 * @return
	 */
	IResult findByURL(String url);
	
	/**
	 * Should only return one doc if any
	 * @param pmid
	 * @return
	 */
	IResult findByPMID(String pmid);

	IResult findByPMCID(String pmcid);

	/**
	 * Should only return one doc if any
	 * @param docId
	 * @return
	 */
	IResult remove(String docId);
	
	/**
	 * count = -1 means ALL
	 * @param start
	 * @param count
	 * @return
	 */
	IResult listDocuments(int start, int count);
	
	/**
	 * Dangerous: empties the table
	 * @return
	 */
	IResult clearTable();

	IResult size();

	void shutDown();
}

/**
 * 
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
	 * @param label can not be <code>null</code>
	 * @param document
	 * @return 
	 */
	IResult put(String docId, String label, JSONObject document);
	
	IResult update(String docId, JSONObject document);
	
	IResult get(String docId);
	
	/**
	 * Can return nothing or a single document, or a list of documents
	 * @param label
	 * @return returns a List of JSONObjects or empty
	 */
	IResult findByLabel(String label);
	
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

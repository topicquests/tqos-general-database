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
	
	IResult put(String docId, JSONObject document);
	
	IResult update(String docId, JSONObject document);
	
	IResult get(String docId);
	
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

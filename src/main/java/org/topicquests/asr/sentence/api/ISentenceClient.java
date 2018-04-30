/**
 * 
 */
package org.topicquests.asr.sentence.api;

import org.topicquests.support.api.IResult;

import net.minidev.json.JSONObject;

/**
 * @author jackpark
 *
 */
public interface ISentenceClient {

	/**
	 * 
	 * @param docId   required
	 * @param paragraphId can be <code>null</code>
	 * @param sentenceId required
	 * @param sentence
	 * @return
	 */
	IResult put(String docId, String paragraphId, String sentenceId, JSONObject sentence);
	
	IResult update(String sentenceId, JSONObject sentence);
	
	IResult get(String sentenceId);
	
	IResult remove(String sentenceId);
	
	/**
	 * Paragraph Ids are UUIDs
	 * @param paragraphId
	 * @return
	 */
	IResult listByParagraphId(String paragraphId);
	
	/**
	 * DocIds are URI-like or UUID: typically based on, e.g. PMIDs
	 * @param docId
	 * @return
	 */
	IResult listByDocId(String docId);
	
	IResult size();
	
	/**
	 * Dangerous: empties the table
	 * @return
	 */
	IResult clearTable();
	
	void shutDown();
}

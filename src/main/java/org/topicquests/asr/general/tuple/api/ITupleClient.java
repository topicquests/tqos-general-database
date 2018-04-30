/**
 * 
 */
package org.topicquests.asr.general.tuple.api;

import org.topicquests.support.api.IResult;

import net.minidev.json.JSONObject;

/**
 * @author jackpark
 *
 */
public interface ITupleClient {

	IResult put(String tupleId, JSONObject tuple);
	
	IResult update(String tupleId, JSONObject tuple);
	
	IResult get(String tupleId);
	
	IResult remove(String tupleId);
	
	/**
	 * count = -1 means ALL
	 * @param start
	 * @param count
	 * @return
	 */
	IResult listTuples(int start, int count);
	
	/**
	 * Dangerous: empties the table
	 * @return
	 */
	IResult clearTable();

	IResult size();

	void shutDown();
}

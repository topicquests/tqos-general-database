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
package org.topicquests.asr.sentence;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.topicquests.asr.general.GeneralDatabaseEnvironment;
import org.topicquests.asr.general.api.IGeneralSchema;
import org.topicquests.asr.sentence.api.ISentenceClient;
import org.topicquests.pg.PostgresConnectionFactory;
import org.topicquests.pg.api.IPostgresConnection;
import org.topicquests.support.ResultPojo;
import org.topicquests.support.api.IResult;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;

/**
 * @author jackpark
 *
 */
public class SentenceClient implements ISentenceClient {
	private GeneralDatabaseEnvironment environment;
	private PostgresConnectionFactory provider;

	/**
	 * 
	 */
	public SentenceClient(GeneralDatabaseEnvironment env, PostgresConnectionFactory p) {
		environment = env;
		provider = p;
	}

	/* (non-Javadoc)
	 * @see org.topicquests.asr.sentence.api.ISentenceClient#put(java.lang.String, java.lang.String, java.lang.String, net.minidev.json.JSONObject)
	 */
	@Override
	public IResult put(String docId, String paragraphId, String sentenceId, JSONObject sentence) {
		IResult result = new ResultPojo();
	    IPostgresConnection conn = null;
	    IResult r = null;
	    try {
	    	conn = provider.getConnection();
			r = conn.beginTransaction();
			if (r.hasError())
				result.addErrorString(r.getErrorString());
			conn.setProxyRole(r);
			if (r.hasError())
				result.addErrorString(r.getErrorString());
			String sql = IGeneralSchema.INSERT_SENTENCE;
			Object [] vals = new Object[4];
			vals[0] = sentenceId;
			vals[1] = docId;
			vals[2] = paragraphId;
			vals[3] = sentence.toJSONString();
			conn.executeSQL(sql, r, vals);
			if (r.hasError())
				result.addErrorString(r.getErrorString());
		} catch (Exception e) {
			environment.logError(e.getMessage(), e);
			result.addErrorString(e.getMessage());
			e.printStackTrace();
		}
	    conn.endTransaction(r);
		if (r.hasError())
			result.addErrorString(r.getErrorString());
	    conn.closeConnection(r);
		if (r.hasError())
			result.addErrorString(r.getErrorString());
		return result;
	}

	/* (non-Javadoc)
	 * @see org.topicquests.asr.sentence.api.ISentenceClient#update(java.lang.String, net.minidev.json.JSONObject)
	 */
	@Override
	public IResult update(String sentenceId, JSONObject sentence) {
		IResult result = new ResultPojo();
	    IPostgresConnection conn = null;
	    IResult r = null;
	    try {
	    	conn = provider.getConnection();
			r = conn.beginTransaction();
			if (r.hasError())
				result.addErrorString(r.getErrorString());
			conn.setProxyRole(r);
			if (r.hasError())
				result.addErrorString(r.getErrorString());
			String sql = IGeneralSchema.UPDATE_SENTENCE;
			Object [] vals = new Object[2];
			vals[0] = sentence.toJSONString();
			vals[1] = sentenceId;
			conn.executeUpdate(sql, r, vals);
			if (r.hasError())
				result.addErrorString(r.getErrorString());
		} catch (Exception e) {
			environment.logError(e.getMessage(), e);
			result.addErrorString(e.getMessage());
			e.printStackTrace();
		}
	    conn.endTransaction(r);
		if (r.hasError())
			result.addErrorString(r.getErrorString());
	    conn.closeConnection(r);
		if (r.hasError())
			result.addErrorString(r.getErrorString());
		return result;
	}

	/* (non-Javadoc)
	 * @see org.topicquests.asr.sentence.api.ISentenceClient#get(java.lang.String)
	 */
	@Override
	public IResult get(String sentenceId) {
		IResult result = new ResultPojo();
	    IPostgresConnection conn = null;
	    IResult r = new ResultPojo();
	    try {
	    	conn = provider.getConnection();
			conn.setProxyRole(r);
			if (r.hasError())
				result.addErrorString(r.getErrorString());
			String sql = IGeneralSchema.GET_SENTENCE;
			conn.executeSelect(sql, r, sentenceId);
			ResultSet rs = (ResultSet)r.getResultObject();
			environment.logDebug("SentenceClient.get "+rs+"  | "+r.getErrorString());
			if (rs != null && rs.next()) {
				String json = rs.getString("sentence");
				environment.logDebug("SentenceClient.get-1 "+json);
				JSONParser p = new JSONParser(JSONParser.MODE_JSON_SIMPLE);
				JSONObject jo = (JSONObject)p.parse(json);
				result.setResultObject(jo);
			}
		} catch (Exception e) {
			environment.logError(e.getMessage(), e);
			result.addErrorString(e.getMessage());
			e.printStackTrace();
		}
	    conn.closeConnection(r);
		if (r.hasError())
			result.addErrorString(r.getErrorString());
		return result;
	}

	/* (non-Javadoc)
	 * @see org.topicquests.asr.sentence.api.ISentenceClient#remove(java.lang.String)
	 */
	@Override
	public IResult remove(String sentenceId) {
		IResult result = new ResultPojo();
	    IPostgresConnection conn = null;
	    IResult r = null;
	    try {
	    	conn = provider.getConnection();
			r = conn.beginTransaction();
			if (r.hasError())
				result.addErrorString(r.getErrorString());
			conn.setProxyRole(r);
			if (r.hasError())
				result.addErrorString(r.getErrorString());
			String sql = IGeneralSchema.REMOVE_SENTENCE;
			conn.executeSQL(sql, r, sentenceId);
			if (r.hasError())
				result.addErrorString(r.getErrorString());
		} catch (Exception e) {
			environment.logError(e.getMessage(), e);
			result.addErrorString(e.getMessage());
			e.printStackTrace();
		}
	    conn.endTransaction(r);
		if (r.hasError())
			result.addErrorString(r.getErrorString());
	    conn.closeConnection(r);
		if (r.hasError())
			result.addErrorString(r.getErrorString());
		return result;
	}

	/* (non-Javadoc)
	 * @see org.topicquests.asr.sentence.api.ISentenceClient#listByParagraphId(java.lang.String)
	 */
	@Override
	public IResult listByParagraphId(String paragraphId) {
		IResult result = new ResultPojo();
	    IPostgresConnection conn = null;
	    IResult r = new ResultPojo();
	    try {
	    	conn = provider.getConnection();
			conn.setProxyRole(r);
			if (r.hasError())
				result.addErrorString(r.getErrorString());
			String sql = IGeneralSchema.LIST_BY_PARA;
			conn.executeSelect(sql, r, paragraphId);
			if (r.hasError())
				result.addErrorString(r.getErrorString());
			ResultSet rs = (ResultSet)r.getResultObject();
			if (rs != null) {
				String json;
				JSONParser p;
				JSONObject jo;
				List<JSONObject> sents = new ArrayList<JSONObject>();
				result.setResultObject(sents);
				while (rs.next()) {
					json = rs.getString("sentence");
					p = new JSONParser(JSONParser.MODE_JSON_SIMPLE);
					jo = (JSONObject)p.parse(json);
					sents.add(jo);
				}
			}
		} catch (Exception e) {
			environment.logError(e.getMessage(), e);
			result.addErrorString(e.getMessage());
			e.printStackTrace();
		}
	    conn.closeConnection(r);
		if (r.hasError())
			result.addErrorString(r.getErrorString());
		return result;
	}

	/* (non-Javadoc)
	 * @see org.topicquests.asr.sentence.api.ISentenceClient#listByDocId(java.lang.String)
	 */
	@Override
	public IResult listByDocId(String docId) {
		IResult result = new ResultPojo();
	    IPostgresConnection conn = null;
	    IResult r = new ResultPojo();
	    try {
	    	conn = provider.getConnection();
			String sql = IGeneralSchema.LIST_BY_DOC;
			conn.executeSelect(sql, r, docId);
			ResultSet rs = (ResultSet)r.getResultObject();
			environment.logDebug("SentenceClient.listByDocId "+docId+" "+rs+"  | "+r.getErrorString());
			if (rs != null) {
				String json;
				JSONParser p;
				JSONObject jo;
				List<JSONObject> sents = new ArrayList<JSONObject>();
				result.setResultObject(sents);
				while (rs.next()) {
					json = rs.getString(1);
					p = new JSONParser(JSONParser.MODE_JSON_SIMPLE);
					jo = (JSONObject)p.parse(json);
					sents.add(jo);
				}
			}
		} catch (Exception e) {
			environment.logError(e.getMessage(), e);
			result.addErrorString(e.getMessage());
			e.printStackTrace();
		}
	    conn.closeConnection(r);
		if (r.hasError())
			result.addErrorString(r.getErrorString());
		return result;
	}
	
	@Override
	public IResult clearTable() {
		IResult result = new ResultPojo();
	    IPostgresConnection conn = null;
	    IResult r = null;
	    try {
	    	conn = provider.getConnection();
			r = conn.beginTransaction();
			if (r.hasError())
				result.addErrorString(r.getErrorString());
			conn.setProxyRole(r);
			if (r.hasError())
				result.addErrorString(r.getErrorString());
			String sql = IGeneralSchema.CLEAR_SENTENCE;
			conn.executeSQL(sql, r);
			
		} catch (Exception e) {
			environment.logError(e.getMessage(), e);
			result.addErrorString(e.getMessage());
			e.printStackTrace();
		} 
	    conn.endTransaction(r);
		if (r.hasError())
			result.addErrorString(r.getErrorString());
	    conn.closeConnection(r);
		if (r.hasError())
			result.addErrorString(r.getErrorString());
		return result;
	}
	
	@Override
	public IResult size() {
		IResult result = new ResultPojo();
	    IPostgresConnection conn = null;
	    IResult r = new ResultPojo();
	    try {
	    	conn = provider.getConnection();
			String sql = IGeneralSchema.SIZE_SENTENCE;
			conn.executeSelect(sql, r);
			ResultSet rs = (ResultSet)r.getResultObject();
			environment.logDebug("SentenceClient.size "+rs+"  | "+r.getErrorString());
			long val = 0;
			if (rs != null && rs.next()) {
					val = rs.getLong("count");
			}
			result.setResultObject(new Long(val));
	
		} catch (Exception e) {
			environment.logError(e.getMessage(), e);
			result.addErrorString(e.getMessage());
			e.printStackTrace();
		} 
	    conn.closeConnection(r);
		if (r.hasError())
			result.addErrorString(r.getErrorString());
		return result;
	}
	
	@Override
	public void shutDown() {
		//
	}





}

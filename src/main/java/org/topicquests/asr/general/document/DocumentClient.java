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
package org.topicquests.asr.general.document;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.topicquests.asr.general.GeneralDatabaseEnvironment;
import org.topicquests.asr.general.api.IGeneralSchema;
import org.topicquests.asr.general.document.api.IDocumentClient;
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
public class DocumentClient implements IDocumentClient {
	private GeneralDatabaseEnvironment environment;
	private PostgresConnectionFactory provider;

	/**
	 * 
	 */
	public DocumentClient(GeneralDatabaseEnvironment env, PostgresConnectionFactory p) {
		environment = env;
		provider = p;
	}

	/* (non-Javadoc)
	 * @see org.topicquests.asr.general.document.api.IDocumentClient#put(java.lang.String, net.minidev.json.JSONObject)
	 */
	@Override
	public IResult put(String docId, String pmid, String pmcid, String url, String label, JSONObject document) {
		environment.logDebug("DocumentClient.put "+docId+" "+label);
		String urx = "";
		if (url != null) urx = url; 
		String pmx = "";
		if (pmid != null)
			pmx = pmid;
		String pmy = "";
		if (pmcid != null)
			pmy = pmcid;
		IResult result = new ResultPojo();
	    IPostgresConnection conn = null;
	    IResult r = null;
	    try {
	    	conn = provider.getConnection();
			r = conn.beginTransaction();
			conn.setProxyRole(r);
			String sql = IGeneralSchema.INSERT_DOCUMENT;
			Object [] vals = new Object [6];
			vals[0] = docId;
			vals[1] = label;
			vals[2] = urx;
			vals[3] = pmx;
			vals[4] = pmy;
			vals[5] = document.toJSONString();
			conn.executeSQL(sql, r, vals);
			environment.logDebug("DocumentClient.put "+r.getErrorString()+" "+r.getResultObject());
		} catch (Exception e) {
			environment.logError(e.getMessage(), e);
			result.addErrorString(e.getMessage());
			e.printStackTrace();
		}
	    conn.endTransaction(r);
	    conn.closeConnection(r);
		if (r.hasError())
			result.addErrorString(r.getErrorString());
		return result;
	}

	/* (non-Javadoc)
	 * @see org.topicquests.asr.general.document.api.IDocumentClient#update(java.lang.String, net.minidev.json.JSONObject)
	 */
	@Override
	public IResult update(String docId, JSONObject document) {
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
			String sql = IGeneralSchema.UPDATE_DOCUMENT;
			Object [] vals = new Object[2];
			vals[0] = document.toJSONString();
			vals[1] = docId;
			conn.executeUpdate(sql, r, vals);
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
	 * @see org.topicquests.asr.general.document.api.IDocumentClient#get(java.lang.String)
	 */
	@Override
	public IResult get(String docId) {
		IResult result = new ResultPojo();
	    IPostgresConnection conn = null;
	    IResult r = new ResultPojo();
	    try {
	    	conn = provider.getConnection();
			String sql = IGeneralSchema.GET_DOCUMENT;
			conn.executeSelect(sql, r, docId);
			ResultSet rs = (ResultSet)r.getResultObject();
			if (rs != null && rs.next()) {
				String json = rs.getString("document");
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
	 * @see org.topicquests.asr.general.document.api.IDocumentClient#remove(java.lang.String)
	 */
	@Override
	public IResult remove(String docId) {
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
			String sql = IGeneralSchema.REMOVE_DOCUMENT;
			conn.executeSQL(sql, r, docId);
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
	 * @see org.topicquests.asr.general.document.api.IDocumentClient#listDocuments(int, int)
	 */
	@Override
	public IResult listDocuments(int start, int count) {
		IResult result = new ResultPojo();
	    IPostgresConnection conn = null;
	    IResult r = new ResultPojo();
	    try {
	    	conn = provider.getConnection();
			String sql;
			Object [] vals = null;
			if (count < 0)
				sql = IGeneralSchema.LIST_DOCUMENTS;
			else {
				sql = IGeneralSchema.LIST_DOCUMENTS_FULL;
				vals = new Object[2];
				vals[0] = start;
				vals[1] = count;
			}			
			System.out.println("DocumentClient.listDocuments "+start+" "+count+" "+sql);
			if (count < 0)
				conn.executeSelect(sql, r);
			else
				conn.executeSelect(sql, r, vals);
			ResultSet rs = (ResultSet)r.getResultObject();
			environment.logDebug("DocumentClient.listDocuments "+rs+"  | "+r.getErrorString());
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
			String sql = IGeneralSchema.CLEAR_DOCUMENT;
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
			String sql = IGeneralSchema.SIZE_DOCUMENT;
			conn.executeSelect(sql, r);
			ResultSet rs = (ResultSet)r.getResultObject();
			environment.logDebug("DocumentClient.size "+rs+"  | "+r.getErrorString());
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

	@Override
	public IResult findByLabel(String label) {
		IResult result = new ResultPojo();
		List<JSONObject> docs = new ArrayList<JSONObject>();
		result.setResultObject(docs);
	    IPostgresConnection conn = null;
	    IResult r = new ResultPojo();
	    try {
	    	conn = provider.getConnection();
			String sql = IGeneralSchema.FIND_DOCUMENT;
			conn.executeSelect(sql, r, label);
			ResultSet rs = (ResultSet)r.getResultObject();
			if (rs != null) {
				while( rs.next()) {
					String json = rs.getString("document");
					JSONParser p = new JSONParser(JSONParser.MODE_JSON_SIMPLE);
					JSONObject jo = (JSONObject)p.parse(json);
					docs.add(jo);
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
		return result;	}

}

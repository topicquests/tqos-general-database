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
package org.topicquests.asr.paragraph;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.topicquests.asr.general.GeneralDatabaseEnvironment;
import org.topicquests.asr.general.api.IGeneralSchema;
import org.topicquests.asr.paragraph.api.IParagraphClient;
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
public class ParagraphClient implements IParagraphClient {
	private GeneralDatabaseEnvironment environment;
	private PostgresConnectionFactory provider;

	/**
	 * @param env
	 * @param p
	 */
	public ParagraphClient(GeneralDatabaseEnvironment env, PostgresConnectionFactory p) {
		environment = env;
		provider = p;
	}

	@Override
	public IResult put(String paraId, JSONObject paragraph) {
		IResult result = new ResultPojo();
	    IPostgresConnection conn = null;
	    IResult r = null;
	    try {
	    	conn = provider.getConnection();
			r = conn.beginTransaction();
			conn.setProxyRole(r);
			String sql = IGeneralSchema.INSERT_PARAGRAPH;
			Object [] vals = new Object[2];
			vals[0] = paraId;
			vals[1] = paragraph.toJSONString();
			conn.executeSQL(sql, r, vals);
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

	@Override
	public IResult update(String paraId, JSONObject paragraph) {
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
			String sql = IGeneralSchema.UPDATE_PARAGRAPH;
			Object [] vals = new Object[2];
			vals[0] = paragraph.toJSONString();
			vals[1] = paraId;
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

	@Override
	public IResult get(String paraId) {
		IResult result = new ResultPojo();
	    IPostgresConnection conn = null;
	    IResult r = new ResultPojo();
	    try {
	    	conn = provider.getConnection();
			conn.setProxyRole(r);
			String sql = IGeneralSchema.GET_PARAGRAPH;
			conn.executeSelect(sql, r, paraId);
			ResultSet rs = (ResultSet)r.getResultObject();
			if (rs != null && rs.next()) {
				String json = rs.getString("tuple");
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

	@Override
	public IResult remove(String paraId) {
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
			String sql = IGeneralSchema.REMOVE_PARAGRAPH;
			conn.executeSQL(sql, r, paraId);
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

	@Override
	public IResult listparagraphs(int start, int count) {
		IResult result = new ResultPojo();
	    IPostgresConnection conn = null;
	    IResult r = new ResultPojo();
	    try {
	    	conn = provider.getConnection();
			conn.setProxyRole(r);
			String sql = IGeneralSchema.LIST_PARAGRAPHS;
			int siz = 1;
			if (count > 0)
				siz ++;
			Object [] vals = new Object[siz];
			vals[0] = start;
			if (siz == 2) {
				sql += " LIMIT ?";
				vals[1] = count;
			}

			conn.executeSelect(sql, r, vals);
			ResultSet rs = (ResultSet)r.getResultObject();
			environment.logDebug("TupleClient.listParagraphs "+rs+" "+r.getErrorString());
			if (rs != null) {
				String json;
				JSONParser p;
				JSONObject jo;
				List<JSONObject> sents = new ArrayList<JSONObject>();
				result.setResultObject(sents);
				while (rs.next()) {
					json = rs.getString("tuple");
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
			String sql = IGeneralSchema.CLEAR_PARAGRAPH;
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
			String sql = IGeneralSchema.SIZE_PARAGRAPH;
			conn.executeSelect(sql, r);
			ResultSet rs = (ResultSet)r.getResultObject();
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
		// TODO Auto-generated method stub
		
	}

}

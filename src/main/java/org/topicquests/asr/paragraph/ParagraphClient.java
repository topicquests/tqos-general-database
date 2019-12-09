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

import org.topicquests.asr.general.GeneralDatabaseEnvironment;
import org.topicquests.asr.paragraph.api.IParagraphClient;
import org.topicquests.pg.PostgresConnectionFactory;
import org.topicquests.support.api.IResult;

import net.minidev.json.JSONObject;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResult update(String paraId, JSONObject paragraph) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResult get(String paraId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResult remove(String paraId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResult listparagraphs(int start, int count) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResult clearTable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResult size() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void shutDown() {
		// TODO Auto-generated method stub
		
	}

}

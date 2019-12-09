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
package org.topicquests.asr.general.api;

/**
 * @author jackpark
 *
 */
public interface IGeneralSchema {
	
	//see asr_schema.sql

	public static final String
		INSERT_SENTENCE =
			"INSERT INTO tqos_asr.sentences VALUES(?, ?, ?, ?)";
	public static final String
		GET_SENTENCE =
			"SELECT sentence FROM tqos_asr.sentences WHERE id = ?";
	public static final String
		REMOVE_SENTENCE =
			"DELETE FROM tqos_asr.sentences WHERE id = ?";
	public static final String
		UPDATE_SENTENCE =
			"UPDATE tqos_asr.sentences SET sentence = ? WHERE id = ?";
	public static final String
		LIST_BY_DOC =
			"SELECT sentence FROM tqos_asr.sentences WHERE docid = ?";
	public static final String
		LIST_BY_PARA =
			"SELECT sentence FROM tqos_asr.sentences WHERE parid = ?";
	public static final String
		CLEAR_SENTENCE =
			"DELETE FROM tqos_asr.sentences";
	public static final String
		SIZE_SENTENCE =
			"SELECT count(id) FROM tqos_asr.sentences";
	public static final String
		INSERT_DOCUMENT =
			"INSERT INTO tqos_asr.documents VALUES(?, ?, ?)";
	public static final String
		GET_DOCUMENT =
			"SELECT document FROM tqos_asr.documents WHERE id = ?";
	public static final String
		FIND_DOCUMENT =
			"SELECT document FROM tqos_asr.documents WHERE label = ?";

	public static final String
		REMOVE_DOCUMENT =
			"DELETE FROM tqos_asr.documents WHERE id = ?";
	public static final String
		UPDATE_DOCUMENT =
			"UPDATE tqos_asr.documents SET document = ? WHERE id=?";
	public static final String
		LIST_DOCUMENTS_FULL = 
			"SELECT document FROM tqos_asr.documents OFFSET ? LIMIT ?";
	public static final String
		LIST_DOCUMENTS = 
			"SELECT document FROM tqos_asr.documents";
	public static final String
		CLEAR_DOCUMENT =
			"DELETE FROM tqos_asr.documents";
	public static final String
		SIZE_DOCUMENT =
			"SELECT count(id) FROM tqos_asr.documents";
	public static final String
		INSERT_TUPLE =
			"INSERT INTO tqos_asr.tuples VALUES(?, ?)";
	public static final String
		GET_TUPLE =
			"SELECT tuple FROM tqos_asr.tuples WHERE id = ?";
	public static final String
		REMOVE_TUPLE =
			"DELETE FROM tqos_asr.tuples WHERE id = ?";
	public static final String
		UPDATE_TUPLE =
			"UPDATE tqos_asr.tuple SET tuple = ? WHERE id = ?";
	public static final String
		LIST_TUPLES_FULL = 
			"SELECT tuple FROM tqos_asr.tuples OFFSET ?";
	public static final String
		CLEAR_TUPLE =
			"DELETE FROM tqos_asr.tuples";
	public static final String
		SIZE_TUPLE =
			"SELECT count(id) FROM tqos_asr.tuples";
	public static final String
		INSERT_PARAGRAPH =
			"INSERT INTO tqos_asr.paragraphs VALUES(?, ?, ?, ?)";
	public static final String
		GET_PARAGRAPH =
			"SELECT paragraph FROM tqos_asr.paragraphs WHERE id = ?";
	public static final String
		REMOVE_PARAGRAPH =
			"DELETE FROM tqos_asr.paragraphs WHERE id = ?";
	public static final String
		UPDATE_PARAGRAPH =
			"UPDATE tqos_asr.paragraphs SET sentence = ? WHERE id = ?";
	public static final String
		CLEAR_PARAGRAPH =
			"DELETE FROM tqos_asr.paragraphs";
	public static final String
		SIZE_PARAGRAPH =
			"SELECT count(id) FROM tqos_asr.paragraphs";
}

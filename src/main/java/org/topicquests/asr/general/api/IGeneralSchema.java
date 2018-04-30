/**
 * 
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
			"UPDATE sentences SET tqos_asr.sentences = ? WHERE id = ?";
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
			"SELECT count(*) FROM tqos_asr.sentences";
	public static final String
		INSERT_DOCUMENT =
			"INSERT INTO tqos_asr.documents VALUES(?, ?)";
	public static final String
		GET_DOCUMENT =
			"SELECT document FROM tqos_asr.documents WHERE id = ?";
	public static final String
		REMOVE_DOCUMENT =
			"DELETE FROM tqos_asr.documents WHERE id = ?";
	public static final String
		UPDATE_DOCUMENT =
			"UPDATE tqos_asr.documents SET document = ? WHERE id=?";
	public static final String
		LIST_DOCUMENTS_FULL = 
			"SELECT document FROM tqos_asr.documents ORDER BY id OFFSET ? LIMIT ?";
	public static final String
		LIST_DOCUMENTS = 
			"SELECT document FROM tqos_asr.documents ORDER BY id OFFSET ?";
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
			"UPDATE tuples SET tqos_asr.tuple = ? WHERE id = ?";
	public static final String
		LIST_TUPLES_FULL = 
			"SELECT tuple FROM tqos_asr.tuples ORDER BY id LIMIT ? OFFSET ?";
	public static final String
		CLEAR_TUPLE =
			"DELETE FROM tqos_asr.tuples";
	public static final String
		SIZE_TUPLE =
			"SELECT count(id) FROM tqos_asr.tuples";

}

/**
 * 
 */
package org.topicquests.asr.general;

import org.topicquests.asr.general.api.IGeneralSchema;
import org.topicquests.asr.general.document.DocumentClient;
import org.topicquests.asr.general.document.api.IDocumentClient;
import org.topicquests.asr.general.tuple.TupleClient;
import org.topicquests.asr.general.tuple.api.ITupleClient;
import org.topicquests.asr.sentence.SentenceClient;
import org.topicquests.asr.sentence.api.ISentenceClient;
import org.topicquests.pg.PostgreSqlProvider;
import org.topicquests.pg.api.IPostgreSqlProvider;
import org.topicquests.support.RootEnvironment;
import org.topicquests.support.api.IResult;

/**
 * @author jackpark
 *
 */
public class GeneralDatabaseEnvironment extends RootEnvironment {
	private IPostgreSqlProvider provider;
	private ISentenceClient sentenceClient;
	private IDocumentClient documentClient;
	private ITupleClient tupleClient;
	
	/**
	 * Clients who use this must provide a <code>schemaName</code> which
	 * must already be present in PostgresQL
	 * @param schemaName 
	 */
	public GeneralDatabaseEnvironment(String schemaName) {
		super("postgress-props.xml", "logger.properties");
		String dbName = getStringProperty("DatabaseName");
		provider = new PostgreSqlProvider(dbName, schemaName);
		sentenceClient = new SentenceClient(this, provider);
		documentClient = new DocumentClient(this, provider);
		tupleClient = new TupleClient(this, provider);
	}
	
	public ISentenceClient getSentenceClient() {
		return sentenceClient;
	}
	
	public IDocumentClient getDocumentClient() {
		return documentClient;
	}
	
	public ITupleClient getTupleClient() {
		return tupleClient;
	}
	
	public void clearDatabase() {
		IResult x = sentenceClient.clearTable();
		x = documentClient.clearTable();
		x = tupleClient.clearTable();
		System.out.println("SentenceDatabaseEnvironment.clearDatabase "+x.getErrorString()+" "+x.getResultObject());
	}
	
	public void shutDown() {
		sentenceClient.shutDown();
		documentClient.shutDown();
		tupleClient.shutDown();
	}

}

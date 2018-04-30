/**
 * 
 */
package devtests;

import org.topicquests.asr.general.GeneralDatabaseEnvironment;
import org.topicquests.asr.sentence.api.ISentenceClient;

/**
 * @author jackpark
 * NOTE: schemaName is based on tqos-asr-core
 * If different database is used, then schemaName must reflect that.
 * In any case, the database which uses that schema must already
 * be in PostgreSQL
 */
public class BootTest {
	private GeneralDatabaseEnvironment environment;
	private ISentenceClient client;

	/**
	 * 
	 */
	public BootTest() {
		String schemaName = "tqos_asr";
		environment = new GeneralDatabaseEnvironment(schemaName);
		client = environment.getSentenceClient();
		System.out.println("Did "+client);
		environment.shutDown();
		System.exit(0);
	}

}

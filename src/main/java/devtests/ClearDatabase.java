/**
 * 
 */
package devtests;

import org.topicquests.asr.general.GeneralDatabaseEnvironment;
import org.topicquests.asr.sentence.api.ISentenceClient;

/**
 * @author jackpark
 *
 */
public class ClearDatabase {
	private GeneralDatabaseEnvironment environment;
	private ISentenceClient client;

	/**
	 * 
	 */
	public ClearDatabase() {
		String schemaName = "tqos_asr";
		environment = new GeneralDatabaseEnvironment(schemaName);
		
		environment.clearDatabase();
		System.out.println("Did "+client);
		environment.shutDown();
		System.exit(0);
	}

}

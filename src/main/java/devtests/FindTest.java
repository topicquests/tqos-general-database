/**
 * 
 */
package devtests;

import org.topicquests.asr.general.GeneralDatabaseEnvironment;
import org.topicquests.asr.general.document.api.IDocumentClient;
import org.topicquests.support.api.IResult;

import net.minidev.json.JSONObject;

/**
 * @author jackpark
 * Reuses documents set in {@link DocumentTest}
 */
public class FindTest {
	private GeneralDatabaseEnvironment environment;
	private IDocumentClient client;
	private final String
		DOC1	= "FirstDoc",
		DOC2	= "SecondDoc";

	/**
	 * 
	 */
	public FindTest() {
		String schemaName = "tqos_asr";
		environment = new GeneralDatabaseEnvironment(schemaName);
		client = environment.getDocumentClient();
		IResult r = client.findByLabel(DOC1);
		System.out.println("a "+r.getErrorString()+" | "+r.getResultObject());
		r = client.findByLabel(DOC2);
		System.out.println("b "+r.getErrorString()+" | "+r.getResultObject());
		environment.shutDown();
		System.exit(0);	
	}

}

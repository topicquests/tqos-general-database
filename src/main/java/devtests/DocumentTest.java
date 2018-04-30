/**
 * 
 */
package devtests;

import org.topicquests.asr.general.GeneralDatabaseEnvironment;
import org.topicquests.asr.general.document.api.IDocumentClient;
import org.topicquests.asr.sentence.api.ISentenceClient;
import org.topicquests.support.api.IResult;

import net.minidev.json.JSONObject;

/**
 * @author jackpark
 *
 */
public class DocumentTest {
	private GeneralDatabaseEnvironment environment;
	private IDocumentClient client;
	private final String
		DOC1	= "FirstDoc",
		DOC2	= "SecondDoc";

	/**
	 * 
	 */
	public DocumentTest() {
		String schemaName = "tqos_asr";
		environment = new GeneralDatabaseEnvironment(schemaName);
		client = environment.getDocumentClient();
		JSONObject doc = new JSONObject();
		doc.put("id", DOC1);
		IResult r = client.put(DOC1, doc);
		System.out.println("A "+r.getErrorString()+" | "+r.getResultObject());
		//A  | 1
		doc = new JSONObject();
		doc.put("id", DOC2);
		r = client.put(DOC2, doc);
		System.out.println("B "+r.getErrorString()+" | "+r.getResultObject());
		//B  | 1
		
		r = client.get(DOC1);
		System.out.println("C "+r.getErrorString()+" | "+r.getResultObject());
		//E  | {"id":"BastTa"}
		r = client.listDocuments(0, 2);
		System.out.println("D "+r.getErrorString()+" | "+r.getResultObject());
		environment.shutDown();
		System.exit(0);	}

}

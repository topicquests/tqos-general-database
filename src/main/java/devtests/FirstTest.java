/**
 * 
 */
package devtests;

import org.topicquests.asr.general.GeneralDatabaseEnvironment;
import org.topicquests.asr.sentence.api.ISentenceClient;
import org.topicquests.support.api.IResult;

import net.minidev.json.JSONObject;

/**
 * @author jackpark
 *
 */
public class FirstTest {
	private GeneralDatabaseEnvironment environment;
	private ISentenceClient client;
	private final String
		DOC1	= "FirstDoc",
		DOC2	= "SecondDoc",
		DOC1P1  = "12345",
		DOC1P1S1	= "AbcDef",
		DOC1P1S2	= "AbcDeg",
		DOC2P1	= "122467",
		DOC2P2	= "122468",
		DOC2P1S1	= "BastT",
		DOC2P2S1	= "BastTa";


	/**
	 * 
	 */
	public FirstTest() {
		String schemaName = "tqos_asr";
		environment = new GeneralDatabaseEnvironment(schemaName);
		client = environment.getSentenceClient();
		JSONObject sentence = new JSONObject();
		sentence.put("id", DOC1P1S1);
		IResult r = client.put(DOC1, DOC1P1, DOC1P1S1, sentence);
		System.out.println("A "+r.getErrorString()+" | "+r.getResultObject());
		//A  | 1
		sentence = new JSONObject();
		sentence.put("id", DOC1P1S2);
		r = client.put(DOC1, DOC1P1, DOC1P1S2, sentence);
		System.out.println("B "+r.getErrorString()+" | "+r.getResultObject());
		//B  | 1
		sentence = new JSONObject();
		sentence.put("id", DOC2P1S1);
		r = client.put(DOC2, DOC2P1, DOC2P1S1, sentence);
		System.out.println("C "+r.getErrorString()+" | "+r.getResultObject());
		//C  | 1
		sentence = new JSONObject();
		sentence.put("id", DOC2P2S1);
		r = client.put(DOC2, DOC2P2, DOC2P2S1, sentence);
		System.out.println("D "+r.getErrorString()+" | "+r.getResultObject());
		//D  | 1
		r = client.get(DOC2P2S1);
		System.out.println("E "+r.getErrorString()+" | "+r.getResultObject());
		//E  | {"id":"BastTa"}
		r = client.listByDocId(DOC1);
		System.out.println("F "+r.getErrorString()+" | "+r.getResultObject());
		//F  | [{"id":"AbcDef"}, {"id":"AbcDeg"}]
		r = client.listByDocId(DOC2);
		System.out.println("G "+r.getErrorString()+" | "+r.getResultObject());
		//G  | [{"id":"BastT"}, {"id":"BastTa"}]
		r = client.listByParagraphId(DOC1P1);
		System.out.println("H "+r.getErrorString()+" | "+r.getResultObject());
		//H  | [{"id":"AbcDef"}, {"id":"AbcDeg"}]
		r = client.size();
		System.out.println("I "+r.getErrorString()+" | "+r.getResultObject());
		//I  | 4
		environment.shutDown();
		System.exit(0);
	}

}

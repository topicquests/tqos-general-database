/**
 * 
 */
package devtests;

import org.topicquests.asr.general.GeneralDatabaseEnvironment;
import org.topicquests.asr.general.tuple.api.ITupleClient;
import org.topicquests.support.api.IResult;

import net.minidev.json.JSONObject;

/**
 * @author jackpark
 *
 */
public class TupleTest {
	private GeneralDatabaseEnvironment environment;
	private ITupleClient client;
	private final String
		TUP1	= "FirstTuple",
		TUP2	= "SecondTuple";

	/**
	 * 
	 */
	public TupleTest() {
		String schemaName = "tqos_asr";
		environment = new GeneralDatabaseEnvironment(schemaName);
		client = environment.getTupleClient();
		JSONObject doc = new JSONObject();
		doc.put("id", TUP1);
		IResult r = client.put(TUP1, doc);
		System.out.println("A "+r.getErrorString()+" | "+r.getResultObject());
		//A  | 1
		doc = new JSONObject();
		doc.put("id", TUP2);
		r = client.put(TUP2, doc);
		System.out.println("B "+r.getErrorString()+" | "+r.getResultObject());
		//B  | 1
		
		r = client.get(TUP1);
		System.out.println("C "+r.getErrorString()+" | "+r.getResultObject());
		//E  | {"id":"BastTa"}
		r = client.listTuples(0, -1);
		System.out.println("D "+r.getErrorString()+" | "+r.getResultObject());
		r = client.listTuples(0, 1);
		System.out.println("DD "+r.getErrorString()+" | "+r.getResultObject());
		r = client.listTuples(1, 1);
		System.out.println("DDD "+r.getErrorString()+" | "+r.getResultObject());
		r = client.size();
		System.out.println("E "+r.getErrorString()+" | "+r.getResultObject());
		environment.shutDown();
		System.exit(0);	
	}

}

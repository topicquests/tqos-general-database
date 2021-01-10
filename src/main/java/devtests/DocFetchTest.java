/**
 * 
 */
package devtests;

import org.topicquests.asr.general.GeneralDatabaseEnvironment;
import org.topicquests.asr.general.document.api.IDocumentClient;
import org.topicquests.support.api.IResult;

/**
 * @author jackpark
 *
 */
public class DocFetchTest {
	private GeneralDatabaseEnvironment environment;
	private IDocumentClient client;
	private final String ID = "rx_GoBkLEeqmklOfbl7xLg"; //"PsAahiKOEeqzqdc3-QFV8w";
	/**
	 * 
	 */
	public DocFetchTest() {
		String schemaName = "tqos_asr";
		environment = new GeneralDatabaseEnvironment(schemaName);
		client = environment.getDocumentClient();
		IResult r = client.get(ID);
		System.out.println("A "+r.getResultObject());
		
		environment.shutDown();
		System.exit(0);

	}

}
/*
{
	"tagList": ["miR-1", "Autophagy", "Interferon-b"],
	"ParagraphPropertyType": {
		"42f74d2a-ebae-4eac-9175-0f96c4f0ff33": {
			"para": "\"We deleted miR-1 in the worm and looked at the effect in a preclinical model of Huntington's and found that when you don't have this microRNA there's more aggregation,\" he said. \"This suggested miR-1 was important to remove Huntington's aggregates.\"",
			"docId": "rx_GoBkLEeqmklOfbl7xLg",
			"id": "42f74d2a-ebae-4eac-9175-0f96c4f0ff33",
			"lang": "en"
		},
		"2c517e44-9c18-4557-b982-90758f0fc789": {
			"para": "The researchers then showed that miR-1 helped protect against toxic protein aggregates by controlling the expression of the TBC-7 protein in worms. This protein regulates the process of autophagy, the body's way of removing and recycling damaged cells and is crucial for clearing toxic proteins from cells.\n\"When you don't have miR-1, autophagy doesn't work correctly and you have aggregation of these Huntington's proteins in worms,\" Associate Professor Pocock said.\nProfessor Rubinsztein then conducted research which showed that the same microRNA regulates a related pathway to control autophagy in human cells.\n\"Expressing more miR-1 removes Huntington's aggregates in human cells,\" Associate Professor Pocock said.\n\"It's a novel pathway that can control these aggregation-prone proteins. As a potential means of alleviating neurodegenerative disease, it's up there,\" he said.\nAdditional work by Associate Professor Pocock's colleagues showed that when human cells are supplied with a molecule called interferon-b the miR-1 pathway is upregulated, revealing a way of manipulating it.",
			"docId": "rx_GoBkLEeqmklOfbl7xLg",
			"id": "2c517e44-9c18-4557-b982-90758f0fc789",
			"lang": "en"
		},
		"ce650d55-c722-4014-8f64-ae3ae9386224": {
			"para": "MicroRNAs, short strands of genetic material, are tiny but powerful molecules that regulate many different genes simultaneously. The scientists sought to identify particular microRNAs that are important for regulating protein aggregates and homed in on miR-1, which is found in low levels in patients with neurodegenerative diseases such as Parkinson's disease.",
			"docId": "rx_GoBkLEeqmklOfbl7xLg",
			"id": "ce650d55-c722-4014-8f64-ae3ae9386224",
			"lang": "en"
		},
		"a275d100-5265-4b33-bc90-6802e4988b40": {
			"para": "The researchers then showed that miR-1 helped protect against toxic protein aggregates by controlling the expression of the TBC-7 protein in worms. This protein regulates the process of autophagy, the body's way of removing and recycling damaged cells and is crucial for clearing toxic proteins from cells.\n\"When you don't have miR-1, autophagy doesn't work correctly and you have aggregation of these Huntington's proteins in worms,\" Associate Professor Pocock said.\nProfessor Rubinsztein then conducted research which showed that the same microRNA regulates a related pathway to control autophagy in human cells.\n\"Expressing more miR-1 removes Huntington's aggregates in human cells,\" Associate Professor Pocock said.\n\"It's a novel pathway that can control these aggregation-prone proteins. As a potential means of alleviating neurodegenerative disease, it's up there,\" he said.\nAdditional work by Associate Professor Pocock's colleagues showed that when human cells are supplied with a molecule called interferon-b the miR-1 pathway is upregulated, revealing a way of manipulating it.",
			"docId": "rx_GoBkLEeqmklOfbl7xLg",
			"id": "a275d100-5265-4b33-bc90-6802e4988b40",
			"lang": "en"
		}
	},
	"crtr": "Gardener@hypothes.is",
	"dateString": "2019-12-07T16:07:36.039377+00:00",
	"id": "rx_GoBkLEeqmklOfbl7xLg",
	"type": "AnnotationType",
	"title": "Ancient worm reveals way to destroy toxic cells in Huntington's disease",
	"paragraphs": [{
		"text": "The researchers then showed that miR-1 helped protect against toxic protein aggregates by controlling the expression of the TBC-7 protein in worms. This protein regulates the process of autophagy, the body's way of removing and recycling damaged cells and is crucial for clearing toxic proteins from cells.\n\"When you don't have miR-1, autophagy doesn't work correctly and you have aggregation of these Huntington's proteins in worms,\" Associate Professor Pocock said.\nProfessor Rubinsztein then conducted research which showed that the same microRNA regulates a related pathway to control autophagy in human cells.\n\"Expressing more miR-1 removes Huntington's aggregates in human cells,\" Associate Professor Pocock said.\n\"It's a novel pathway that can control these aggregation-prone proteins. As a potential means of alleviating neurodegenerative disease, it's up there,\" he said.\nAdditional work by Associate Professor Pocock's colleagues showed that when human cells are supplied with a molecule called interferon-b the miR-1 pathway is upregulated, revealing a way of manipulating it.",
		"lang": "en"
	}],
	"url": "https:\/\/phys.org\/news\/2019-12-ancient-worm-reveals-toxic-cells.html"
}

*/
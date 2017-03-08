package eu.hyvar.feature.graphical.configurator.reconfigurator;

import java.net.URI;
import java.nio.channels.UnresolvedAddressException;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.client.util.StringContentProvider;
import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.PlatformUI;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import eu.hyvar.context.HyContextModel;
import eu.hyvar.context.contextValidity.HyValidityModel;
import eu.hyvar.context.information.contextValue.HyContextValueModel;
import eu.hyvar.feature.HyFeatureModel;
import eu.hyvar.feature.configuration.HyConfiguration;
import eu.hyvar.feature.constraint.HyConstraintModel;
import eu.hyvar.preferences.HyPreferenceModel;
import eu.hyvar.reconfigurator.input.exporter.HyVarRecExporter;
import eu.hyvar.reconfigurator.output.translation.HyVarRecOutputTranslator;
import eu.hyvar.reconfigurator.output.translation.format.OutputOfHyVarRec;

public class HyReconfiguratorClient {
	private static final String MSG_TYPE_RAW_HYVARREC = "raw_hyvarrec_input";

	private static final String MSG_TYPE_HYVARREC_CONFIG_2_HYCONFIG = "hyvarrec_config_plus_fm";

	private static final String FILENAME = "HyVarUseCaseReview";

	HttpClient client;
	URI uri;

	GsonBuilder builder = new GsonBuilder();
	Gson gson = builder.create();

	String json = "";

	Request request;

	ContentResponse response;
	String answerString;
	
	public HyConfiguration reconfigure(URI uri, HyContextModel contextModel, HyValidityModel contextValidityModel,
			HyFeatureModel featureModel, HyConstraintModel constraintModel, HyConfiguration oldConfiguration,
			HyPreferenceModel preferenceModel, HyContextValueModel contextValues, Date date) {
		
		HyVarRecExporter exporter = new HyVarRecExporter();
		String messageForHyVarRec = exporter.exportContextMappingModel(contextModel, contextValidityModel, featureModel, constraintModel, oldConfiguration, preferenceModel, contextValues, date);
		
		
//		InputForHyVarRec answer = gson.fromJson(answerString, InputForHyVarRec.class);
//		System.out.println("Input for HyVarRec: "+answerString);
//
//		// send it to HyVarRec
//		String hyvarrecJson = gson.toJson(answer);
		HttpClient hyvarrecClient = new HttpClient();
		try {
			hyvarrecClient.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		URI hyvarrecUri = uri;
		Request hyvarrecRequest = hyvarrecClient.POST(hyvarrecUri);
		hyvarrecRequest.header(HttpHeader.CONTENT_TYPE, "application/json");
		hyvarrecRequest.content(new StringContentProvider(messageForHyVarRec), "application/json");
		ContentResponse hyvarrecResponse;
		String hyvarrecAnswerString = "";
		try {
			hyvarrecResponse = hyvarrecRequest.send();
			hyvarrecAnswerString = hyvarrecResponse.getContentAsString();
		} catch (UnresolvedAddressException | ExecutionException e){
			MessageDialog dialog = new MessageDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Unresolvable Server Adress", null,
					"The adress '"+uri.toString()+"' could not be resolved. No configuration was generated.", MessageDialog.ERROR, new String[] { "Ok" }, 0);
			dialog.open();
			
			return null;
		} catch (InterruptedException | TimeoutException e) {
			//e.printStackTrace();
			return null;
		} 

		// Only for Debug
		System.out.println("HyVarRec Answer: "+hyvarrecAnswerString);
		
		OutputOfHyVarRec hyvarrecAnswer = gson.fromJson(hyvarrecAnswerString, OutputOfHyVarRec.class);
//		hyvarrecAnswer.get
		
		HyConfiguration configuration = HyVarRecOutputTranslator.translateConfiguration(featureModel, hyvarrecAnswer, date);
		
		return configuration;
	}

}

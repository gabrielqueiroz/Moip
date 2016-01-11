package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.Webhook;

/**
 * 
 * Classe responsavel pela analise de webhooks presentes em um log.
 * 
 * @author Gabriel Queiroz Ï
 */
public class VerifyLog {
	/**
	 * Recebe um Log contendo um ou mais Webhooks, retornando uma lista de
	 * objetos Webhooks.
	 * 
	 * @param log
	 * @return List<Webhook>
	 */
	public List<Webhook> getWebhook(List<String> log) {
		List<Webhook> listWebhooks = new ArrayList<>();

		for (String line : log) {
			if (isWebhook(line)) {
				Webhook webhook = new Webhook();
				String[] aux = line.split("\\s+(?![^\\[]*\\])");

				for (String temp : aux) {
					if (temp.contains("level"))
						webhook.setLevel(temp.substring(6, temp.length()));
					else if (temp.contains("response_body"))
						webhook.setResponseBody(temp.substring(15,
								temp.length() - 1));
					else if (temp.contains("request_to"))
						webhook.setRequestTo(temp.substring(12,
								temp.length() - 1));
					else if (temp.contains("response_status"))
						webhook.setResponseStatus(temp.substring(17,
								temp.length() - 1));
					else if (temp.contains("response_headers"))
						webhook.setResponseHeaders(temp.substring(17,
								temp.length()));
					else
						webhook.setResponseHeaders(webhook.getResponseHeaders()
								+ " " + temp);
				}

				listWebhooks.add(webhook);
			}
		}
		return listWebhooks;
	}

	/**
	 * Funcao para verificar se um dado log possui caracteristicas de um webhook
	 * 
	 * @param log
	 * @return false caso nao possua um dos elementos necessarios para um
	 *         webhook
	 */
	public boolean isWebhook(String log) {
		if (!log.contains("response_body") || !log.contains("request_to")
				|| !log.contains("response_status")
				|| !log.contains("response_body")
				|| !log.contains("response_headers") || !log.contains("status"))
			return false;
		return true;
	}

	/**
	 * Funcao para relacionar as informacoes relativas aos parametros de uma
	 * lista de Webhooks
	 * 
	 * @param listWebhooks
	 *            lista contendo objetos webhooks
	 * @param param
	 *            atributo de um webhook a ser verificado. level, responseBody,
	 *            requestTo, status, responseHeaders
	 * @param pos
	 *            numero de posicoes desejadas. Caso 0, sera mostrada todas as
	 *            opcoes possiveis
	 */
	public String getStatistics(List<Webhook> listWebhooks, String param,
			int pos) {
		List<String> aux = new ArrayList<>();

		switch (param) {
		case "level":
			for (Webhook webhook : listWebhooks)
				aux.add(webhook.getLevel());
			break;
		case "responseBody":
			for (Webhook webhook : listWebhooks)
				aux.add(webhook.getResponseBody());
			break;
		case "requestTo":
			for (Webhook webhook : listWebhooks)
				aux.add(webhook.getRequestTo());
			break;
		case "status":
			for (Webhook webhook : listWebhooks)
				aux.add(webhook.getResponseStatus());
			break;
		case "responseHeaders":
			for (Webhook webhook : listWebhooks)
				aux.add(webhook.getResponseHeaders());
			break;
		default:
			return "Parametro invalido";
		}

		Set<String> unique = new HashSet<String>(aux);
		List<String> ranked = new ArrayList<String>();

		for (String key : unique)
			ranked.add(Collections.frequency(aux, key) + " % " + key);

		Collections.sort(ranked, Collections.reverseOrder());
		String result = "";

		if (pos == 0 || pos >= ranked.size())
			for (int i = 0; i < ranked.size(); i++)
				result = result + swapString(ranked.get(i)) + "\n";
		else
			for (int i = 0; i < pos; i++)
				result = result + swapString(ranked.get(i)) + "\n";

		return result;
	}

	/**
	 * Formata uma string "x % y" para "y - x".
	 * 
	 * @param String
	 *            que sera formatada
	 * @return String formatada.
	 */
	public String swapString(String string) {
		try {
			String[] aux = string.split("%");
			string = aux[1] + " - " + aux[0];
			return string;
		} catch (ArrayIndexOutOfBoundsException e) {
			return string + " Exception de formatacao:" + e.toString();
		}
	}
}

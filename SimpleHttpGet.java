package watchbotpinger.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SimpleHttpGet {

    public static class HttpResponse {

        public final int code;
        public final String response;

        public HttpResponse(int code, String response) {
            this.code = code;
            this.response = response;
        }
    }

    public static HttpResponse readURL(String source) throws Exception {        
        URL url = new URL(source);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(30*1000);
        connection.setReadTimeout(30*1000);
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        int code = connection.getResponseCode();
        String response = "";
        String t = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        while ((t = br.readLine()) != null) {
            response += t + "\n";
        }

        return new HttpResponse(code, response);
    }

}

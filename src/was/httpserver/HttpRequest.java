package was.httpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

public class HttpRequest {
    private String method;
    private String path;
    private final Map<String, String> queryParameters = new HashMap<>();
    private final Map<String, String> headers = new HashMap<>();

    public HttpRequest(BufferedReader reader) throws IOException {
        parseRequestLine(reader); // Request line
        parseHeaders(reader); // Header

    }

    // Request Line
    // GET /search?q=hello HTTP/1.1
    private void parseRequestLine(BufferedReader reader) throws IOException {
        String requestLine = reader.readLine();

        if(requestLine == null) {
            throw new IOException("EOF : No request line received");
        }

        String[] parts = requestLine.split(" ");

        if(parts.length != 3) {
            throw  new IOException("Invalid request line : " + requestLine);
        }

        method = parts[0];
        String[] pathParts = parts[1].split("\\?"); // /search?q=hello
        path = pathParts[0];

        // key1=value1&key2=value2
        if(pathParts.length > 1) {
            parseQueryParameters(pathParts[1]);
        }
    }

    // key1=value1&key2=value2
    private void parseQueryParameters(String queryString) {
        for (String param : queryString.split("&")) {
            String[] keyValue = param.split("=");

            String key = URLDecoder.decode(keyValue[0], UTF_8);
            String value = keyValue.length > 1 ? URLDecoder.decode(keyValue[1], UTF_8) : "";

            queryParameters.put(key, value);
        }
    }

    // Host: ...
    // Connection: ...
    //
    private void parseHeaders(BufferedReader reader) throws IOException {
        String line;
        while (!(line = reader.readLine()).isEmpty()) {
            String[] headerParts = line.split(":");
            headers.put(headerParts[0].trim(), headerParts[1].trim());
        }
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public String getParameter(String name) {
        return queryParameters.get(name);
    }

    public String getHeader(String name) {
        return headers.get(name);
    }

    @Override
    public String toString() {
        return "HttpRequest{" +
                "method='" + method + '\'' +
                ", path='" + path + '\'' +
                ", queryParameters=" + queryParameters +
                ", headers=" + headers +
                '}';
    }
}

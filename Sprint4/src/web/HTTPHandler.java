/*
package web;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.*;


public class HTTPHandler {

    // a shared area where we get the POST data and then use it in the other handler
    static String sharedResponse = "data=[{\"title\":\"Mrs.\",\"firstName\":\"wqewq\",\"lastName\":\"bbbbb\",\"department\":\"aaaaa\",\"phoneNumber\":\"23113\",\"gender\":\"Male\"},{\"title\":\"Col.\",\"firstName\":\"wqeq\",\"lastName\":\"aaaaa\",\"department\":\"bbbbb\",\"phoneNumber\":\"3243324\",\"gender\":\"Female\"}]\n";
    static boolean gotMessageFlag = false;

    public static void main(String[] args) throws Exception {

        // set up a simple HTTP server on our local host
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        // create a context to get the request to display the results
        server.createContext("/displayresults/lastname", new DisplayHandlerLastName());
        server.createContext("/displayresults/firstname", new DisplayHandlerFirstName());
        server.createContext("/displayresults/department", new DisplayHandlerDepartment());
        server.createContext("/displayresults/mystyle.css", new CSSHandler());

        // create a context to get the request for the POST
        server.createContext("/sendresults",new PostHandler());
        server.setExecutor(null); // creates a default executor

        // get it going
        System.out.println("Starting Server...");
        server.start();
    }

    private static String getResponseBodyFromArrayList(ArrayList<Racer> fromJson) {
        String result = "<link rel=\"stylesheet\" type=\"text/css\" href=\"mystyle.css\">";
        result += "<table>\n";
        result += "<caption>Company Directory";
        result += "\t<tr>" +
                "<th>Title</th>" +
                "<th><a href=\"/displayresults/firstname\">First Name</a></th>" +
                "<th><a href=\"/displayresults/lastname\">Last Name</a></th>" +
                "<th>Gender</th>" +
                "<th><a href=\"/displayresults/department\">Department</a></th>" +
                "<th>Phone Number</th>" +
                "<tr>";
        for (Employee e : fromJson) {
            result += "<tr>" +
                    "<td>" + e.getTitle() + "</td>" +
                    "<td>" + e.getFirstName() + "</td>" +
                    "<td>" + e.getLastName() + "</td>" +
                    "<td>" + e.getGender() + "</td>" +
                    "<td>" + e.getDepartment() + "</td>" +
                    "<td>" + e.getPhoneNumber() + "</td>" +
                    "</tr>";
        }

        result += "</caption>";
        result += "</table>";
        return result;

    }

    private static void createResponseWithComparator(HttpExchange t, Comparator c) throws IOException {
        Gson g = new Gson();
        ArrayList<Employee> fromJson = g.fromJson(sharedResponse.substring(5),
                new TypeToken<Collection<Employee>>() {
                }.getType());
        Collections.sort(fromJson, c);
        String response = getResponseBodyFromArrayList(fromJson);
        // write out the response
        t.getResponseHeaders().set("Content-Type", "text/html");
        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    static class DisplayHandlerLastName implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            createResponseWithComparator(t, new EmployeeLastNameComparator());
        }
    }

    static class DisplayHandlerFirstName implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            createResponseWithComparator(t, new EmployeeFirstNameComparator());
        }
    }

    static class DisplayHandlerDepartment implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            createResponseWithComparator(t, new EmployeeDepartmentComparator());
        }
    }

    static class PostHandler implements HttpHandler {
        public void handle(HttpExchange transmission) throws IOException {

            //  shared data that is used with other handlers
            sharedResponse = "";

            // set up a stream to read the body of the request
            InputStream inputStr = transmission.getRequestBody();

            // set up a stream to write out the body of the response
            OutputStream outputStream = transmission.getResponseBody();

            // string to hold the result of reading in the request
            StringBuilder sb = new StringBuilder();

            // read the characters from the request byte by byte and build up the sharedResponse
            int nextChar = inputStr.read();
            while (nextChar > -1) {
                sb=sb.append((char)nextChar);
                nextChar=inputStr.read();
            }

            // create our response String to use in other handler
            sharedResponse = sharedResponse+sb.toString();

            // respond to the POST with ROGER
            String postResponse = "ROGER JSON RECEIVED";

            System.out.println("response: " + sharedResponse);

            //Desktop dt = Desktop.getDesktop();
            //dt.open(new File("raceresults.html"));

            // assume that stuff works all the time
            transmission.sendResponseHeaders(300, postResponse.length());

            // write it and return it
            outputStream.write(postResponse.getBytes());

            outputStream.close();
        }
    }

    private static class CSSHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            String filename = httpExchange.getRequestURI().toString();
            File file = new File("src/com/example/" + filename.substring(filename.lastIndexOf('/') + 1));
            Headers h = httpExchange.getResponseHeaders();
            h.set("Content-Type", "text/css");
            OutputStream os = httpExchange.getResponseBody();
            Scanner s = new Scanner(file);
            String response = "";
            while (s.hasNextLine()) {
                response += s.nextLine();
            }
            httpExchange.sendResponseHeaders(200, response.length());
            os.write(response.getBytes());
            os.close();
            s.close();
        }
    }
}
*/

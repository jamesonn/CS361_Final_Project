
public class Driver {

    public static void main(String[] args) {
        Client c = new Client("http://localhost", 8000);
        new UserInterface(c);
    }
}

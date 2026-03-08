
import java.io.IOException;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

class ConsoleClient {

    private static final String SERVER_ADDRESS = "localhost";
    private static final int PORT = 59001;

    // This thread just listens for messages from the server and prints them
    private static class MessageListener extends Thread {
        private BufferedReader reader;

        public MessageListener(BufferedReader reader) {
            this.reader = reader;
        }

        @Override
        public void run() {
            String message;
            try {
                while ((message = reader.readLine()) != null) {
                    System.out.println(message);
                }
            } catch (IOException e) {
                System.out.println("Disconnected from server.");
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Connecting to server at " + SERVER_ADDRESS + ":" + PORT);

        Socket socket = null;
        PrintWriter writer = null;
        BufferedReader reader = null;

        try {
            socket = new Socket(SERVER_ADDRESS, PORT);
            writer = new PrintWriter(socket.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Start the background thread to listen for incoming messages
            MessageListener listener = new MessageListener(reader);
            listener.start();

            // Main thread handles user input
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                String userInput = scanner.nextLine();

                // If user types QUIT, we close everything
                if (userInput.equalsIgnoreCase("QUIT")) {
                    System.out.println("Leaving chat...");
                    break;
                }

                writer.println(userInput);
            }

        } catch (IOException e) {
            System.out.println("Could not connect to server: " + e.getMessage());
        } finally {
            // Clean up
            try {
                if (reader != null) reader.close();
                if (writer != null) writer.close();
                if (socket != null) socket.close();
            } catch (IOException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}
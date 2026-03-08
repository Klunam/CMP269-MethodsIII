
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
// removed gradle/javaFX to compile, must run in 3 seperate terminals
// used Swing instead, built in into java

public class ChatApp extends JFrame {

    private static final int PORT = 59001;

    // UI elements
    private JTextArea chatArea;
    private JTextField messageField;
    private JButton sendButton;

    // Connection stuff
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;

    public ChatApp(String serverIP, String username) {
        // Set up the window
        setTitle("Chat - " + username);
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Chat area - not editable, just shows messages
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(chatArea);

        // Input area at the bottom
        messageField = new JTextField();
        sendButton = new JButton("Send");

        // Send when Enter is pressed
        messageField.addActionListener(e -> sendMessage());

        // Send when button is clicked
        sendButton.addActionListener(e -> sendMessage());

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(messageField, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);

        // Add everything to the window
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Connect to server
        connectToServer(serverIP, username);

        // Start listening for messages in background
        startListening();

        setVisible(true);
    }

    private void connectToServer(String serverIP, String username) {
        try {
            socket = new Socket(serverIP, PORT);
            writer = new PrintWriter(socket.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Read the server's prompt and send our username
            String prompt = reader.readLine();
            System.out.println(prompt);
            writer.println(username);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Could not connect to server!");
            System.out.println("Connection error: " + e.getMessage());
        }
    }

    private void startListening() {
        // Background thread to receive messages
        Thread listenerThread = new Thread(() -> {
            String message;
            try {
                while ((message = reader.readLine()) != null) {
                    // Swing UI updates must be done on the Event Dispatch Thread
                    final String finalMessage = message;
                    SwingUtilities.invokeLater(() -> {
                        chatArea.append(finalMessage + "\n");
                    });
                }
            } catch (IOException e) {
                SwingUtilities.invokeLater(() -> {
                    chatArea.append("Disconnected from server.\n");
                });
            }
        });

        listenerThread.setDaemon(true);
        listenerThread.start();
    }

    private void sendMessage() {
        String message = messageField.getText().trim();
        if (!message.isEmpty() && writer != null) {
            writer.println(message);
            messageField.setText("");
        }
    }

    // Login window that pops up first
    public static void main(String[] args) {
        JTextField ipField = new JTextField("localhost");
        JTextField nameField = new JTextField();

        JPanel loginPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        loginPanel.add(new JLabel("Server IP:"));
        loginPanel.add(ipField);
        loginPanel.add(new JLabel("Your Name:"));
        loginPanel.add(nameField);

        int result = JOptionPane.showConfirmDialog(null, loginPanel,
                "Connect to Chat", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String serverIP = ipField.getText().trim();
            String username = nameField.getText().trim();

            if (username.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter a username!");
                return;
            }

            new ChatApp(serverIP, username);
        }
    }
}
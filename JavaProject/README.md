Lehman ChatApp

Used Swing instead of JavaFx/Graddle, Swing is built in Java


How to run:
In terminal cd into JavaProject, open 3 terminals run them in order.

# Terminal 1 - Starting the server first: javac ChatServer.java && java ChatServer

# Terminal 2 - Starting the Console Client: javac ConsoleClient.java && java ConsoleClient

# Terminal 3 - GUI Client: javac ChatApp.java && java ChatApp


# Expected output:

Terminal 1:
s at port 59001...
New client connected: /127.0.0.1:52776
Handling client communication in thread: Thread-0 for client: /127.0.0.1:52776
Client /127.0.0.1:52776 set username to: KevinM13
New client connected: /127.0.0.1:52782
Handling client communication in thread: Thread-1 for client: /127.0.0.1:52782
Client /127.0.0.1:52782 set username to: KevinM13


Terminal 2: 
Connecting to server at localhost:59001
SERVER: Enter your username:
KevinM13
SERVER: KevinM13 has joined the chat!
SERVER: KevinM13 has joined the chat!


Terminal 3:
c ChatApp.java && java ChatApp
SERVER: Enter your username:
KevinM13
Hello



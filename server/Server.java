import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server {

	CircularShifter circularShifter = new CircularShifter();
	Alphabetizer alphabetizer = new Alphabetizer();
	private DataInputStream inLines;
	private DataOutputStream outLines;

	public Server(DataInputStream inLines, DataOutputStream outLines) {
		this.inLines = inLines;
		this.outLines = outLines;
	}

	public static void main(String[] args) throws Exception, IOException {
		ServerSocket serverSocket = new ServerSocket(8080);
		Socket clientSocket = serverSocket.accept();
		DataInputStream inLines = new DataInputStream(clientSocket.getInputStream());
		DataOutputStream outLines = new DataOutputStream(clientSocket.getOutputStream());
		Server server = new Server(inLines, outLines);
		List<String> lines = server.readIn();
		server.writeOut(lines);
		clientSocket.close();
		serverSocket.close();
	}

	public void writeOut(List<String> lines) throws IOException {
		for (String line : lines) {
			outLines.writeUTF(line);
		}
	}

	public String readLine(String message) {
		try {
			return inLines.readUTF();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	public List<String> readIn() throws IOException {
		String message = "";
		while (!message.equals("LAST LINE")) {
			message = readLine(message);
			circularShifter.passNewLineToBeShifted(message, alphabetizer);
		}
		return alphabetizer.getAlphabetizedLines();
	}
}

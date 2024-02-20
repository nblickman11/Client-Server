import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {

	public static void main(String[] args) throws Exception, IOException {
		Socket clientSocket = new Socket("localhost", 8080);
		DataInputStream inLines = new DataInputStream(clientSocket.getInputStream());
		DataOutputStream outLines = new DataOutputStream(clientSocket.getOutputStream());
		outLines.writeUTF("Hello!  My name is Clyde the Client. I have awaken");
		outLines.writeUTF("It is good to be alive");
		outLines.writeUTF("With more lines comes more random words");
		outLines.writeUTF("That's all folks, sincerely, bugs bunny and daffy duck");
		outLines.writeUTF("LAST LINE");
		outLines.flush();
		String result = "";
		while (!result.equals("LAST LINE")) {
			result = inLines.readUTF();
			System.out.println(result);
		}
		outLines.close();
		clientSocket.close();
	}

}

package Controller;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.crypto.SecretKey;

public class Server {
    private static final int port = 49200;
    private List<ClientHandler> clients = new ArrayList<>();
    static Set<PrintWriter> clientwriter = Collections.synchronizedSet(new HashSet<>());
    
    private static SecretKey aesKey;
    
    static {
        try {
            aesKey = MaHoaAES.khoiTaoKhoa();
            MaHoaAES.luuKhoa(aesKey, "keys/aes.key");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/staylearn";
        String user = "root";
        String pass = "W@2915djkq#";
        try {
            return DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi kết nối đến cơ sở dữ liệu");
        }
    }

    public static void addclientWriter(PrintWriter writer) {
        clientwriter.add(writer);
    }

    public static void removeclientWriter(PrintWriter writer) {
        clientwriter.remove(writer);
    }

    public static void broadcastMessage(String sender, String message) {
        synchronized (clientwriter) {
            for (PrintWriter writer : clientwriter) {
                try {
                    String encryptedMessage = MaHoaAES.maHoa("CHAT: " + sender + ": " + message, aesKey);
                    writer.println(encryptedMessage);
                    writer.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Server đang chạy...");

        try (ServerSocket svsocket = new ServerSocket(port)) {
            while (true) {
                new ClientHandler(svsocket.accept(), aesKey).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

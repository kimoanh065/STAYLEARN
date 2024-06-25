package Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.crypto.SecretKey;

public class Client {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private String username;
    private SecretKey aesKey;

    public Client(String address, int port, String username) throws IOException {
        try {
            this.socket = new Socket(address, port);
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new PrintWriter(socket.getOutputStream(), true);
            this.username = username;

            // Tải khóa AES
            this.aesKey = MaHoaAES.taiKhoa("keys/aes.key");

            // Mã hóa và gửi tên người dùng
            if (aesKey != null) {
                out.println(MaHoaAES.maHoa(username, aesKey));
            } else {
                System.err.println("Khóa AES null, không thể mã hóa tên người dùng.");
            }
        } catch (IOException e) {
            close();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        try {
            if (aesKey != null) {
                out.println(MaHoaAES.maHoa(message, aesKey));
            } else {
                System.err.println("Khóa AES null, không thể mã hóa thông điệp.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void requestChatHistory(String friendUsername) {
        try {
            if (aesKey != null) {
                out.println(MaHoaAES.maHoa("/history " + friendUsername, aesKey));
            } else {
                System.err.println("Khóa AES null, không thể mã hóa yêu cầu lịch sử chat.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String readMessage() throws IOException {
        try {
            String encryptedMessage = in.readLine();
            if (encryptedMessage != null && aesKey != null) {
                String message = MaHoaAES.giaiMa(encryptedMessage, aesKey);
                System.out.println("Read message: " + message);
                return message;
            } else {
                System.err.println("Thông điệp hoặc khóa AES null, không thể giải mã.");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void close() {
        try {
            if (in != null) in.close();
            if (out != null) out.close();
            if (socket != null && !socket.isClosed()) socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createUser(String username, String password) {
        try {
            if (aesKey != null) {
                out.println(MaHoaAES.maHoa("/create_user " + username + " " + password, aesKey));
            } else {
                System.err.println("Khóa AES null, không thể mã hóa yêu cầu tạo người dùng.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void xacnhanhocphi(String command, String tennguoinop, String tenkhoahoc, int sotien, String hinhthuc, Date ngay, String username) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String ngayStr = sdf.format(ngay);
            String message = command + "," + tennguoinop + "," + tenkhoahoc + "," + sotien + "," + hinhthuc + "," + ngayStr + "," + username;
            if (aesKey != null) {
                out.println(MaHoaAES.maHoa(message, aesKey));
                System.out.println("Sent to server: " + message);
            } else {
                System.err.println("Khóa AES null, không thể mã hóa thông điệp xác nhận học phí.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loginUser(String username, String password) {
        try {
            if (aesKey != null) {
                out.println(MaHoaAES.maHoa("/loginUser " + username + " " + password, aesKey));
                System.out.println("Login user: " + username + " " + password);
            } else {
                System.err.println("Khóa AES null, không thể mã hóa yêu cầu đăng nhập người dùng.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void registerUser(String username, String password) {
        try {
            if (aesKey != null) {
                out.println(MaHoaAES.maHoa("/register " + username + " " + password, aesKey));
                System.out.println("Register: " + username + " " + password);
            } else {
                System.err.println("Khóa AES null, không thể mã hóa yêu cầu đăng nhập người dùng.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loginStaff(String username, String password) {
        try {
            if (aesKey != null) {
                out.println(MaHoaAES.maHoa("/loginStaff " + username + " " + password, aesKey));
                System.out.println("Login staff: " + username + " " + password);
            } else {
                System.err.println("Khóa AES null, không thể mã hóa yêu cầu đăng nhập nhân viên.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendtoServer(String command, String message) {
        try {
            if (aesKey != null) {
                out.println(MaHoaAES.maHoa(command + " " + message, aesKey));
                System.out.println("Client sent: " + command + " " + message); // Debug message
            } else {
                System.err.println("Khóa AES null, không thể mã hóa yêu cầu gửi tới server.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void capnhapthongtin(String command, String data1, String data2, String data3, String data4, String data5, String data6, String data7, String data8, String data9, String data10) {
        try {
            String message = command + "," + data1 + "," + data2 + "," + data3 + "," + data4 + "," + data5 + "," + data6 + "," + data7 + "," + data8 + "," + data9 + "," + data10;
            if (aesKey != null) {
                out.println(MaHoaAES.maHoa(message, aesKey));
                System.out.println("Sent to server: " + message);
            } else {
                System.err.println("Khóa AES null, không thể mã hóa thông điệp cập nhật thông tin.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void capnhapthongtingv(String command, String data1, String data2, String data3, String data4, String data5, String data6, String data7, String data8) {
        try {
            String message = command + "," + data1 + "," + data2 + "," + data3 + "," + data4 + "," + data5 + "," + data6 + "," + data7 + "," + data8;
            if (aesKey != null) {
                out.println(MaHoaAES.maHoa(message, aesKey));
                System.out.println("Sent to server: " + message);
            } else {
                System.err.println("Khóa AES null, không thể mã hóa thông điệp cập nhật thông tin giảng viên.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void writetofilestaff(String studentID, String fullName, String gender, String dob, String address, String phoneNumber, String email, String position, String staffname) {
        try {
            String message = "/writetofilestaff" + "," + studentID + "," + fullName + "," + gender + "," + dob + "," + address + "," + phoneNumber + "," + email + "," + position + "," + staffname;
            if (aesKey != null) {
                out.println(MaHoaAES.maHoa(message, aesKey));
                System.out.println("Sent to server: " + message);
            } else {
                System.err.println("Khóa AES null, không thể mã hóa thông điệp cập nhật thông tin giảng viên.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void writetofileuser(String studentID, String fullName, String gender, String dob, String phoneNumber,
            String email, String address, String parentName, String parentPhoneNumber, String joinDate, String username) {
        try {
            String message = "/writetofileuser" + "," + studentID + "," + fullName + "," + gender + "," + dob + "," + phoneNumber + "," + email + "," + address  + "," + parentName + "," + parentPhoneNumber + "," + joinDate + "," + username;
            if (aesKey != null) {
                out.println(MaHoaAES.maHoa(message, aesKey));
                System.out.println("Sent to server: " + message);
            } else {
                System.err.println("Khóa AES null, không thể mã hóa thông điệp cập nhật thông tin giảng viên.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

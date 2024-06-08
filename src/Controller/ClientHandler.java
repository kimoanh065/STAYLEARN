package Controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.crypto.SecretKey;

public class ClientHandler extends Thread {
    private Socket mysocket;
    private String username;
    private BufferedReader in;
    private PrintWriter out;
    private SecretKey aeskey;

    public ClientHandler(Socket mysocket, SecretKey aeskey) {
        this.mysocket = mysocket;
        this.aeskey = aeskey;
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(mysocket.getInputStream()));
            out = new PrintWriter(mysocket.getOutputStream(), true);
            Server.addclientWriter(out);

            String gmusername = in.readLine();
            username = MaHoaAES.giaiMa(gmusername, aeskey);

            String input;
            while ((input = in.readLine()) != null) {
                String gminput = MaHoaAES.giaiMa(input, aeskey);
                System.out.println("Hệ thống nhận: " + gminput);

                if (gminput.startsWith("/loginStaff")) {
                    String[] parts = gminput.split(" ", 3);
                    if (parts.length < 3) {
                        out.println(MaHoaAES.maHoa("Invalid format. Use /loginStaff <username> <password>", aeskey));
                        continue;
                    }
                    String username = parts[1].trim();
                    String password = parts[2].trim();
                    loginStaff(username, password);
                } else if (gminput.startsWith("/loginUser")) {
                    String[] parts = gminput.split(" ", 3);
                    if (parts.length < 3) {
                        out.println(MaHoaAES.maHoa("Invalid format. Use /loginUser <username> <password>", aeskey));
                        continue;
                    }
                    String username = parts[1].trim();
                    String password = parts[2].trim();
                    loginUser(username, password);
                } else if (gminput.startsWith("/loaddatatotable2")) {
                    Vector<Vector<String>> getlichhoc = getLichHoc();
                    String serializedData = serializeVector(getlichhoc);
                    String encryptedData = MaHoaAES.maHoa(serializedData, aeskey);
                    out.println(encryptedData);
                    out.flush();
                } else if (gminput.startsWith("/loadDataToTable1")) {
                    loadDataToTable1();
                } else if (gminput.startsWith("/loadDataStudent")) {
                    String[] parts = gminput.split(" ", 2);
                    if (parts.length < 2) {
                        out.println(MaHoaAES.maHoa("Invalid format. Use /loadData <username>", aeskey));
                        continue;
                    }
                    String username = parts[1].trim();
                    String data = loadDataStudent(username);
                    out.println(MaHoaAES.maHoa(data, aeskey));
                    out.flush();
                } else if (gminput.startsWith("/loadDataStaff")) {
                    String[] parts = gminput.split(" ", 2);
                    if (parts.length < 2) {
                        out.println(MaHoaAES.maHoa("Invalid format. Use /loadData <staffname>", aeskey));
                        continue;
                    }
                    String staffname = parts[1].trim();
                    String data = loadDataStaff(staffname);
                    out.println(MaHoaAES.maHoa(data, aeskey));
                    out.flush();
                } else if (gminput.startsWith("/hocphi")) {
                    String[] parts = gminput.split(",", 7);
                    if (parts.length < 7) {
                        out.println(MaHoaAES.maHoa("fail", aeskey));
                        out.flush();
                        continue;
                    }
                    String tennguoinop = parts[1].trim();
                    String tenkhoahoc = parts[2].trim();
                    int sotien;
                    try {
                        sotien = Integer.parseInt(parts[3].trim());
                    } catch (NumberFormatException e) {
                        out.println(MaHoaAES.maHoa("fail", aeskey));
                        out.flush();
                        continue;
                    }
                    String hinhthuc = parts[4].trim();
                    String ngayStr = parts[5].trim();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date ngay;
                    try {
                        ngay = sdf.parse(ngayStr);
                    } catch (ParseException e) {
                        out.println(MaHoaAES.maHoa("fail", aeskey));
                        out.flush();
                        continue;
                    }
                    String username = parts[6].trim();
                    xacnhanhocphi(tennguoinop, tenkhoahoc, sotien, hinhthuc, ngay, username);
                } else if (gminput.startsWith("/countOffice")) {
                    out.println(MaHoaAES.maHoa(String.valueOf(countOffice()), aeskey));
                    out.flush();
                } else if (gminput.startsWith("/countStaff")) {
                    out.println(MaHoaAES.maHoa(String.valueOf(countStaff()), aeskey));
                    out.flush();
                } else if (gminput.startsWith("/countClass")) {
                    out.println(MaHoaAES.maHoa(String.valueOf(countClass()), aeskey));
                    out.flush();
                } else if (gminput.startsWith("/countStudent")) {
                    out.println(MaHoaAES.maHoa(String.valueOf(countStudent()), aeskey));
                    out.flush();
                } else if (gminput.startsWith("/loaddatatotable")) {
                    loadDataToTable();
                } else if (gminput.startsWith("/updateStudent")) {
                    String[] parts = gminput.split(",", 11);
                    if (parts.length < 11) {
                        out.println(MaHoaAES.maHoa("Invalid format. Use /updateStudent <params>", aeskey));
                        continue;
                    }
                    String name = parts[1].trim();
                    String gender = parts[2].trim();
                    String dateOfBirthStr = parts[3].trim();
                    String address = parts[4].trim();
                    String phoneNumber = parts[5].trim();
                    String email = parts[6].trim();
                    String parentName = parts[7].trim();
                    String phoneParent = parts[8].trim();
                    String dayArriveStr = parts[9].trim();
                    String username = parts[10].trim();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date dateOfBirth;
                    Date dayArrive;
                    try {
                        dateOfBirth = sdf.parse(dateOfBirthStr);
                        dayArrive = sdf.parse(dayArriveStr);
                    } catch (ParseException e) {
                        out.println(MaHoaAES.maHoa("fail", aeskey));
                        continue;
                    }
                    boolean success = updateStudent(name, gender, dateOfBirth, address, phoneNumber, email, parentName, phoneParent, dayArrive, username);
                    if (success) {
                        out.println(MaHoaAES.maHoa("success", aeskey));
                    } else {
                        out.println(MaHoaAES.maHoa("fail", aeskey));
                    }
                } else if (gminput.startsWith("/updateStaff")) {
                    String[] parts = gminput.split(",", 9);
                    if (parts.length < 9) {
                        out.println(MaHoaAES.maHoa("Invalid format. Use /updateStaff <params>", aeskey));
                        continue;
                    }
                    String name = parts[1].trim();
                    String gender = parts[2].trim();
                    String dateOfBirthStr = parts[3].trim();
                    String address = parts[4].trim();
                    String phoneNumber = parts[5].trim();
                    String email = parts[6].trim();
                    String position = parts[7].trim();
                    String staffname = parts[8].trim();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date dateOfBirth;
                    try {
                        dateOfBirth = sdf.parse(dateOfBirthStr);
                    } catch (ParseException e) {
                        out.println(MaHoaAES.maHoa("fail", aeskey));
                        continue;
                    }
                    boolean success = updateStaff(name, gender, dateOfBirth, address, phoneNumber, email, position, staffname);
                    if (success) {
                        out.println(MaHoaAES.maHoa("success", aeskey));
                    } else {
                        out.println(MaHoaAES.maHoa("fail", aeskey));
                    }
                } 
                else if (gminput.startsWith("/register")) {
                	String[] parts = gminput.split(" ", 3);
                    if (parts.length < 3) {
                        out.println(MaHoaAES.maHoa("Invalid format. Use /register <username> <password>", aeskey));
                        continue;
                    }
                    String username = parts[1].trim();
                    String password = parts[2].trim();
                    register(username, password);
                }
                else {
                    String message = gminput;
                    System.out.println("Message nhận: " + message);
                    Server.broadcastMessage(this.username, message);
                }
            }
        } catch (Exception e) {
            System.out.println("Client disconnected!");
            e.printStackTrace();
        } finally {
            Server.removeclientWriter(out);
            try {
                mysocket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void loginStaff(String username, String password) throws Exception {
        try (Connection conn = Server.getConnection()) {
            String query = "SELECT staffname, staffpassword FROM staylearn.staff_account WHERE staffname = ? AND staffpassword = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, username);
                stmt.setString(2, password);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        out.println(MaHoaAES.maHoa("success", aeskey));
                    } else {
                        out.println(MaHoaAES.maHoa("fail", aeskey));
                    }
                }
            }
        } catch (Exception e) {
            out.println(MaHoaAES.maHoa("Error logging in staff: " + e.getMessage(), aeskey));
            e.printStackTrace();
        }
    }

    private void loginUser(String username, String password) throws Exception {
        try (Connection conn = Server.getConnection()) {
            String query = "SELECT username, passworduser FROM staylearn.user_account WHERE username = ? AND passworduser = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, username);
                stmt.setString(2, password);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        out.println(MaHoaAES.maHoa("success", aeskey));
                    } else {
                        out.println(MaHoaAES.maHoa("fail", aeskey));
                    }
                }
            }
        } catch (Exception e) {
            out.println(MaHoaAES.maHoa("Error logging in user: " + e.getMessage(), aeskey));
            e.printStackTrace();
        }
    }
    
    private void register(String username, String password) throws Exception {
        try (Connection conn = Server.getConnection()) {
            String query = "INSERT INTO staylearn.user_account (username, passworduser) VALUES (?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, username);
                stmt.setString(2, password);
                int row = stmt.executeUpdate();
                if (row > 0) {
                    out.println(MaHoaAES.maHoa("success", aeskey));
                } 
                else {
                    out.println(MaHoaAES.maHoa("fail", aeskey));
                }  
            }
        } catch (Exception e) {
            out.println(MaHoaAES.maHoa("Error logging in user: " + e.getMessage(), aeskey));
            e.printStackTrace();
        }
    }

    public Vector<Vector<String>> getLichHoc() {
        Vector<Vector<String>> vLichHoc = new Vector<>();
        String sql = "SELECT thu, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday FROM lichhoc";
        try (Connection conn = Server.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Vector<String> row = new Vector<>();
                row.add(rs.getString("thu"));
                row.add(rs.getString("Monday"));
                row.add(rs.getString("Tuesday"));
                row.add(rs.getString("Wednesday"));
                row.add(rs.getString("Thursday"));
                row.add(rs.getString("Friday"));
                row.add(rs.getString("Saturday"));
                row.add(rs.getString("Sunday"));
                vLichHoc.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vLichHoc;
    }

    private String serializeVector(Vector<Vector<String>> vector) {
        StringBuilder sb = new StringBuilder();
        for (Vector<String> innerVector : vector) {
            for (String element : innerVector) {
                sb.append(element).append(";;;");
            }
            sb.append("|||");
        }
        return sb.toString();
    }

    private String loadDataStudent(String username) {
        StringBuilder sb = new StringBuilder();
        String sql = "SELECT idstudent, name, gender, dateofbirth, address, phonenumber, email, Parentname, phone_parent, day_arrive FROM staylearn.student WHERE username = ?;";
        try (Connection conn = Server.getConnection();
             PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                sb.append(rs.getString("idstudent")).append(";;;");
                sb.append(rs.getString("name")).append(";;;");
                sb.append(rs.getString("gender")).append(";;;");
                sb.append(rs.getString("dateofbirth")).append(";;;");
                sb.append(rs.getString("address")).append(";;;");
                sb.append(rs.getString("phonenumber")).append(";;;");
                sb.append(rs.getString("email")).append(";;;");
                sb.append(rs.getString("Parentname")).append(";;;");
                sb.append(rs.getString("phone_parent")).append(";;;");
                sb.append(rs.getString("day_arrive")).append(";;;");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public void xacnhanhocphi(String tennop, String tenkhoahoc, int sotien, String hinhthuc, Date ngay, String username) throws Exception {
        String sql = "INSERT INTO hocphi (tennguoinop, tenkhoahoc, sotien, hinhthucthanhtoan, ngaythu, username) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = Server.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, tennop);
            pstmt.setString(2, tenkhoahoc);
            pstmt.setInt(3, sotien);
            pstmt.setString(4, hinhthuc);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            pstmt.setString(5, sdf.format(ngay));
            pstmt.setString(6, username);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                out.println(MaHoaAES.maHoa("success", aeskey));
            } else {
                out.println(MaHoaAES.maHoa("fail", aeskey));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            out.println(MaHoaAES.maHoa("fail", aeskey));
        }
    }

    private boolean updateStudent(String name, String gender, Date dateOfBirth, String address, String phoneNumber, String email, String parentName, String phoneParent, Date dayArrive, String username) {
        String sql = "UPDATE staylearn.student SET name = ?, gender = ?, dateofbirth = ?, address = ?, phonenumber = ?, email = ?, Parentname = ?, phone_parent = ?, day_arrive = ? WHERE username = ?";
        try (Connection conn = Server.getConnection();
             PreparedStatement stm = conn.prepareStatement(sql)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            stm.setString(1, name);
            stm.setString(2, gender);
            stm.setString(3, sdf.format(dateOfBirth));
            stm.setString(4, address);
            stm.setString(5, phoneNumber);
            stm.setString(6, email);
            stm.setString(7, parentName);
            stm.setString(8, phoneParent);
            stm.setString(9, sdf.format(dayArrive));
            stm.setString(10, username);
            int rows = stm.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean updateStaff(String name, String gender, Date dateOfBirth, String address, String phoneNumber, String email, String position, String staffname) {
        String sql = "Update staylearn.staff set name = ?, gender = ?, dateofbirth = ?, address = ?, phonenumber = ?, email = ?, position = ? WHERE staffname = ?";
        try (Connection conn = Server.getConnection();
             PreparedStatement stm = conn.prepareStatement(sql)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            stm.setString(1, name);
            stm.setString(2, gender);
            stm.setString(3, sdf.format(dateOfBirth));
            stm.setString(4, address);
            stm.setString(5, phoneNumber);
            stm.setString(6, email);
            stm.setString(7, position);
            stm.setString(8, staffname);
            int rows = stm.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static int countStaff() {
        try (Connection conn = Server.getConnection();
             PreparedStatement stm = conn.prepareStatement("SELECT count(*) FROM staylearn.staff");
             ResultSet rs = stm.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int countClass() {
        try (Connection conn = Server.getConnection();
             PreparedStatement stm = conn.prepareStatement("SELECT count(*) FROM staylearn.class");
             ResultSet rs = stm.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int countStudent() {
        try (Connection conn = Server.getConnection();
             PreparedStatement stm = conn.prepareStatement("SELECT count(*) FROM staylearn.student");
             ResultSet rs = stm.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int countOffice() {
        try (Connection conn = Server.getConnection();
             PreparedStatement stm = conn.prepareStatement("SELECT count(*) FROM staylearn.office");
             ResultSet rs = stm.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private String loadDataStaff(String staffname) {
        StringBuilder sb = new StringBuilder();
        String sql = "SELECT IDStaff, name, gender, dateofbirth, address, phonenumber, email, position FROM staylearn.staff WHERE staffname = ?;";
        try (Connection conn = Server.getConnection();
             PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setString(1, staffname);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                sb.append(rs.getString("IDStaff")).append(";;;");
                sb.append(rs.getString("name")).append(";;;");
                sb.append(rs.getString("gender")).append(";;;");
                sb.append(rs.getString("dateofbirth")).append(";;;");
                sb.append(rs.getString("address")).append(";;;");
                sb.append(rs.getString("phonenumber")).append(";;;");
                sb.append(rs.getString("email")).append(";;;");
                sb.append(rs.getString("position")).append(";;;");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public void loadDataToTable() throws Exception {
        String query = "SELECT IdStudent, Name, Gender, dateofbirth, Address, Phonenumber, Email, Parentname, Phone_parent, Day_arrive, Id_class, sotien FROM staylearn.student LEFT JOIN staylearn.user_account ON student.username = user_account.username LEFT JOIN staylearn.hocphi ON student.username = hocphi.username";
        try (Connection conn = Server.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            Vector<Vector<String>> data = new Vector<>();
            while (rs.next()) {
                Vector<String> row = new Vector<>();
                row.add(rs.getString("IdStudent"));
                row.add(rs.getString("Name"));
                row.add(rs.getString("Gender"));
                row.add(rs.getString("dateofbirth"));
                row.add(rs.getString("Address"));
                row.add(rs.getString("Phonenumber"));
                row.add(rs.getString("Email"));
                row.add(rs.getString("Parentname"));
                row.add(rs.getString("Phone_parent"));
                row.add(rs.getString("Day_arrive"));
                row.add(rs.getString("Id_class"));
                row.add(rs.getString("sotien"));
                data.add(row);
            }

            String serializedData = serializeVector(data);
            String encryptedData = MaHoaAES.maHoa(serializedData, aeskey);
            out.println(encryptedData);
            out.flush();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadDataToTable1() throws Exception {
        String query = "SELECT stt, tennguoinop, tenkhoahoc, sotien, hinhthucthanhtoan, ngaythu FROM hocphi";
        try (Connection conn = Server.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            Vector<Vector<String>> data = new Vector<>();
            while (rs.next()) {
                Vector<String> row = new Vector<>();
                row.add(Integer.toString(rs.getInt("stt")));
                row.add(rs.getString("tennguoinop"));
                row.add(rs.getString("tenkhoahoc"));
                row.add(Integer.toString(rs.getInt("sotien")));
                row.add(rs.getString("hinhthucthanhtoan"));
                row.add(rs.getString("ngaythu"));
                data.add(row);
            }

            String serializedData = serializeVector(data);
            String encryptedData = MaHoaAES.maHoa(serializedData, aeskey);
            out.println(encryptedData);
            out.flush();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

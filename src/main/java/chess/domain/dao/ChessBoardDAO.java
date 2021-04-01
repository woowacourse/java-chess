package chess.domain.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChessBoardDAO {
    public Connection getConnection() {
        Connection con = null;
        String server = "localhost:13306"; // MySQL 서버 주소
        String database = "db_chess"; // MySQL DATABASE 이름
        String option = "?useSSL=false&serverTimezone=UTC";
        String userName = "root"; //  MySQL 서버 아이디
        String password = "root"; // MySQL 서버 비밀번호

        // 드라이버 로딩
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }

        // 드라이버 연결
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + option, userName, password);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        }

        return con;
    }

    // 드라이버 연결해제
    public void closeConnection(Connection con) {
        try {
            if (con != null)
                con.close();
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
        }
    }

    public void addPosition(ChessBoardDTOForDAO eachPosition) throws SQLException {

            String query = "INSERT INTO chessTable (game_id, position, teamColor, pieceType, alive) VALUES (1, ?, ?, ?, ?)";

            PreparedStatement pstmt = getConnection().prepareStatement(query);
            pstmt.setString(1, eachPosition.getPosition());
            pstmt.setString(2, eachPosition.getTeamColor());
            pstmt.setString(3, eachPosition.getPieceType());
            pstmt.setString(4, eachPosition.getAlive());

            pstmt.executeUpdate();

        }



        //Statement 생성 후 실행할 쿼리정보 등록 Statement stmt = conn.createStatement(); //결과를 담을 ResultSet 생성 후 결과 담기 ResultSet rs = stmt.executeQuery(sql); //결과를 담을 ArrayList생성 ArrayList<UserBean> list = new ArrayList<UserBean>(); //ResultSet에 담긴 결과를 ArrayList에 담기 while(rs.next()) { UserBean bean = new UserBean(); bean.setId(rs.getString("ID")); bean.setName(rs.getString("NAME")); bean.setEmail(rs.getString("EMAIL")); list.add(bean); } //결과물 출력 for(int i=0; i<list.size(); i++) { System.out.println("회원아이디:"+list.get(i).getId()); System.out.println("회원이름:"+list.get(i).getName()); System.out.println("회원이메일:"+list.get(i).getEmail()); }



//    public List<ChessBoardDTOForDAO> findByGameId(String userId) throws SQLException {
//        String query = "SELECT * FROM user WHERE user_id = ?";
//        PreparedStatement pstmt = getConnection().prepareStatement(query);
//        pstmt.setString(1, userId);
//        ResultSet rs = pstmt.executeQuery();
//        List<ChessBoardDTOForDAO> chessboard = new ArrayList<>();
//        if (!rs.next()) return null;
//        new User(
//                rs.getString("user_id"),
//                rs.getString("name"));
//        return chessboard;
//    }

    public void removePositions() throws SQLException {
        String query = "DELETE FROM chessTable WHERE game_id=1";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.executeUpdate();
    }

}

package chess.web.dao;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceSymbol;
import chess.domain.piece.vo.TeamColor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PieceDao {

    private static final String URL = "jdbc:mysql://localhost:3306/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    public void updatePieces(Board board) {
        try {
            initialize();
            saveAllPieceBy(board);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initialize() throws SQLException{
        Connection connection = getConnection();
        final String clearSql = "truncate table piece";
        connection.prepareStatement(clearSql).executeUpdate();
        connection.close();
    }

    private void saveAllPieceBy(Board board) throws SQLException {
        Connection connection = getConnection();
        final String sql = "insert into piece (teamColor, symbol, position) values (?, ?, ?)";
        for (Piece piece : board.getPieces()) {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, piece.getTeamColor());
            statement.setString(2, PieceSymbol.findWebSymbol(piece));
            statement.setString(3, piece.getPosition().toFileRankString());
            statement.executeUpdate();
        }
    }

    private Connection getConnection() {
        loadDriver();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);// 어떤 URL의 커넥션을 가져올 것 인지
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private void loadDriver() { // 생략 가능( 드라이버를 지정하고 싶을 때 작성, 하나만 사용한다면 DriverManager가 알아서 해준다.
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Piece> findAll(Board board) {
        List<Piece> pieces = new ArrayList<>();
        try {
            Connection connection = getConnection();
            String sql = "select teamColor, symbol, position from piece";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            pieces = convertPieces(resultSet);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pieces;
    }

    private List<Piece> convertPieces(ResultSet resultSet) throws SQLException {
        List<Piece> pieces = new ArrayList<>();
        while (resultSet.next()) {
            pieces.add(PieceSymbol.getConstructor(resultSet.getString("symbol")).apply(
                    TeamColor.valueOf(resultSet.getString("teamColor")),
                    Position.from(resultSet.getString("position")
                    )));
        }
        return pieces;
    }
}

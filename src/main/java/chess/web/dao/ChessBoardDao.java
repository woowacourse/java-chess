package chess.web.dao;

import chess.web.dto.ChessBoardDto;
import chess.web.dto.ChessGameDto;
import chess.web.dto.PieceDto;
import chess.web.dto.PositionDto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

public class ChessBoardDao {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/chess";
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    private void loadDriver() {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        loadDriver();
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return  connection;
    }
    public int save(ChessGameDto chessGameDto) {
        Connection connection = getConnection();

        ChessBoardDto chessBoard = chessGameDto.getChessBoard();

        Map<PositionDto, PieceDto> cells = chessBoard.getCells();

        String sql = "insert into chessboard values ()";

        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();

            resultSet.next();

            int chessBoardId = resultSet.getInt(1);

            for (PositionDto positionDto : cells.keySet()) {
                addBoard(connection, chessBoardId, positionDto, cells.get(positionDto));
            }
            return chessBoardId;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void addBoard(Connection connection, int chessBoardId, PositionDto position, PieceDto piece) {

        final String sql = "insert into piece (type, team, `rank`, file, chessboard_id) values (?, ?, ?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, piece.getSymbol());
            statement.setString(2, piece.getTeam());
            statement.setInt(3, position.getRank());
            statement.setString(4, position.getFile());
            statement.setInt(5, chessBoardId);

            statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}

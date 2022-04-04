package chess.web.dao;

import chess.web.dto.ChessGameDto;
import chess.web.dto.PieceDto;
import chess.web.dto.PositionDto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ChessGameDao {

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


    public void save(ChessGameDto chessGameDto, int chessBoardId) {
        Connection connection = getConnection();

        String sql = "insert into chessgame (game_name, turn, chess_board_id) values (?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, chessGameDto.getGameName());
            statement.setString(2, chessGameDto.getTurn());
            statement.setInt(3, chessBoardId);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ChessGameDto findByName(String gameName) {
        Connection connection = getConnection();

        String sql = "select CHESSGAME.turn, CHESSGAME.game_name, PIECE.type, PIECE.team, PIECE.`rank`, PIECE.file from CHESSGAME, CHESSBOARD, PIECE\n"
                + "where CHESSGAME.chess_board_id = CHESSBOARD.id AND (CHESSBOARD.id IN (SELECT chessboard_id FROM PIECE))\n"
                + "AND game_name = ?;";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, gameName);

            ResultSet resultSet = statement.executeQuery();

            String turn = "";

            Map<PositionDto, PieceDto> cells = new HashMap<>();

            while(resultSet.next()) {
                turn = resultSet.getString("turn");

                int rank = resultSet.getInt("rank");
                String file = resultSet.getString("file");

                PositionDto positionDto = new PositionDto(rank, file);

                String type = resultSet.getString("type");
                String team = resultSet.getString("team");

                PieceDto pieceDto = new PieceDto(type, team);

                cells.put(positionDto, pieceDto);
            }

            new ChessGameDto(turn, gameName, cells);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void remove(String gameName) {
        Connection connection = getConnection();

        String selectSQL = "select chess_board_id from chessgame where game_name = ?";
        String chessGameDeleteSQL = "delete from chessgame where game_name = ?";
        String chessBoardDeleteSQL = "delete from chessboard where id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(selectSQL);
            statement.setString(1, gameName);

            ResultSet resultSet = statement.executeQuery();

            resultSet.next();

            int chessBoardId = resultSet.getInt("chess_board_id");

            statement = connection.prepareStatement(chessGameDeleteSQL);
            statement.setString(1, gameName);

            statement.executeUpdate();

            statement = connection.prepareStatement(chessBoardDeleteSQL);
            statement.setInt(1, chessBoardId);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

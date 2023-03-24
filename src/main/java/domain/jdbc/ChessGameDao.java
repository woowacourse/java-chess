package domain.jdbc;

import domain.ChessGame;
import domain.chessboard.ChessBoard;
import domain.chessboard.Result;
import domain.chessboard.SquareStatus;
import domain.coordinate.Position;
import domain.piece.Color;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChessGameDao implements JdbcDao {

    private static final String SERVER = "localhost:13306";
    private static final String DATABASE = "chess";
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void save(ChessBoard chessBoard) {
        final String saveQuery = "INSERT INTO chess_game(id, turn) VALUES(?, ?)";
        final String currentIdQuery = "SELECT MAX(id) AS id FROM chess_game";
        final String savePieceQuery = "INSERT INTO chess_board(x, y, piece_type, piece_color, game_id) VALUES(?, ?, ?, ?, ?)";

        try (final Connection connection = getConnection()) {
            PreparedStatement chessGameSave = connection.prepareStatement(saveQuery);
            PreparedStatement currentIdSelect = connection.prepareStatement(currentIdQuery);
            PreparedStatement pieceSave = connection.prepareStatement(savePieceQuery);

            chessGameSave.setString(1, "100");
            chessGameSave.setString(2, Color.WHITE.name());
            chessGameSave.executeUpdate();

            ResultSet resultSet = currentIdSelect.executeQuery();
            String currentId = null;
            if (resultSet.next()) {
                currentId = resultSet.getString("id");
            }

            for (int x = 0; x < 8; x++) {
                for (int y = 0; y < 8; y++) {
                    SquareStatus squareStatus = chessBoard.findSquare(Position.of(x, y)).getSquareStatus();
                    pieceSave.setString(1, Integer.toString(x));
                    pieceSave.setString(2, Integer.toString(y));
                    pieceSave.setString(3, squareStatus.getType().name());
                    pieceSave.setString(4, squareStatus.getColor().name());
                    pieceSave.setString(5, currentId);
                    pieceSave.executeUpdate();
                }
            }

        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public ChessGame select() { // join 해서, 가져온다
        return null;
    }

    @Override
    public void update() { // update 는 이제, turn, (Position, Square)

    }

    @Override
    public void delete() { // cascade 로 생성해서, 그냥 delete game id

    }

}

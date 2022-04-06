package chess.dao;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.game.ChessGame;
import chess.domain.piece.Piece;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class BoardDao {

    private static final Connection connection = ConnectionManager.getConnection();

    public static void save(final ChessGame chessGame) {
        delete(chessGame.getName());
        String insertSql = "insert into board (name, raw_position, piece_name, piece_team_value) values (?, ?, ?, ?)";
        Map<String, Piece> currentBoard = chessGame.getCurrentBoardByRawPosition();
        try {
            PreparedStatement insertStatement = connection.prepareStatement(insertSql);
            for (String key : currentBoard.keySet()) {
                insertStatement.setString(1, chessGame.getName());
                insertStatement.setString(2, key);
                insertStatement.setString(3, currentBoard.get(key).getName());
                insertStatement.setString(4, currentBoard.get(key).getTeam().getValue());
                insertStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delete(final String name) {
        String deleteSql = "delete from board where name=?";
        try {
            PreparedStatement deleteStatement = connection.prepareStatement(deleteSql);
            deleteStatement.setString(1, name);
            deleteStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Board load(final String name) {
        String loadSql = "select * from board where name=?";
        Map<Position, Piece> board = new HashMap<>();
        try {
            PreparedStatement loadStatement = connection.prepareStatement(loadSql);
            loadStatement.setString(1, name);
            ResultSet resultSet = loadStatement.executeQuery();
            gatherPositionAndPiece(board, resultSet);
            validateBoardExist(board);
            return new Board(board);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void gatherPositionAndPiece(Map<Position, Piece> board, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            Position position = Position.valueOf(resultSet.getString("raw_position"));
            Piece piece = StringToPieceConvertor.convert(
                    resultSet.getString("piece_name"), resultSet.getString("piece_team_value")
            );
            board.put(position, piece);
        }
    }

    private static void validateBoardExist(Map<Position, Piece> board) {
        if (board.size() == 0) {
            throw new IllegalArgumentException("[ERROR] Board 가 존재하지 않습니다.");
        }
    }
}

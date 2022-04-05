package chess.dao;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
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

    public void save(final ChessGame chessGame) {
        String deleteSql = "delete from board where raw_position=?";
        String insertSql = "insert into board (raw_position, piece_name, piece_team_value) values (?, ?, ?)";

        Map<String, Piece> currentBoard = chessGame.getCurrentBoardByRawPosition();

        try {
            Connection connection = ConnectionManager.getConnection();

            PreparedStatement deleteStatement = connection.prepareStatement(deleteSql);
            PreparedStatement insertStatement = connection.prepareStatement(insertSql);
            for (String key : currentBoard.keySet()) {
                deleteStatement.setString(1, key);
                deleteStatement.execute();

                insertStatement.setString(1, key);
                insertStatement.setString(2, currentBoard.get(key).getName());
                insertStatement.setString(3, currentBoard.get(key).getTeam().getValue());
                insertStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Board load() {
        String sql = "select * from board";
        Map<Position, Piece> board = new HashMap<>();
        try {
            PreparedStatement statement = ConnectionManager.getConnection().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Position position = Position.valueOf(resultSet.getString("raw_position"));
                Piece piece = StringToPieceConvertor.convert(
                        resultSet.getString("piece_name"), resultSet.getString("piece_team_value")
                );
                board.put(position, piece);
            }
            if (board.size() == 0) {
                return BoardFactory.generateChessBoard();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Board(board);
    }
}

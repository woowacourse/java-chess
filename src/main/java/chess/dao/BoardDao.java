package chess.dao;

import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.dto.BoardDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class BoardDao {

    private final Connection connection = ConnectionManager.getConnection();

    public void save(final String name, final BoardDto boardDto) {
        String insertSql = "insert into board (name, position_column_value, position_row_value, piece_name, piece_team_value) values (?, ?, ?, ?, ?)";
        Map<Position, Piece> board = boardDto.getBoard();
        try {
            PreparedStatement insertStatement = connection.prepareStatement(insertSql);
            for (Position position : board.keySet()) {
                insertStatement.setString(1, name);
                insertStatement.setString(2, String.valueOf(position.getColumn().getValue()));
                insertStatement.setInt(3, position.getRow().getValue());
                insertStatement.setString(4, board.get(position).getName());
                insertStatement.setString(5, board.get(position).getTeam().getValue());
                insertStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(final String name) {
        String deleteSql = "delete from board where name=?";
        try {
            PreparedStatement deleteStatement = connection.prepareStatement(deleteSql);
            deleteStatement.setString(1, name);
            deleteStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public BoardDto load(final String name) {
        String selectSql = "select * from board where name=?";
        Map<Position, Piece> board = new HashMap<>();
        try {
            PreparedStatement loadStatement = connection.prepareStatement(selectSql);
            loadStatement.setString(1, name);
            ResultSet resultSet = loadStatement.executeQuery();
            putPositionAndPiece(board, resultSet);
            validateBoardExist(board);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new BoardDto(board);
    }

    private void putPositionAndPiece(final Map<Position, Piece> board, final ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            String columnValue = resultSet.getString("position_column_value");
            int rowValue = resultSet.getInt("position_row_value");
            Position position = Position.valueOf(columnValue.charAt(0), rowValue);
            Piece piece = StringToPieceConvertor.convert(
                    resultSet.getString("piece_name"),
                    resultSet.getString("piece_team_value")
            );
            board.put(position, piece);
        }
    }

    private void validateBoardExist(Map<Position, Piece> board) {
        if (board.size() == 0) {
            throw new IllegalArgumentException("[ERROR] Board 가 존재하지 않습니다.");
        }
    }
}

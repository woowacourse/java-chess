package chess.domain.dao;

import chess.dto.BoardDto;
import chess.dto.PieceDto;
import chess.domain.entity.Board;
import chess.util.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BoardDaoImpl implements BoardDao {

    private final Connection connection;

    public BoardDaoImpl() {
        this(DbConnection.getConnection());
    }

    public BoardDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Long save(BoardDto boardDto) {
        final String sql = "INSERT INTO chess_board(game_id, board_position, board_piece, board_color) values (?, ?, ?, ?)";

        final Map<String, PieceDto> pieces = boardDto.getBoard();
        for (String position : pieces.keySet()) {
            savePiece(sql, boardDto.getGameId(), position, pieces.get(position));
        }

        return boardDto.getGameId();
    }

    private void savePiece(String sql, Long gameId, String position, PieceDto pieceDto) {
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, gameId);
            statement.setString(2, position);
            statement.setString(3, pieceDto.getType());
            statement.setString(4, pieceDto.getColor());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public List<Board> findBoardById(Long id) {
        final String sql = "SELECT * from chess_board WHERE game_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);

            ResultSet rs = statement.executeQuery();
            List<Board> boards = new ArrayList<>();
            while (rs.next()) {
                boards.add(new Board(
                        rs.getLong("board_id"),
                        rs.getLong("game_id"),
                        rs.getString("board_position"),
                        rs.getString("board_piece"),
                        rs.getString("board_color")));
            }

            return boards;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void updateByPosition(Long gameId, String position, PieceDto pieceDto) {
        final String sql = "UPDATE chess_board SET board_piece = ?, board_color = ? WHERE game_id = ? AND board_position = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, pieceDto.getType());
            statement.setString(2, pieceDto.getColor());
            statement.setLong(3, gameId);
            statement.setString(4, position);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}

package chess.domain.dao;

import chess.domain.util.DbConnection;
import chess.domain.dto.BoardDto;
import chess.domain.dto.PieceDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, gameId);
            statement.setString(2, position);
            statement.setString(3, pieceDto.getType());
            statement.setString(4, pieceDto.getColor());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

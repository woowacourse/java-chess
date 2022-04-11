package chess.dao;

import chess.service.dto.BoardDto;
import chess.service.dto.PieceWithSquareDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BoardDao {

    private static final String PIECE_COLOR = "piece_color";
    private static final String PIECE_TYPE = "piece_type";
    private static final String SQUARE = "square";
    private static final String EXCEPTION_MESSAGE_REMOVE = "보드를 제거하는 도중 문제가 생겼습니다.";
    private static final String EXCEPTION_MESSAGE_UPDATE = "기물 정보를 수정하던 도중 문제가 생겼습니다.";

    public void initBoard(int gameId) {
        String sql = "insert into board (game_id, piece_type, piece_color, square)\n"
                + "select ?, init.piece_type, init.piece_color, init.square from init_board as init\n"
                + "on duplicate key update piece_type = init.piece_type, piece_color = init.piece_color";
        new StatementExecutor(JdbcUtil.getConnection(), sql)
                .setInt(gameId)
                .executeUpdate();
    }

    public BoardDto getBoardByGameId(int id) {
        String sql = "select piece_type, piece_color, square from board where game_id = ?";
        try (Connection connection = JdbcUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            return getBoardDtoFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException("해당 게임에 있는 보드를 찾지 못했습니다.", e);
        }
    }

    private BoardDto getBoardDtoFromResultSet(ResultSet resultSet) throws SQLException {
        List<PieceWithSquareDto> pieces = new ArrayList<>();
        while (resultSet.next()) {
            String color = resultSet.getString(PIECE_COLOR);
            String type = resultSet.getString(PIECE_TYPE);
            String square = resultSet.getString(SQUARE);
            pieces.add(new PieceWithSquareDto(square, type, color));
        }
        return new BoardDto(pieces);
    }

    public void remove(int id) {
        String sql = "delete from board where game_id = ?";
        new StatementExecutor(JdbcUtil.getConnection(), sql)
                .setInt(id)
                .executeUpdate();
    }

    public void update(PieceWithSquareDto piece, int gameId) {
        String sql = "update board set piece_type = ?, piece_color = ? where square = ? and game_id = ?";
        new StatementExecutor(JdbcUtil.getConnection(), sql)
                .setString(piece.getType())
                .setString(piece.getColor())
                .setString(piece.getSquare())
                .setInt(gameId)
                .executeUpdate();
    }
}

package chess.dao;

import chess.dao.jdbcutil.JdbcUtil;
import chess.dao.jdbcutil.StatementExecutor;
import chess.service.dto.BoardDto;
import chess.service.dto.PieceWithSquareDto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DatabaseBoardDao implements BoardDao {

    private static final String PIECE_COLOR = "piece_color";
    private static final String PIECE_TYPE = "piece_type";
    private static final String SQUARE = "square";

    @Override
    public void initBoard(int gameId) {
        String sql = "insert into board (game_id, piece_type, piece_color, square)\n"
                + "select ?, init.piece_type, init.piece_color, init.square from init_board as init\n"
                + "on duplicate key update piece_type = init.piece_type, piece_color = init.piece_color";
        new StatementExecutor(JdbcUtil.getConnection(), sql)
                .setInt(gameId)
                .executeUpdate();
    }

    @Override
    public BoardDto getBoardByGameId(int id) {
        String sql = "select piece_type, piece_color, square from board where game_id = ?";
        List<PieceWithSquareDto> pieces = new StatementExecutor(JdbcUtil.getConnection(), sql)
                .setInt(id)
                .findAll(this::getPieceDto);
        return new BoardDto(pieces);
    }

    private PieceWithSquareDto getPieceDto(ResultSet resultSet) throws SQLException {
        String color = resultSet.getString(PIECE_COLOR);
        String type = resultSet.getString(PIECE_TYPE);
        String square = resultSet.getString(SQUARE);
        return new PieceWithSquareDto(square, type, color);
    }

    @Override
    public void remove(int id) {
        String sql = "delete from board where game_id = ?";
        new StatementExecutor(JdbcUtil.getConnection(), sql)
                .setInt(id)
                .executeUpdate();
    }

    @Override
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

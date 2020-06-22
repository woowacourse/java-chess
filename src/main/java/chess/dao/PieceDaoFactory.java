package chess.dao;

import chess.dao.jdbc.*;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class PieceDaoFactory {
    public PieceDao createPieceDao() {
        DataSource dataSource = new JdbcChessDataSource();
        RowMapper<Position, Piece> rowMapper = new PieceRowMapper();
        JdbcContext<Position, Piece> jdbcContext = new JdbcContext<>(dataSource, rowMapper);
        return new PieceDao(jdbcContext);
    }
}

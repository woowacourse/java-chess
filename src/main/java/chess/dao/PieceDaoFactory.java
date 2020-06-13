package chess.dao;

import chess.dao.jdbc.*;
import chess.domain.dto.PieceDto;

public class PieceDaoFactory {
    public PieceDao createPieceDao() {
        DataSource dataSource = new JdbcChessDataSource();
        RowMapper<PieceDto> rowMapper = new PieceRowMapper();
        JdbcContext<PieceDto> jdbcContext = new JdbcContext<>(dataSource, rowMapper);
        return new PieceDao(jdbcContext);
    }
}

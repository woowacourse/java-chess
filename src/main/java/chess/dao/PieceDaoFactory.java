package chess.dao;

public class PieceDaoFactory {
    public PieceDao createPieceDao() {
        DataSource dataSource = new JdbcChessDataSource();
        JdbcContext jdbcContext = new JdbcContext(dataSource);
        return new PieceDao(jdbcContext);
    }
}

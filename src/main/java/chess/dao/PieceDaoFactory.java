package chess.dao;

public class PieceDaoFactory {
    public PieceDao createPieceDao() {
        DataSource dataSource = new JdbcChessDataSource();
        return new PieceDao(dataSource);
    }
}

package chess.dao;

public class PieceDaoFactory {
    public PieceDao createPieceDao() {
        ConnectionGetter connectionGetter = new JdbcConnectionGetter();
        return new PieceDao(connectionGetter);
    }
}

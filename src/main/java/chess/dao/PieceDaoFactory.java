package chess.dao;

import chess.domain.piece.Piece;

public class PieceDaoFactory {
    public PieceDao createPieceDaoAdd(Piece piece) {
        ConnectionGetter connectionGetter = new JdbcConnectionGetter();
        return new PieceDaoAdd(connectionGetter, piece);
    }

    public PieceDao createPieceDaoGet(String id) {
        ConnectionGetter connectionGetter = new JdbcConnectionGetter();
        return new PieceDaoGet(connectionGetter, id);
    }
}

package chess.domain.piece.maker;

import chess.dao.PieceDao;
import chess.domain.piece.Piece;

import java.util.Set;

public class DbPieceLoadingGenerator implements PiecesGenerator{

    private final PieceDao pieceDao;

    public DbPieceLoadingGenerator(final PieceDao pieceDao){
        this.pieceDao = pieceDao;
    }

    @Override
    public Set<Piece> generate() {
        return pieceDao.findAllPieceInGame();
    }
}

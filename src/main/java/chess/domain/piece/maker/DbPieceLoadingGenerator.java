package chess.domain.piece.maker;

import chess.dao.PieceDao;
import chess.domain.piece.Piece;

import java.util.Set;

public class DbPieceLoadingGenerator implements PiecesGenerator {

    private final PieceDao pieceDao;
    private final int gameRoomId;

    public DbPieceLoadingGenerator(final PieceDao pieceDao, final int gameRoomId) {
        this.pieceDao = pieceDao;
        this.gameRoomId = gameRoomId;
    }

    @Override
    public Set<Piece> generate() {
        return pieceDao.findAllPieceInGame(gameRoomId);
    }
}

package chess.dao.initialboard;

import chess.dao.PieceDao;
import java.util.HashMap;
import java.util.Map;

public class PieceIdFactory {

    private static final PieceDao pieceDao = new PieceDao();
    private static final Map<String, Integer> value = new HashMap<>();

    static {
        value.put("blackQueen", pieceDao.getIdFrom("q", "BLACK"));
        value.put("blackBishop", pieceDao.getIdFrom("b", "BLACK"));
        value.put("blackKnight", pieceDao.getIdFrom("n", "BLACK"));
        value.put("blackRook", pieceDao.getIdFrom("r", "BLACK"));
        value.put("blackKing", pieceDao.getIdFrom("k", "BLACK"));
        value.put("blackPawn", pieceDao.getIdFrom("p", "BLACK"));

        value.put("whiteQueen", pieceDao.getIdFrom("q", "WHITE"));
        value.put("whiteBishop", pieceDao.getIdFrom("b", "WHITE"));
        value.put("whiteKnight", pieceDao.getIdFrom("n", "WHITE"));
        value.put("whiteRook", pieceDao.getIdFrom("r", "WHITE"));
        value.put("whiteKing", pieceDao.getIdFrom("k", "WHITE"));
        value.put("whitePawn", pieceDao.getIdFrom("p", "WHITE"));
    }

    static Integer of(final String pieceName) {
        return value.get(pieceName);
    }

    private PieceIdFactory() {
    }
}

package chess.service;

import chess.dao.PieceDao;
import chess.dao.PieceDaoImpl;
import chess.dao.dto.PieceDto;
import chess.domain.board.Board;
import chess.domain.board.Square;
import chess.domain.piece.Piece;
import java.util.List;
import java.util.Map;

public class PieceService {

    private static final PieceDao pieceDao = new PieceDaoImpl();

    public void saveAll(final Long chessGameId, final Board board) {
        final Map<Square, Piece> table = board.getBoard();
        for (final Square square : table.keySet()) {
            pieceDao.save(chessGameId, square, table.get(square));
        }
    }

    public List<PieceDto> findAllByChessGameId(final Long chessGameId) {
        return pieceDao.findAllByChessGameId(chessGameId);
    }
}

package chess.service;

import chess.dao.PieceDao;
import chess.dao.PieceDaoImpl;
import chess.dao.dto.PieceDto;
import chess.domain.board.Board;
import chess.domain.board.Square;
import chess.domain.game.ChessGame;
import chess.domain.piece.Piece;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    public void move(final ChessGame chessGame, final Square source, final Square destination) {
        final Optional<PieceDto> searchPiece = pieceDao.findBySquare(chessGame.getId(), destination);
        final Board board = chessGame.getBoard();
        final Piece destinationPiece = board.findPieceOf(destination)
                .orElseThrow(() -> new IllegalStateException("데이터가 존재하지 않습니다."));
        if (searchPiece.isEmpty()) {
            pieceDao.save(chessGame.getId(), destination, destinationPiece);
        } else {
            pieceDao.update(chessGame.getId(), destination, destinationPiece);
        }
        pieceDao.delete(chessGame.getId(), source);
    }

    public void deleteAll(final Long chessGameId) {
        pieceDao.deleteAll(chessGameId);
    }
}

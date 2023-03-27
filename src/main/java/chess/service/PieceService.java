package chess.service;

import chess.dao.PieceDao;
import chess.domain.board.ChessBoard;
import chess.domain.piece.Piece;
import chess.dto.PieceDto;
import java.util.ArrayList;
import java.util.List;

public class PieceService {

    private final PieceDao pieceDao = new PieceDao();

    public void create(final ChessBoard board, final int gameId) {
        for (Piece piece : board.pieces()) {
            final PieceDto pieceDto = new PieceDto(gameId, piece.type(), piece.getFileToString(), piece.rank(), piece.getColorToString());
            pieceDao.create(pieceDto);
        }
    }

    public List<Piece> findAllPieces() {
        final List<Integer> allIds = pieceDao.findAllIds();
        final List<Piece> allPieces = new ArrayList<>();
        for (int id : allIds) {
            allPieces.add(pieceDao.findPieceById(id));
        }
        return allPieces;
    }

    public void deleteAll() {
        final List<Integer> allIds = pieceDao.findAllIds();
        for (int id : allIds) {
            pieceDao.delete(id);
        }
    }
}

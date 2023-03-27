package chess.service;

import chess.dao.PieceDao;
import chess.domain.board.ChessBoard;
import chess.domain.piece.Piece;
import chess.dto.PieceDto;

public class PieceService {

    private final PieceDao pieceDao = new PieceDao();

    public void create(final ChessBoard board, final int gameId) {
        for (Piece piece : board.pieces()) {
            final PieceDto pieceDto = new PieceDto(gameId, piece.type(), piece.getFileToString(), piece.rank(), piece.getColorToString());
            pieceDao.create(pieceDto);
        }
    }
}

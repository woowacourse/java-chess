package chess.service;

import java.util.List;

import chess.domain.board.BoardDTO;
import chess.domain.chess.Chess;
import chess.domain.chess.ChessDAO;
import chess.domain.piece.PieceDAO;

public class ChessService {
    private final ChessDAO chessDAO;
    private final PieceDAO pieceDAO;

    public ChessService() {
        this.chessDAO = new ChessDAO();
        this.pieceDAO = new PieceDAO();
    }

    public Long insert() {
        final long newChessId = chessDAO.insert();

        final Chess chess = Chess.createWithEmptyBoard()
                                 .start();
        final BoardDTO boardDTO = BoardDTO.from(chess);
        pieceDAO.insert(newChessId, boardDTO);
        return newChessId;
    }

    public void delete(Long chessId) {
        chessDAO.delete(chessId);
    }
}

package chess.service;

import chess.domain.board.BoardDTO;
import chess.domain.chess.Chess;
import chess.domain.chess.ChessDAO;
import chess.domain.chess.ChessDTO;
import chess.domain.piece.PieceDAO;
import chess.domain.position.MovePosition;

public class ChessService {
    private final ChessDAO chessDAO;
    private final PieceDAO pieceDAO;

    public ChessService() {
        this.chessDAO = new ChessDAO();
        this.pieceDAO = new PieceDAO();
    }

    public ChessDTO getChessGame(Long chessId) {
        final Chess chess = chessDAO.findChessById(chessId);
        return new ChessDTO(chess);
    }

    public Long insert() {
        final Chess chess = Chess.createWithEmptyBoard()
                                 .start();
        final BoardDTO boardDTO = BoardDTO.from(chess);

        final long chessId = chessDAO.insert();
        pieceDAO.insert(chessId, boardDTO);

        return chessId;
    }

    public void move(Long chessId, MovePosition movePosition) {
        final Chess chess = chessDAO.findChessById(chessId)
                                    .move(movePosition);
        pieceDAO.move(chessId, movePosition);
        chessDAO.updateChess(chessId, chess.status(), chess.color());
    }
}

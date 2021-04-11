package chess.service;

import chess.domain.board.BoardDTO;
import chess.domain.chess.Chess;
import chess.domain.chess.ChessDAO;
import chess.domain.chess.ChessDTO;
import chess.domain.position.MovePosition;

public class ChessService {
    private final ChessDAO chessDAO;

    public ChessService() {
        this.chessDAO = new ChessDAO();
    }

    public Long insert() {
        final Chess chess = Chess.createWithEmptyBoard()
                                 .start();
        final BoardDTO boardDTO = BoardDTO.from(chess);
        return chessDAO.insert(boardDTO);
    }

    public ChessDTO getChessGame(Long chessId) {
        return chessDAO.findChessById(chessId);
    }

    public void move(Long chessId, MovePosition movePosition) {
        final ChessDTO chessDTO = chessDAO.findChessById(chessId);
        final Chess chess = Chess.from(chessDTO).move(movePosition);
        chessDAO.move(chessId, movePosition);
        chessDAO.updateChess(chessId, chess.status(), chess.color());
    }
}

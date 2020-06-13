package chess.domain.board.service;

import chess.domain.board.Board;
import chess.domain.piece.service.PieceService;
import chess.domain.position.MovingFlow;

public class BoardService {
    private Board board;

    public Board initialize(PieceService pieceService) {
        board = Board.initialize(pieceService);
        return board;
    }

    public Board resume(PieceService pieceService) {
        board = Board.resume(pieceService);
        return board;
    }

    public Board movePiece(MovingFlow movingFlow) {
        board = board.movePiece(movingFlow);
        return board;
    }
}

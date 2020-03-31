package chess.service;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.result.ChessResult;

public class ChessService {

    private Board board;

    public Board createEmpty() {
        board = Board.createEmpty();
        return board;
    }

    public Board placeInitialPieces() {
        board = board.placeInitialPieces();
        return board;
    }

    public Board move(Position source, Position target) {
        board = board.move(source, target);
        return board;
    }

    public ChessResult calculateResult() {
        return board.calculateResult();
    }

    public boolean checkGameNotFinished() {
        return board.isNotFinished();
    }
}

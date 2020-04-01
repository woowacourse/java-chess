package chess.service;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.result.ChessResult;

public class ChessService {

    private Board board;

    public Board createEmpty() {
        board = BoardFactory.createEmptyBoard();
        return board;
    }

    public Board placeInitialPieces() {
        board = BoardFactory.createInitialBoard();
        return board;
    }

    public Board move(String source, String target) {
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

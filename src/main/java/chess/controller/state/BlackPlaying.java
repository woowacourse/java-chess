package chess.controller.state;

import chess.domain.Color;
import chess.domain.board.Board;
import chess.domain.board.MoveResult;

public class BlackPlaying extends Playing {
    BlackPlaying(Board board) {
        super(board);
    }

    @Override
    public Turn getTurn() {
        return Turn.BLACK_TURN;
    }

    @Override
    public ChessGameState move(String from, String to) {
        MoveResult result = movePiece(from, to, Color.BLACK);
        return getMoveResult(result, MoveResult.FAIL);
    }
}

package chess.controller.state;

import chess.domain.Color;
import chess.domain.board.Board;
import chess.domain.board.MoveResult;

public class BlackPlaying extends Playing {

    public BlackPlaying(Board board) {
        this.board = board;
    }

    @Override
    public ChessGameState move(String from, String to) {
        MoveResult result = movePiece(from, to, Color.BLACK);
        return getMoveResult(result, MoveResult.FAIL);
    }
}

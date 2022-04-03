package chess.controller.state;

import chess.domain.Color;
import chess.domain.board.Board;
import chess.domain.board.MoveResult;

public class WhitePlaying extends Playing {
    WhitePlaying(Board board) {
        super(board);
    }

    @Override
    public Turn getTurn() {
        return Turn.WHITE_TURN;
    }

    @Override
    public ChessGameState move(String from, String to) {
        MoveResult result = movePiece(from, to, Color.WHITE);

        return getMoveResult(result, MoveResult.SUCCESS);
    }
}

package chess.controller.state;

import chess.domain.Color;
import chess.domain.board.Board;
import chess.domain.board.MoveResult;

public class WhitePlaying extends Playing  {
    public WhitePlaying(Board board) {
        this.board = board;
    }

    @Override
    public ChessGameState move(String from, String to) {
        MoveResult result = movePiece(from, to, Color.WHITE);

        return getMoveResult(result, MoveResult.SUCCESS);
    }
}

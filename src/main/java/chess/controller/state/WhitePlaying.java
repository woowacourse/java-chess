package chess.controller.state;

import chess.domain.Color;
import chess.domain.board.Board;
import chess.domain.board.MoveResult;

public class WhitePlaying extends Playing {
    WhitePlaying(Board board) {
        super(board);
    }

    @Override
    public ChessGameState move(String from, String to) {
        MoveResult result = movePiece(from, to, Color.WHITE);
        return checkMoveResult(result);
    }

    @Override
    ChessGameState checkMoveResult(MoveResult result) {
        if (result == MoveResult.ENDED) {
            return new Finished();
        }

        if (result == MoveResult.SUCCESS) {
            return new BlackPlaying(board);
        }
        return new WhitePlaying(board);
    }

    @Override
    public ChessGameState end() {
        return new Finished();
    }
}

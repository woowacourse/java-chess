package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.board.Score;
import chess.domain.board.ScoreResult;
import chess.domain.piece.Color;
import java.util.HashMap;
import java.util.Map;

abstract class Running extends Ready {

    Running(Board board) {
        super(board);
    }

    public abstract State movePiece(Position src, Position dest);

    @Override
    public boolean isFinished() {
        return false;
    }
}

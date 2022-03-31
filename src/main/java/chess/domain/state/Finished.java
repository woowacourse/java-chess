package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.command.Command;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import java.util.Map;

import chess.domain.ChessScore;
import chess.domain.position.Position;

public class Finished extends State {

    private static final String CANNOT_PROCEED = "끝난 상태에서는 진행할 수 없습니다.";

    public Finished(Map<Position, Piece> pieces) {
        this.board = new Board(pieces);
    }

    @Override
    public State proceed(Command command) {
        throw new IllegalStateException(CANNOT_PROCEED);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}

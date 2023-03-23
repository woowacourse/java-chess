package chess.domain.piece.info;

import chess.domain.Turn;
import chess.domain.position.Position;

public class Log {

    private final Turn turn;
    private final Position position;

    Log(final Turn turn, final Position position) {
        this.turn = turn;
        this.position = position;
    }
}

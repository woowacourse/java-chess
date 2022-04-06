package chess.domain.state;

import chess.domain.Board;
import chess.domain.piece.Team;

public class StateFactory {
    public static State of(Team team, Board board) {
        if (team.isBlack()) {
            return new Black(board);
        }

        if (team.isWhite()) {
            return new White(board);
        }

        return new Ready();
    }
}

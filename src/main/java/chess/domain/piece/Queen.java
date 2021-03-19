package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Position;
import chess.domain.Score;
import chess.domain.TeamColor;

public class Queen extends Piece {

    public Queen(TeamColor teamColor, Position position) {
        super("q", teamColor, Score.from(9), Direction.aroundDirections(), Direction.aroundDirections(), true, position);
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }
}

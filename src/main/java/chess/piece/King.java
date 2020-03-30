package chess.piece;

import chess.position.File;
import chess.position.Position;
import chess.position.Rank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class King extends Piece {
    public King(Team team) {
        super(team, "K");
    }

    @Override
    public boolean isInvalidMovementWithoutConsideringOtherPieces(Position source, Position target) {
        return source.isNotDistanceOneSquare(target);
    }

    @Override
    public List<Position> movePathExceptSourceAndTarget(Position start, Position end) {
        return Collections.emptyList();
    }
}

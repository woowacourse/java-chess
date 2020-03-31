package chess.piece;

import chess.position.File;
import chess.position.Position;
import chess.position.Rank;
import chess.validator.KingMoveValidator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class King extends Piece {
    public King(Team team) {
        super(team, "K", new KingMoveValidator());
    }

//    @Override
//    public List<Position> movePathExceptSourceAndTarget(Position start, Position end) {
//        return Collections.emptyList();
//    }
}

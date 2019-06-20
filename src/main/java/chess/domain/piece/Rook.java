package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.AbstractPiece;
import chess.domain.MoveRules;
import chess.domain.Team;
import chess.domain.exceptions.InvalidDirectionException;

import java.util.Arrays;
import java.util.List;

import static chess.domain.Direction.*;
import static chess.domain.Direction.W;

public class Rook extends AbstractPiece {
    private static final String NAME = "r";
    private static final double SCORE = 5;

    public Rook(Team team) {
        super(team, MoveRules::rook);
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public double getScore() {
        return SCORE;
    }


}

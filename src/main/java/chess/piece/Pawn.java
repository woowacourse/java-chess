package chess.piece;

import chess.position.Position;
import chess.position.Rank;
import chess.validator.PawnMoveValidator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Pawn extends Piece {
    public Pawn(Team team) {
        super(team, "P", new PawnMoveValidator());
    }
}
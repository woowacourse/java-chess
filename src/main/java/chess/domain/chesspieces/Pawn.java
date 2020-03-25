package chess.domain.chesspieces;

import chess.domain.moverules.MoveRule;
import chess.domain.position.Position;

import java.util.Arrays;

public class Pawn extends ChessPiece {
    public Pawn(String name) {
        super(name);
        moveRules.addAll(Arrays.asList(MoveRule.TOP,
                MoveRule.DIAGONAL_TOP_LEFT, MoveRule.DIAGONAL_TOP_RIGHT));
    }

    @Override
    public void move(Position source, Position target) {

    }
}

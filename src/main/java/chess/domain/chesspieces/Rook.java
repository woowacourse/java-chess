package chess.domain.chesspieces;

import chess.domain.moverules.MoveRule;
import chess.domain.position.Position;

import java.util.Arrays;

public class Rook extends ChessPiece{
    public Rook(String name) {
        super(name);
        moveRules.addAll(Arrays.asList(MoveRule.TOP, MoveRule.DOWN,
                MoveRule.LEFT, MoveRule.RIGHT));
    }

    @Override
    public void move(Position source, Position target) {

    }
}

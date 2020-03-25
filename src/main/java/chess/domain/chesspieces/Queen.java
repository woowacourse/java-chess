package chess.domain.chesspieces;

import chess.domain.moverules.MoveRule;
import chess.domain.position.Position;

import java.util.Arrays;

public class Queen extends ChessPiece{
    public Queen(String name) {
        super(name);
        moveRules.addAll(Arrays.asList(MoveRule.values()));
    }

    @Override
    public void move(Position source, Position target) {

    }
}

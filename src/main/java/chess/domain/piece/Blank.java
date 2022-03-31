package chess.domain.piece;

import chess.domain.Movement;
import java.util.List;

import chess.domain.Color;
import chess.domain.position.Position;

public class Blank extends Piece {

    public Blank() {
        super(Color.NONE);
    }

    @Override
    protected List<Movement> chooseMovements() {
        return List.of();
    }

    @Override
    public String baseSignature() {
        return ".";
    }

    @Override
    public boolean isCorrectMovement(Position source, Position target, Piece targetPiece) {
        throw new IllegalArgumentException("기물이 없는 위치입니다.");
    }

    @Override
    public boolean canJumpOverPieces() {
        return true;
    }

    @Override
    public double score() {
        throw new IllegalArgumentException("기물이 없는 위치입니다.");
    }

    @Override
    public boolean isBlank() {
        return true;
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

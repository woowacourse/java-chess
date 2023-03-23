package chess.domain.piece;

import chess.domain.position.Position;

public class Empty implements Piece {
    private static Empty instance;

    private Empty() {
    }

    public static Empty getInstance() {
        if (instance == null) {
            instance = new Empty();
        }
        return instance;
    }

    @Override
    public Piece move(Position currentPosition, Position nextPosition, Piece pieceOfNextPosition) {
        throw new IllegalArgumentException("기물이 존재하지 않는 위치입니다.");
    }

    @Override
    public void validateMovement(Position currentPosition, Position nextPosition) {
        throw new IllegalArgumentException("기물이 존재하지 않는 위치입니다.");
    }

    @Override
    public boolean isOpponent(Color other) {
        throw new IllegalArgumentException("기물이 존재하지 않는 위치입니다.");
    }

    @Override
    public boolean isOpponent(Piece other) {
        throw new IllegalArgumentException("기물이 존재하지 않는 위치입니다.");
    }

    @Override
    public boolean isSameColor(Color other) {
        throw new IllegalArgumentException("기물이 존재하지 않는 위치입니다.");
    }

    @Override
    public boolean isSameColor(Piece other) {
        throw new IllegalArgumentException("기물이 존재하지 않는 위치입니다.");
    }

    @Override
    public String formatName() {
        return ".";
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public boolean isPiece() {
        return false;
    }

    @Override
    public boolean isSliding() {
        throw new IllegalArgumentException("기물이 존재하지 않는 위치입니다.");
    }
}

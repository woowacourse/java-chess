package chess.domain.piece;

import chess.domain.direction.DirectionDecider;
import chess.domain.direction.KnightDirection;
import java.util.List;

import chess.domain.position.Position;
import chess.domain.direction.Direction;

public class Knight extends Piece {

    private static final Knight whiteKing = new Knight(Color.WHITE);
    private static final Knight blackKing = new Knight(Color.BLACK);

    private static final List<Direction> DIRECTIONS = List.of(KnightDirection.values());

    private Knight(Color color) {
        super(color);
    }

    public static Knight createWhite() {
        return whiteKing;
    }

    public static Knight createBlack() {
        return blackKing;
    }

    @Override
    protected Direction matchDirection(Position from, Position to) {
        return DirectionDecider.generateUnitPosition(DIRECTIONS, from, to);
    }

    @Override
    public boolean isKnight() {
        return true;
    }

    @Override
    public String getImage() {
        if (this.color == Color.WHITE) {
            return "white_knight.png";
        }
        return "black_knight.png";
    }

    @Override
    public double getScore() {
        return 2.5;
    }

    @Override
    public String getName() {
        return "Knight";
    }
}

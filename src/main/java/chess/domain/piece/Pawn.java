package chess.domain.piece;

import chess.domain.board.position.Position;
import chess.domain.board.position.Ypoint;
import chess.domain.piece.team.Color;
import chess.domain.piece.team.Symbol;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Pawn extends Piece {
    private Pawn(Color color) {
        super(color, Symbol.PAWN);
    }

    public static Pawn createBlack() {
        return new Pawn(Color.BLACK);
    }

    public static Pawn createWhite() {
        return new Pawn(Color.WHITE);
    }

    public Set<Position> moveStrategy(Position position) {
        if (isBlack()) {
            return blackMovable(position);
        }
        return whiteMovable(position);
    }

    private Set<Position> whiteMovable(Position position) {
        Set<Position> positions = new HashSet<>(Arrays.asList(position.leftUp(), position.up(), position.rightUp()));
        if (position.isSameY(Ypoint.TWO)) {
            positions.add(position.doubleUp());
        }
        return positions;
    }

    private Set<Position> blackMovable(Position position) {
        Set<Position> positions = new HashSet<>(Arrays.asList(position.leftDown(), position.down(), position.rightDown()));
        if (position.isSameY(Ypoint.SEVEN)) {
            positions.add(position.doubleDown());
        }
        return positions;
    }
}

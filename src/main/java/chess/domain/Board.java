package chess.domain;

import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Board {
    private final Map<Position, Piece> squares;

    public Board(Map<Position, Piece> squares) {
        this.squares = squares;
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(squares);
    }

    public void move(Position source, Position target) {
        Piece sourcePiece = squares.get(source);
        Piece targetPiece = squares.get(target);
        if (sourcePiece.getTeam() == targetPiece.getTeam()) {
            throw new IllegalArgumentException();
        }
        if (!sourcePiece.canMove(source, target)) {
            throw new IllegalArgumentException();
        }
        List<Position> routes = source.getBetweenPositions(target);
        for (Position route : routes) {
            if (squares.get(route) != Empty.INSTANCE) {
                throw new IllegalArgumentException();
            }
        }
        squares.put(source, Empty.INSTANCE);
        squares.put(target, sourcePiece);
    }
}

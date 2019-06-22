package chess.domain.board;

import chess.domain.piece.piecefigure.Piece;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Board {
    private final Map<Position, Piece> pieces = new HashMap<>();

    public Board() {

    }

    public boolean isMovableDestination(Position source, Position destination) {
        Set<Position> possiblePositions =
                pieces.get(source).makePossiblePositions(source, this::getCurrentPiece);

        return possiblePositions.contains(destination);
    }

    public Piece getCurrentPiece(Position current) {
        return pieces.get(current);
    }
}

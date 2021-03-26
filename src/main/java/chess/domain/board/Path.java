package chess.domain.board;

import chess.domain.piece.strategy.Direction;
import chess.domain.position.Coordinate;
import java.util.ArrayList;
import java.util.List;

public final class Path {

    private final Coordinate source;
    private final Direction direction;

    private final List<Coordinate> coordinates;

    public Path(final Piece, final Direction direction) {
        this()
    }

    private Path(final List<Coordinate> possibleCoordinates) {
        this.coordinates = possibleCoordinates;
    }

    public List<Coordinate> possibleCoordinates() {
        final List<Coordinate> possibleCoordinates = new ArrayList<>();

        return possibleCoordinates;
    }

    public boolean isEmpty() {
        return coordinates.isEmpty();
    }
//    Stream<Position2> stream();
    // PATH UNTIL END OF THE BOARD
//    private final PieceColor pieceColor;
//    private final List<Position2> possiblePositions;
//
//    public Path2(PieceColor pieceColor, List<Position2> possiblePositions) {
//        this.pieceColor = pieceColor;
//        this.possiblePositions = possiblePositions;
//    }
//
//    public List<Position2> validPositions() {
//        final List<Position2> validPositions = new ArrayList<>();
//        for (Position2 position : possiblePositions) {
//            if (position.holdingPieceIsColor(pieceColor)) {
//                break;
//            }
//        }
//        return validPositions;
//    }
//
//    public List<Position2> allPositions() {
//
//    }
}

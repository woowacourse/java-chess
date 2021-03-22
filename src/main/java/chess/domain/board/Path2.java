package chess.domain.board;

import chess.domain.position.Position2;
import java.util.List;
import java.util.stream.Stream;

public interface Path2 {

    List<Position2> allPositions();

    Stream<Position2> stream();
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
//            if (position.isColor(pieceColor)) {
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

package chess.model.position;

import chess.model.piece.Blank;
import chess.model.piece.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.Collections.unmodifiableList;

public class Path {
    private final List<ChessPosition> chessPositions;

    public Path(List<ChessPosition> chessPositions) {
        this.chessPositions = chessPositions;
    }

    public static Path makeStraightPath(ChessPosition sourcePosition, Movement movement) {
        return new Path(findStraightPath(sourcePosition, movement));
    }

    public static Path empty() {
        return new Path(List.of());
    }

    private static List<ChessPosition> findStraightPath(ChessPosition sourcePosition, Movement movement) {
        if (!movement.isDiagonal() && !movement.isOrthogonal()) {
            return List.of();
        }
        int fileOffset = movement.calculateFileIncrement();
        int rankOffset = movement.calculateRankIncrement();
        int pathLength = movement.calculateLength();
        return makePath(sourcePosition, pathLength, fileOffset, rankOffset);
    }

    private static List<ChessPosition> makePath(ChessPosition sourcePosition, int pathLength, int fileOffset, int rankOffset) {
        List<ChessPosition> path = new ArrayList<>();
        ChessPosition prevPosition = sourcePosition;
        while (path.size() < pathLength) {
            ChessPosition nextPosition = prevPosition.calculateNextPosition(fileOffset, rankOffset);
            path.add(nextPosition);
            prevPosition = nextPosition;
        }
        return unmodifiableList(path);
    }

    public boolean isEmpty() {
        return chessPositions.isEmpty();
    }

    public boolean containsPiece(Map<ChessPosition, Piece> board) {
        int middlePathLength = chessPositions.size() - 1;
        return IntStream.range(0, middlePathLength)
                .mapToObj(chessPositions::get)
                .map(board::get)
                .anyMatch(piece -> !piece.equals(Blank.INSTANCE));
    }

    public List<ChessPosition> getChessPositions() {
        return chessPositions;
    }
}

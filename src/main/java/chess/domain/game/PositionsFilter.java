package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.position.Direction;
import chess.domain.board.position.Position;
import chess.domain.piece.Piece;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class PositionsFilter {

    public List<Position> generateValidPositions(Map<Direction, Queue<Position>> candidateAllPositions,
                                                 Piece piece,
                                                 Board board) {
        return candidateAllPositions.keySet().stream()
                .map(direction -> filterInvalidPositions(candidateAllPositions.get(direction), direction, piece, board))
                .flatMap(List::stream)
                .toList();
    }

    private List<Position> filterInvalidPositions(Queue<Position> expectedPositions, Direction direction,
                                                  Piece piece, Board board) {
        List<Position> result = new ArrayList<>();
        Position currentPosition = expectedPositions.poll();
        while (isEmptySpace(direction, piece, currentPosition, board)) {
            result.add(currentPosition);
            currentPosition = expectedPositions.poll();
        }
        if (isEnemySpace(direction, piece, currentPosition, board)) {
            result.add(currentPosition);
        }
        return result;
    }

    private boolean isEmptySpace(Direction direction, Piece piece, Position currentPosition, Board board) {
        return currentPosition != null
                && piece.isPawnMovePossible(direction)
                && board.isEmptySpace(currentPosition);
    }

    private boolean isEnemySpace(Direction direction, Piece piece, Position currentPosition, Board board) {
        return currentPosition != null
                && piece.isPawnAttackPossible(direction)
                && board.existPiece(currentPosition)
                && board.findPieceByPosition(currentPosition).isEnemy(piece);
    }
}

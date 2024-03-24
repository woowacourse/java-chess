package chess.domain;

import chess.domain.board.Board;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class ChessGame {

    public final Board board;

    public ChessGame(Board board) {
        this.board = board;
    }

    public List<Position> generateMovablePositions(Position position) {
        Piece piece = board.findPieceByPosition(position);
        Map<Direction, Queue<Position>> expectedAllPositions = piece.generateAllDirectionPositions(position);
        return generateValidPositions(expectedAllPositions, piece);
    }

    private List<Position> generateValidPositions(Map<Direction, Queue<Position>> expectedAllPositions, Piece piece) {
        return expectedAllPositions.keySet().stream()
                .map(direction -> filterInvalidPositions(expectedAllPositions.get(direction), direction, piece))
                .flatMap(List::stream)
                .toList();
    }

    private List<Position> filterInvalidPositions(Queue<Position> expectedPositions, Direction direction, Piece piece) {
        List<Position> result = new ArrayList<>();
        Position currentPosition = expectedPositions.poll();
        while (isEmptySpace(direction, piece, currentPosition)) {
            result.add(currentPosition);
            currentPosition = expectedPositions.poll();
        }
        if (isEnemySpace(direction, piece, currentPosition)) {
            result.add(currentPosition);
        }
        return result;
    }

    private boolean isEmptySpace(Direction direction, Piece piece, Position currentPosition) {
        return currentPosition != null
                && piece.isPawnMovePossible(direction)
                && board.isEmptySpace(currentPosition);
    }

    private boolean isEnemySpace(Direction direction, Piece piece, Position currentPosition) {
        return currentPosition != null
                && piece.isPawnAttackPossible(direction)
                && board.hasPiece(currentPosition)
                && board.findPieceByPosition(currentPosition).isEnemy(piece);
    }

    public void movePiece(List<Position> positions, Position from, Position to) {
        if (positions.contains(to)) {
            board.movePiece(from, to);
            return;
        }
        throw new IllegalArgumentException("기물을 해당 위치로 이동시킬 수 없습니다.");
    }

    public Board getBoard() {
        return board;
    }
}

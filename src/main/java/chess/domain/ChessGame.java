package chess.domain;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class ChessGame {

    public final Board board;

    public Board getBoard() {
        return board;
    }

    public ChessGame(Board board) {
        this.board = board;
    }

    public List<Position> generateMovablePositions(Position position) {
        Piece piece = board.findPieceByPosition(position);
        Map<Direction, Deque<Position>> expectedAllPositions = piece.calculateAllDirectionPositions(position); // TODO Map<Direction, List<Position>> 객체 감싸기
        if (piece.getPieceType() == PieceType.BLACK_PAWN || piece.getPieceType() == PieceType.WHITE_PAWN) { // TODO getter refactor
            return generateValidPositionsWithPawn(expectedAllPositions, piece);
        }
        return generateValidPositions(expectedAllPositions, piece);
    }

    private List<Position> generateValidPositions(Map<Direction, Deque<Position>> expectedAllPositions, Piece piece) {
        return expectedAllPositions.keySet()
                .stream()
                .map(expectedAllPositions::get)
                .map(expectedPositions -> filterInvalidPositions(expectedPositions, piece))
                .flatMap(List::stream)
                .toList();
    }

    private List<Position> filterInvalidPositions(Deque<Position> expectedPositions, Piece piece) {
        List<Position> result = new ArrayList<>();
        while (isNotEmpty(expectedPositions) && board.isEmptySpace(expectedPositions.peek())) {
            Position position = expectedPositions.poll();
            result.add(position);
        }
        addObstaclePosition(result, expectedPositions, piece);
        return result;
    }

    // 상대 기물이 있을 때만 추가
    private void addObstaclePosition(List<Position> result, Queue<Position> expectedPositions, Piece piece) {
        Position last = expectedPositions.poll();
        if (last != null && board.hasPiece(last) && !board.findPieceByPosition(last).isSameTeam(piece)) {
            result.add(last);
        }
    }

    private boolean isNotEmpty(Queue<Position> expectedPositions) { // TODO 메서드명 변경
        return !expectedPositions.isEmpty();
    }

    private List<Position> generateValidPositionsWithPawn(Map<Direction, Deque<Position>> expectedAllPositions, Piece piece) {
        return expectedAllPositions.keySet()
                .stream()
                .map(direction -> filterInvalidPositionsWithPawn(expectedAllPositions.get(direction), direction, piece))
                .flatMap(List::stream)
                .toList();
    }

    private List<Position> filterInvalidPositionsWithPawn(Deque<Position> expectedPositions, Direction direction, Piece piece) {
        if (piece.isBlack()) {
            return handleBlackPawn(expectedPositions, direction, piece);
        }
        return handleWhitePawn(expectedPositions, direction, piece);
    }

    private List<Position> handleBlackPawn(Deque<Position> expectedPositions, Direction direction, Piece piece) {
        List<Position> result = new ArrayList<>();
        while (isNotEmpty(expectedPositions) && board.isEmptySpace(expectedPositions.peek()) && direction == Direction.S) {
            Position position = expectedPositions.poll();
            result.add(position);
        }

        if (direction == Direction.SE || direction == Direction.SW) {
            addObstaclePosition(result, expectedPositions, piece);
        }
        return result;
    }

    private List<Position> handleWhitePawn(Deque<Position> expectedPositions, Direction direction, Piece piece) {
        List<Position> result = new ArrayList<>();
        while (isNotEmpty(expectedPositions) && board.isEmptySpace(expectedPositions.peek()) && direction == Direction.N) {
            Position position = expectedPositions.poll();
            result.add(position);
        }

        if (direction == Direction.NE || direction == Direction.NW) {
            addObstaclePosition(result, expectedPositions, piece);
        }
        return result;
    }

    public void movePiece(List<Position> positions, Position from, Position to) {
        Piece fromPiece = board.findPieceByPosition(from);
        Position toPosition = positions.stream()
                .filter(position -> position.equals(to))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 위치로 이동시킬 수 없습니다."));
        Piece toPiece = board.findPieceByPosition(toPosition);
        if (fromPiece.isSameTeam(toPiece)) {
            throw new RuntimeException("같은 팀 기물 위치로 이동시킬 수 없습니다.");
        }
        board.movePiece(from, to);
    }
}

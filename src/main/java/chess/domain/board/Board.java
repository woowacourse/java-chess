package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceFactory;
import chess.domain.position.Position;
import chess.domain.position.XAxis;
import chess.domain.position.YAxis;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class Board {
    private final Map<Position, Piece> value;

    private Board(Map<Position, Piece> value) {
        this.value = value;
    }

    public static Board createInitializedBoard() {
        return new Board(initBoard());
    }

    private static Map<Position, Piece> initBoard() {
        Map<Position, Piece> value = new HashMap<>();

        initializeSpecialPieces(value, YAxis.ONE, PieceColor.WHITE);
        initializeSpecialPieces(value, YAxis.EIGHT, PieceColor.BLACK);

        initializePawns(value, YAxis.TWO, PieceColor.WHITE);
        initializePawns(value, YAxis.SEVEN, PieceColor.BLACK);

        return value;
    }

    private static void initializePawns(Map<Position, Piece> value, YAxis yAxis, PieceColor pieceColor) {
        for (XAxis xAxis : XAxis.values()) {
            value.put(Position.from(xAxis, yAxis), PieceFactory.createPawn(pieceColor));
        }
    }

    private static void initializeSpecialPieces(Map<Position, Piece> value, YAxis yAxis,
                                                PieceColor pieceColor) {
        value.put(Position.from(XAxis.A, yAxis), PieceFactory.createRook(pieceColor));
        value.put(Position.from(XAxis.B, yAxis), PieceFactory.createNight(pieceColor));
        value.put(Position.from(XAxis.C, yAxis), PieceFactory.createBishop(pieceColor));
        value.put(Position.from(XAxis.D, yAxis), PieceFactory.createQueen(pieceColor));
        value.put(Position.from(XAxis.E, yAxis), PieceFactory.createKing(pieceColor));
        value.put(Position.from(XAxis.F, yAxis), PieceFactory.createBishop(pieceColor));
        value.put(Position.from(XAxis.G, yAxis), PieceFactory.createNight(pieceColor));
        value.put(Position.from(XAxis.H, yAxis), PieceFactory.createRook(pieceColor));
    }

    public Optional<Piece> find(Position position) {
        return Optional.ofNullable(value.get(position));
    }

    // TODO: 실패 원인을 클라이언트가 알 수 있게 개선
    public MoveResult executeCommand(Position from, Position to, PieceColor pieceColor) {
        Piece piece = value.get(from);

        if (Objects.isNull(piece)) {
            return MoveResult.EMPTY_CELL;
        }
        if (!piece.isSameColorAs(pieceColor)) {
            return MoveResult.INVALID_TURN;
        }
        Piece otherPiece = value.get(to);

        if (!piece.isAbleToJump() && hasObstacle(from, to)) {
            return MoveResult.HAS_OBSTACLE;
        }

        if (otherPiece == null) {
            return move(from, to);
        }

        return attack(from, to);
    }

    private boolean hasObstacle(Position from, Position to) {
        if (from.isSameXAxis(to)) {
            return hasAnyPiece(from.getPositionsSameYAxisBetween(to));
        }

        if (from.isSameYAxis(to)) {
            return hasAnyPiece(from.getPositionsSameXAxisBetween(to));
        }

        if (from.isOnDiagonal(to)) {
            return hasAnyPiece(from.getPositionsSameDirectionDiagonalBetween(to));
        }

        return false;
    }

    private boolean hasAnyPiece(List<Position> positions) {
        return positions.stream()
                .map(value::get)
                .anyMatch(position -> !Objects.isNull(position));
    }

    private MoveResult move(Position from, Position to) {
        Piece piece = value.get(from);
        Piece otherPiece = value.get(to);

        if (!piece.isAbleToMove(from, to)) {
            return MoveResult.INVALID_MOVE_STRATEGY;
        }

        if (isExistingSameTeam(piece, otherPiece)) {
            return MoveResult.EXISTING_SAME_TEAM;
        }

        value.put(to, piece);
        value.remove(from);
        return MoveResult.MOVE_SUCCESS;
    }

    private MoveResult attack(Position from, Position to) {
        Piece piece = value.get(from);
        Piece otherPiece = value.get(to);

        if (!piece.isAbleToAttack(from, to)) {
            return MoveResult.INVALID_MOVE_STRATEGY;
        }

        if (isExistingSameTeam(piece, otherPiece)) {
            return MoveResult.EXISTING_SAME_TEAM;
        }

        value.put(to, piece);
        value.remove(from);

        if (otherPiece.isKing()) {
            return MoveResult.KILL_KING;
        }

        return MoveResult.KILL_ENEMY;
    }

    private boolean isExistingSameTeam(Piece piece, Piece otherPiece) {
        return !Objects.isNull(otherPiece) && otherPiece.isSameColorAs(piece);
    }

    public double calculateScore(PieceColor pieceColor) {
        return value.values()
                .stream()
                .filter(piece -> piece.isSameColorAs(pieceColor))
                .mapToDouble(Piece::getScore)
                .sum() - adjustPawnScore(pieceColor);
    }

    private double adjustPawnScore(PieceColor pieceColor) {
        int totalDuplicatedPawnCount = 0;

        for (XAxis xAxis : XAxis.values()) {
            totalDuplicatedPawnCount += getDuplicatedPawnCountInColumn(pieceColor, xAxis);
        }

        return totalDuplicatedPawnCount * 0.5;
    }

    private int getDuplicatedPawnCountInColumn(PieceColor pieceColor, XAxis xAxis) {
        List<Position> positions = Position.getPositionsByXAxis(xAxis);
        int count = countPawnByPositions(pieceColor, positions);

        if (count >= 2) {
            return count;
        }
        return 0;
    }

    private int countPawnByPositions(PieceColor pieceColor, List<Position> positions) {
        return (int) positions.stream()
                .map(value::get)
                .filter(piece -> !Objects.isNull(piece))
                .filter(piece -> piece.isSameColorAs(pieceColor))
                .filter(Piece::isPawn)
                .count();
    }
}

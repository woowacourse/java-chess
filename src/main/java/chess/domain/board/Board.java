package chess.domain.board;

import chess.domain.piece.AbstractPiece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceFactory;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import chess.domain.position.XAxis;
import chess.domain.position.YAxis;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class Board {
    private final Map<Position, AbstractPiece> value;

    private Board(Map<Position, AbstractPiece> value) {
        this.value = value;
    }

    public static Board createInitializedBoard() {
        return new Board(initBoard());
    }

    private static Map<Position, AbstractPiece> initBoard() {
        Map<Position, AbstractPiece> value = new HashMap<>();

        initializeSpecialPieces(value, YAxis.ONE, PieceColor.WHITE);
        initializeSpecialPieces(value, YAxis.EIGHT, PieceColor.BLACK);

        initializePawns(value, YAxis.TWO, PieceColor.WHITE);
        initializePawns(value, YAxis.SEVEN, PieceColor.BLACK);

        return value;
    }

    private static void initializePawns(Map<Position, AbstractPiece> value, YAxis yAxis, PieceColor pieceColor) {
        for (XAxis xAxis : XAxis.values()) {
            value.put(Position.from(xAxis, yAxis), PieceFactory.createPawn(pieceColor));
        }
    }

    private static void initializeSpecialPieces(Map<Position, AbstractPiece> value, YAxis yAxis,
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

    public Optional<AbstractPiece> find(Position position) {
        return Optional.ofNullable(value.get(position));
    }

    // TODO: 실패 원인을 클라이언트가 알 수 있게 개선
    public MoveResult executeCommand(Position from, Position to, PieceColor pieceColor) {
        AbstractPiece piece = value.get(from);

        if (Objects.isNull(piece)) {
            return MoveResult.EMPTY_CELL;
        }
        if (!piece.isPieceColor(pieceColor)) {
            return MoveResult.INVALID_TURN;
        }
        AbstractPiece otherPiece = value.get(to);

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
        AbstractPiece piece = value.get(from);
        AbstractPiece otherPiece = value.get(to);

        if (!piece.isMovable(from, to)) {
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
        AbstractPiece piece = value.get(from);
        AbstractPiece otherPiece = value.get(to);

        if (!piece.isAbleToAttack(from, to)) {
            return MoveResult.INVALID_MOVE_STRATEGY;
        }

        if (isExistingSameTeam(piece, otherPiece)) {
            return MoveResult.EXISTING_SAME_TEAM;
        }

        value.put(to, piece);
        value.remove(from);

        if (otherPiece.isPieceType(PieceType.KING)) {
            return MoveResult.KILL_KING;
        }

        return MoveResult.KILL_ENEMY;
    }

    private boolean isExistingSameTeam(AbstractPiece piece, AbstractPiece otherPiece) {
        return !Objects.isNull(otherPiece) && otherPiece.isSameTeam(piece);
    }
}

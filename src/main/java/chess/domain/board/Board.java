package chess.domain.board;

import chess.domain.exceptions.InvalidMoveException;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceKind;
import chess.domain.piece.strategy.MoveDirection;

import java.util.EnumMap;
import java.util.Map;

public class Board {

    private static final String STAY_ERROR_MESSAGE = "지금 위치와 이동하려는 위치가 같습니다.";
    private static final Piece VOID_PIECE = new Piece(PieceKind.VOID, PieceColor.VOID);

    private final Map<Position, Piece> board;
    private PieceColor deadKingColor;

    public Board(Map<Position, Piece> existedBoard) {
        this.board = existedBoard;
        this.deadKingColor = PieceColor.VOID;
    }

    public void move(Position source, Position target, PieceColor turnColor) {
        checkMoving(source, target);
        checkNoJumpPiece(source, target);

        Piece originalPiece = pieceAtPosition(source);
        originalPiece.movable(source, target);
        checkTurn(originalPiece, turnColor);

        checkPawnCase(source, target, originalPiece);
        checkIsNotSameTeam(source, target);

        judgeKingsState(target);
        movePiece(source, target, originalPiece);
    }

    private void movePiece(Position source, Position target, Piece originalPiece) {
        putPieceAtPosition(target, originalPiece);
        putPieceAtPosition(source, VOID_PIECE);
    }

    private void checkPawnCase(Position source, Position target, Piece originalPiece) {
        if (originalPiece.isPawn()) {
            checkDirection(source, target);
            checkMoveType(source, target);
        }
    }

    private void checkNoJumpPiece(Position source, Position target) {
        if (source.isLineMove(target) || source.isDiagonalMove(target)) {
            checkClearPath(source, target);
        }
    }

    private void checkMoving(Position source, Position target) {
        if (source.computeHorizontalDistance(target) == 0 && source.computeVerticalDistance(target) == 0) {
            throw new InvalidMoveException(STAY_ERROR_MESSAGE);
        }
    }

    private void checkIsNotSameTeam(Position source, Position target) {
        Piece sourcePiece = pieceAtPosition(source);
        Piece targetPiece = pieceAtPosition(target);

        if (sourcePiece.isSameColor(targetPiece)) {
            throw new InvalidMoveException(Piece.SAME_TEAM_MESSAGE);
        }
    }

    private void checkClearPath(Position source, Position target) {
        MoveDirection moveDirection = MoveDirection.getDirection(source, target);
        int xVector = moveDirection.getXVector();
        int yVector = moveDirection.getYVector();

        Position pathPosition = new Position(source);
        pathPosition.moveUnit(xVector, yVector);

        while (!pathPosition.equals(target)) {
            checkIfClear(pathPosition);
            pathPosition.moveUnit(xVector, yVector);
        }
    }

    private void checkIfClear(Position pathPosition) {
        if (!pieceAtPosition(pathPosition).equals(VOID_PIECE)) {
            throw new InvalidMoveException(Piece.UNABLE_CROSS_MESSAGE);
        }
    }

    private void checkDirection(Position source, Position target) {
        Piece piece = pieceAtPosition(source);
        if (!piece.isMovingForward(source, target)) {
            throw new InvalidMoveException(Piece.UNABLE_MOVE_TYPE_MESSAGE);
        }
    }

    private void checkMoveType(Position source, Position target) {
        if (isVoid(target) && source.isDiagonalMove(target)) {
            throw new InvalidMoveException(Piece.UNABLE_MOVE_TYPE_MESSAGE);
        }

        if (!isVoid(target) && source.isLineMove(target)) {
            throw new InvalidMoveException(Piece.UNABLE_MOVE_TYPE_MESSAGE);
        }
    }

    private boolean isVoid(Position target) {
        Piece targetPiece = pieceAtPosition(target);
        return targetPiece.equals(VOID_PIECE);
    }

    private void judgeKingsState(Position target) {
        if (pieceAtPosition(target).isKing()) {
            deadKingColor = pieceAtPosition(target).color();
        }
    }

    public boolean kingIsDead() {
        return deadKingColor != PieceColor.VOID;
    }

    public void checkTurn(Piece piece, PieceColor turnColor) {
        if (!piece.isSameColor(turnColor)) {
            throw new RuntimeException("해당 턴이 아닙니다.");
        }
    }

    public double computePoint(PieceColor pieceColor) {
        double pointSum = 0.0;
        for (XPosition xPosition : XPosition.values()) {
            Map<PieceKind, Integer> existingPiece = countPieceKindAtColumn(pieceColor, xPosition);
            pointSum += columnPoint(existingPiece);
        }
        return pointSum;
    }

    private Map<PieceKind, Integer> countPieceKindAtColumn(PieceColor pieceColor,
                                             XPosition xPosition) {
        Map<PieceKind, Integer> existingPiece = new EnumMap<>(PieceKind.class);
        for (YPosition yPosition : YPosition.values()) {
            Position currentPosition = Position.of(xPosition, yPosition);
            Piece piece = pieceAtPosition(currentPosition);
            checkSameColor(pieceColor, existingPiece, piece);
        }

        return existingPiece;
    }

    private void checkSameColor(PieceColor pieceColor, Map<PieceKind, Integer> existingPiece, Piece piece) {
        if (piece.isSameColor(pieceColor)) {
            existingPiece.put(piece.kind(), existingPiece.getOrDefault(piece.kind(), 0) + 1);
        }
    }

    private double columnPoint(Map<PieceKind, Integer> column) {
        double points = 0.0;
        for (Map.Entry<PieceKind, Integer> entry : column.entrySet()) {
            points += eachPiecePoint(entry);
        }
        return points;
    }

    private double eachPiecePoint(Map.Entry<PieceKind, Integer> entry) {
        double point = entry.getValue() * entry.getKey().point();
        if (entry.getKey() == PieceKind.PAWN && entry.getValue() > 1) {
            point /= 2;
        }
        return point;
    }

    public Piece pieceAtPosition(Position position) {
        return board.get(position);
    }

    public void putPieceAtPosition(Position position, Piece piece) {
        board.put(position, piece);
    }

    public PieceColor winnerColor() {
        return this.deadKingColor.oppositeColor();
    }
}

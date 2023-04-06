package domain;

import domain.piece.Empty;
import domain.piece.Piece;
import domain.piece.pawn.OnceMovedBlackPawn;
import domain.piece.pawn.OnceMovedWhitePawn;
import domain.point.File;
import domain.point.Point;
import domain.point.Rank;
import util.BoardInitializer;
import util.ExceptionMessages;
import util.MovablePointFinder;
import exception.CheckMateException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Board {
    private final Map<Point, Piece> pieceStatus;

    public Board() {
        this.pieceStatus = BoardInitializer.initializeBoard();
    }

    private Board(Map<Point, Piece> pieceStatus) {
        this.pieceStatus = pieceStatus;
    }

    public void reset() {
        pieceStatus.clear();
        pieceStatus.putAll(BoardInitializer.initializeBoard());
    }

    public List<List<Piece>> findCurrentStatus() {
        List<List<Piece>> status = new ArrayList<>();
        for (Rank rank : Rank.values()) {
            List<Piece> statusPerFile = new ArrayList<>();
            for (File file : File.values()) {
                statusPerFile.add(pieceStatus.get(new Point(file, rank)));
            }
            status.add(statusPerFile);
        }
        return status;
    }

    public void move(Point startingPoint, Point destinationPoint, Turn turn) {
        Piece piece = pieceStatus.get(startingPoint);
        validateFromPoint(piece, turn);

        List<Point> movablePoints = MovablePointFinder.addPoints(startingPoint, destinationPoint, pieceStatus);
        validateToPoint(destinationPoint, movablePoints);

        if (piece.isWhitePawn() || piece.isBlackPawn()) {
            movePawnOfFirstStep(startingPoint, destinationPoint, piece, turn);
            return;
        }

        move(startingPoint, destinationPoint, piece, turn);
    }

    private static void validateFromPoint(Piece piece, Turn turn) {
        if (piece.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.TARGET_PIECE_NOT_FOUND);
        }
        if (!turn.isTurnOf(piece)) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_GAME_TURN);
        }
    }

    private static void validateToPoint(Point destinationPoint, List<Point> movablePoints) {
        if (!movablePoints.contains(destinationPoint)) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_DESTINATION);
        }
    }

    private void move(Point startingPoint, Point destinationPoint, Piece piece, Turn turn) {
        Piece previousPiece = pieceStatus.get(destinationPoint);
        pieceStatus.put(startingPoint, new Empty());
        pieceStatus.put(destinationPoint, piece);
        if (previousPiece.isKing()) {
            throw new CheckMateException(turn);
        }
    }

    private void movePawnOfFirstStep(Point startingPoint, Point destinationPoint, Piece piece, Turn turn) {
        if (piece.isBlackPawn()) {
            move(startingPoint, destinationPoint, new OnceMovedBlackPawn(), turn);
        }
        if (piece.isWhitePawn()) {
            move(startingPoint, destinationPoint, new OnceMovedWhitePawn(), turn);
        }
    }
}

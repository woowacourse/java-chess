package domain;

import domain.exception.InvalidDestinationPointException;
import domain.exception.TargetPieceNotFoundException;
import domain.piece.*;
import domain.piece.pawn.OnceMovedBlackPawn;
import domain.piece.pawn.OneMovedWhitePawn;
import domain.point.Point;
import domain.util.BoardInitializer;
import domain.util.MovablePointFinder;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final List<List<Piece>> pieceStatus;

    public Board(List<List<Piece>> pieceStatus) {
        this.pieceStatus = pieceStatus;
    }

    public Board() {
        this.pieceStatus = BoardInitializer.initializeBoard();
    }

    public void reset() {
        pieceStatus.clear();
        pieceStatus.addAll(BoardInitializer.initializeBoard());
    }

    public List<List<Piece>> findCurrentStatus() {
        return new ArrayList<>(pieceStatus);
    }

    public void move(String from, String to) {
        Point fromPoint = Point.fromSymbol(from);
        Point toPoint = Point.fromSymbol(to);

        move(fromPoint, toPoint);
    }

    private void move(Point fromPoint, Point toPoint) {
        Piece piece = findPieceByPoint(fromPoint);
        validateFromPoint(piece);

        List<Point> movablePoints = MovablePointFinder.addPoints(piece, fromPoint, toPoint, pieceStatus);
        validateToPoint(toPoint, movablePoints);

        if (piece.isWhitePawn() || piece.isBlackPawn()) {
            movePawnOfFirstStep(fromPoint, toPoint, piece);
            return;
        }

        move(fromPoint, toPoint, piece);
    }

    private static void validateFromPoint(Piece piece) {
        if (piece.isEmpty()) {
            throw new TargetPieceNotFoundException();
        }
    }

    private static void validateToPoint(Point toPoint, List<Point> movablePoints) {
        if (!movablePoints.contains(toPoint)) {
            throw new InvalidDestinationPointException();
        }
    }

    private void move(Point fromPoint, Point toPoint, Piece piece) {
        pieceStatus.get(fromPoint.findIndexFromBottom())
                .set(fromPoint.findIndexFromLeft(), new Empty());
        pieceStatus.get(toPoint.findIndexFromBottom())
                .set(toPoint.findIndexFromLeft(), piece);
    }

    private void movePawnOfFirstStep(Point fromPoint, Point toPoint, Piece piece) {
        if (piece.isBlackPawn()) {
            move(fromPoint, toPoint, new OnceMovedBlackPawn());
        }
        if (piece.isWhitePawn()) {
            move(fromPoint, toPoint, new OneMovedWhitePawn());
        }
    }

    private Piece findPieceByPoint(Point fromPoint) {
        return pieceStatus.get(fromPoint.findIndexFromBottom())
                .get(fromPoint.findIndexFromLeft());
    }
}

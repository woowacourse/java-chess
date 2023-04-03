package domain;

import domain.piece.Empty;
import domain.piece.Piece;
import domain.piece.pawn.OnceMovedBlackPawn;
import domain.piece.pawn.OneMovedWhitePawn;
import domain.point.File;
import domain.point.Point;
import domain.point.Rank;
import domain.util.BoardInitializer;
import domain.util.ExceptionMessages;
import domain.util.MovablePointFinder;

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

    public void move(String from, String to) {
        Point fromPoint = Point.fromSymbol(from);
        Point toPoint = Point.fromSymbol(to);

        move(fromPoint, toPoint);
    }

    private void move(Point fromPoint, Point toPoint) {
        Piece piece = pieceStatus.get(fromPoint);
        validateFromPoint(piece);

        List<Point> movablePoints = MovablePointFinder.addPoints(fromPoint, toPoint, pieceStatus);
        validateToPoint(toPoint, movablePoints);

        if (piece.isWhitePawn() || piece.isBlackPawn()) {
            movePawnOfFirstStep(fromPoint, toPoint, piece);
            return;
        }

        move(fromPoint, toPoint, piece);
    }

    private static void validateFromPoint(Piece piece) {
        if (piece.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.TARGET_PIECE_NOT_FOUND);
        }
    }

    private static void validateToPoint(Point toPoint, List<Point> movablePoints) {
        if (!movablePoints.contains(toPoint)) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_DESTINATION);
        }
    }

    private void move(Point fromPoint, Point toPoint, Piece piece) {
        pieceStatus.put(fromPoint, new Empty());
        pieceStatus.put(toPoint, piece);
    }

    private void movePawnOfFirstStep(Point fromPoint, Point toPoint, Piece piece) {
        if (piece.isBlackPawn()) {
            move(fromPoint, toPoint, new OnceMovedBlackPawn());
        }
        if (piece.isWhitePawn()) {
            move(fromPoint, toPoint, new OneMovedWhitePawn());
        }
    }
}

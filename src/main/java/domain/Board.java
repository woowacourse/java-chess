package domain;

import dao.Movement;
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
        findCurrentRanks(status);
        return status;
    }

    private void findCurrentRanks(List<List<Piece>> status) {
        for (Rank rank : Rank.values()) {
            List<Piece> files = new ArrayList<>();
            findCurrentFiles(rank, files);
            status.add(files);
        }
    }

    private void findCurrentFiles(Rank rank, List<Piece> files) {
        for (File file : File.values()) {
            files.add(pieceStatus.get(new Point(file, rank)));
        }
    }

    public void move(Movement movement, Turn turn) {
        Piece piece = pieceStatus.get(movement.getStartingPoint());
        validateFromPoint(piece, turn);

        List<Point> movablePoints = MovablePointFinder.addPoints(movement, pieceStatus);
        validateDestinationPoint(movement.getDestinationPoint(), movablePoints);

        if (piece.isWhitePawn() || piece.isBlackPawn()) {
            movePawnOfFirstStep(movement, piece, turn);
            return;
        }

        move(movement, piece, turn);
    }

    private static void validateFromPoint(Piece piece, Turn turn) {
        if (piece.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.TARGET_PIECE_NOT_FOUND);
        }
        if (!turn.isTurnOf(piece)) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_GAME_TURN);
        }
    }

    private static void validateDestinationPoint(Point point, List<Point> movablePoints) {
        if (!movablePoints.contains(point)) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_DESTINATION);
        }
    }

    private void move(Movement movement, Piece piece, Turn turn) {
        Piece previousPiece = pieceStatus.get(movement.getDestinationPoint());
        pieceStatus.put(movement.getStartingPoint(), new Empty());
        pieceStatus.put(movement.getDestinationPoint(), piece);
        if (previousPiece.isKing()) {
            throw new CheckMateException(turn);
        }
    }

    private void movePawnOfFirstStep(Movement movement, Piece piece, Turn turn) {
        if (piece.isBlackPawn()) {
            move(movement, new OnceMovedBlackPawn(), turn);
        }
        if (piece.isWhitePawn()) {
            move(movement, new OnceMovedWhitePawn(), turn);
        }
    }
}

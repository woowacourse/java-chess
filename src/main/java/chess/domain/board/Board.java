package chess.domain.board;


import chess.domain.piece.Piece;
import chess.domain.piece.Vector;
import chess.dto.BoardDto;

import java.util.*;

public class Board {

    private static final double HALF_PAWN_SCORE = 0.5;
    private static final int ONE_MOVE_COUNT = 1;

    private final Map<Point, Square> squares = new HashMap<>();
    private boolean aliveKing;

    public Board() {
        initializeBoard();
        aliveKing = true;
    }

    private void initializeBoard() {
        Point.getAllPoints()
                .forEach(point -> squares.put(point, Square.of(Piece.EMPTY, Team.NONE)));
    }

    public void putSymmetrically(Piece piece, Point point) {
        squares.put(point, Square.of(piece, Team.WHITE));
        squares.put(point.yAxisOpposite(), Square.of(piece, Team.BLACK));
    }

    public boolean canMove(Point source, Point destination) {
        Square sourceSquare = squares.get(source);
        Square destinationSquare = squares.get(destination);
        Vector vector = sourceSquare.findMovableVector(source, destination);

        if (sourceSquare.isPawn()) {
            return canMovePawn(source, destination);
        }
        return isValidSourceAndDestination(sourceSquare, destinationSquare)
                && isValidPath(source, destination, vector);
    }

    private boolean isValidSourceAndDestination(Square sourceSquare, Square destinationSquare) {
        return sourceSquare.isNotEmpty() && isNotSameTeam(sourceSquare,
                destinationSquare);
    }

    private boolean canMovePawn(Point source, Point destination) {
        return canMovePawnToDiagonalDirection(source, destination)
                || canMovePawnToStraight(source, destination);
    }

    private boolean canMovePawnToDiagonalDirection(Point source, Point destination) {
        Square sourceSquare = squares.get(source);
        Square destinationSquare = squares.get(destination);
        Vector vector = sourceSquare.findMovableVector(source, destination);

        return canWhitePawnKillEnemy(sourceSquare, destinationSquare, vector)
                || canBlackPawnKillEnemy(sourceSquare, destinationSquare, vector);
    }

    private boolean canMovePawnToStraight(Point source, Point destination) {
        Vector vector = squares.get(source).findMovableVector(source, destination);

        return canMoveWhitePawnToStraight(source, destination, vector)
                || canMoveBlackPawnToStraight(source, destination, vector);
    }

    private boolean canMoveWhitePawnToStraight(Point source, Point destination, Vector vector) {
        Square sourceSquare = squares.get(source);
        Square destinationSquare = squares.get(destination);

        return sourceSquare.isTeam(Team.WHITE)
                && vector.isWhitePawnsStraight()
                && isValidPath(source, destination, vector)
                && destinationSquare.isEmpty();
    }

    private boolean isValidPath(Point source, Point destination, Vector vector) {
        Square sourceSquare = squares.get(source);
        int moveCount = ONE_MOVE_COUNT;
        boolean unblocked = true;

        for (Point now = source.move(vector); isGoing(destination, now) && unblocked;
             now = now.move(vector)) {
            unblocked = underMoveLength(sourceSquare, moveCount) && squares.get(now).isEmpty();
            moveCount++;
        }
        return unblocked;
    }

    private boolean canMoveBlackPawnToStraight(Point source, Point destination, Vector vector) {
        Square sourceSquare = squares.get(source);
        Square destinationSquare = squares.get(destination);

        return sourceSquare.isTeam(Team.BLACK)
                && vector.isBlackPawnsStraight()
                && isValidPath(source, destination, vector)
                && destinationSquare.isEmpty();
    }

    private boolean canWhitePawnKillEnemy(Square sourceSquare, Square destinationSquare, Vector vector) {
        return sourceSquare.isTeam(Team.WHITE)
                && destinationSquare.isTeam(Team.BLACK)
                && vector.isWhiteDiagonalVector();
    }

    private boolean canBlackPawnKillEnemy(Square sourceSquare, Square destinationSquare, Vector vector) {
        return sourceSquare.isTeam(Team.BLACK)
                && destinationSquare.isTeam(Team.WHITE)
                && vector.isBlackDiagonalVector();
    }

    private boolean isNotSameTeam(Square sourceSquare, Square destinationSquare) {
        return destinationSquare.isNotSameTeam(sourceSquare);
    }

    private boolean isGoing(Point destination, Point now) {
        return !now.equals(destination);
    }

    private boolean underMoveLength(Square sourceSquare, int moveCount) {
        return moveCount <= sourceSquare.getMoveLength();
    }

    public void move(Point source, Point destination) {
        if (squares.get(destination).isKing()) {
            aliveKing = !aliveKing;
        }
        squares.put(destination, squares.get(source));
        squares.put(source, Square.of(Piece.EMPTY, Team.NONE));
    }

    public boolean isTeam(Point source, Team currentTeam) {
        Square square = squares.get(source);
        return square.isTeam(currentTeam);
    }

    public boolean isContinued() {
        return aliveKing;
    }

    public double score(Team team) {
        return sumScore(team) - (HALF_PAWN_SCORE * pawnCountInSameColumn(team));
    }

    private long pawnCountInSameColumn(Team team) {
        return Arrays.stream(Column.values())
                .mapToLong(column -> pawnCountInColumn(team, column))
                .filter(count -> count >= 2)
                .sum();
    }

    private long pawnCountInColumn(Team team, Column column) {
        return squares.keySet().stream()
                .filter(point -> point.isColumn(column))
                .map(squares::get)
                .filter(square -> square.isTeam(team) && square.isPawn())
                .count();
    }

    private double sumScore(Team team) {
        return squares.values().stream()
                .filter(square -> square.isTeam(team))
                .mapToDouble(Square::score)
                .sum();
    }

    public Square getSquareState(Point point) {
        return squares.get(point);
    }

    public BoardDto boardDto() {
        List<List<String>> board = new ArrayList<>();
        for (Row row : Row.reverseRows()) {
            board.add(rowLine(row));
        }

        return new BoardDto(board);
    }

    private List<String> rowLine(Row row) {
        List<String> rowLine = new ArrayList<>();
        for (Column column : Column.columns()) {
            Square square = squares.get(Point.of(column.xCoordinate() + row.yCoordinate()));
            rowLine.add(square.pieceName());
        }
        return rowLine;
    }

    public boolean isEmpty(Point source) {
        return squares.get(source).isEmpty();
    }
}

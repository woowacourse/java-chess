package chess.domain.board;


import chess.domain.piece.Piece;
import chess.domain.piece.Vector;
import chess.dto.BoardDto;

import java.util.*;

public class Board {

    private static final int ONE_MOVE_COUNT = 1;
    public static final int RUNNING_NUMBER = 2;

    private final Map<Point, Square> squares = new HashMap<>();

    public Board() {
        initializeBoard();
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

    public void move(Point source, Point destination, Team team) {
        validatePoint(source, destination);
        validateTurn(source, team);
        squares.put(destination, squares.get(source));
        squares.put(source, Square.of(Piece.EMPTY, Team.NONE));
    }

    private void validatePoint(Point source, Point destination) {
        if (isEmpty(source)) {
            throw new IllegalArgumentException("움직일 수 있는 기물이 존재하지 않습니다.");
        }
        if (!canMove(source, destination)) {
            throw new IllegalArgumentException("해당 위치로는 움직일 수 없습니다.");
        }
    }

    private void validateTurn(Point source, Team currentTeam) {
        if (!isTeam(source, currentTeam)) {
            throw new IllegalArgumentException("현재 플레이어의 기물이 아닙니다.");
        }
    }

    public boolean isTeam(Point source, Team currentTeam) {
        Square square = squares.get(source);
        return square.isTeam(currentTeam);
    }

    public boolean isRunning() {
        long kingCount = squares.values().stream()
                .filter(Square::isKing)
                .count();
        return kingCount == RUNNING_NUMBER;
    }

    public double score(Team team) {
        return new BoardScore(squares).score(team);
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

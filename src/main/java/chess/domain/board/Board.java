package chess.domain.board;


import chess.domain.piece.Piece;
import chess.dto.BoardDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {

    public static final int RUNNING_NUMBER = 2;

    private final Map<Point, Square> squares = new HashMap<>();
    private final PieceMovement pieceMovement;

    public Board() {
        initializeBoard();
        this.pieceMovement = new PieceMovement(squares);
    }

    private void initializeBoard() {
        Point.getAllPoints()
                .forEach(point -> squares.put(point, Square.of(Piece.EMPTY, Team.NONE)));
    }

    public void putSymmetrically(Piece piece, Point point) {
        squares.put(point, Square.of(piece, Team.WHITE));
        squares.put(point.yAxisOpposite(), Square.of(piece, Team.BLACK));
    }

    public void move(Point source, Point destination, Team team) {
        pieceMovement.validateMovement(source, destination, team);

        squares.put(destination, squares.get(source));
        squares.put(source, Square.of(Piece.EMPTY, Team.NONE));
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
}

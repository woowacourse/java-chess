package domain.chessboard;

import domain.coordinate.Coordinate;
import domain.coordinate.Position;
import domain.direction.Direction;
import domain.piece.Blank;
import domain.piece.Color;
import domain.piece.base.ChessPiece;
import java.util.List;

public class ChessBoard {

    private final List<Row> board;

    public ChessBoard() {
        this.board = ChessBoardInitializer.createInitialBoard();
    }

    public void movePiece(Coordinate start, Coordinate destination, Color currentTurnColor) {
        ChessPiece movingPiece = getMovingPiece(start, currentTurnColor);
        validateDestination(destination, currentTurnColor);
        validatePath(movingPiece, start, destination);
        move(start, destination);
    }

    private ChessPiece getMovingPiece(Coordinate start, Color currentTurnColor) {
        ChessPiece piece = getPiece(start);

        if (!piece.hasSameColor(currentTurnColor)) {
            throw new IllegalArgumentException("색상이 다른 말을 움직일 수 없습니다.");
        }
        return piece;
    }

    private void validateDestination(Coordinate destination, Color currentTurnColor) {
        if (isEmptyCoordinate(destination)) {
            return;
        }
        if (getPiece(destination).hasSameColor(currentTurnColor)) {
            throw new IllegalArgumentException("같은 색의 말이 있는 곳으로는 이동할 수 없습니다.");
        }
    }

    private void validatePath(ChessPiece piece, Coordinate start, Coordinate destination) {
        Direction direction = piece.getDirection(start, destination);
        Coordinate current = start.moveOneStep(direction);

        while (!current.equals(destination)) {
            validatePieceExist(current);
            current = current.moveOneStep(direction);
        }
    }

    private void validatePieceExist(Coordinate current) {
        if (!isEmptyCoordinate(current)) {
            throw new IllegalArgumentException("이동 경로에 말이 존재합니다.");
        }
    }

    private boolean isEmptyCoordinate(Coordinate coordinate) {
        return getPiece(coordinate) instanceof Blank;
    }

    private void move(Coordinate start, Coordinate destination) {
        ChessPiece movingPiece = getPiece(start);
        replacePiece(start, Blank.getInstance());
        replacePiece(destination, movingPiece);
    }

    private void replacePiece(Coordinate coordinate, ChessPiece chessPiece) {
        Row row = getRow(coordinate.getRow());
        row.replace(coordinate.getColumn(), chessPiece);
    }

    private ChessPiece getPiece(Coordinate coordinate) {
        Row row = getRow(coordinate.getRow());

        return row.getPiece(coordinate.getColumn());
    }

    private Row getRow(Position row) {
        return board.get(row.getValue());
    }

    public List<Row> getBoard() {
        return List.copyOf(board);
    }
}

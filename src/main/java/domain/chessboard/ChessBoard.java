package domain.chessboard;

import domain.coordinate.Coordinate;
import domain.direction.Direction;
import domain.piece.Color;
import domain.piece.base.ChessPiece;
import java.util.Collections;
import java.util.Map;

public class ChessBoard {

    private final Map<Coordinate, ChessPiece> board;

    private Color currentTurn = Color.WHITE;

    public ChessBoard() {
        this.board = ChessBoardInitializer.createInitialBoard();
    }

    public void playTurn(Coordinate start, Coordinate destination) {
        ChessPiece piece = getPiece(start);
        validateCanMove(piece, start, destination);
        movePiece(piece, start, destination);
        changeTurn();
    }

    private void validateCanMove(ChessPiece piece, Coordinate start, Coordinate destination) {
        validateDestination(destination);
        validatePath(piece, start, destination);
    }

    private void validateDestination(Coordinate destination) {
        ChessPiece destinationPiece = board.get(destination);

        if (destinationPiece == null) {
            return;
        }
        if (destinationPiece.hasSameColor(currentTurn)) {
            throw new IllegalArgumentException("같은 색의 말이 있는 곳으로는 이동할 수 없습니다.");
        }
    }

    private void validatePath(ChessPiece piece, Coordinate start, Coordinate destination) {
        Direction direction = piece.getDirection(start, destination);
        Coordinate current = start.moveOneStep(direction);

        while(!current.equals(destination)) {
            validatePieceExist(current);
            current = current.moveOneStep(direction);
        }
    }

    private void validatePieceExist(Coordinate current) {
        if (board.get(current) != null) {
            throw new IllegalArgumentException("이동 경로에 말이 존재합니다.");
        }
    }

    private void movePiece(ChessPiece piece, Coordinate start, Coordinate destination) {
        board.remove(start);
        board.put(destination, piece);
    }

    private void changeTurn() {
        if (currentTurn == Color.WHITE) {
            currentTurn = Color.BLACK;
            return;
        }
        currentTurn = Color.WHITE;
    }

    private ChessPiece getPiece(Coordinate coordinate) {
        ChessPiece piece = board.get(coordinate);
        if (piece.hasSameColor(currentTurn)) {
            return piece;
        }
        throw new IllegalArgumentException("상대의 말을 이동할 수 없습니다.");
    }

    public Map<Coordinate, ChessPiece> getBoard() {
        return Collections.unmodifiableMap(board);
    }
}

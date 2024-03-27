package domain.chessboard;

import domain.coordinate.Coordinate;
import domain.direction.Direction;
import domain.piece.Blank;
import domain.piece.Color;
import domain.piece.Pawn;
import domain.piece.base.ChessPiece;
import java.util.Collections;
import java.util.Map;

public class ChessBoard {

    private final Map<Coordinate, ChessPiece> board;

    private Color currentTurn = Color.WHITE;

    public ChessBoard() {
        board = ChessBoardInitializer.createInitialBoard();
    }

    public void playTurn(Coordinate start, Coordinate destination) {
        ChessPiece piece = findPiece(start);
        movePiece(start, destination, piece);

        currentTurn = changeTurn();
    }

    private void movePiece(Coordinate start, Coordinate destination, ChessPiece piece) {
        validateBeforePlay(start, destination, piece);

        board.replace(start, new Blank());
        board.replace(destination, piece);
    }

    private Color changeTurn() {
        if (currentTurn == Color.BLACK) {
            return Color.WHITE;
        }
        return Color.BLACK;
    }

    private void validateBeforePlay(Coordinate start, Coordinate destination, ChessPiece piece) {
        validateDestination(destination, currentTurn);
        validateTurn(piece, currentTurn);
        checkPath(start, destination, piece);
    }

    private void validateDestination(Coordinate destination, Color currentTurn) {
        ChessPiece chessPiece = findPiece(destination);

        if (chessPiece.getClass() == Blank.class) {
            return;
        }
        if (!chessPiece.isOpponentColor(currentTurn)) {
            throw new IllegalArgumentException("같은 색의 말은 공격할 수 없습니다.");
        }
    }

    private void validateTurn(ChessPiece piece, Color currentTurn) {
        if (piece.isOpponentColor(currentTurn)) {
            throw new IllegalArgumentException("상대의 말을 움직일 수 없습니다.");
        }
    }

    private void checkPath(Coordinate start, Coordinate destination, ChessPiece piece) {
        boolean isAttack = isAttack(currentTurn, destination);
        Direction direction = piece.getDirection(start, destination, isAttack);
        validateNoPieceOnPath(start, destination, direction);

        if (!isAttack && piece instanceof Pawn) {
            validateCanMoveTwoDistance(start, (Pawn) piece);
        }
    }

    private void validateCanMoveTwoDistance(Coordinate coordinate, Pawn pawn) {
        if (!pawn.isFirstPosition(coordinate)) {
            throw new IllegalArgumentException("폰은 처음에만 2칸을 이동할 수 있습니다.");
        }
    }

    private void validateNoPieceOnPath(Coordinate coordinate, Coordinate destination, Direction direction) {
        while (!coordinate.equals(destination)) {
            coordinate = coordinate.next(direction);
            hasPieceOnPath(coordinate, destination);
        }
    }

    private void hasPieceOnPath(Coordinate coordinate, Coordinate destination) {
        if (coordinate.equals(destination)) {
            return;
        }

        if (!findPiece(coordinate).getClass().equals(Blank.class)) {
            throw new IllegalArgumentException("이동 경로에 말이 존재합니다.");
        }
    }

    private ChessPiece findPiece(Coordinate coordinate) {
        return board.get(coordinate);
    }

    private boolean isAttack(Color current, Coordinate destination) {
        ChessPiece destinationPiece = board.get(destination);

        return destinationPiece.getClass() != Blank.class && destinationPiece.isOpponentColor(current);
    }

    public Map<Coordinate, ChessPiece> getBoard() {
        return Collections.unmodifiableMap(board);
    }
}

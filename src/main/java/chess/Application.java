package chess;

import chess.piece.BlankPiece;
import chess.piece.Piece;

import java.util.Map;

public class Application {

    private static final Console console = new Console();
    private static final Board board = new Board();
    private static Color currentTurn = Color.BLACK;

    public static void main(String[] args) {
        board.setup();

        while (board.containsKing()) {
            commenceRound();
        }
    }

    public static void commenceRound() {
        Map<Position, Piece> pieces = board.getPieces();

        console.printPieces(pieces);
        console.printSide(currentTurn);

        String positions = console.readPositions();
        String startInput = positions.split(",")[0];
        String endInput = positions.split(",")[1];

        Position start = new Position(Column.getColumn(startInput.split("")[0]), Row.getRow(startInput.split("")[1]));
        Position end = new Position(Column.getColumn(endInput.split("")[0]), Row.getRow(endInput.split("")[1]));

        if (!pieces.containsKey(start)) throw new IllegalArgumentException();

        Piece startPiece = pieces.get(start);

        validateTurn(startPiece);

        Piece endPiece = pieces.getOrDefault(end, new BlankPiece(Color.EMPTY));

        if (!startPiece.canMove(start, end, endPiece, pieces)) throw new IllegalArgumentException();

        pieces.remove(start);
        pieces.put(end, startPiece);

        currentTurn = currentTurn.opposite();
    }

    private static void validateTurn(Piece piece) {
        if (currentTurn.isBlack() && piece.isBlack()) {
            return;
        }
        if (currentTurn.isWhite() && piece.isWhite()) {
            return;
        }
        throw new IllegalArgumentException();
    }
}

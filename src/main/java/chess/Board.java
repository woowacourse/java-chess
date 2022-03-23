package chess;

import static chess.PieceColor.*;
import static chess.PieceType.*;
import static chess.Rank.*;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> values;
    private static final List<PieceType> BACK_LINE_PIECES = List.of(ROOK, KNIGHT, BISHOP, QUEEN, KING, BISHOP, KNIGHT, ROOK);

    public Board() {
        this.values = initBoard();
    }

    private Map<Position, Piece> initBoard() {
        Map<Position, Piece> result = new HashMap<>();

        putBlackPieces(result);
        putWhitePieces(result);

        return result;
    }

    private void putWhitePieces(Map<Position, Piece> result) {
        putPawns(result, WHITE, SEVEN);
        putRemainPiecesExceptPawn(result, WHITE, EIGHT);

    }

    private void putBlackPieces(Map<Position, Piece> result) {
        putPawns(result, BLACK, TWO);
        putRemainPiecesExceptPawn(result, BLACK, ONE);
    }

    private void putRemainPiecesExceptPawn(Map<Position, Piece> result, PieceColor color, Rank rank) {

        ListIterator<PieceType> pieceTypeListIterator = BACK_LINE_PIECES.listIterator();

        for (File file : File.values()) {
            result.put(new Position(rank, file), new Piece(pieceTypeListIterator.next(), color));
        }
    }

    private void putPawns(Map<Position, Piece> result, PieceColor color, Rank rank) {
        for (File file : File.values()) {
            result.put(new Position(rank, file), new Piece(PAWN, color));
        }
    }

    public Map<Position, Piece> getValues() {
        return values;
    }
}

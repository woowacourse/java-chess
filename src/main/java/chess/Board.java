package chess;

import static chess.PieceColor.BLACK;
import static chess.PieceColor.WHITE;
import static chess.PieceType.BISHOP;
import static chess.PieceType.KING;
import static chess.PieceType.KNIGHT;
import static chess.PieceType.PAWN;
import static chess.PieceType.QUEEN;
import static chess.PieceType.ROOK;
import static chess.Rank.EIGHT;
import static chess.Rank.ONE;
import static chess.Rank.SEVEN;
import static chess.Rank.TWO;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> values;
    private static final List<PieceType> BACK_LINE_PIECES = List.of(ROOK, KNIGHT, BISHOP, QUEEN, KING, BISHOP, KNIGHT,
            ROOK);

    public Board() {
        this.values = initBoard();
    }

    private Map<Position, Piece> initBoard() {
        Map<Position, Piece> result = new HashMap<>();

        putAllEmptyPieces(result);
        putBlackPieces(result);
        putWhitePieces(result);

        return result;
    }

    private void putAllEmptyPieces(Map<Position, Piece> result) {
        for (Rank rank : Rank.values()) {
            for (File file : File.values()) {
                result.put(new Position(rank, file), new Piece(PieceType.EMPTY, PieceColor.EMPTY));
            }
        }
    }

    private void putWhitePieces(Map<Position, Piece> result) {
        putPawns(result, WHITE, TWO);
        putRemainPiecesExceptPawn(result, WHITE, ONE);

    }

    private void putBlackPieces(Map<Position, Piece> result) {
        putPawns(result, BLACK, SEVEN);
        putRemainPiecesExceptPawn(result, BLACK, EIGHT);
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

    public void move(Position source, Position target) {
        validateSourceNotEmpty(source);
        changePieces(source, target);
    }

    private void changePieces(Position source, Position target) {
        values.put(target, values.get(source));
        values.put(source, new Piece(PieceType.EMPTY, PieceColor.EMPTY));
    }

    private void validateSourceNotEmpty(Position source) {
        if (values.get(source).equals(new Piece(PieceType.EMPTY, PieceColor.EMPTY))) {
            throw new IllegalArgumentException("[ERROR] 출발 위치에는 말이 있어야 합니다.");
        }
    }

    public Map<Position, Piece> getValues() {
        return values;
    }
}

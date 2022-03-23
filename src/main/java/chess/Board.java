package chess;

import static chess.PieceColor.*;
import static chess.Rank.*;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.function.Function;

import chess.piece.Bishop;
import chess.piece.EmptyPiece;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;

public class Board {

    static final String SOURCE_POSITION_SHOULD_HAVE_PIECE_MESSAGE = "[ERROR] 출발 위치에는 말이 있어야 합니다.";
    private final Map<Position, Piece> values;

    private static final Function<PieceColor, List<Piece>> createPieceFunction = (PieceColor pieceColor) -> List.of(
        new Rook(pieceColor), new Knight(pieceColor), new Bishop(pieceColor),
        new Queen(pieceColor), new King(pieceColor), new Bishop(pieceColor),
        new Knight(pieceColor), new Rook(pieceColor));

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
        for (Rank rank : Rank.reverseValues()) {
            for (File file : File.values()) {
                result.put(new Position(rank, file), new EmptyPiece(PieceColor.EMPTY));
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
        ListIterator<Piece> piecesIterator = createPieceFunction.apply(color).listIterator();

        for (File file : File.values()) {
            result.put(new Position(rank, file), piecesIterator.next());
        }
    }

    private void putPawns(Map<Position, Piece> result, PieceColor color, Rank rank) {
        for (File file : File.values()) {
            result.put(new Position(rank, file), new Pawn(color));
        }
    }

    public void move(Position source, Position target) {
        validateSourceNotEmpty(source);
        changePieces(source, target);
    }

    private void changePieces(Position source, Position target) {
        values.put(target, values.get(source));
        values.put(source, new EmptyPiece(PieceColor.EMPTY));
    }

    private void validateSourceNotEmpty(Position source) {
        if (values.get(source).equals(new EmptyPiece(PieceColor.EMPTY))) {
            throw new IllegalArgumentException(SOURCE_POSITION_SHOULD_HAVE_PIECE_MESSAGE);
        }
    }

    public Map<Position, Piece> getValues() {
        return values;
    }
}

package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceKind;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class InitializedBoard {

    private static final Map<Position, Piece> initializedBoard = new HashMap<>();

    private InitializedBoard() {
    }

    public static Map<Position, Piece> emptyBoard() {
        return new HashMap<>();
    }

    public static Map<Position, Piece> board() {
        Arrays.stream(PieceKind.values())
            .forEach(InitializedBoard::traverseXPositions);

        return initializedBoard;
    }

    private static void traverseXPositions(PieceKind pieceKind) {
        pieceKind.bringInitialXPositions()
            .forEach(x -> traversYPositions(pieceKind, x));
    }

    private static void traversYPositions(PieceKind pieceKind, XPosition x) {
        pieceKind.bringInitialYPositions()
            .stream()
            .map(y -> Position.of(x, y))
            .forEach(position -> putBoard(pieceKind, position));
    }

    private static void putBoard(PieceKind pieceKind, Position position) {
        if (pieceKind == PieceKind.VOID) {
            putVoidPieces(pieceKind, position);
            return;
        }
        putColorPieces(pieceKind, position);
    }

    private static void putColorPieces(PieceKind pieceKind, Position position) {
        Piece pieceW = new Piece(pieceKind, PieceColor.WHITE);
        initializedBoard.put(position, pieceW);

        Piece pieceB = new Piece(pieceKind, PieceColor.BLACK);
        initializedBoard.put(position.computeSymmetricPosition(), pieceB);
    }

    private static void putVoidPieces(PieceKind pieceKind, Position position) {
        Piece pieceVoid = new Piece(pieceKind, PieceColor.VOID);
        initializedBoard.put(position, pieceVoid);
        initializedBoard.put(position.computeSymmetricPosition(), pieceVoid);
    }
}

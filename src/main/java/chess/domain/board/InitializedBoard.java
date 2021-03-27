package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceKind;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class InitializedBoard {

    private final Map<Position, Piece> initializedBoard = new HashMap<>();

    public InitializedBoard() {
    }

    public Map<Position, Piece> emptyBoard() {
        return new HashMap<>();
    }

    public Map<Position, Piece> board() {
        Arrays.stream(PieceKind.values())
            .forEach(this::traverseXPositions);

        return new HashMap<>(initializedBoard);
    }

    private void traverseXPositions(PieceKind pieceKind) {
        pieceKind.bringInitialXPositions()
            .forEach(x -> traversYPositions(pieceKind, x));
    }

    private void traversYPositions(PieceKind pieceKind, XPosition x) {
        pieceKind.bringInitialYPositions()
            .stream()
            .map(y -> Position.of(x, y))
            .forEach(position -> putBoard(pieceKind, position));
    }

    private void putBoard(PieceKind pieceKind, Position position) {
        if (pieceKind == PieceKind.VOID) {
            putVoidPieces(pieceKind, position);
            return;
        }
        putColorPieces(pieceKind, position);
    }

    private void putColorPieces(PieceKind pieceKind, Position position) {
        Piece pieceW = new Piece(pieceKind, PieceColor.WHITE);
        initializedBoard.put(position, pieceW);

        Piece pieceB = new Piece(pieceKind, PieceColor.BLACK);
        initializedBoard.put(position.computeSymmetricPosition(), pieceB);
    }

    private void putVoidPieces(PieceKind pieceKind, Position position) {
        Piece pieceVoid = new Piece(pieceKind, PieceColor.VOID);
        initializedBoard.put(position, pieceVoid);
        initializedBoard.put(position.computeSymmetricPosition(), pieceVoid);
    }
}

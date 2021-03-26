package chess.domain.board;

import chess.domain.piece.InitialLocation;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceKind;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class InitializedBoard {

    private final Map<Position, Piece> initializedBoard = new HashMap<>();

    public InitializedBoard() {
        Arrays.stream(PieceKind.values())
            .forEach(this::traversePositions);
    }

    private void traversePositions(final PieceKind pieceKind) {
        InitialLocation initialLocation = InitialLocation.matchPiece(pieceKind);
        initialLocation.bringPositions()
            .forEach(position -> putBoard(pieceKind, position));
    }

    private void putBoard(final PieceKind pieceKind, final Position position) {
        if (pieceKind == PieceKind.VOID) {
            putVoidPieces(pieceKind, position);
            return;
        }
        putColorPieces(pieceKind, position);
    }

    private void putColorPieces(final PieceKind pieceKind, final Position position) {
        Piece pieceW = new Piece(pieceKind, PieceColor.WHITE);
        initializedBoard.put(position, pieceW);

        Piece pieceB = new Piece(pieceKind, PieceColor.BLACK);
        initializedBoard.put(position.computeSymmetricPosition(), pieceB);
    }

    private void putVoidPieces(final PieceKind pieceKind, final Position position) {
        Piece pieceVoid = new Piece(pieceKind, PieceColor.VOID);
        initializedBoard.put(position, pieceVoid);
        initializedBoard.put(position.computeSymmetricPosition(), pieceVoid);
    }

    public Map<Position, Piece> locatePieces() {
        return initializedBoard;
    }
}

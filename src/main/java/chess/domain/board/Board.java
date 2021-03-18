package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceKind;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> board = new HashMap<>();

    public Board() {
        initBoard();
    }

    private void initBoard() { //TODO class 분리
        Arrays.stream(PieceKind.values())
            .forEach(this::traverseXPositions);
    }

    private void traverseXPositions(PieceKind pieceKind) {
        pieceKind.bringInitialXPositions()
            .forEach(x -> traversYPositions(pieceKind, x));
    }

    private void traversYPositions(PieceKind pieceKind, char x) {
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
        putPieceAtPosition(position, pieceW);

        Piece pieceB = new Piece(pieceKind, PieceColor.BLACK);
        putPieceAtPosition(position.computeSymmetricPosition(), pieceB);
    }

    private void putVoidPieces(PieceKind pieceKind, Position position) {
        Piece pieceVoid = new Piece(pieceKind, PieceColor.VOID);
        putPieceAtPosition(position, pieceVoid);
        putPieceAtPosition(position.computeSymmetricPosition(), pieceVoid);
    }

    public Piece checkPieceAtPosition(Position position) {
        return board.get(position);
    }

    public void putPieceAtPosition(Position position, Piece piece) {
        board.put(position, piece);
    }
}

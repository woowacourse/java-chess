package chess.board;

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

    private void initBoard() {
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
            setVoidPiece(pieceKind, position);
            return;
        }

        Piece pieceW = new Piece(pieceKind, PieceColor.WHITE);
        board.put(position, pieceW);

        Piece pieceB = new Piece(pieceKind, PieceColor.BLACK);
        board.put(position.getSymmetricPosition(), pieceB);
    }

    private void setVoidPiece(PieceKind pieceKind, Position position) {
        Piece pieceW = new Piece(pieceKind, PieceColor.VOID);
        board.put(position, pieceW);

        Piece pieceB = new Piece(pieceKind, PieceColor.VOID);
        board.put(position.getSymmetricPosition(), pieceB);
    }

    public Piece checkPieceAtPosition(Position position) {
        return board.get(position);
    }
}

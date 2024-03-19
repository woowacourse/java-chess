package chess.domain;

import chess.PieceColor;
import java.util.ArrayList;
import java.util.List;

public class ChessBoard {

    private final List<List<PieceWithColor>> board;

    public ChessBoard(final List<List<PieceWithColor>> board) {
        this.board = board;
    }

    public static ChessBoard init() {
        List<List<PieceWithColor>> board = new ArrayList<>();

        // White pieces
        List<PieceWithColor> whitePiecesRow = new ArrayList<>();
        whitePiecesRow.add(new PieceWithColor(Piece.ROOK, PieceColor.WHITE));
        whitePiecesRow.add(new PieceWithColor(Piece.NIGHT, PieceColor.WHITE));
        whitePiecesRow.add(new PieceWithColor(Piece.BISHOP, PieceColor.WHITE));
        whitePiecesRow.add(new PieceWithColor(Piece.QUEEN, PieceColor.WHITE));
        whitePiecesRow.add(new PieceWithColor(Piece.KING, PieceColor.WHITE));
        whitePiecesRow.add(new PieceWithColor(Piece.BISHOP, PieceColor.WHITE));
        whitePiecesRow.add(new PieceWithColor(Piece.NIGHT, PieceColor.WHITE));
        whitePiecesRow.add(new PieceWithColor(Piece.ROOK, PieceColor.WHITE));
        board.add(whitePiecesRow);

        List<PieceWithColor> whitePawnsRow = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            whitePawnsRow.add(new PieceWithColor(Piece.PAWN, PieceColor.WHITE));
        }
        board.add(whitePawnsRow);

        // Empty rows
        for (int i = 0; i < 4; i++) {
            List<PieceWithColor> emptyRow = new ArrayList<>();
            for (int j = 0; j < 8; j++) {
                emptyRow.add(new PieceWithColor(Piece.EMPTY, PieceColor.EMPTY));
            }
            board.add(emptyRow);
        }

        // Black pawns row
        List<PieceWithColor> blackPawnsRow = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            blackPawnsRow.add(new PieceWithColor(Piece.PAWN, PieceColor.BLACK));
        }
        board.add(blackPawnsRow);

        // Black pieces
        List<PieceWithColor> blackPiecesRow = new ArrayList<>();
        blackPiecesRow.add(new PieceWithColor(Piece.ROOK, PieceColor.BLACK));
        blackPiecesRow.add(new PieceWithColor(Piece.NIGHT, PieceColor.BLACK));
        blackPiecesRow.add(new PieceWithColor(Piece.BISHOP, PieceColor.BLACK));
        blackPiecesRow.add(new PieceWithColor(Piece.QUEEN, PieceColor.BLACK));
        blackPiecesRow.add(new PieceWithColor(Piece.KING, PieceColor.BLACK));
        blackPiecesRow.add(new PieceWithColor(Piece.BISHOP, PieceColor.BLACK));
        blackPiecesRow.add(new PieceWithColor(Piece.NIGHT, PieceColor.BLACK));
        blackPiecesRow.add(new PieceWithColor(Piece.ROOK, PieceColor.BLACK));
        board.add(blackPiecesRow);

        return new ChessBoard(board);
    }

    public List<List<PieceWithColor>> getBoard() {
        return board;
    }
}

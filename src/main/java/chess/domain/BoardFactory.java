package chess.domain;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.Arrays;
import java.util.List;

public class BoardFactory {

    private static final int MIN_ROW = 1;
    private static final int MAX_ROW = 8;
    private static final int BLACK_PAWN_ROW = 7;
    private static final int WHITE_PAWN_ROW = 2;

    public static Board initializeBoard() {
        return initializePieces(new Board());
    }

    private static Board initializePieces(Board board) {
        initializeSpecialPiecesByRow(board, MIN_ROW, PieceColor.WHITE);
        initializeSpecialPiecesByRow(board, MAX_ROW, PieceColor.BLACK);

        for (int column = MIN_ROW; column <= MAX_ROW; column++) {
            board.putPiece(new Pawn(PieceColor.WHITE), new Position(WHITE_PAWN_ROW, column));
            board.putPiece(new Pawn(PieceColor.BLACK), new Position(BLACK_PAWN_ROW, column));
        }

        return board;
    }

    private static void initializeSpecialPiecesByRow(Board board, int row, PieceColor color) {
        List<Piece> pieces = createPieces(color);
        for (int column = MIN_ROW; column <= MAX_ROW; column++) {
            board.putPiece(pieces.get(column - 1), new Position(row, column));
        }
    }

    private static List<Piece> createPieces(PieceColor color) {
        return Arrays.asList(
            new Rook(color),
            new Knight(color),
            new Bishop(color),
            new Queen(color),
            new King(color),
            new Bishop(color),
            new Knight(color),
            new Rook(color)
        );
    }
}

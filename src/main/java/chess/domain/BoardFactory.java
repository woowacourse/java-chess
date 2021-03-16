package chess.domain;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.PieceColor;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;

public class BoardFactory {

    public static Board initializeBoard() {
        Board board = new Board();
        initializePieces(board);
        return board;
    }

    private static void initializePieces(Board board) {
        // special pieces
        // white black
        board.putPiece(new Position(1,1), new Rook(PieceColor.WHITE));
        board.putPiece(new Position(1,2), new Knight(PieceColor.WHITE));
        board.putPiece(new Position(1,3), new Bishop(PieceColor.WHITE));
        board.putPiece(new Position(1,4), new Queen(PieceColor.WHITE));
        board.putPiece(new Position(1,5), new King(PieceColor.WHITE));
        board.putPiece(new Position(1,6), new Bishop(PieceColor.WHITE));
        board.putPiece(new Position(1,7), new Knight(PieceColor.WHITE));
        board.putPiece(new Position(1,8), new Rook(PieceColor.WHITE));

        board.putPiece(new Position(8,1), new Rook(PieceColor.BLACK));
        board.putPiece(new Position(8,2), new Knight(PieceColor.BLACK));
        board.putPiece(new Position(8,3), new Bishop(PieceColor.BLACK));
        board.putPiece(new Position(8,4), new Queen(PieceColor.BLACK));
        board.putPiece(new Position(8,5), new King(PieceColor.BLACK));
        board.putPiece(new Position(8,6), new Bishop(PieceColor.BLACK));
        board.putPiece(new Position(8,7), new Knight(PieceColor.BLACK));
        board.putPiece(new Position(8,8), new Rook(PieceColor.BLACK));
        // pawn
        // white black
        for (int column = 1; column <= 8; column++) {
            board.putPiece(new Position(2, column), new Pawn(PieceColor.WHITE));
        }
        for (int column = 1; column <= 8; column++) {
            board.putPiece(new Position(7, column), new Pawn(PieceColor.BLACK));
        }
    }


}

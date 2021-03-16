package chess.domain;

import chess.domain.piece.*;

import java.util.HashMap;
import java.util.Map;

public class ChessBoard {
    private static final int SIZE = 8;

    private Map<Position, Piece> chessBoard;

    public ChessBoard() {
        initPiece();
    }

    private void initPiece() {
        chessBoard = new HashMap<>();
        initPieceByTeam(7, "Black");
        initPawnByTeam(6, "Black");
        initPieceByTeam(0, "White");
        initPawnByTeam(1, "White");
    }

    private void initPieceByTeam(final int row, final String team) {
        chessBoard.put(new Position(row, 0), new Rook(team));
        chessBoard.put(new Position(row, 1), new Knight(team));
        chessBoard.put(new Position(row, 2), new Bishop(team));
        chessBoard.put(new Position(row, 3), new Queen(team));
        chessBoard.put(new Position(row, 4), new King(team));
        chessBoard.put(new Position(row, 5), new Bishop(team));
        chessBoard.put(new Position(row, 6), new Knight(team));
        chessBoard.put(new Position(row, 7), new Rook(team));
    }

    private void initPawnByTeam(final int row, final String team) {
        for (int i = 0; i < SIZE; i++) {
            chessBoard.put(new Position(row, i), new Pawn(team));
        }
    }

    public Map<Position, Piece> getChessBoard() {
        return chessBoard;
    }
}

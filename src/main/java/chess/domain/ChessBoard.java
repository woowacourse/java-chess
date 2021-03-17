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

    private void initPieceByTeam(final int column, final String team) {
        chessBoard.put(new Position( 0, column), new Rook(team));
        chessBoard.put(new Position( 1, column), new Knight(team));
        chessBoard.put(new Position( 2, column), new Bishop(team));
        chessBoard.put(new Position( 3, column), new Queen(team));
        chessBoard.put(new Position( 4, column), new King(team));
        chessBoard.put(new Position( 5, column), new Bishop(team));
        chessBoard.put(new Position( 6, column), new Knight(team));
        chessBoard.put(new Position( 7, column), new Rook(team));
    }

    private void initPawnByTeam(final int column, final String team) {
        for (int i = 0; i < SIZE; i++) {
            chessBoard.put(new Position(i, column), new Pawn(team));
        }
    }

    public Map<Position, Piece> getChessBoard() {
        return chessBoard;
    }

    public boolean havePiece(final Position position) {
        return chessBoard.containsKey(position);
    }
}

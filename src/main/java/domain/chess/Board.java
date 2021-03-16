package domain.chess;

import domain.chess.piece.Piece;
import domain.chess.piece.Position;

import java.util.List;

public class Board {
    public static final int SIZE = 8;
    private Piece[][] board = new Piece[SIZE][SIZE];

    public Board(List<Piece> pieces) {
        pieces.stream().forEach(piece -> put(piece, piece.getPosition()));
    }

    public void put(Piece piece, Position movePosition) {
        board[movePosition.getRow()][movePosition.getColumn()] = piece;
    }

    public Piece[][] getBoard(){
        return board;
    }
}

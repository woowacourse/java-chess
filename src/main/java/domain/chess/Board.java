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

    public void move(Position start, Position end) {
        Piece piece = board[start.getRow()][start.getColumn()];
        if (!piece.canMove(board, end)) {
            throw new IllegalArgumentException("말을 움직일 수 없습니다!");
        }
        piece = piece.movePosition(end);
        put(piece, end);
        board[start.getRow()][start.getColumn()] = null;
    }

    public void put(Piece piece, Position movePosition) {
        board[movePosition.getRow()][movePosition.getColumn()] = piece;
    }

    public Piece[][] getBoard() {
        return board;
    }

    public Piece getPiece(Position position) {
        return board[position.getRow()][position.getColumn()];
    }
}

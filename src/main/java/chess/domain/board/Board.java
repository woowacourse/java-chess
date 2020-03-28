package chess.domain.board;

import chess.domain.piece.Piece;

import java.util.List;

public class Board {
    private final List<Piece> board;

    public Board(final List<Piece> board) {
        if (isNotProperBoardSize(board)) {
            throw new IllegalArgumentException("보드가 제대로 생성되지 못했습니다.");
        }
        this.board = board;
    }

    private boolean isNotProperBoardSize(final List<Piece> board) {
        return board.size() != 64;
    }

    public Piece getPiece(int index) {
        return board.get(index);
    }

    public Piece findPieceByStringPosition(String position) {
        String x = String.valueOf(position.charAt(0));
        String y = String.valueOf(position.charAt(1));

        int col = x.charAt(0) - 96;
        int row = Integer.parseInt(y);

        return getPiece((row - 1) * 8 + col - 1);
    }
}

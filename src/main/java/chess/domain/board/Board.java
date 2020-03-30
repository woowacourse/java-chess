package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.List;

public class Board {
    private static final int BOARD_SIZE = 64;
    private static final int FIRST_INDEX = 0;
    private static final int ASCII_GAP = 96;

    private static boolean isFinished;

    private final List<Piece> board;

    public Board(final List<Piece> board) {
        if (isNotProperBoardSize(board)) {
            throw new IllegalArgumentException("보드가 제대로 생성되지 못했습니다.");
        }
        this.board = board;
    }

    public static void changeFlagWhenKingCaptured(final Piece toPiece) {
        if (toPiece.isKing()) {
            isFinished = true;
        }
    }

    public Piece findPieceBy(int index) {
        return board.get(index);
    }

    public Piece findPieceBy(Position position) {
        return board.get(getBoardIndex(position.getX(), position.getY()));
    }

    private boolean isNotProperBoardSize(final List<Piece> board) {
        return board.size() != BOARD_SIZE;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public int getBoardIndexByStringPosition(String position) {
        String x = String.valueOf(position.charAt(0));
        String y = String.valueOf(position.charAt(1));

        int col = x.charAt(FIRST_INDEX) - ASCII_GAP;
        int row = Integer.parseInt(y);

        return getBoardIndex(col, row);
    }

    private int getBoardIndex(final int col, final int row) {
        return (row - 1) * Position.ROW_SIZE + col - 1;
    }

    public List<Piece> getBoard() {
        return board;
    }
}

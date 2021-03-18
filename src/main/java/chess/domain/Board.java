package chess.domain;

import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;

import java.util.Arrays;

public class Board {
    private static final int BOARD_SIZE = 8;

    private final Piece[][] board = new Piece[BOARD_SIZE][BOARD_SIZE];

    public Board() {
        initialize();
    }

    private void initialize() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            initializeRow(i);
        }
    }

    private void initializeRow(int i) {
        for (int j = 0; j < BOARD_SIZE; j++) {
            board[i][j] = Pieces.findPiece(i, j);
        }
    }

    public Piece[][] getBoard() {
        return board;
    }

    public void movePiece(Point source, Point target, String currentColor) {
        Piece sourcePiece = selectSourcePiece(source);
        if(sourcePiece.isSameColor(currentColor)){
            throw new IllegalArgumentException("기물 색이 일치하지 않습니다.");
        }
    }

    private Piece selectSourcePiece(Point source) {
        int x = source.getX();
        int y = source.getY();
        validateSourcePiece(x, y);
        return this.board[x][y];
    }

    private void validateSourcePiece(int x, int y) {
        if (this.board[x][y] == null) {
            throw new IllegalArgumentException("기물이 존재하지 않습니다.");
        }

        // TODO: 색 검사
//        if (this.board[x][y])
    }

    public boolean hasBothKings() {
        return Arrays.stream(this.board)
                .flatMap(
                        row -> Arrays.stream(row)
                                .filter(piece -> piece instanceof King)
                )
                .count() == 2;
    }
}

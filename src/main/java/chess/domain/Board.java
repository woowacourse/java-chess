package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;

import java.util.stream.IntStream;

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
}

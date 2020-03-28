package chess.domain.board;

import chess.domain.piece.*;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class BoardFactory {
    private static final List<Piece> INITIALIZED_BOARD = new ArrayList<>();

    static {
        setUpWhitePiece();
        setUpBlankPiece();
        setUpBlackPiece();
    }

    private static void setUpWhitePiece() {
        INITIALIZED_BOARD.add(new Rook('r', new Position(1, 1)));
        INITIALIZED_BOARD.add(new Knight('n', new Position(2, 1)));
        INITIALIZED_BOARD.add(new Bishop('b', new Position(3, 1)));
        INITIALIZED_BOARD.add(new Queen('q', new Position(4, 1)));
        INITIALIZED_BOARD.add(new King('k', new Position(5, 1)));
        INITIALIZED_BOARD.add(new Bishop('b', new Position(6, 1)));
        INITIALIZED_BOARD.add(new Knight('n', new Position(7, 1)));
        INITIALIZED_BOARD.add(new Rook('r', new Position(8, 1)));

        IntStream.rangeClosed(1, 8)
                .forEach(col -> INITIALIZED_BOARD.add(new Pawn('p', new Position(col, 2))));
    }

    private static void setUpBlankPiece() {
        for (int row = 3; row <= 6; row++) {
            for (int col = 1; col <= 8; col++) {
                INITIALIZED_BOARD.add(new Blank('.', new Position(col, row)));
            }
        }
    }

    private static void setUpBlackPiece() {
        IntStream.rangeClosed(1, 8)
                .forEach(col -> INITIALIZED_BOARD.add(new Pawn('P', new Position(col, 7))));

        INITIALIZED_BOARD.add(new Rook('R', new Position(1, 8)));
        INITIALIZED_BOARD.add(new Knight('N', new Position(2, 8)));
        INITIALIZED_BOARD.add(new Bishop('B', new Position(3, 8)));
        INITIALIZED_BOARD.add(new Queen('Q', new Position(4, 8)));
        INITIALIZED_BOARD.add(new King('K', new Position(5, 8)));
        INITIALIZED_BOARD.add(new Bishop('B', new Position(6, 8)));
        INITIALIZED_BOARD.add(new Knight('N', new Position(7, 8)));
        INITIALIZED_BOARD.add(new Rook('R', new Position(8, 8)));
    }

    private BoardFactory() {
    }

    public static Board createBoard() {
        return new Board(new ArrayList<>(INITIALIZED_BOARD));
    }
}

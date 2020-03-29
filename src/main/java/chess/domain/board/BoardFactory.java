package chess.domain.board;

import chess.domain.piece.*;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class BoardFactory {
    private static final List<Piece> INITIALIZED_BOARD = new ArrayList<>();
    private static final int START_INDEX = 1;
    private static final int END_INDEX = 8;
    private static final int BLANK_START_ROW = 3;
    private static final int BLANK_END_ROW = 6;

    static {
        setUpWhitePiece();
        setUpBlankPiece();
        setUpBlackPiece();
    }

    private static void setUpWhitePiece() {
        INITIALIZED_BOARD.add(new Rook('r', Team.WHITE, new Position(1, 1)));
        INITIALIZED_BOARD.add(new Knight('n', Team.WHITE, new Position(2, 1)));
        INITIALIZED_BOARD.add(new Bishop('b', Team.WHITE, new Position(3, 1)));
        INITIALIZED_BOARD.add(new Queen('q', Team.WHITE, new Position(4, 1)));
        INITIALIZED_BOARD.add(new King('k', Team.WHITE, new Position(5, 1)));
        INITIALIZED_BOARD.add(new Bishop('b', Team.WHITE, new Position(6, 1)));
        INITIALIZED_BOARD.add(new Knight('n', Team.WHITE, new Position(7, 1)));
        INITIALIZED_BOARD.add(new Rook('r', Team.WHITE, new Position(8, 1)));

        IntStream.rangeClosed(START_INDEX, END_INDEX)
                .forEach(col -> INITIALIZED_BOARD.add(new WhitePawn('p', Team.WHITE, new Position(col, 2))));
    }

    private static void setUpBlankPiece() {
        for (int row = BLANK_START_ROW; row <= BLANK_END_ROW; row++) {
            for (int col = START_INDEX; col <= END_INDEX; col++) {
                INITIALIZED_BOARD.add(new Blank('.', Team.BLANK, new Position(col, row)));
            }
        }
    }

    private static void setUpBlackPiece() {
        IntStream.rangeClosed(START_INDEX, END_INDEX)
                .forEach(col -> INITIALIZED_BOARD.add(new BlackPawn('P', Team.BLACK, new Position(col, 7))));

        INITIALIZED_BOARD.add(new Rook('R', Team.BLACK, new Position(1, 8)));
        INITIALIZED_BOARD.add(new Knight('N', Team.BLACK, new Position(2, 8)));
        INITIALIZED_BOARD.add(new Bishop('B', Team.BLACK, new Position(3, 8)));
        INITIALIZED_BOARD.add(new Queen('Q', Team.BLACK, new Position(4, 8)));
        INITIALIZED_BOARD.add(new King('K', Team.BLACK, new Position(5, 8)));
        INITIALIZED_BOARD.add(new Bishop('B', Team.BLACK, new Position(6, 8)));
        INITIALIZED_BOARD.add(new Knight('N', Team.BLACK, new Position(7, 8)));
        INITIALIZED_BOARD.add(new Rook('R', Team.BLACK, new Position(8, 8)));
    }

    private BoardFactory() {
    }

    public static Board createBoard() {
        return new Board(new ArrayList<>(INITIALIZED_BOARD));
    }
}

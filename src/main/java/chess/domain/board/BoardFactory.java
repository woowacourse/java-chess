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
    private static final char WHITE_ROOK_REPRESENTATION = 'r';
    private static final char WHITE_KNIGHT_REPRESENTATION = 'n';
    private static final char WHITE_BISHOP_REPRESENTATION = 'b';
    private static final char WHITE_QUEEN_REPRESENTATION = 'q';
    private static final char WHITE_KING_REPRESENTATION = 'k';
    private static final char WHITE_PAWN_REPRESENTATION = 'p';
    private static final int BLANK_START_ROW = 3;
    private static final int BLANK_END_ROW = 6;
    private static final char BLANK_REPRESENTATION = '.';
    private static final char BLACK_PAWN_REPRESENTATION = 'P';
    private static final char BLACK_ROOK_REPRESENTATION = 'R';
    private static final char BLACK_KNIGHT_REPRESENTATION = 'N';
    private static final char BLACK_BISHOP_REPRESENTATION = 'B';
    private static final char BLACK_QUEEN_REPRESENTATION = 'Q';
    private static final char BLACK_KING_REPRESENTATION = 'K';

    static {
        setUpWhitePiece();
        setUpBlankPiece();
        setUpBlackPiece();
    }

    private static void setUpWhitePiece() {
        INITIALIZED_BOARD.add(new Rook(WHITE_ROOK_REPRESENTATION, Team.WHITE, new Position(1, 1)));
        INITIALIZED_BOARD.add(new Knight(WHITE_KNIGHT_REPRESENTATION, Team.WHITE, new Position(2, 1)));
        INITIALIZED_BOARD.add(new Bishop(WHITE_BISHOP_REPRESENTATION, Team.WHITE, new Position(3, 1)));
        INITIALIZED_BOARD.add(new Queen(WHITE_QUEEN_REPRESENTATION, Team.WHITE, new Position(4, 1)));
        INITIALIZED_BOARD.add(new King(WHITE_KING_REPRESENTATION, Team.WHITE, new Position(5, 1)));
        INITIALIZED_BOARD.add(new Bishop(BoardFactory.WHITE_BISHOP_REPRESENTATION, Team.WHITE, new Position(6, 1)));
        INITIALIZED_BOARD.add(new Knight(BoardFactory.WHITE_KNIGHT_REPRESENTATION, Team.WHITE, new Position(7, 1)));
        INITIALIZED_BOARD.add(new Rook(BoardFactory.WHITE_ROOK_REPRESENTATION, Team.WHITE, new Position(8, 1)));

        IntStream.rangeClosed(START_INDEX, END_INDEX)
                .forEach(col -> INITIALIZED_BOARD.add(new WhitePawn(WHITE_PAWN_REPRESENTATION, Team.WHITE, new Position(col, 2))));
    }

    private static void setUpBlankPiece() {
        for (int row = BLANK_START_ROW; row <= BLANK_END_ROW; row++) {
            for (int col = START_INDEX; col <= END_INDEX; col++) {
                INITIALIZED_BOARD.add(new Blank(BLANK_REPRESENTATION, Team.BLANK, new Position(col, row)));
            }
        }
    }

    private static void setUpBlackPiece() {
        IntStream.rangeClosed(START_INDEX, END_INDEX)
                .forEach(col -> INITIALIZED_BOARD.add(new BlackPawn(BLACK_PAWN_REPRESENTATION, Team.BLACK, new Position(col, 7))));

        INITIALIZED_BOARD.add(new Rook(BLACK_ROOK_REPRESENTATION, Team.BLACK, new Position(1, 8)));
        INITIALIZED_BOARD.add(new Knight(BLACK_KNIGHT_REPRESENTATION, Team.BLACK, new Position(2, 8)));
        INITIALIZED_BOARD.add(new Bishop(BLACK_BISHOP_REPRESENTATION, Team.BLACK, new Position(3, 8)));
        INITIALIZED_BOARD.add(new Queen(BLACK_QUEEN_REPRESENTATION, Team.BLACK, new Position(4, 8)));
        INITIALIZED_BOARD.add(new King(BLACK_KING_REPRESENTATION, Team.BLACK, new Position(5, 8)));
        INITIALIZED_BOARD.add(new Bishop(BLACK_BISHOP_REPRESENTATION, Team.BLACK, new Position(6, 8)));
        INITIALIZED_BOARD.add(new Knight(BLACK_KNIGHT_REPRESENTATION, Team.BLACK, new Position(7, 8)));
        INITIALIZED_BOARD.add(new Rook(BLACK_ROOK_REPRESENTATION, Team.BLACK, new Position(8, 8)));
    }

    private BoardFactory() {
    }

    public static Board createBoard() {
        return new Board(new ArrayList<>(INITIALIZED_BOARD));
    }
}

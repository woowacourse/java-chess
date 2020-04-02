package chess.domain.board;

import chess.domain.piece.*;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class BoardFactory {
    private static final List<Piece> INITIALIZED_BOARD = new ArrayList<>();
    private static final int BLANK_START_ROW = 3;
    private static final int BLANK_END_ROW = 6;

    static {
        setUpWhitePiece();
        setUpBlankPiece();
        setUpBlackPiece();
    }

    private static void setUpWhitePiece() {
        INITIALIZED_BOARD.add(Piece.createWhiteRook(new Position(1, 1)));
        INITIALIZED_BOARD.add(Piece.createWhiteKnight(new Position(2, 1)));
        INITIALIZED_BOARD.add(Piece.createWhiteBishop(new Position(3, 1)));
        INITIALIZED_BOARD.add(Piece.createWhiteQueen(new Position(4, 1)));
        INITIALIZED_BOARD.add(Piece.createWhiteKing(new Position(5, 1)));
        INITIALIZED_BOARD.add(Piece.createWhiteBishop(new Position(6, 1)));
        INITIALIZED_BOARD.add(Piece.createWhiteKnight(new Position(7, 1)));
        INITIALIZED_BOARD.add(Piece.createWhiteRook(new Position(8, 1)));

        IntStream.rangeClosed(Position.START_INDEX, Position.END_INDEX)
                .forEach(col -> INITIALIZED_BOARD.add(Piece.createWhitePawn(new Position(col, 2))));
    }

    private static void setUpBlankPiece() {
        for (int row = BLANK_START_ROW; row <= BLANK_END_ROW; row++) {
            for (int col = Position.START_INDEX; col <= Position.END_INDEX; col++) {
                INITIALIZED_BOARD.add(Piece.createBlank(new Position(col, row)));
            }
        }
    }

    private static void setUpBlackPiece() {
        IntStream.rangeClosed(Position.START_INDEX, Position.END_INDEX)
                .forEach(col -> INITIALIZED_BOARD.add(Piece.createBlackPawn(new Position(col, 7))));

        INITIALIZED_BOARD.add(Piece.createBlackRook(new Position(1, 8)));
        INITIALIZED_BOARD.add(Piece.createBlackKnight(new Position(2, 8)));
        INITIALIZED_BOARD.add(Piece.createBlackBishop(new Position(3, 8)));
        INITIALIZED_BOARD.add(Piece.createBlackQueen(new Position(4, 8)));
        INITIALIZED_BOARD.add(Piece.createBlackKing(new Position(5, 8)));
        INITIALIZED_BOARD.add(Piece.createBlackBishop(new Position(6, 8)));
        INITIALIZED_BOARD.add(Piece.createBlackKnight(new Position(7, 8)));
        INITIALIZED_BOARD.add(Piece.createBlackRook(new Position(8, 8)));
    }

    private BoardFactory() {
    }

    public static Board createBoard() {
        return new Board(new ArrayList<>(INITIALIZED_BOARD));
    }
}

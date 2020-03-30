package chess.domain.board;

import chess.domain.piece.*;
import chess.domain.position.Position;
import chess.domain.strategy.*;

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
        INITIALIZED_BOARD.add(new Rook(new RookMoveStrategy(), 'r', Team.WHITE, new Position(1, 1)));
        INITIALIZED_BOARD.add(new Knight(new KnightMoveStrategy(), 'n', Team.WHITE, new Position(2, 1)));
        INITIALIZED_BOARD.add(new Bishop(new BishopMoveStrategy(), 'b', Team.WHITE, new Position(3, 1)));
        INITIALIZED_BOARD.add(new Queen(new QueenMoveStrategy(), 'q', Team.WHITE, new Position(4, 1)));
        INITIALIZED_BOARD.add(new King(new KingMoveStrategy(), 'k', Team.WHITE, new Position(5, 1)));
        INITIALIZED_BOARD.add(new Bishop(new BishopMoveStrategy(), 'b', Team.WHITE, new Position(6, 1)));
        INITIALIZED_BOARD.add(new Knight(new KnightMoveStrategy(), 'n', Team.WHITE, new Position(7, 1)));
        INITIALIZED_BOARD.add(new Rook(new RookMoveStrategy(), 'r', Team.WHITE, new Position(8, 1)));

        IntStream.rangeClosed(Position.START_INDEX, Position.END_INDEX)
                .forEach(col -> INITIALIZED_BOARD.add(new WhitePawn(new WhitePawnMoveStrategy(), 'p', Team.WHITE, new Position(col, 2))));
    }

    private static void setUpBlankPiece() {
        for (int row = BLANK_START_ROW; row <= BLANK_END_ROW; row++) {
            for (int col = Position.START_INDEX; col <= Position.END_INDEX; col++) {
                INITIALIZED_BOARD.add(new Blank(new BlankMoveStrategy(), '.', Team.BLANK, new Position(col, row)));
            }
        }
    }

    private static void setUpBlackPiece() {
        IntStream.rangeClosed(Position.START_INDEX, Position.END_INDEX)
                .forEach(col -> INITIALIZED_BOARD.add(new BlackPawn(new BlackPawnMoveStrategy(), 'P', Team.BLACK, new Position(col, 7))));

        INITIALIZED_BOARD.add(new Rook(new RookMoveStrategy(), 'R', Team.BLACK, new Position(1, 8)));
        INITIALIZED_BOARD.add(new Knight(new KnightMoveStrategy(), 'N', Team.BLACK, new Position(2, 8)));
        INITIALIZED_BOARD.add(new Bishop(new BishopMoveStrategy(), 'B', Team.BLACK, new Position(3, 8)));
        INITIALIZED_BOARD.add(new Queen(new QueenMoveStrategy(), 'Q', Team.BLACK, new Position(4, 8)));
        INITIALIZED_BOARD.add(new King(new KingMoveStrategy(), 'K', Team.BLACK, new Position(5, 8)));
        INITIALIZED_BOARD.add(new Bishop(new BishopMoveStrategy(), 'B', Team.BLACK, new Position(6, 8)));
        INITIALIZED_BOARD.add(new Knight(new KnightMoveStrategy(), 'N', Team.BLACK, new Position(7, 8)));
        INITIALIZED_BOARD.add(new Rook(new RookMoveStrategy(), 'R', Team.BLACK, new Position(8, 8)));
    }

    private BoardFactory() {
    }

    public static Board createBoard() {
        return new Board(new ArrayList<>(INITIALIZED_BOARD));
    }
}

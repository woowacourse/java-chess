package chess.piece;

import chess.chessboard.position.File;
import chess.chessboard.position.Position;
import chess.chessboard.position.Rank;

import java.util.HashMap;
import java.util.Map;

import static chess.chessboard.position.File.*;
import static chess.chessboard.position.Rank.*;
import static chess.game.Player.*;

public class PieceFactory {

    private static final Map<Position, Piece> INITIAL_BOARD = new HashMap<>();

    static {
        for (final Rank rank : Rank.values()) {
            createBlankIn(rank);
        }
        createBlackPieces();
        createWhitePieces();
    }

    public static Map<Position, Piece> initBoard() {
        return INITIAL_BOARD;
    }
    
    private static void createBlankIn(final Rank rank) {
        for (File file : File.values()) {
            INITIAL_BOARD.put(Position.of(rank, file), new Blank(NONE, "."));
        }
    }

    private static void createBlackPieces() {
        INITIAL_BOARD.put(Position.of(EIGHT, A), new Rook(BLACK, "R"));
        INITIAL_BOARD.put(Position.of(EIGHT, B), new Knight(BLACK, "N"));
        INITIAL_BOARD.put(Position.of(EIGHT, C), new Bishop(BLACK, "B"));
        INITIAL_BOARD.put(Position.of(EIGHT, D), new Queen(BLACK, "Q"));
        INITIAL_BOARD.put(Position.of(EIGHT, E), new King(BLACK, "K"));
        INITIAL_BOARD.put(Position.of(EIGHT, F), new Bishop(BLACK, "B"));
        INITIAL_BOARD.put(Position.of(EIGHT, G), new Knight(BLACK, "N"));
        INITIAL_BOARD.put(Position.of(EIGHT, H), new Rook(BLACK, "R"));
        for (final File file : File.values()) {
            INITIAL_BOARD.put(Position.of(SEVEN, file), new Pawn(BLACK, "P"));
        }
    }

    private static void createWhitePieces() {
        INITIAL_BOARD.put(Position.of(ONE, A), new Rook(WHITE, "r"));
        INITIAL_BOARD.put(Position.of(ONE, B), new Knight(WHITE, "n"));
        INITIAL_BOARD.put(Position.of(ONE, C), new Bishop(WHITE, "b"));
        INITIAL_BOARD.put(Position.of(ONE, D), new Queen(WHITE, "q"));
        INITIAL_BOARD.put(Position.of(ONE, E), new King(WHITE, "k"));
        INITIAL_BOARD.put(Position.of(ONE, F), new Bishop(WHITE, "b"));
        INITIAL_BOARD.put(Position.of(ONE, G), new Knight(WHITE, "n"));
        INITIAL_BOARD.put(Position.of(ONE, H), new Rook(WHITE, "r"));
        for (final File file : File.values()) {
            INITIAL_BOARD.put(Position.of(TWO, file), new Pawn(WHITE, "p"));
        }
    }
}

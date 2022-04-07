package chess.piece;

import chess.chessboard.position.File;
import chess.chessboard.position.Position;
import chess.chessboard.position.Rank;
import chess.game.Player;

import java.util.HashMap;
import java.util.Map;

import static chess.chessboard.position.File.*;
import static chess.chessboard.position.Rank.*;
import static chess.game.Player.*;
import static chess.piece.Symbol.*;

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
        return new HashMap<>(INITIAL_BOARD);
    }
    
    private static void createBlankIn(final Rank rank) {
        for (File file : File.values()) {
            INITIAL_BOARD.put(Position.of(rank, file), new Blank(NONE, BLANK));
        }
    }

    private static void createBlackPieces() {
        createPieces(EIGHT, BLACK);
        for (final File file : File.values()) {
            INITIAL_BOARD.put(Position.of(SEVEN, file), new Pawn(BLACK, PAWN));
        }
    }

    private static void createWhitePieces() {
        createPieces(ONE, WHITE);
        for (final File file : File.values()) {
            INITIAL_BOARD.put(Position.of(TWO, file), new Pawn(WHITE, PAWN));
        }
    }

    private static void createPieces(Rank rank, Player player) {
        INITIAL_BOARD.put(Position.of(rank, A), new Rook(player, ROOK));
        INITIAL_BOARD.put(Position.of(rank, B), new Knight(player, KNIGHT));
        INITIAL_BOARD.put(Position.of(rank, C), new Bishop(player, BISHOP));
        INITIAL_BOARD.put(Position.of(rank, D), new Queen(player, QUEEN));
        INITIAL_BOARD.put(Position.of(rank, E), new King(player, KING));
        INITIAL_BOARD.put(Position.of(rank, F), new Bishop(player, BISHOP));
        INITIAL_BOARD.put(Position.of(rank, G), new Knight(player, KNIGHT));
        INITIAL_BOARD.put(Position.of(rank, H), new Rook(player, ROOK));
    }
}

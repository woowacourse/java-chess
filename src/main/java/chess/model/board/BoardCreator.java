package chess.model.board;

import static chess.model.Team.BLACK;
import static chess.model.Team.NONE;
import static chess.model.Team.WHITE;
import static chess.model.position.File.A;
import static chess.model.position.File.B;
import static chess.model.position.File.C;
import static chess.model.position.File.D;
import static chess.model.position.File.E;
import static chess.model.position.File.F;
import static chess.model.position.File.G;
import static chess.model.position.File.H;
import static chess.model.position.Rank.EIGHT;
import static chess.model.position.Rank.ONE;
import static chess.model.position.Rank.SEVEN;
import static chess.model.position.Rank.TWO;

import chess.model.piece.Bishop;
import chess.model.piece.Blank;
import chess.model.piece.King;
import chess.model.piece.Knight;
import chess.model.piece.Pawn;
import chess.model.piece.Piece;
import chess.model.piece.Queen;
import chess.model.piece.Rook;
import chess.model.position.File;
import chess.model.position.Position;
import chess.model.position.Rank;
import java.util.HashMap;
import java.util.Map;

public class BoardCreator {

    public static Map<Position, Piece> create() {
        final Map<Position, Piece> board = new HashMap<>();
        initBoard(board);
        createBlackPieces(board);
        createWhitePieces(board);
        return board;
    }

    private static void initBoard(final Map<Position, Piece> board) {
        for (Rank rank : Rank.values()) {
            createBlankIn(board, rank);
        }
    }

    private static void createBlankIn(final Map<Position, Piece> board, final Rank rank) {
        for (File file : File.values()) {
            board.put(Position.of(rank, file), new Blank());
        }
    }

    private static void createBlackPieces(final Map<Position, Piece> board) {
        board.put(Position.of(EIGHT, A), new Rook(BLACK));
        board.put(Position.of(EIGHT, B), new Knight(BLACK));
        board.put(Position.of(EIGHT, C), new Bishop(BLACK));
        board.put(Position.of(EIGHT, D), new Queen(BLACK));
        board.put(Position.of(EIGHT, E), new King(BLACK));
        board.put(Position.of(EIGHT, F), new Bishop(BLACK));
        board.put(Position.of(EIGHT, G), new Knight(BLACK));
        board.put(Position.of(EIGHT, H), new Rook(BLACK));
        for (File file : File.values()) {
            board.put(Position.of(SEVEN, file), new Pawn(BLACK));
        }
    }

    private static void createWhitePieces(final Map<Position, Piece> board) {
        board.put(Position.of(ONE, A), new Rook(WHITE));
        board.put(Position.of(ONE, B), new Knight(WHITE));
        board.put(Position.of(ONE, C), new Bishop(WHITE));
        board.put(Position.of(ONE, D), new Queen(WHITE));
        board.put(Position.of(ONE, E), new King(WHITE));
        board.put(Position.of(ONE, F), new Bishop(WHITE));
        board.put(Position.of(ONE, G), new Knight(WHITE));
        board.put(Position.of(ONE, H), new Rook(WHITE));
        for (File file : File.values()) {
            board.put(Position.of(TWO, file), new Pawn(WHITE));
        }
    }
}

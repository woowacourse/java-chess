package chess.domain;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;

import java.util.HashMap;
import java.util.Map;

public class BoardMaker {

    public Map<Square, Piece> make() {
        Map<Square, Piece> board = new HashMap<>();
        board.putAll(makePawns());
        board.putAll(makeMajorPieces());
        return new HashMap<>(board);
    }

    private Map<Square, Piece> makePawns() {
        Map<Square, Piece> pawns = new HashMap<>();
        pawns.putAll(makePawn(Color.WHITE));
        pawns.putAll(makePawn(Color.BLACK));
        return new HashMap<>(pawns);
    }

    private Map<Square, Piece> makePawn(final Color color) {
        Map<Square, Piece> pawns = new HashMap<>();
        Rank rank = getPawnRankByColor(color);
        for (File file : File.values()) {
            pawns.put(Square.of(file, rank), new Pawn(color));
        }
        return new HashMap<>(pawns);
    }

    private Rank getPawnRankByColor(final Color color) {
        if (color == Color.WHITE) {
            return Rank.TWO;
        }
        return Rank.SEVEN;
    }

    private Map<Square, Piece> makeMajorPieces() {
        Map<Square, Piece> majors = new HashMap<>();
        majors.putAll(makeRooks());
        majors.putAll(makeKnights());
        majors.putAll(makeBishops());
        majors.putAll(makeQueens());
        majors.putAll(makeKings());
        return new HashMap<>(majors);
    }

    private Map<Square, Piece> makeRooks() {
        Map<Square, Piece> rooks = new HashMap<>();
        rooks.put(Square.of(File.A, Rank.ONE), new Rook(Color.WHITE));
        rooks.put(Square.of(File.H, Rank.ONE), new Rook(Color.WHITE));
        rooks.put(Square.of(File.A, Rank.EIGHT), new Rook(Color.BLACK));
        rooks.put(Square.of(File.H, Rank.EIGHT), new Rook(Color.BLACK));
        return new HashMap<>(rooks);
    }

    private Map<Square, Piece> makeKnights() {
        Map<Square, Piece> knights = new HashMap<>();
        knights.put(Square.of(File.B, Rank.ONE), new Knight(Color.WHITE));
        knights.put(Square.of(File.G, Rank.ONE), new Knight(Color.WHITE));
        knights.put(Square.of(File.B, Rank.EIGHT), new Knight(Color.BLACK));
        knights.put(Square.of(File.G, Rank.EIGHT), new Knight(Color.BLACK));
        return new HashMap<>(knights);
    }

    private Map<Square, Piece> makeBishops() {
        Map<Square, Piece> bishops = new HashMap<>();
        bishops.put(Square.of(File.C, Rank.ONE), new Bishop(Color.WHITE));
        bishops.put(Square.of(File.F, Rank.ONE), new Bishop(Color.WHITE));
        bishops.put(Square.of(File.C, Rank.EIGHT), new Bishop(Color.BLACK));
        bishops.put(Square.of(File.F, Rank.EIGHT), new Bishop(Color.BLACK));
        return new HashMap<>(bishops);
    }

    private Map<Square, Piece> makeQueens() {
        Map<Square, Piece> queens = new HashMap<>();
        queens.put(Square.of(File.D, Rank.ONE), new Queen(Color.WHITE));
        queens.put(Square.of(File.D, Rank.EIGHT), new Queen(Color.BLACK));
        return new HashMap<>(queens);
    }

    private Map<Square, Piece> makeKings() {
        Map<Square, Piece> kings = new HashMap<>();
        kings.put(Square.of(File.E, Rank.ONE), new King(Color.WHITE));
        kings.put(Square.of(File.E, Rank.EIGHT), new King(Color.BLACK));
        return new HashMap<>(kings);
    }
}

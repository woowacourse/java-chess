package chess.domain;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.strategy.DiagonalMoveStrategy;
import chess.domain.piece.strategy.KingMoveStrategy;
import chess.domain.piece.strategy.KnightMoveStrategy;
import chess.domain.piece.strategy.MoveStrategy;
import chess.domain.piece.strategy.PawnDirection;
import chess.domain.piece.strategy.PawnMoveStrategy;
import chess.domain.piece.strategy.StraightMoveStrategy;
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
        MoveStrategy moveStrategy = new PawnMoveStrategy(PawnDirection.from(color));
        for (File file : File.values()) {
            pawns.put(Square.of(file, rank), new Pawn(color, moveStrategy));
        }
        return new HashMap<>(pawns);
    }

    private Rank getPawnRankByColor(final Color color) {
        if (color.equals(Color.WHITE)) {
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
        MoveStrategy moveStrategy = new StraightMoveStrategy();
        rooks.put(Square.of(File.A, Rank.ONE), new Rook(Color.WHITE, moveStrategy));
        rooks.put(Square.of(File.H, Rank.ONE), new Rook(Color.WHITE, moveStrategy));
        rooks.put(Square.of(File.A, Rank.EIGHT), new Rook(Color.BLACK, moveStrategy));
        rooks.put(Square.of(File.H, Rank.EIGHT), new Rook(Color.BLACK, moveStrategy));
        return new HashMap<>(rooks);
    }

    private Map<Square, Piece> makeKnights() {
        Map<Square, Piece> knights = new HashMap<>();
        MoveStrategy moveStrategy = new KnightMoveStrategy();
        knights.put(Square.of(File.B, Rank.ONE), new Knight(Color.WHITE, moveStrategy));
        knights.put(Square.of(File.G, Rank.ONE), new Knight(Color.WHITE, moveStrategy));
        knights.put(Square.of(File.B, Rank.EIGHT), new Knight(Color.BLACK, moveStrategy));
        knights.put(Square.of(File.G, Rank.EIGHT), new Knight(Color.BLACK, moveStrategy));
        return new HashMap<>(knights);
    }

    private Map<Square, Piece> makeBishops() {
        Map<Square, Piece> bishops = new HashMap<>();
        MoveStrategy moveStrategy = new DiagonalMoveStrategy();
        bishops.put(Square.of(File.C, Rank.ONE), new Bishop(Color.WHITE, moveStrategy));
        bishops.put(Square.of(File.F, Rank.ONE), new Bishop(Color.WHITE, moveStrategy));
        bishops.put(Square.of(File.C, Rank.EIGHT), new Bishop(Color.BLACK, moveStrategy));
        bishops.put(Square.of(File.F, Rank.EIGHT), new Bishop(Color.BLACK, moveStrategy));
        return new HashMap<>(bishops);
    }

    private Map<Square, Piece> makeQueens() {
        Map<Square, Piece> queens = new HashMap<>();
        MoveStrategy moveStrategy = new StraightMoveStrategy();
        MoveStrategy subMoveStrategy = new DiagonalMoveStrategy();
        queens.put(Square.of(File.D, Rank.ONE), new Queen(Color.WHITE, moveStrategy, subMoveStrategy));
        queens.put(Square.of(File.D, Rank.EIGHT), new Queen(Color.BLACK, moveStrategy, subMoveStrategy));
        return new HashMap<>(queens);
    }

    private Map<Square, Piece> makeKings() {
        Map<Square, Piece> kings = new HashMap<>();
        MoveStrategy moveStrategy = new KingMoveStrategy();
        kings.put(Square.of(File.E, Rank.ONE), new King(Color.WHITE, moveStrategy));
        kings.put(Square.of(File.E, Rank.EIGHT), new King(Color.BLACK, moveStrategy));
        return new HashMap<>(kings);
    }
}

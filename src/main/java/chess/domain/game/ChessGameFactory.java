package chess.domain.game;

import chess.domain.piece.Bishop;
import chess.domain.piece.Empty;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;
import chess.domain.piece.pawn.InitialBlackPawn;
import chess.domain.piece.pawn.InitialWhitePawn;

import java.util.HashMap;
import java.util.Map;

public final class ChessGameFactory {

    ChessGameFactory() {
        throw new AssertionError("생성할 수 없는 클래스입니다.");
    }

    public static ChessGame generate() {
        Map<Position, Piece> board = new HashMap<>();
        initializeBoard(board);
        initPawn(board);
        initRook(board);
        initKnight(board);
        initBishop(board);
        initQueen(board);
        initKing(board);
        return ChessGame.from(board);
    }

    private static void initializeBoard(final Map<Position, Piece> board) {
        for (File file : File.values()) {
            for (Rank rank : Rank.values()) {
                board.put(Position.of(file, rank), Empty.instance());
            }
        }
    }

    private static void initPawn(final Map<Position, Piece> board) {
        for (File file : File.values()) {
            board.put(Position.of(file, Rank.TWO), InitialWhitePawn.instance());
            board.put(Position.of(file, Rank.SEVEN), InitialBlackPawn.instance());
        }
    }

    private static void initRook(final Map<Position, Piece> board) {
        board.put(Position.of(File.A, Rank.ONE), Rook.create(Team.WHITE));
        board.put(Position.of(File.H, Rank.ONE), Rook.create(Team.WHITE));
        board.put(Position.of(File.A, Rank.EIGHT), Rook.create(Team.BLACK));
        board.put(Position.of(File.H, Rank.EIGHT), Rook.create(Team.BLACK));
    }

    private static void initKnight(final Map<Position, Piece> board) {
        board.put(Position.of(File.B, Rank.ONE), Knight.instance(Team.WHITE));
        board.put(Position.of(File.G, Rank.ONE), Knight.instance(Team.WHITE));
        board.put(Position.of(File.B, Rank.EIGHT), Knight.instance(Team.BLACK));
        board.put(Position.of(File.G, Rank.EIGHT), Knight.instance(Team.BLACK));
    }

    private static void initBishop(final Map<Position, Piece> board) {
        board.put(Position.of(File.C, Rank.ONE), Bishop.instance(Team.WHITE));
        board.put(Position.of(File.F, Rank.ONE), Bishop.instance(Team.WHITE));
        board.put(Position.of(File.C, Rank.EIGHT), Bishop.instance(Team.BLACK));
        board.put(Position.of(File.F, Rank.EIGHT), Bishop.instance(Team.BLACK));
    }

    private static void initQueen(final Map<Position, Piece> board) {
        board.put(Position.of(File.D, Rank.ONE), Queen.instance(Team.WHITE));
        board.put(Position.of(File.D, Rank.EIGHT), Queen.instance(Team.BLACK));
    }

    private static void initKing(final Map<Position, Piece> board) {
        board.put(Position.of(File.E, Rank.ONE), King.instance(Team.WHITE));
        board.put(Position.of(File.E, Rank.EIGHT), King.instance(Team.BLACK));
    }
}

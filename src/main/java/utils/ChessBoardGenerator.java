package utils;

import domain.Player;
import domain.piece.Bishop;
import domain.piece.Blank;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.Queen;
import domain.piece.Rook;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ChessBoardGenerator {

    public static Map<Position, Piece> generate() {
        Map<Position, Piece> board = new HashMap<>();
        createInitialize(board);
        createTeamBoard(board, Player.BLACK);
        createTeamBoard(board, Player.WHITE);
        return board;
    }

    private static void createInitialize(Map<Position, Piece> board) {
        for (Rank rank : Rank.values()) {
            initializeByRow(board, rank);
        }
    }

    private static void initializeByRow(Map<Position, Piece> board, final Rank rank) {
        for (File file : File.values()) {
            board.put(Position.of(file, rank), new Blank());
        }
    }

    private static void createTeamBoard(final Map<Position, Piece> board, final Player player) {
        createInitPawn(board, player);
        createInitRook(board, player);
        createInitKnight(board, player);
        createInitBishop(board, player);
        createInitQueen(board, player);
        createInitKing(board, player);
    }

    private static void createInitPawn(Map<Position, Piece> board, final Player player) {
        if (player == Player.BLACK) {
            Arrays.stream(File.values())
                .forEach(
                    column -> board.put(Position.of(column, Rank.SEVEN),
                        new Pawn(Player.BLACK)));
            return;
        }
        Arrays.stream(File.values())
            .forEach(
                column -> board.put(Position.of(column, Rank.TWO), new Pawn(Player.WHITE)));
    }

    private static void createInitRook(Map<Position, Piece> board, final Player player) {
        if (player == Player.BLACK) {
            board.put(Position.of(File.A, Rank.EIGHT), new Rook(Player.BLACK));
            board.put(Position.of(File.H, Rank.EIGHT), new Rook(Player.BLACK));
            return;
        }
        board.put(Position.of(File.A, Rank.ONE), new Rook(Player.WHITE));
        board.put(Position.of(File.H, Rank.ONE), new Rook(Player.WHITE));
    }

    private static void createInitKnight(Map<Position, Piece> board, final Player player) {
        if (player == Player.BLACK) {
            board.put(Position.of(File.B, Rank.EIGHT), new Knight(Player.BLACK));
            board.put(Position.of(File.G, Rank.EIGHT), new Knight(Player.BLACK));
            return;
        }
        board.put(Position.of(File.B, Rank.ONE), new Knight(Player.WHITE));
        board.put(Position.of(File.G, Rank.ONE), new Knight(Player.WHITE));
    }

    private static void createInitBishop(Map<Position, Piece> board, final Player player) {
        if (player == Player.BLACK) {
            board.put(Position.of(File.C, Rank.EIGHT), new Bishop(Player.BLACK));
            board.put(Position.of(File.F, Rank.EIGHT), new Bishop(Player.BLACK));
            return;
        }
        board.put(Position.of(File.C, Rank.ONE), new Bishop(Player.WHITE));
        board.put(Position.of(File.F, Rank.ONE), new Bishop(Player.WHITE));
    }

    private static void createInitQueen(Map<Position, Piece> board, final Player player) {
        if (player == Player.BLACK) {
            board.put(Position.of(File.D, Rank.EIGHT), new Queen(Player.BLACK));
            return;
        }
        board.put(Position.of(File.D, Rank.ONE), new Queen(Player.WHITE));
    }

    private static void createInitKing(Map<Position, Piece> board, final Player player) {
        if (player == Player.BLACK) {
            board.put(Position.of(File.E, Rank.EIGHT), new King(Player.BLACK));
            return;
        }
        board.put(Position.of(File.E, Rank.ONE), new King(Player.WHITE));
    }
}

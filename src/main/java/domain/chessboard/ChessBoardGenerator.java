package domain.chessboard;

import domain.Player;
import domain.piece.Bishop;
import domain.piece.BlackPawn;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Piece;
import domain.piece.Queen;
import domain.piece.Rook;
import domain.piece.WhitePawn;
import domain.position.Rank;
import domain.position.File;
import domain.position.Position;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ChessBoardGenerator implements BoardGenerator {

    @Override
    public Map<Position, Piece> generate() {
        Map<Position, Piece> board = new HashMap<>();
        createInitialize(board);
        createTeamBoard(board, Player.BLACK);
        createTeamBoard(board, Player.WHITE);
        return board;
    }

    private void createInitialize(Map<Position, Piece> board) {
        for (Rank rank : Rank.values()) {
            initializeByRow(board, rank);
        }
    }

    private void initializeByRow(Map<Position, Piece> board, final Rank rank) {
        for (File file : File.values()) {
            board.put(Position.of(file, rank), null);
        }
    }

    private void createTeamBoard(final Map<Position, Piece> board, final Player player) {
        createInitPawn(board, player);
        createInitRook(board, player);
        createInitKnight(board, player);
        createInitBishop(board, player);
        createInitQueen(board, player);
        createInitKing(board, player);
    }

    private void createInitPawn(Map<Position, Piece> board, final Player player) {
        if (player == Player.BLACK) {
            Arrays.stream(File.values())
                .forEach(
                    column -> board.put(Position.of(column, Rank.SEVEN),
                        new BlackPawn(Player.BLACK)));
            return;
        }
        Arrays.stream(File.values())
            .forEach(
                column -> board.put(Position.of(column, Rank.TWO), new WhitePawn(Player.WHITE)));
    }

    private void createInitRook(Map<Position, Piece> board, final Player player) {
        if (player == Player.BLACK) {
            board.put(Position.of(File.A, Rank.EIGHT), new Rook(Player.BLACK));
            board.put(Position.of(File.H, Rank.EIGHT), new Rook(Player.BLACK));
            return;
        }
        board.put(Position.of(File.A, Rank.ONE), new Rook(Player.WHITE));
        board.put(Position.of(File.H, Rank.ONE), new Rook(Player.WHITE));
    }

    private void createInitKnight(Map<Position, Piece> board, final Player player) {
        if (player == Player.BLACK) {
            board.put(Position.of(File.B, Rank.EIGHT), new Knight(Player.BLACK));
            board.put(Position.of(File.G, Rank.EIGHT), new Knight(Player.BLACK));
            return;
        }
        board.put(Position.of(File.B, Rank.ONE), new Knight(Player.WHITE));
        board.put(Position.of(File.G, Rank.ONE), new Knight(Player.WHITE));
    }

    private void createInitBishop(Map<Position, Piece> board, final Player player) {
        if (player == Player.BLACK) {
            board.put(Position.of(File.C, Rank.EIGHT), new Bishop(Player.BLACK));
            board.put(Position.of(File.F, Rank.EIGHT), new Bishop(Player.BLACK));
            return;
        }
        board.put(Position.of(File.C, Rank.ONE), new Bishop(Player.WHITE));
        board.put(Position.of(File.F, Rank.ONE), new Bishop(Player.WHITE));
    }

    private void createInitQueen(Map<Position, Piece> board, final Player player) {
        if (player == Player.BLACK) {
            board.put(Position.of(File.D, Rank.EIGHT), new Queen(Player.BLACK));
            return;
        }
        board.put(Position.of(File.D, Rank.ONE), new Queen(Player.WHITE));
    }

    private void createInitKing(Map<Position, Piece> board, final Player player) {
        if (player == Player.BLACK) {
            board.put(Position.of(File.E, Rank.EIGHT), new King(Player.BLACK));
            return;
        }
        board.put(Position.of(File.E, Rank.ONE), new King(Player.WHITE));
    }
}

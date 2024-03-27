package chess.domain.chessboard;

import static chess.domain.chesspiece.Team.BLACK;
import static chess.domain.chesspiece.Team.WHITE;

import chess.domain.chesspiece.Empty;
import chess.domain.chesspiece.Knight;
import chess.domain.chesspiece.Piece;
import chess.domain.chesspiece.Team;
import chess.domain.chesspiece.pawn.BlackPawn;
import chess.domain.chesspiece.pawn.WhitePawn;
import chess.domain.chesspiece.slidingPiece.Bishop;
import chess.domain.chesspiece.slidingPiece.King;
import chess.domain.chesspiece.slidingPiece.Queen;
import chess.domain.chesspiece.slidingPiece.Rook;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.LinkedHashMap;
import java.util.Map;

public class ChessBoardGenerator {

    public static Map<Position, Piece> initializeBoard() {
        Map<Position, Piece> board = new LinkedHashMap<>();
        initializeBackRank(board, Rank.EIGHT, BLACK);
        initializeBlackPawnRank(board);
        initializeEmptyRanks(board);
        initializeWhitePawnRank(board);
        initializeBackRank(board, Rank.ONE, WHITE);
        return board;
    }

    private static void initializeBackRank(Map<Position, Piece> board, Rank rank, Team team) {
        board.put(new Position(File.a, rank), new Rook(team));
        board.put(new Position(File.b, rank), new Knight(team));
        board.put(new Position(File.c, rank), new Bishop(team));
        board.put(new Position(File.d, rank), new Queen(team));
        board.put(new Position(File.e, rank), new King(team));
        board.put(new Position(File.f, rank), new Bishop(team));
        board.put(new Position(File.g, rank), new Knight(team));
        board.put(new Position(File.h, rank), new Rook(team));
    }

    private static void initializeBlackPawnRank(Map<Position, Piece> board) {
        initializeFiles(board, Rank.SEVEN, new BlackPawn());
    }

    private static void initializeWhitePawnRank(Map<Position, Piece> board) {
        initializeFiles(board, Rank.TWO, new WhitePawn());
    }

    private static void initializeEmptyRanks(Map<Position, Piece> board) {
        initializeFiles(board, Rank.SIX, new Empty());
        initializeFiles(board, Rank.FIVE, new Empty());
        initializeFiles(board, Rank.FOUR, new Empty());
        initializeFiles(board, Rank.THREE, new Empty());
    }

    private static void initializeFiles(Map<Position, Piece> board, Rank rank, Piece piece) {
        for(File file : File.values()) {
            board.put(new Position(file, rank), piece);
        }
    }
}

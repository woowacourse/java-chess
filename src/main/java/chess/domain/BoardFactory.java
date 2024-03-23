package chess.domain;

import chess.domain.piece.Bishop;
import chess.domain.piece.Empty;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class BoardFactory {

    public static Map<Point, Piece> createEmptyBoard() {
        Map<Point, Piece> board = new HashMap<>();

        IntStream.rangeClosed(1, 8)
                .boxed()
                .map(BoardFactory::lineOfEmpty)
                .forEach(board::putAll);

        return board;
    }

    public static Map<Point, Piece> createInitialChessBoard() {
        Map<Point, Piece> board = new HashMap<>();

        board.putAll(createEighthLine());
        board.putAll(createSeventhLine());
        IntStream.rangeClosed(3, 6)
                .boxed()
                .map(BoardFactory::lineOfEmpty)
                .forEach(board::putAll);
        board.putAll(createSecondLine());
        board.putAll(createFirstLine());
        return board;
    }

    private static Map<Point, Piece> createEighthLine() {
        return lineOfKing(8, Team.BLACK);
    }

    private static Map<Point, Piece> createSeventhLine() {
        return lineOfPawn(7, Team.BLACK);
    }

    private static Map<Point, Piece> createSecondLine() {
        return lineOfPawn(2, Team.WHITE);
    }

    private static Map<Point, Piece> createFirstLine() {
        return lineOfKing(1, Team.WHITE);
    }

    private static Map<Point, Piece> lineOfPawn(int rank, Team team) {
        Map<Point, Piece> line = new HashMap<>();

        for (char c = 'a'; c <= 'h'; c++) {
            line.put(new Point(c, rank), Piece.pawnFrom(team));
        }
        return line;
    }

    private static Map<Point, Piece> lineOfKing(int rank, Team team) {
        return Map.of(
                new Point('a', rank), Piece.rookFrom(team),
                new Point('b', rank), Piece.knightFrom(team),
                new Point('c', rank), Piece.bishopFrom(team),
                new Point('d', rank), Piece.queenFrom(team),
                new Point('e', rank), Piece.kingFrom(team),
                new Point('f', rank), Piece.bishopFrom(team),
                new Point('g', rank), Piece.knightFrom(team),
                new Point('h', rank), Piece.rookFrom(team)
        );
    }

    private static Map<Point, Piece> lineOfEmpty(int rank) {
        Map<Point, Piece> line = new HashMap<>();

        for (char c = 'a'; c <= 'h'; c++) {
            line.put(new Point(c, rank), Piece.empty());
        }
        return line;
    }
}

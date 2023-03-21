package chess.domain;

import chess.domain.piece.BishopPiece;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.KingPiece;
import chess.domain.piece.KnightPiece;
import chess.domain.piece.PawnPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.QueenPiece;
import chess.domain.piece.RookPiece;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardGenerator {
    private static final int RANK_SIZE = 8;
    private static final Board emptyBoard;

    static {
        Map<Position, Piece> emptyMap = Collections.emptyMap();
        emptyBoard = new Board(emptyMap);
    }

    public static Board emtpyBoard() {
        return emptyBoard;
    }

    public static Board makeBoard() {
        Map<Position, Piece> board = new HashMap<>();
        enrollBlackPiece(board);
        enrollWhitePiece(board);
        enrollEmptyPiece(board);
        return new Board(board);
    }

    private static void enrollBlackPiece(Map<Position, Piece> board) {
        enrollPiece(board, makePiecesExceptPawns(Color.BLACK, Rank.EIGHT));
        enrollPiece(board, makePawns(Color.BLACK, Rank.SEVEN));
    }

    private static Map<Position, Piece> makePiecesExceptPawns(Color color, Rank rank) {
        HashMap<Position, Piece> result = new HashMap<>();
        List<Piece> highPieceOrder = orderedPiece(color);
        for (int i = 0; i < RANK_SIZE; i++) {
            Position position = Position.of(File.from(i + 1), rank);
            result.put(position, highPieceOrder.get(i));
        }
        return result;
    }

    private static List<Piece> orderedPiece(Color color) {
        return List.of(
                new RookPiece(color), new KnightPiece(color), new BishopPiece(color), new QueenPiece(color),
                new KingPiece(color), new BishopPiece(color), new KnightPiece(color), new RookPiece(color));
    }

    private static Map<Position, Piece> makePawns(Color color, Rank rank) {
        Map<Position, Piece> result = new HashMap<>();
        for (File file : File.values()) {
            result.put(Position.of(file, rank), new PawnPiece(color));
        }
        return result;
    }

    private static void enrollPiece(Map<Position, Piece> board, Map<Position, Piece> data) {
        board.putAll(data);
    }

    private static void enrollWhitePiece(Map<Position, Piece> board) {
        enrollPiece(board, makePiecesExceptPawns(Color.WHITE, Rank.ONE));
        enrollPiece(board, makePawns(Color.WHITE, Rank.TWO));
    }

    private static void enrollEmptyPiece(Map<Position, Piece> board) {
        enrollPiece(board, makeEmptyPiece());
    }

    private static Map<Position, Piece> makeEmptyPiece() {
        HashMap<Position, Piece> result = new HashMap<>();
        for (File file : File.values()) {
            for (int i = 3; i < 7; i++) {
                result.computeIfAbsent(Position.of(file, Rank.from(i)), (ignored) -> EmptyPiece.getInstance());
            }
        }
        return result;
    }
}

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
        board.putAll(makeBlackPiece());
        board.putAll(makeWhitePiece());
        board.putAll(makeEmptyPiece());
        return new Board(board);
    }

    private static Map<Position, Piece> makeBlackPiece() {
        HashMap<Position, Piece> result = new HashMap<>();
        result.putAll(makePiecesExceptPawns(Color.BLACK, Rank.EIGHT));
        result.putAll(makePawns(Color.BLACK, Rank.SEVEN));

        return result;
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

    private static Map<Position, Piece> makeWhitePiece() {
        HashMap<Position, Piece> result = new HashMap<>();
        result.putAll(makePiecesExceptPawns(Color.WHITE, Rank.ONE));
        result.putAll(makePawns(Color.WHITE, Rank.TWO));
        return result;
    }

    private static Map<Position, Piece> makeEmptyPiece() {
        HashMap<Position, Piece> result = new HashMap<>();
        for (File file : File.values()) {
            for (int i = 3; i < 7; i++) {
                result.put(Position.of(file, Rank.from(i)), EmptyPiece.getInstance());
            }
        }
        return result;
    }
}

package chess.domain;

import chess.domain.piece.BishopPiece;
import chess.domain.piece.Color;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.KingPiece;
import chess.domain.piece.KnightPiece;
import chess.domain.piece.PawnPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.QueenPiece;
import chess.domain.piece.RookPiece;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.LinkedHashMap;
import java.util.Map;

public class BoardInitializer {

    private static final int INITIAL_CAPACITY = 64;

    public static Map<Position, Piece> create() {
        final Map<Position, Piece> board = new LinkedHashMap<>(INITIAL_CAPACITY);

        initialPieces(board);

        return board;
    }

    private static void initialPieces(final Map<Position, Piece> board) {
        putPiecesWithoutPawn(board, Rank.EIGHT, Color.BLACK);
        putPiecesOnRank(board, Rank.SEVEN, new PawnPiece(Color.BLACK));

        for (int i = 6; i >= 3; i--) {
            putPiecesOnRank(board, Rank.of(i), new EmptyPiece());
        }

        putPiecesOnRank(board, Rank.TWO, new PawnPiece(Color.WHITE));
        putPiecesWithoutPawn(board, Rank.ONE, Color.WHITE);
    }

    private static void putPiecesWithoutPawn(final Map<Position, Piece> board,
                                             final Rank rank,
                                             final Color color) {
        board.put(new Position(Column.A, rank), new RookPiece(color));
        board.put(new Position(Column.B, rank), new KnightPiece(color));
        board.put(new Position(Column.C, rank), new BishopPiece(color));
        board.put(new Position(Column.D, rank), new QueenPiece(color));
        board.put(new Position(Column.E, rank), new KingPiece(color));
        board.put(new Position(Column.F, rank), new BishopPiece(color));
        board.put(new Position(Column.G, rank), new KnightPiece(color));
        board.put(new Position(Column.H, rank), new RookPiece(color));
    }

    private static void putPiecesOnRank(final Map<Position, Piece> board,
                                        final Rank rank,
                                        final Piece piece) {
        for (Column column : Column.values()) {
            board.put(new Position(column, rank), piece);
        }
    }
}

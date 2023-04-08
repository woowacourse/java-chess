package chess.domain.chessboard;

import chess.domain.piece.*;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.strategy.piecemovestrategy.*;

import java.util.HashMap;
import java.util.Map;

public class ChessBoardFactory {

    private static final Rank INITIAL_BLACK_PAWN_RANK = Rank.SEVEN;
    private static final Rank INITIAL_BLACK_NON_PAWN_RANK = Rank.EIGHT;
    private static final Rank INITIAL_WHITE_PAWN_RANK = Rank.TWO;
    private static final Rank INITIAL_WHITE_NON_PAWN_RANK = Rank.ONE;

    public ChessBoard createInitialBoard() {
        return new ChessBoard(createInitialPieces());
    }

    private Map<Position, Piece> createInitialPieces() {
        final Map<Position, Piece> pieces = new HashMap<>();
        createPawns(pieces, INITIAL_BLACK_PAWN_RANK, Color.BLACK);
        createPawns(pieces, INITIAL_WHITE_PAWN_RANK, Color.WHITE);
        createNonePawnPieces(pieces);
        createEmptyPosition(pieces);
        return pieces;
    }

    private void createPawns(final Map<Position, Piece> pieces, final Rank rank, final Color color) {
        for (final File file : File.values()) {
            final Position position = Position.of(rank, file);
            pieces.put(position, new Pawn(color, position, PawnMoveStrategy.from(color)));
        }
    }

    private void createNonePawnPieces(final Map<Position, Piece> pieces) {
        createWhiteNonPawnPieces(pieces);
        createBlackNonPawnPieces(pieces);
    }

    private void createWhiteNonPawnPieces(final Map<Position, Piece> pieces) {
        createWhiteNonPawnPieces(pieces, File.A, RookMove.getInstance());
        createWhiteNonPawnPieces(pieces, File.B, KnightMove.getInstance());
        createWhiteNonPawnPieces(pieces, File.C, BishopMove.getInstance());
        createWhiteNonPawnPieces(pieces, File.D, QueenMove.getInstance());
        createWhiteNonPawnPieces(pieces, File.E, KnightMove.getInstance());
        createWhiteNonPawnPieces(pieces, File.F, BishopMove.getInstance());
        createWhiteNonPawnPieces(pieces, File.G, KnightMove.getInstance());
        createWhiteNonPawnPieces(pieces, File.H, RookMove.getInstance());
    }

    private void createWhiteNonPawnPieces(final Map<Position, Piece> pieces, File file, PieceMoveStrategy pieceMoveStrategy) {
        Position position = Position.of(INITIAL_WHITE_NON_PAWN_RANK, file);
        pieces.put(position, new NonPawnPiece(Color.WHITE, position, pieceMoveStrategy));
    }

    private void createBlackNonPawnPieces(final Map<Position, Piece> pieces) {
        createBlackNonPawnPieces(pieces, File.A, RookMove.getInstance());
        createBlackNonPawnPieces(pieces, File.B, KnightMove.getInstance());
        createBlackNonPawnPieces(pieces, File.C, BishopMove.getInstance());
        createBlackNonPawnPieces(pieces, File.D, QueenMove.getInstance());
        createBlackNonPawnPieces(pieces, File.E, KnightMove.getInstance());
        createBlackNonPawnPieces(pieces, File.F, BishopMove.getInstance());
        createBlackNonPawnPieces(pieces, File.G, KnightMove.getInstance());
        createBlackNonPawnPieces(pieces, File.H, RookMove.getInstance());
    }

    private void createBlackNonPawnPieces(final Map<Position, Piece> pieces, File file, PieceMoveStrategy pieceMoveStrategy) {
        Position position = Position.of(INITIAL_BLACK_NON_PAWN_RANK, file);
        pieces.put(position, new NonPawnPiece(Color.BLACK, position, pieceMoveStrategy));
    }

    private void createEmptyPosition(final Map<Position, Piece> pieces) {
        for (final Rank rank : Rank.ranksBetween(INITIAL_WHITE_PAWN_RANK, INITIAL_BLACK_PAWN_RANK)) {
            createEmptyPosition(pieces, rank);
        }
    }

    private void createEmptyPosition(final Map<Position, Piece> pieces, final Rank rank) {
        for (final File file : File.values()) {
            final Position position = Position.of(rank, file);
            pieces.put(position, new Empty(position));
        }
    }
}

package chess.dao.chess;

import chess.entity.PieceEntity;

import java.util.ArrayList;
import java.util.List;

public class PieceEntityHelper {

    public static List<PieceEntity> createPieceEntities(final Long chessGameId) {
        final List<PieceEntity> results = new ArrayList<>();
        results.addAll(createMockPieces(1L, chessGameId, 0, "WHITE"));
        results.addAll(createMockPawnPieces(6L, chessGameId, 1, "WHITE"));
        results.addAll(createMockPawnPieces(10L, chessGameId, 6, "BLACK"));
        results.addAll(createMockPieces(14L, chessGameId, 7, "BLACK"));
        return results;
    }

    private static List<PieceEntity> createMockPieces(final long id, final long chessGameId,
                                                      final int rank, final String campType) {
        final List<PieceEntity> results = new ArrayList<>();
        results.add(PieceEntity.create(id, chessGameId, rank, 0, "ROOK", campType));
        results.add(PieceEntity.create(id + 1, chessGameId, rank, 1, "KNIGHT", campType));
        results.add(PieceEntity.create(id + 2, chessGameId, rank, 2, "BISHOP", campType));
        results.add(PieceEntity.create(id + 3, chessGameId, rank, 3, "QUEEN", campType));
        results.add(PieceEntity.create(id + 4, chessGameId, rank, 4, "KING", campType));
        return results;
    }

    private static List<PieceEntity> createMockPawnPieces(final long id, final long chessGameId,
                                                          final int rank, final String campType) {
        final List<PieceEntity> results = new ArrayList<>();
        for (int index = 0; index < 4; index++) {
            results.add(PieceEntity.create(id + index, chessGameId, rank, index, "PAWN", campType));
        }
        return results;
    }
}

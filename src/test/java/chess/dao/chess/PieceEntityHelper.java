package chess.dao.chess;

import chess.entity.PieceEntity;

import java.util.ArrayList;
import java.util.List;

public class PieceEntityHelper {

    public static List<PieceEntity> createPieceEntities() {
        final List<PieceEntity> results = new ArrayList<>();
        results.addAll(createMockPieces(1L, 0, "WHITE"));
        results.addAll(createMockPawnPieces(9L, 1, "WHITE"));
        results.addAll(createMockPawnPieces(17L, 6, "BLACK"));
        results.addAll(createMockPieces(25L, 7, "BLACK"));
        return results;
    }

    private static List<PieceEntity> createMockPieces(final long id, final int rank, final String campType) {
        final List<PieceEntity> results = new ArrayList<>();
        results.add(new PieceEntity(id, rank, 0, "ROOK", campType));
        results.add(new PieceEntity(id + 1, rank, 1, "KNIGHT", campType));
        results.add(new PieceEntity(id + 2, rank, 2, "BISHOP", campType));
        results.add(new PieceEntity(id + 3, rank, 3, "QUEEN", campType));
        results.add(new PieceEntity(id + 4, rank, 4, "KING", campType));
        return results;
    }

    private static List<PieceEntity> createMockPawnPieces(final long id, final int rank, final String campType) {
        final List<PieceEntity> results = new ArrayList<>();
        for (int file = 0; file < 4; file++) {
            results.add(new PieceEntity(id + file, rank, file, "PAWN", campType));
        }
        return results;
    }
}

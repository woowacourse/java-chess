package chess.dao.chess;

import chess.entity.PieceEntity;

import java.util.ArrayList;
import java.util.List;

public class PieceEntityHelper {

    public static List<PieceEntity> createPieceEntities(final Long chessGameId) {
        final List<PieceEntity> results = new ArrayList<>();
        results.addAll(createMockPieces(chessGameId, 0, "WHITE"));
        results.addAll(createMockPawnPieces(chessGameId, 1, "WHITE"));
        results.addAll(createMockPawnPieces(chessGameId, 6, "BLACK"));
        results.addAll(createMockPieces(chessGameId, 7, "BLACK"));
        return results;
    }

    private static List<PieceEntity> createMockPieces(final long chessGameId, final int rank,
                                                      final String campType) {
        final List<PieceEntity> results = new ArrayList<>();
        results.add(PieceEntity.createWithChessGameId(chessGameId, rank, 0, "ROOK", campType));
        results.add(PieceEntity.createWithChessGameId(chessGameId, rank, 1, "KNIGHT", campType));
        results.add(PieceEntity.createWithChessGameId(chessGameId, rank, 2, "BISHOP", campType));
        results.add(PieceEntity.createWithChessGameId(chessGameId, rank, 3, "QUEEN", campType));
        results.add(PieceEntity.createWithChessGameId(chessGameId, rank, 4, "KING", campType));
        return results;
    }

    private static List<PieceEntity> createMockPawnPieces(final long chessGameId, final int rank, final String campType) {
        final List<PieceEntity> results = new ArrayList<>();
        for (int file = 0; file < 4; file++) {
            results.add(PieceEntity.createWithChessGameId(chessGameId, rank, file, "PAWN", campType));
        }
        return results;
    }
}

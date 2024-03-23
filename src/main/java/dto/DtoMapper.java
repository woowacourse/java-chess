package dto;

import domain.board.Board;
import domain.piece.Piece;
import domain.piece.info.Position;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import view.PieceShape;

public class DtoMapper {

    private DtoMapper() {
    }

    public static RankInfo getPieceShapeOfRank(final Board board, final int rank) {
        final List<Piece> pieces = findPiecesByOrderOfRank(board, rank);
        final List<String> pieceShapes = new ArrayList<>();

        for (final Piece piece : pieces) {
            addByPieceColor(piece, pieceShapes);
        }
        return new RankInfo(pieceShapes);
    }

    private static List<Piece> findPiecesByOrderOfRank(final Board board, final int rank) {
        final Map<Position, Piece> squares = board.squares();
        return squares.entrySet().stream()
                .filter(entry -> entry.getKey().toRankIndex() == rank)
                .sorted(Comparator.comparingInt(entry -> entry.getKey().toFileIndex()))
                .map(Entry::getValue)
                .toList();
    }

    private static void addByPieceColor(final Piece piece, final List<String> pieceShapes) {
        if (piece.isWhite()) {
            pieceShapes.add(PieceShape.whiteShapeOf(piece.getClass().getName()));
            return;
        }
        pieceShapes.add(PieceShape.blackShapeOf(piece.getClass().getName()));
    }
}

package dto;

import domain.board.position.Position;
import domain.piece.Piece;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import view.PieceShape;

public class DtoMapper {

    private DtoMapper() {
    }


    public static GameResultResponse generateGameResultResponse(final Double whiteScore, final Double blackScore) {
        return new GameResultResponse(whiteScore, blackScore);
    }

    public static BoardResponse generateBoardResponse(final Map<Position, Piece> squares) {
        final List<RankResponse> rankResponses = new ArrayList<>();
        for (int rank = 7; rank >= 0; rank--) {
            final RankResponse pieceShapeOfRank = DtoMapper.generateRankResponse(squares, rank);
            rankResponses.add(pieceShapeOfRank);
        }
        return new BoardResponse(rankResponses);
    }

    private static RankResponse generateRankResponse(final Map<Position, Piece> squares, final int rank) {
        final List<Piece> pieces = findPiecesByOrderOfRank(squares, rank);
        final List<String> pieceShapes = new ArrayList<>();

        for (final Piece piece : pieces) {
            addByPieceColor(piece, pieceShapes);
        }
        return new RankResponse(pieceShapes);
    }

    private static List<Piece> findPiecesByOrderOfRank(final Map<Position, Piece> squares, final int rank) {
        return squares.entrySet()
                .stream()
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

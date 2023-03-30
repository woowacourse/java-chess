package chess.domain.game;

import chess.domain.board.PieceProvider;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceScore;
import chess.domain.piece.PieceType;
import chess.domain.piece.Score;
import chess.domain.position.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class StatusFactory {
    
    private static final Function<Integer, Double> PAWN_SCORE_WEIGHT = size -> {
        if (size > 1) {
            return 0.5;
        }
        if (size == 1) {
            return 1.0;
        }
        return 0.0;
    };
    
    public static Status createStatus(final PieceProvider pieceProvider) {
        Map<Color, Score> scoreMap = new HashMap<>();
        scoreMap.put(Color.WHITE, calculateScore(pieceProvider, Color.WHITE));
        scoreMap.put(Color.BLACK, calculateScore(pieceProvider, Color.BLACK));
        return Status.from(scoreMap);
    }
    
    private static Score calculateScore(final PieceProvider pieceProvider, final Color color) {
        Score score = Score.from(0);
        for (File file : File.values()) {
            List<Piece> filePieces = pieceProvider.getFilePieces(file);
            removeOtherPieces(color, filePieces);
            Score pawnScore = calculatePawnScore(getPawnCount(filePieces));
            Score otherScore = calculateOtherPieceScore(filePieces);
            score = score.add(pawnScore).add(otherScore);
        }
        return score;
    }
    
    private static Score calculateOtherPieceScore(final List<Piece> filePieces) {
        filePieces.removeIf(Piece::isPawn);
        return filePieces.stream()
                .map(piece -> PieceScore.getScore(piece.getType()))
                .reduce(Score.from(0), Score::add);
    }
    
    private static int getPawnCount(final List<Piece> filePieces) {
        return (int) filePieces.stream()
                .filter(Piece::isPawn)
                .count();
    }
    
    private static void removeOtherPieces(final Color color, final List<Piece> filePieces) {
        filePieces.removeIf(piece -> piece.isNotSameColor(color));
        filePieces.removeIf(Piece::isEmpty);
    }
    
    private static Score calculatePawnScore(Integer pawnCount) {
        return PieceScore.getScore(PieceType.PAWN).multiply(PAWN_SCORE_WEIGHT.apply(pawnCount)).multiply(pawnCount);
    }
    
}

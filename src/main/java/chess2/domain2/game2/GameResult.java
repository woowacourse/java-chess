package chess2.domain2.game2;

import static chess2.domain2.board2.piece2.PieceType.PAWN;
import static chess2.util2.PositionUtil.FILES_TOTAL_SIZE;
import static chess2.util2.PositionUtil.RANKS_TOTAL_SIZE;

import chess2.domain2.board2.Position;
import chess2.domain2.board2.piece2.Color;
import chess2.domain2.board2.piece2.Piece;
import chess2.domain2.board2.piece2.PieceScoreUtil;
import chess2.dto2.GameScoreDto;
import java.util.Map;
import java.util.stream.IntStream;

public class GameResult {

    private static final int SAME_FILE_PAWN_MIN_COUNT = 2;
    private static final double SAME_FILE_PAWN_DISADVANTAGE = 0.5;

    private final Color winnerColor;
    private final GameScoreDto scoreResult;

    public GameResult(Map<Position, Piece> board, Color winnerColor) {
        this.winnerColor = winnerColor;
        this.scoreResult = new GameScoreDto(
                calculateScore(board, Color.WHITE), calculateScore(board, Color.BLACK));
    }

    public Color winnerColor() {
        return winnerColor;
    }

    public double whiteScore() {
        return scoreResult.whiteScore();
    }

    public double blackScore() {
        return scoreResult.blackScore();
    }

    public double calculateScore(Map<Position, Piece> board, Color color) {
        double defaultScore = defaultScore(board, color);
        int sameFilePawnCount = countAllSameFilePawns(board, color);

        return defaultScore - (sameFilePawnCount * SAME_FILE_PAWN_DISADVANTAGE);
    }

    private double defaultScore(Map<Position, Piece> board, Color color) {
        return board.values()
                .stream()
                .filter(piece -> piece.hasColorOf(color))
                .mapToDouble(PieceScoreUtil::toScore)
                .sum();
    }

    private int countAllSameFilePawns(Map<Position, Piece> board, Color color) {
        return IntStream.range(0, FILES_TOTAL_SIZE)
                .map(fileIdx -> countSameFilePawns(board, fileIdx, color))
                .filter(sameFilePawnCount -> sameFilePawnCount >= SAME_FILE_PAWN_MIN_COUNT)
                .sum();
    }

    private int countSameFilePawns(Map<Position, Piece> board, int fileIdx, Color color) {
        return (int) IntStream.range(0, RANKS_TOTAL_SIZE)
                .mapToObj(rankIdx -> Position.of(fileIdx, rankIdx))
                .filter(board::containsKey)
                .map(board::get)
                .filter(piece -> piece.hasColorOf(color))
                .filter(piece -> piece.hasTypeOf(PAWN))
                .count();
    }
}

package chess2.domain2.game2;

import static chess2.domain2.board2.piece2.Color.BLACK;
import static chess2.domain2.board2.piece2.Color.WHITE;
import static chess2.domain2.board2.piece2.PieceType.KING;
import static chess2.domain2.board2.piece2.PieceType.PAWN;
import static chess2.domain2.board2.position.Position.FILES_TOTAL_SIZE;
import static chess2.domain2.board2.position.Position.RANKS_TOTAL_SIZE;

import chess2.domain2.board2.piece2.Color;
import chess2.domain2.board2.piece2.Piece;
import chess2.domain2.board2.position.Position;
import chess2.dto2.GameScoreDto;
import chess2.util2.PieceScoreUtil;
import java.util.Map;
import java.util.stream.IntStream;

public class GameResult {

    private static final int SAME_FILE_PAWN_MIN_COUNT = 2;
    private static final double SAME_FILE_PAWN_DISADVANTAGE = 0.5;

    private final Color winnerColor;
    private final GameScoreDto scoreResult;

    public GameResult(Map<Position, Piece> board) {
        this.winnerColor = chooseWinner(board);
        this.scoreResult = new GameScoreDto(calculateScore(board, WHITE), calculateScore(board, BLACK));
    }

    private Color chooseWinner(Map<Position, Piece> board) {
        Piece king = getOnlyKingLeft(board);
        if (king.hasColorOf(WHITE)) {
            return WHITE;
        }
        return BLACK;
    }

    private Piece getOnlyKingLeft(Map<Position, Piece> board) {
        return board.values()
                .stream()
                .filter(piece -> piece.hasTypeOf(KING))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("킹이 하나도 없는 게임은 존재할 수 없습니다."));
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

    public Color winnerColor() {
        return winnerColor;
    }

    public double whiteScore() {
        return scoreResult.whiteScore();
    }

    public double blackScore() {
        return scoreResult.blackScore();
    }
}

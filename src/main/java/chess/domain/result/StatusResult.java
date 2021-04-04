package chess.domain.result;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Column;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;

public final class StatusResult implements Result {

    private static final double PAWN_SCORE = 1;
    private static final double DUPLICATED_PAWN_SCORE = 0.5;

    private final Board board;

    public StatusResult(final Board board) {
        this.board = board;
    }

    @Override
    public String infoAsString() {
        return renderScore();
    }

    @Override
    public Map<Position, Piece> infoAsMap() {
        throw new IllegalArgumentException("점수는 맵으로 활용할 수 없습니다.");
    }

    @Override
    public List<Position> infoAsList() {
        throw new IllegalArgumentException("점수는 리스트로 활용할 수 없습니다.");
    }

    @Override
    public Score infoAsScore(final Color color) {
        return totalScore(color);
    }

    private String renderScore() {
        final String whiteScoreVisual = scoreMessage(Color.WHITE, totalScore(Color.WHITE));
        final String blackScoreVisual = scoreMessage(Color.BLACK, totalScore(Color.BLACK));
        return whiteScoreVisual + "\n" + blackScoreVisual;
    }

    private String scoreMessage(final Color color, final Score totalScore) {
        return String.format("%s 의 점수는 %s입니다\n", color.getName(), totalScore.toString());
    }

    private Score totalScore(final Color color) {
        final Score specialScore = specialScore(color, board);
        final Score pawnScore = pawnScore(color, board);

        return specialScore.add(pawnScore);
    }

    private Score specialScore(final Color color, final Board board) {
        return board.remainingSpecialPieces(color)
                .stream()
                .map(Piece::score)
                .reduce(Score.MIN, Score::add);
    }

    private Score pawnScore(final Color color, final Board board) {
        Score score = Score.MIN;
        for (Column column : Column.values()) {
            final int pawnCount = board.pawnCount(column, color);
            if (pawnCount == 1) {
                score = score.add(new Score(PAWN_SCORE));
            }
            if (pawnCount > 1) {
                score = score.add(new Score(DUPLICATED_PAWN_SCORE * pawnCount));
            }
        }
        return score;
    }
}

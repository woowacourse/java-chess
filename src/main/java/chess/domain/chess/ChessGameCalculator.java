package chess.domain.chess;

import chess.domain.chess.vo.ScoreVO;
import chess.domain.piece.Piece;
import chess.domain.piece.Score;
import chess.domain.piece.move.Position;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class ChessGameCalculator {

    private static final Score DEFAULT_SCORE = Score.create(0);
    private static final int PAWN_SUBTRACT_COUNT_THRESHOLD = 1;
    private static final Score PAWN_SPECIAL_SCORE = Score.create(0.5);

    public static ScoreVO calculate(final ChessGame chessGame) {
        final Map<Position, Piece> whiteBoard = chessGame.getWhiteBoard();
        Score whiteScore = calculateScore(whiteBoard);

        final Map<Position, Piece> blackBoard = chessGame.getBlackBoard();
        Score blackScore = calculateScore(blackBoard);

        return new ScoreVO(whiteScore, blackScore);
    }

    private static Score calculateScore(Map<Position, Piece> board) {
        final Map<Integer, List<Piece>> piecesByFile = groupBoardByFile(board);
        Score score = calculateTotalScore(board);
        for (List<Piece> pieces : piecesByFile.values()) {
            final Score subtractScore = getSubtractScoreByPawn(pieces);
            score = score.subtract(subtractScore);
        }
        return score;
    }

    private static Score calculateTotalScore(Map<Position, Piece> board) {
        return board.values().stream()
                .map(Piece::getScore)
                .reduce(DEFAULT_SCORE, Score::add);
    }

    private static Map<Integer, List<Piece>> groupBoardByFile(final Map<Position, Piece> board) {
        return board.entrySet().stream()
                .collect(Collectors.groupingBy(entry -> entry.getKey().getFile(),
                        Collectors.mapping(Map.Entry::getValue, Collectors.toList())
                ));
    }

    private static Score getSubtractScoreByPawn(final List<Piece> pieces) {
        final long pawnCount = getPawnCount(pieces);
        if (pawnCount > PAWN_SUBTRACT_COUNT_THRESHOLD) {
            return PAWN_SPECIAL_SCORE.multiply(pawnCount);
        }
        return DEFAULT_SCORE;
    }

    private static long getPawnCount(final List<Piece> pieces) {
        return pieces.stream().filter(Piece::isPawn).count();
    }
}

package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.square.Square;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class ScoreCalculator {

    public static Score calculateWhiteScore(final Map<Square, Piece> board) {
        final Map<Square, Piece> whiteBoard = getWhitePieces(board);
        final Score score = getDefaultScore(whiteBoard);
        final Score minusScore = getPawnMinusScoreByPosition(getPawnsPosition(whiteBoard));
        return score.minus(minusScore);
    }

    private static Map<Square, Piece> getWhitePieces(final Map<Square, Piece> board) {
        return board.entrySet().stream()
                .filter(entry -> entry.getValue().isWhite())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static Score calculateBlackScore(final Map<Square, Piece> board) {
        final Map<Square, Piece> blackBoard = getBlackPieces(board);
        final Score score = getDefaultScore(blackBoard);
        final Score minusScore = getPawnMinusScoreByPosition(getPawnsPosition(blackBoard));
        return score.minus(minusScore);
    }

    private static Map<Square, Piece> getBlackPieces(final Map<Square, Piece> board) {
        return board.entrySet().stream()
                .filter(entry -> entry.getValue().isBlack())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private static Score getDefaultScore(Map<Square, Piece> colorBoard) {
        return colorBoard.values().stream()
                .map(ScoreCalculator::getPieceScore)
                .reduce(Score.init(), Score::plus);
    }

    private static Score getPieceScore(final Piece piece) {
        final double point = piece.getPieceType().getValue();
        return new Score(point);
    }

    private static List<Square> getPawnsPosition(final Map<Square, Piece> colorBoard) {
        return colorBoard.entrySet().stream()
                .filter(entry -> entry.getValue().isPawn())
                .map(Map.Entry::getKey)
                .collect(Collectors.toUnmodifiableList());
    }

    private static Score getPawnMinusScoreByPosition(final List<Square> squares) {
        Score score = Score.init();
        for (final Square square : squares) {
            score = score.plus(getOnePawnDeductionScore(square, new ArrayList<>(squares)));
        }
        return score;
    }

    private static Score getOnePawnDeductionScore(final Square currentPawn, final List<Square> otherPawns) {
        return otherPawns.stream()
                .filter(targetPawn -> isSpecialPawnScore(currentPawn, targetPawn))
                .findFirst()
                .map(targetPawn -> new Score(PieceType.getPawnSpecialValue()))
                .orElse(Score.init());
    }

    private static boolean isSpecialPawnScore(final Square source, final Square target) {
        return source.isSameFile(target) && !source.isSameRank(target);
    }
}

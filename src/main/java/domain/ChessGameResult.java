package domain;

import domain.piece.Piece;
import domain.piece.PieceType;
import domain.square.File;
import domain.square.Square;

import java.util.Map;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class ChessGameResult {

    private static final Score SAME_FILE_PAWN_SCORE = new Score(0.5);

    private final Score whiteScore;
    private final Score blackScore;
    private final WinStatus winStatus;

    private ChessGameResult(final Score whiteScore, final Score blackScore, final WinStatus winStatus) {
        this.whiteScore = whiteScore;
        this.blackScore = blackScore;
        this.winStatus = winStatus;
    }

    public static ChessGameResult from(final Map<Square, Piece> pieceSquares) {
        final Score whiteScore = sumTotalScore(pieceSquares, Team.WHITE);
        final Score blackScore = sumTotalScore(pieceSquares, Team.BLACK);

        final WinStatus winStatus = WinStatus.of(whiteScore, blackScore);

        return new ChessGameResult(whiteScore, blackScore, winStatus);
    }

    private static Score sumTotalScore(final Map<Square, Piece> pieceSquares, final Team team) {
        if (isKingDead(pieceSquares, team)) {
            return new Score(0);
        }

        final Score originalScoreSum = sumOriginalScore(pieceSquares, team);
        final int sameFilePawnCount = countSameFilePawn(pieceSquares, team);

        return originalScoreSum.subtract(SAME_FILE_PAWN_SCORE.multiply(sameFilePawnCount));
    }

    private static boolean isKingDead(final Map<Square, Piece> pieceSquares, final Team team) {
        return pieceSquares.values().stream()
                .noneMatch(piece -> piece.getPieceType() == PieceType.KING && piece.isTeam(team));
    }

    private static Score sumOriginalScore(final Map<Square, Piece> pieceSquares, final Team team) {
        return pieceSquares.values().stream()
                .filter(piece -> piece.isTeam(team))
                .map(Piece::getScore)
                .reduce(new Score(0), Score::sum);
    }

    private static int countSameFilePawn(final Map<Square, Piece> pieceSquares, final Team team) {
        final Map<File, Long> pawns = countPawnByFile(pieceSquares, team);

        return pawns.values().stream()
                .filter(count -> count > 1L)
                .mapToInt(Long::intValue)
                .sum();
    }

    private static Map<File, Long> countPawnByFile(final Map<Square, Piece> pieceSquares, final Team team) {
        return pieceSquares.entrySet().stream()
                .filter(entry -> entry.getValue().isTeam(team)
                        && entry.getValue().getPieceType() == PieceType.PAWN)
                .collect(groupingBy(entry -> entry.getKey().getFile(), counting()));
    }

    public double getWhiteScore() {
        return whiteScore.toDouble();
    }

    public double getBlackScore() {
        return blackScore.toDouble();
    }

    public WinStatus getWinStatus() {
        return winStatus;
    }
}

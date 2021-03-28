package chess.domain.result;

import chess.domain.piece.Piece;
import chess.domain.team.Team;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ScoreResult implements Result {

    private final static double BASIC_SCORE_CRITERION = 1;
    private final static double PAWN_SAME_COL_SCORE = 0.5;

    private final double blackScore;
    private final double whiteScore;

    private ScoreResult(List<Piece> pieces) {
        this.blackScore = score(pieces, Team.BLACK);
        this.whiteScore = score(pieces, Team.WHITE);
    }

    public static ScoreResult yield(List<Piece> pieces) {
        return new ScoreResult(pieces);
    }

    private double score(List<Piece> pieces, Team team) {
        return scoreExceptPawn(pieces, team) + scorePawn(pieces, team);
    }

    private double scoreExceptPawn(List<Piece> pieces, Team team) {
        return pieces
            .stream()
            .filter(piece -> piece.isSameTeam(team))
            .filter(piece -> !piece.isPawn())
            .mapToDouble(piece -> piece.getPieceType().getScore())
            .sum();
    }

    private double scorePawn(List<Piece> pieces, Team team) {
        final Map<Integer, Long> frequencyPerX = pieces
            .stream()
            .filter(piece -> piece.isSameTeam(team))
            .filter(Piece::isPawn)
            .collect(Collectors.groupingBy(Piece::getX, Collectors.counting()));

        return frequencyPerX
            .values()
            .stream()
            .mapToDouble(count -> count <= BASIC_SCORE_CRITERION ?
                count :
                count * PAWN_SAME_COL_SCORE)
            .sum();
    }

    public double getBlackScore() {
        return blackScore;
    }

    public double getWhiteScore() {
        return whiteScore;
    }
}

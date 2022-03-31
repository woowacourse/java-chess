package domain.classification;

import domain.board.ChessBoard;
import domain.piece.property.PieceFeature;
import domain.piece.property.Team;
import domain.piece.unit.Piece;
import domain.position.Position;
import domain.position.XPosition;
import domain.position.YPosition;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public enum Result {

    WIN,
    LOSE,
    DRAW;

    private static final int PAWN_DUPLICATE_CONDITION_FOR_SCORE = 2;

    public static Result calculateWinner(ChessBoard chessBoard) {
        final double currentTeamScore = Result.calculateTeamScore(chessBoard.getBoard(), chessBoard.getCurrentTurn());
        final double opponentTeamScore = Result.calculateTeamScore(chessBoard.getBoard(), chessBoard.getOpponentTeam());

        return Result.competeScore(currentTeamScore, opponentTeamScore);
    }

    public static double calculateTeamScore(final Map<Position, Piece> board, final Team team) {
        double sum = 0;
        for (XPosition x : XPosition.values()) {
            List<Piece> pieces = calculateTeamPiecesByXPosition(board, team, x);
            sum += calculateXPositionScore(pieces);
        }
        return sum;
    }

    private static Result competeScore(final double currentTeamScore, final double opponentTeamScore) {
        if (currentTeamScore > opponentTeamScore) {
            return Result.WIN;
        }
        if (currentTeamScore < opponentTeamScore) {
            return Result.LOSE;
        }
        return Result.DRAW;
    }

    private static List<Piece> calculateTeamPiecesByXPosition(final Map<Position, Piece> board, final Team team,
                                                              final XPosition x) {
        return Arrays.stream(YPosition.values())
                .map(yPosition -> board.get(Position.of(x, yPosition)))
                .filter(piece -> piece != null)
                .filter(piece -> piece.checkSameTeam(team))
                .collect(Collectors.toList());
    }

    private static double calculateXPositionScore(final List<Piece> pieces) {
        List<Double> scores = new ArrayList<>();
        pieces.forEach(piece -> scores.add(PieceFeature.createScore(piece.symbol(), checkDuplicatePawn(pieces))));

        return scores.stream()
                .filter(score -> score != null)
                .mapToDouble(Double::doubleValue).sum();
    }

    private static boolean checkDuplicatePawn(List<Piece> pieces) {
        return pieces.stream()
                .filter(piece -> piece.symbol().equals(PieceFeature.PAWN.symbol()))
                .count() >= PAWN_DUPLICATE_CONDITION_FOR_SCORE;
    }
}

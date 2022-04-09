package chess.dto;

import chess.domain.board.ChessBoard;
import chess.domain.classification.Result;
import chess.domain.piece.property.PieceFeature;
import chess.domain.piece.property.Team;
import chess.domain.piece.unit.Piece;
import chess.domain.position.Position;
import chess.domain.position.XPosition;
import chess.domain.position.YPosition;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class GameStatus {

    private static final int PAWN_DUPLICATE_CONDITION_FOR_SCORE = 2;

    private final Result result;
    private final Team turn;
    private final double score;

    public GameStatus(final ChessBoard chessBoard) {
        this.result = calculateWinner(chessBoard);
        this.turn = chessBoard.getCurrentTurn();
        this.score = calculateTeamScore(chessBoard.getBoard(), turn);
    }

    public static Result calculateWinner(final ChessBoard chessBoard) {
        final double currentTeamScore = calculateTeamScore(chessBoard.getBoard(), chessBoard.getCurrentTurn());
        final double opponentTeamScore = calculateTeamScore(chessBoard.getBoard(), chessBoard.getOpponentTeam());

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
        for (Piece piece : pieces) {
            scores.add(PieceFeature.createScore(piece.symbol(), checkDuplicatePawn(pieces)));
        }

        return scores.stream()
                .filter(score -> score != null)
                .mapToDouble(Double::doubleValue).sum();
    }

    private static boolean checkDuplicatePawn(final List<Piece> pieces) {
        return pieces.stream()
                .filter(piece -> piece.symbol().equals(PieceFeature.PAWN.symbol()))
                .count() >= PAWN_DUPLICATE_CONDITION_FOR_SCORE;
    }

    public Result getResult() {
        return result;
    }

    public Team getTurn() {
        return turn;
    }

    public double getScore() {
        return score;
    }
}

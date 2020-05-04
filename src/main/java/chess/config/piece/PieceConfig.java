package chess.config.piece;

import chess.domain.piece.policy.move.CanNotMoveStrategy;
import chess.domain.piece.team.Team;
import chess.domain.piece.policy.score.CalculateScoreStrategy;
import chess.domain.position.InitialColumn;

public class PieceConfig {
    public static String getName(InitialColumn initialColumn, Team team) {
        Piece piece = Piece.valueOf(initialColumn);
        return piece.getName(team);
    }

    public static CanNotMoveStrategy getPawnCanNotMoveStrategy(Team team) {
        return Piece.PAWN.getCanNotMoveStrategy(team);
    }

    public static CalculateScoreStrategy getPawnScoreCalculator() {
        return Piece.PAWN.getCalculateScoreStrategy();
    }

    public static CalculateScoreStrategy getCalculateScoreStrategy(InitialColumn initialColumn) {
        Piece piece = Piece.valueOf(initialColumn);

        return piece.getCalculateScoreStrategy();
    }

    public static CanNotMoveStrategy getCanNotMoveStrategy(InitialColumn initialColumn, Team team) {
        Piece piece = Piece.valueOf(initialColumn);
        return piece.getCanNotMoveStrategy(team);
    }

    public static String getPawnName(Team team) {
        return Piece.PAWN.getName(team);
    }

    public static String getKingName(Team team) {
        return Piece.KING.getName(team);
    }
}

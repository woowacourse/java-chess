package chess.domain.piece.factory;

import chess.config.piece.PieceConfig;
import chess.domain.piece.Piece;
import chess.domain.piece.policy.move.CanNotMoveStrategy;
import chess.domain.piece.policy.score.CalculateScoreStrategy;
import chess.domain.piece.team.Team;
import chess.domain.position.InitialColumn;

public class PieceFactory {
    public static Piece createPieceWithInitialColumn(int initialColumn, Team team) {
        return createPieceWithInitialColumn(InitialColumn.valueOf(initialColumn), team);
    }

    public static Piece createPieceWithInitialColumn(InitialColumn initialColumn, Team team) {
        String name = PieceConfig.getName(initialColumn, team);
        CanNotMoveStrategy canNotMoveStrategy = PieceConfig.getCanNotMoveStrategy(initialColumn, team);
        CalculateScoreStrategy calculateScoreStrategy = PieceConfig.getCalculateScoreStrategy(initialColumn);
        return new Piece(team, name, canNotMoveStrategy, calculateScoreStrategy);

    }

    public static Piece createInitializedPawn(Team team) {
        String name = PieceConfig.getName(InitialColumn.PAWN, team);
        CanNotMoveStrategy pawnCanNotMoveStrategy = PieceConfig.getPawnCanNotMoveStrategy(team);
        CalculateScoreStrategy pawnScoreCalculator = PieceConfig.getPawnScoreCalculator();
        return new Piece(team, name, pawnCanNotMoveStrategy, pawnScoreCalculator);
    }
}

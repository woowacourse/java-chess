package chess.config.piece;

import chess.domain.policy.score.DefaultScoreCalculator;
import chess.domain.policy.score.PawnScoreCalculator;
import chess.domain.piece.policy.move.CanNotMoveStrategy;
import chess.domain.piece.score.Score;
import chess.domain.piece.team.Team;
import chess.domain.piece.policy.score.CalculateScoreStrategy;
import chess.domain.policy.move.*;
import chess.domain.policy.move.PawnCanNotMoveStrategy;
import chess.domain.position.InitialColumn;

import java.util.Arrays;

enum Piece {
    PAWN(InitialColumn.PAWN, (Team team) -> Team.convertName("p", team), PawnCanNotMoveStrategy::new, new PawnScoreCalculator()),
    ROOK(InitialColumn.ROOK, (Team team) -> Team.convertName("r", team), ignored -> new RookCanNotMoveStrategy(), Score.of(5)),
    KNIGHT(InitialColumn.KNIGHT, (Team team) -> Team.convertName("n", team), ignored -> new KnightCanNotMoveStrategy(), Score.of(2.5)),
    BISHOP(InitialColumn.BISHOP, (Team team) -> Team.convertName("b", team), ignored -> new BishopCanNotMoveStrategy(), Score.of(3)),
    QUEEN(InitialColumn.QUEEN, (Team team) -> Team.convertName("q", team), ignored -> new QueenCanNotMoveStrategy(), Score.of(9)),
    KING(InitialColumn.KING, (Team team) -> Team.convertName("k", team), ignored -> new KingCanNotMoveStrategy(), Score.zero());

    private final InitialColumn initialColumn;
    private final NameCreator nameCreator;
    private final CanNotMoveStrategyCreator canNotMoveStrategyCreator;
    private final CalculateScoreStrategy calculateScoreStrategy;

    Piece(InitialColumn initialColumn, NameCreator nameCreator, CanNotMoveStrategyCreator canNotMoveStrategyCreator, Score score) {
        this.initialColumn = initialColumn;
        this.nameCreator = nameCreator;
        this.canNotMoveStrategyCreator = canNotMoveStrategyCreator;
        this.calculateScoreStrategy = new DefaultScoreCalculator(score);
    }

    Piece(InitialColumn initialColumn, NameCreator nameCreator, CanNotMoveStrategyCreator canNotMoveStrategyCreator, CalculateScoreStrategy calculateScoreStrategy) {
        this.initialColumn = initialColumn;
        this.nameCreator = nameCreator;
        this.canNotMoveStrategyCreator = canNotMoveStrategyCreator;
        this.calculateScoreStrategy = calculateScoreStrategy;
    }

    static Piece valueOf(InitialColumn initialColumn) {
        return Arrays.stream(values())
                .filter(piece -> piece.initialColumn == initialColumn)
                .findAny()
                //todo: refac
                .orElseThrow(() -> new IllegalArgumentException("해당하는 체스 말이 없습니다."));
    }

    String getName(Team team) {
        return nameCreator.create(team);
    }

    CanNotMoveStrategy getCanNotMoveStrategy(Team team) {
        return canNotMoveStrategyCreator.create(team);
    }

    CalculateScoreStrategy getCalculateScoreStrategy() {
        return calculateScoreStrategy;
    }

    private interface NameCreator {
        String create(Team team);

    }
    private interface CanNotMoveStrategyCreator {
        CanNotMoveStrategy create(Team team);

    }
}

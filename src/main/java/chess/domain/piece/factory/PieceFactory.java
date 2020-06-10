package chess.domain.piece.factory;

import chess.domain.piece.Piece;
import chess.domain.piece.score.Score;
import chess.domain.piece.team.Team;
import chess.domain.policy.move.*;
import chess.domain.policy.score.DefaultScoreCalculator;
import chess.domain.policy.score.PawnScoreCalculator;
import chess.domain.position.InitialColumn;

import java.util.HashMap;
import java.util.Map;

public class PieceFactory {

    private static Map<InitialColumn, PieceCreator> pieceCreators = new HashMap<>();

    static {
        pieceCreators.put(InitialColumn.ROOK,
                (Team team) -> new Piece(team,
                        Team.convertName("r", team),
                        new RookCanNotMoveStrategy(),
                        new DefaultScoreCalculator(Score.of(5))));
        pieceCreators.put(InitialColumn.KNIGHT,
                (Team team) -> new Piece(team,
                        Team.convertName("n", team),
                        new KnightCanNotMoveStrategy(),
                        new DefaultScoreCalculator(Score.of(2.5))));
        pieceCreators.put(InitialColumn.BISHOP,
                (Team team) -> new Piece(team,
                        Team.convertName("b", team),
                        new BishopCanNotMoveStrategy(),
                        new DefaultScoreCalculator(Score.of(3))));
        pieceCreators.put(InitialColumn.QUEEN,
                (Team team) -> new Piece(team,
                        Team.convertName("q", team),
                        new QueenCanNotMoveStrategy(),
                        new DefaultScoreCalculator(Score.of(9))));
        pieceCreators.put(InitialColumn.KING,
                (Team team) -> new Piece(team,
                        Team.convertName("k", team),
                        new KingCanNotMoveStrategy(),
                        new DefaultScoreCalculator(Score.zero())));
    }

    private interface PieceCreator {
        Piece create(Team team);
    }

    public static Piece createPieceWithInitialColumn(int initialColumn, Team team) {
        return createPieceWithInitialColumn(InitialColumn.valueOf(initialColumn), team);
    }

    public static Piece createPieceWithInitialColumn(InitialColumn initialColumn, Team team) {
        PieceCreator pieceCreator = pieceCreators.get(initialColumn);
        return pieceCreator.create(team);

    }

    public static Piece createInitializedPawn(Team team) {
        return new Piece(team, Team.convertName("p", team), new PawnCanNotMoveStrategy(team), new PawnScoreCalculator());
    }
}

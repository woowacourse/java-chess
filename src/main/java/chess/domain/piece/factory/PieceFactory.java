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

    private static Map<InitialColumn, PieceCreator> pieceCreatorsWithInitialColumn = new HashMap<>();
    private static Map<String, PieceCreator> pieceCreatorsWithName = new HashMap<>();


    static {
        pieceCreatorsWithName.put("r",
                (Team team) -> new Piece(team,
                        Team.convertName("r", team),
                        new RookCanNotMoveStrategy(),
                        new DefaultScoreCalculator(Score.of(5))));

        pieceCreatorsWithName.put("n",
                (Team team) -> new Piece(team,
                        Team.convertName("n", team),
                        new KnightCanNotMoveStrategy(),
                        new DefaultScoreCalculator(Score.of(2.5))));

        pieceCreatorsWithName.put("b",
                (Team team) -> new Piece(team,
                        Team.convertName("b", team),
                        new BishopCanNotMoveStrategy(),
                        new DefaultScoreCalculator(Score.of(3))));

        pieceCreatorsWithName.put("q",
                (Team team) -> new Piece(team,
                        Team.convertName("q", team),
                        new QueenCanNotMoveStrategy(),
                        new DefaultScoreCalculator(Score.of(9))));

        pieceCreatorsWithName.put("k",
                (Team team) -> new Piece(team,
                        Team.convertName("k", team),
                        new KingCanNotMoveStrategy(),
                        new DefaultScoreCalculator(Score.zero())));

        pieceCreatorsWithName.put("p",
                (Team team) -> new Piece(team,
                        Team.convertName("p", team),
                        new PawnCanNotMoveStrategy(team),
                        new PawnScoreCalculator()));
        pieceCreatorsWithInitialColumn.put(InitialColumn.ROOK,
                (Team team) -> new Piece(team,
                        Team.convertName("r", team),
                        new RookCanNotMoveStrategy(),
                        new DefaultScoreCalculator(Score.of(5))));
        pieceCreatorsWithInitialColumn.put(InitialColumn.KNIGHT,
                (Team team) -> new Piece(team,
                        Team.convertName("n", team),
                        new KnightCanNotMoveStrategy(),
                        new DefaultScoreCalculator(Score.of(2.5))));
        pieceCreatorsWithInitialColumn.put(InitialColumn.BISHOP,
                (Team team) -> new Piece(team,
                        Team.convertName("b", team),
                        new BishopCanNotMoveStrategy(),
                        new DefaultScoreCalculator(Score.of(3))));
        pieceCreatorsWithInitialColumn.put(InitialColumn.QUEEN,
                (Team team) -> new Piece(team,
                        Team.convertName("q", team),
                        new QueenCanNotMoveStrategy(),
                        new DefaultScoreCalculator(Score.of(9))));
        pieceCreatorsWithInitialColumn.put(InitialColumn.KING,
                (Team team) -> new Piece(team,
                        Team.convertName("k", team),
                        new KingCanNotMoveStrategy(),
                        new DefaultScoreCalculator(Score.zero())));
    }

    public static Piece createPiece(String name, Team team) {
        PieceCreator pieceCreator = pieceCreatorsWithName.get(name.toLowerCase());
        return pieceCreator.create(team);
    }

    private interface PieceCreator {
        Piece create(Team team);
    }

    public static Piece createPieceWithInitialColumn(int initialColumn, Team team) {
        return createPieceWithInitialColumn(InitialColumn.valueOf(initialColumn), team);
    }

    public static Piece createPieceWithInitialColumn(InitialColumn initialColumn, Team team) {
        PieceCreator pieceCreator = pieceCreatorsWithInitialColumn.get(initialColumn);
        return pieceCreator.create(team);

    }

    public static Piece createInitializedPawn(Team team) {
        return new Piece(team, Team.convertName("p", team), new PawnCanNotMoveStrategy(team), new PawnScoreCalculator());
    }
}

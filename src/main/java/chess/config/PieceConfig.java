package chess.config;

import chess.domain.piece.PiecesState;
import chess.domain.piece.position.InitialColumn;
import chess.domain.piece.policy.move.*;
import chess.domain.piece.position.Position;
import chess.domain.piece.score.Score;
import chess.domain.piece.team.Team;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PieceConfig {
    public static String getName(InitialColumn initialColumn, Team team) {
        Piece piece = Piece.valueOf(initialColumn);
        return piece.getName(team);
    }

    public static CanNotMoveStrategy getCanNotMoveStrategie(InitialColumn initialColumn) {
        Piece piece = Piece.valueOf(initialColumn);
        return piece.getCanNotMoveStrategies();
    }

    public static List<Score> getScores(InitialColumn initialColumn) {
        Piece piece = Piece.valueOf(initialColumn);
        return piece.getScores();
    }

    private enum Piece {
        PAWN(InitialColumn.PAWN, (Team team) -> assignName("p", team), Arrays.asList(new Score(0.5), new Score(1))),
        ROOK(InitialColumn.ROOK, (Team team) -> assignName("r", team), Collections.singletonList(new Score(5))),
        KNIGHT(InitialColumn.KNIGHT, (Team team) -> assignName("n", team), Collections.singletonList(new Score(2.5))),
        BISHOP(InitialColumn.BISHOP, (Team team) -> assignName("b", team), Collections.singletonList(new Score(3))),
        QUEEN(InitialColumn.QUEEN, (Team team) -> assignName("q", team), Collections.singletonList(new Score(9))),
        KING(InitialColumn.KING, (Team team) -> assignName("k", team), Collections.singletonList(new Score(0)));


        private final InitialColumn initialColumn;
        private final Name name;
        private final List<Score> scores;
//        private final MoveStrategy moveStrategy;

        private interface Name {
            String get(Team team);
        }

        private interface MoveStrategy {
            CanNotMoveStrategy get(Team team);
        }

        Piece(InitialColumn initialColumn, Name name, List<Score> scores) {
            this.initialColumn = initialColumn;
            this.name = name;
            this.scores = scores;
        }

        private static Piece valueOf(InitialColumn initialColumn) {
            return Arrays.stream(values())
                    .filter(piece -> piece.initialColumn == initialColumn)
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("해당 칼럼에 해당하는 체스 말을 찾을 수 없습니다."));
        }

        private static String assignName(String name, Team team) {
            return Team.convertName(name, team);
        }

        private String getName(Team team) {
            return name.get(team);
        }

        private List<Score> getScores() {
            return scores;
        }

        private CanNotMoveStrategy getCanNotMoveStrategies() {
            return null;
        }

        private static abstract class MultipleCanNotMoveStrategies implements CanNotMoveStrategy {
            private final List<CanNotMoveStrategy> canNotMoveStrategies;

            private MultipleCanNotMoveStrategies(List<CanNotMoveStrategy> canNotMoveStrategies) {
                this.canNotMoveStrategies = canNotMoveStrategies;
            }

            @Override
            public boolean canNotMove(Position from, Position to, PiecesState piecesState) {
                return canNotMoveStrategies.stream()
                        .anyMatch(canNotMoveStrategy -> canNotMoveStrategy.canNotMove(from, to, piecesState));
            }
        }
    }
}

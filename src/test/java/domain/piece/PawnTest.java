package domain.piece;

<<<<<<< HEAD
import static domain.game.File.A;
import static domain.game.File.B;
import static domain.game.File.C;
import static domain.game.Rank.FIVE;
import static domain.game.Rank.FOUR;
import static domain.game.Rank.ONE;
import static domain.game.Rank.SEVEN;
import static domain.game.Rank.SIX;
import static domain.game.Rank.THREE;
import static domain.game.Rank.TWO;
import static org.assertj.core.api.Assertions.assertThat;

import domain.game.File;
import domain.game.Position;
import domain.game.Rank;
import domain.game.Score;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
=======
import org.junit.jupiter.api.DisplayName;
>>>>>>> 3ad1dbf (refactor: 패키지 분리)
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

<<<<<<< HEAD
@DisplayName("Pawn은")
public class PawnTest {

    @Nested
    @DisplayName("White 진영의 경우")
    class White {
        Pawn whitePawn = Pawn.createOfWhite();

        @Nested
        @DisplayName("비어 있는 곳으로 이동할 때")
        class MoveToEmptyPiece {

            @DisplayName("a2에서 a3로 이동하면 true를 반환한다.")
            @Test
            void shouldReturnIfIsMovableToTargetPositionWhenPawnIsWhiteSide() {
                assertThat(whitePawn.isMovable(new EmptyPiece(), Position.of(A, TWO), Position.of(A, THREE))).isTrue();
            }

            @DisplayName("처음 움직이면서, Target position rank - Source position rank가 2 이하이면 true를, 아니면 false를 반환한다.")
            @ParameterizedTest
            @CsvSource(value = {"TWO,FOUR,true", "TWO,THREE,true", "TWO,FIVE,false"})
            void shouldReturnTrueWhenWhitePawnMoveUnderTwoStepOrFalse(
                    Rank sourceRank,
                    Rank targetRank,
                    boolean result) {
                boolean movable = whitePawn.isMovable(new EmptyPiece(), Position.of(A, sourceRank),
                        Position.of(A, targetRank));
                assertThat(movable).isEqualTo(result);
            }
        }

        @DisplayName("위쪽 대각선에 상대편 말이 있는 경우 true를, 아니라면 false를 반환한다.")
        @ParameterizedTest
        @CsvSource(value = {"C,TWO,B,THREE,true", "C,TWO,D,THREE,true", "C,TWO,C,THREE,false"})
        void shouldReturnTrueWhenMoveToOpponentPieceWhitePawn(
                File sourceFile, Rank sourceRank,
                File targetFile, Rank targetRank,
                boolean result) {
            boolean movable = whitePawn.isMovable(
                    Pawn.createOfBlack(),
                    Position.of(sourceFile, sourceRank),
                    Position.of(targetFile, targetRank));
            assertThat(movable).isEqualTo(result);
        }

        @DisplayName("White piece와 동일한 팀이라면 true를 반환한다.")
        @Test
        void shouldReturnIfSameSideWhenTargetIsWhiteSideAndSourceSideIsWhite() {
            assertThat(whitePawn.isSameSideWith(Pawn.createOfWhite())).isEqualTo(true);
        }

        @DisplayName("Black piece와 다른 팀이라면 false를 반환한다.")
        @Test
        void shouldReturnIfDifferentSideWhenTargetIsWhiteSideAndSourceSideIsBlack() {
            assertThat(whitePawn.isSameSideWith(Pawn.createOfBlack())).isEqualTo(false);
        }

        @DisplayName("Black piece의 상대 팀이라면 true를 반환한다.")
        @Test
        void shouldReturnTrueWhenTargetPieceIsOpponentSide() {
            assertThat(whitePawn.isOpponentSideWith(Pawn.createOfBlack())).isEqualTo(true);
        }

        @DisplayName("White piece의 같은 팀이라면 false를 반환한다.")
        @Test
        void shouldReturnFalseWhenTargetPieceIsSameSide() {
            assertThat(whitePawn.isOpponentSideWith(Pawn.createOfWhite())).isEqualTo(false);
        }

        @Nested
        @DisplayName("이동 경로를 구할 때")
        class collectPath {

            @DisplayName("위로 한 칸 이동하면, 이동 경로는 위치를 0개 가진다.")
            @Test
            void shouldHasNoPositionWhenGetPathWhitePawn() {
                List<Position> path = whitePawn.collectPath(Position.of(B, TWO), Position.of(B, THREE));
                assertThat(path).isEmpty();
            }

            @DisplayName("위로 두 칸 이동하면, 이동 경로는 위치를 1개 가진다.")
            @Test
            void shouldReturnPathWhenWhitePawnMoveTwoSteps() {
                List<Position> path = whitePawn.collectPath(Position.of(B, TWO), Position.of(B, FOUR));
                assertThat(path).containsExactlyInAnyOrder(Position.of(B, THREE));
            }

            @DisplayName("오른쪽 위로 한 칸 이동하면, 이동 경로는 위치를 1개 가진다.")
            @Test
            void shouldReturnPathWhenWhitePawnMoveRightUpward() {
                List<Position> path = whitePawn.collectPath(Position.of(B, TWO), Position.of(C, THREE));
                assertThat(path).isEmpty();
            }

            @DisplayName("왼쪽 위로 한 칸 이동하면, 이동 경로는 위치를 1개 가진다.")
            @Test
            void shouldReturnPathWhenWhitePawnMoveLeftUpward() {
                Pawn pawn = Pawn.createOfWhite();
                List<Position> path = pawn.collectPath(Position.of(B, TWO), Position.of(A, ONE));
                assertThat(path).isEmpty();
            }
        }

        @DisplayName("점수를 요청하면 1점을 반환한다.")
        @Test
        void shouldReturnScoreOf1WhenRequestScore() {
            Pawn whitePawn = Pawn.createOfWhite();
            assertThat(whitePawn.getScore()).isEqualTo(new Score(1));
        }
    }

    @Nested
    @DisplayName("Black 진영의 경우")
    class Black {
        Pawn blackPawn = Pawn.createOfBlack();

        @Nested
        @DisplayName("비어 있는 곳으로 이동할 때")
        class MoveToEmptyPiece {

            @DisplayName("a7에서 a6로 이동하면 true를 반환한다.")
            @Test
            void shouldReturnIfIsMovableToTargetPositionWhenPawnIsBlackSide() {
                assertThat(blackPawn.isMovable(new EmptyPiece(), Position.of(A, SEVEN), Position.of(A, SIX))).isTrue();
            }

            @DisplayName("처음 움직이면서, Source position rank - Target position rank가 2 이하이면 true를, 아니면 false를 반환한다.")
            @ParameterizedTest
            @CsvSource(value = {"SEVEN,FIVE,true", "SEVEN,SIX,true", "SEVEN,FOUR,false"})
            void shouldReturnTrueWhenBlackPawnMoveUnderTwoStepOrFalse(
                    Rank sourceRank,
                    Rank targetRank,
                    boolean result) {
                boolean movable = blackPawn.isMovable(new EmptyPiece(), Position.of(A, sourceRank),
                        Position.of(A, targetRank));
                assertThat(movable).isEqualTo(result);
            }
        }

        @DisplayName("아래쪽 대각선에 상대편 말이 있는 경우 true를, 아니라면 false를 반환한다.")
        @ParameterizedTest
        @CsvSource(value = {"C,SEVEN,B,SIX,true", "C,SEVEN,D,SIX,true", "C,SEVEN,C,SIX,false"})
        void shouldReturnTrueWhenMoveToOpponentPieceBlackPawn(
                File sourceFile, Rank sourceRank,
                File targetFile, Rank targetRank,
                boolean result) {
            boolean movable = blackPawn.isMovable(
                    Pawn.createOfWhite(),
                    Position.of(sourceFile, sourceRank),
                    Position.of(targetFile, targetRank));
            assertThat(movable).isEqualTo(result);
        }

        @DisplayName("Black piece와 동일한 팀이라면 true를 반환한다.")
        @Test
        void shouldReturnIfSameSideWhenTargetIsBlackSideAndSourceSideIsBlack() {
            assertThat(blackPawn.isSameSideWith(Pawn.createOfBlack())).isEqualTo(true);
        }

        @DisplayName("White piece와 다른 팀이라면 false를 반환한다.")
        @Test
        void shouldReturnIfDifferentSideWhenTargetIsWhiteSideAndSourceSideIsBlack() {
            assertThat(blackPawn.isSameSideWith(Pawn.createOfWhite())).isEqualTo(false);
        }

        @DisplayName("White piece의 상대 팀이라면 true를 반환한다.")
        @Test
        void shouldReturnTrueWhenTargetPieceIsOpponentSide() {
            assertThat(blackPawn.isOpponentSideWith(Pawn.createOfWhite())).isEqualTo(true);
        }

        @DisplayName("White piece의 같은 팀이라면 false를 반환한다.")
        @Test
        void shouldReturnFalseWhenTargetPieceIsSameSide() {
            assertThat(blackPawn.isOpponentSideWith(Pawn.createOfBlack())).isEqualTo(false);
        }

        @Nested
        @DisplayName("이동 경로를 구할 때")
        class collectPath {

            @DisplayName("아래로 한 칸 이동하면, 이동 경로는 위치를 0개 가진다.")
            @Test
            void shouldHasNoPositionWhenGetPath() {
                List<Position> path = blackPawn.collectPath(Position.of(B, SEVEN), Position.of(B, SIX));
                assertThat(path).isEmpty();
            }

            @DisplayName("아래로 두 칸 이동하면, 이동 경로는 위치를 1개 가진다.")
            @Test
            void shouldReturnPathWhenBlackPawnMoveTwoSteps() {
                List<Position> path = blackPawn.collectPath(Position.of(B, SEVEN), Position.of(B, FIVE));
                assertThat(path).containsExactlyInAnyOrder(Position.of(B, SIX));
            }

            @DisplayName("오른쪽 아래로 한 칸 이동하면, 이동 경로는 위치를 1개 가진다.")
            @Test
            void shouldReturnPathWhenBlackPawnMoveRightDownward() {
                List<Position> path = blackPawn.collectPath(Position.of(B, SEVEN), Position.of(C, SIX));
                assertThat(path).isEmpty();
            }

            @DisplayName("왼쪽 아래로 한 칸 이동하면, 이동 경로는 위치를 1개 가진다.")
            @Test
            void shouldReturnPathWhenBlackPawnMoveLeftUpward() {
                Pawn pawn = Pawn.createOfWhite();
                List<Position> path = pawn.collectPath(Position.of(B, SEVEN), Position.of(A, SIX));
                assertThat(path).isEmpty();
            }
        }

        @DisplayName("점수를 요청하면 1점을 반환한다.")
        @Test
        void shouldReturnScoreOf1WhenRequestScore() {
            Pawn blackPawn = Pawn.createOfBlack();
            assertThat(blackPawn.getScore()).isEqualTo(new Score(1));
        }
=======
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PawnTest {

    @DisplayName("White 진영인 경우 - target position이 source position보다 rank가 1높고, target position이 비어있으면 true를 반환한다.")
    @Test
    void shouldReturnIfIsMovableToTargetPositionWhenPawnIsWhiteSide() {
        Pawn whitePawn = Pawn.createOfWhite();
        boolean movable = whitePawn.isMovable(new EmptyPiece(), Position.of("a", "2"), Position.of("a", "3"));
        assertThat(movable).isTrue();
    }

    @DisplayName("Black 진영인 경우 - target position이 source position보다 rank가 1낮고, target position이 비어있으면 true를 반환한다.")
    @Test
    void shouldReturnIfIsMovableToTargetPositionWhenPawnIsBlackSide() {
        Pawn blackPawn = Pawn.createOfBlack();
        boolean movable = blackPawn.isMovable(new EmptyPiece(), Position.of("a", "7"), Position.of("a", "6"));
        assertThat(movable).isTrue();
    }

    @DisplayName("White 진영인 경우 - 처음 움직일 때 target position이 source position의 rank보다 2높으면 true를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"2, 4, true", "2,3,true", "2,5,false", "3,4,true", "3,5,false"})
    void shouldReturnIfIsMovableTwoStepTargetPositionWhenPawnIsWhiteSideAndFirstMoving(String sourceRank, String targetRank, boolean result) {
        Pawn whitePawn = Pawn.createOfWhite();
        boolean movable = whitePawn.isMovable(new EmptyPiece(), Position.of("a", sourceRank), Position.of("a", targetRank));
        assertThat(movable).isEqualTo(result);
    }

    @DisplayName("Black 진영인 경우 - 처음 움직일 때 target position이 source position의 rank보다 2낮으면 true를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"7, 5, true", "7,6,true", "7,4,false", "6,5,true", "6,4,false"})
    void shouldReturnIfIsMovableTwoStepTargetPositionWhenPawnIsBlackSideAndFirstMoving(String sourceRank, String targetRank, boolean result) {
        Pawn blackPawn = Pawn.createOfBlack();
        boolean movable = blackPawn.isMovable(new EmptyPiece(), Position.of("a", sourceRank), Position.of("a", targetRank));
        assertThat(movable).isEqualTo(result);
    }

    @DisplayName("targetPiece와 동일한 팀이라면 true를 반환한다.")
    @Test
    void shouldReturnIfSameSideWhenTargetIsWhiteSideAndSourceSideIsWhite() {
        Pawn sourcePawn = Pawn.createOfWhite();
        Pawn targetPawn = Pawn.createOfWhite();

        assertThat(sourcePawn.isSameSideWith(targetPawn)).isEqualTo(true);
    }

    @DisplayName("targetPiece와 다른 팀이라면 false를 반환한다.")
    @Test
    void shouldReturnIfDifferentSideWhenTargetIsWhiteSideAndSourceSideIsBlack() {
        Pawn sourcePawn = Pawn.createOfWhite();
        Pawn targetPawn = Pawn.createOfBlack();

        assertThat(sourcePawn.isSameSideWith(targetPawn)).isEqualTo(false);
    }

    @DisplayName("targetPiece의 상대 팀이라면 true를 반환한다.")
    @Test
    void shouldReturnTrueWhenTargetPieceIsOpponentSide() {
        Pawn sourcePawn = Pawn.createOfWhite();
        Pawn targetPawn = Pawn.createOfBlack();

        assertThat(sourcePawn.isOpponentSideWith(targetPawn)).isEqualTo(true);
    }

    @DisplayName("targetPiece의 같은 팀이라면 false를 반환한다.")
    @Test
    void shouldReturnFalseWhenTargetPieceIsSameSide() {
        Pawn sourcePawn = Pawn.createOfWhite();
        Pawn targetPawn = Pawn.createOfWhite();

        assertThat(sourcePawn.isOpponentSideWith(targetPawn)).isEqualTo(false);
    }

    @DisplayName("White 진영인 경우 - 위쪽 대각선에 상대편 말이 있는 경우 true를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"c,2,b,3,true", "c,2,d,3,true", "c,2,c,3,false"})
    void shouldReturnTrueWhenMoveToOpponentPieceWhitePawn(
            String sourceFile, String sourceRank,
            String targetFile, String targetRank,
            boolean result) {
        Pawn sourcePawn = Pawn.createOfWhite();
        boolean movable = sourcePawn.isMovable(
                Pawn.createOfBlack(),
                Position.of(sourceFile, sourceRank),
                Position.of(targetFile, targetRank));
        assertThat(movable).isEqualTo(result);
    }

    @DisplayName("Black 진영인 경우 - 아래쪽 대각선에 상대편 말이 있는 경우 true를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"c,7,b,6,true", "c,7,d,6,true", "c,7,c,6,false"})
    void shouldReturnTrueWhenMoveToOpponentPieceBlackPawn(
            String sourceFile, String sourceRank,
            String targetFile, String targetRank,
            boolean result) {
        Pawn sourcePawn = Pawn.createOfBlack();
        boolean movable = sourcePawn.isMovable(
                Pawn.createOfWhite(),
                Position.of(sourceFile, sourceRank),
                Position.of(targetFile, targetRank));
        assertThat(movable).isEqualTo(result);
    }

    @DisplayName("White 진영의 Pawn이 위로 한 칸 이동하면, 이동 경로는 위치를 0개 가진다.")
    @Test
    void shouldHasNoPositionWhenGetPathWhitePawn() {
        Pawn pawn = Pawn.createOfWhite();
        List<Position> path = pawn.collectPath(Position.of("b", "2"), Position.of("b", "3"));
        assertThat(path).isEmpty();
    }

    @DisplayName("White 진영의 Pawn이 위로 두 칸 이동할 때 경로를 반환한다.")
    @Test
    void shouldReturnPathWhenWhitePawnMoveTwoSteps() {
        Pawn pawn = Pawn.createOfWhite();
        List<Position> path = pawn.collectPath(Position.of("b", "2"), Position.of("b", "4"));
        assertThat(path).containsExactlyInAnyOrder(Position.of("b", "3"));
    }

    @DisplayName("White 진영의 Pawn이 오른쪽 위로 이동하면, 이동 경로는 위치를 0개 가진다.")
    @Test
    void shouldReturnPathWhenWhitePawnMoveRightUpward() {
        Pawn pawn = Pawn.createOfWhite();
        List<Position> path = pawn.collectPath(Position.of("b", "2"), Position.of("c", "3"));
        assertThat(path).isEmpty();
    }

    @DisplayName("White 진영의 Pawn이 왼쪽 위로 이동하면, 이동 경로는 위치를 0개 가진다.")
    @Test
    void shouldReturnPathWhenWhitePawnMoveLeftUpward() {
        Pawn pawn = Pawn.createOfWhite();
        List<Position> path = pawn.collectPath(Position.of("b", "2"), Position.of("c", "1"));
        assertThat(path).isEmpty();
    }

    @DisplayName("Black 진영의 Pawn이 아래로 한 칸 이동하면, 이동 경로는 위치를 0개 가진다.")
    @Test
    void shouldHasNoPositionWhenGetPathBlackPawn() {
        Pawn pawn = Pawn.createOfBlack();
        List<Position> path = pawn.collectPath(Position.of("b", "7"), Position.of("b", "6"));
        assertThat(path).isEmpty();
    }

    @DisplayName("Black 진영의 Pawn이 아래로 두 칸 이동할 때 경로를 반환한다.")
    @Test
    void shouldReturnPathWhenBlackPawnMoveTwoSteps() {
        Pawn pawn = Pawn.createOfBlack();
        List<Position> path = pawn.collectPath(Position.of("b", "7"), Position.of("b", "5"));
        assertThat(path).containsExactlyInAnyOrder(Position.of("b", "6"));
    }

    @DisplayName("Black 진영의 Pawn이 오른쪽 아래로 이동하면, 이동 경로는 위치를 0개 가진다.")
    @Test
    void shouldReturnPathWhenBLackPawnMoveRightDownward() {
        Pawn pawn = Pawn.createOfBlack();
        List<Position> path = pawn.collectPath(Position.of("b", "7"), Position.of("c", "6"));
        assertThat(path).isEmpty();
    }

    @DisplayName("Black 진영의 Pawn이 왼쪽 아래로 이동하면, 이동 경로는 위치를 0개 가진다.")
    @Test
    void shouldReturnPathWhenBlackPawnMoveLeftDownward() {
        Pawn pawn = Pawn.createOfBlack();
        List<Position> path = pawn.collectPath(Position.of("b", "7"), Position.of("a", "6"));
        assertThat(path).isEmpty();
>>>>>>> 3ad1dbf (refactor: 패키지 분리)
    }
}

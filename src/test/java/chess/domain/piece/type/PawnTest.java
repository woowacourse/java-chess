package chess.domain.piece.type;

import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import chess.domain.piece.Color;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@SuppressWarnings({"NonAsciiCharacters","SpellCheckingInspection"})
class PawnTest {

    @Nested
    class PawnIsBlack {

        private final Pawn blackPawn = new Pawn(Color.BLACK);

        /**
         * blackPawn은 아래, 왼쪽아래, 오른쪽 아래로만 이동 가능하다
         */
        @ParameterizedTest
        @CsvSource(value = {"C, FOUR" , "B, FOUR", "D, FOUR"})
        void Black폰이_갈수없는_방향으로_이동하려고_하면_예외 (Column endColumn, Rank endRank) {
            Position start = Position.of(Column.C, Rank.THREE);
            Position end = Position.of(endColumn, endRank);
            Color colorOfDestination = Color.NONE;

            assertThatThrownBy(() -> blackPawn.isMovable(start, end, colorOfDestination))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("Pawn이 이동할 수 있는 방향이 아닙니다");
        }

        @Test
        void 첫번째_이동이고_Black폰이_한번에_이동가능한_거리가_아니면_예외() {
            Position start = Position.of(Column.C, Rank.SEVEN);
            Position end = Position.of(Column.C, Rank.TWO);
            Color colorOfDestination = Color.NONE;

            assertThatThrownBy(() -> blackPawn.isMovable(start, end, colorOfDestination))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("Pawn이 한 번에 이동할 수 있는 거리가 아닙니다");
        }

        @Test
        void 첫번째_이동_이후_Black폰이_한번에_이동가능한_거리가_아니면_예외() {
            Position start = Position.of(Column.B, Rank.SEVEN);
            Position firstEnd = Position.of(Column.B, Rank.FIVE);
            Position secondEnd = Position.of(Column.B,Rank.THREE);
            Color colorOfDestination = Color.NONE;
            blackPawn.isMovable(start, firstEnd, colorOfDestination);

            assertThatThrownBy(() -> blackPawn.isMovable(firstEnd, secondEnd, colorOfDestination))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("Pawn이 한 번에 이동할 수 있는 거리가 아닙니다");
        }

        @ParameterizedTest
        @CsvSource(value = {"WHITE", "BLACK"})
        void 이동하려는_방향이_직선인경우_도착점_기물의_색깔이_NONE이_아니면_예외(Color colorOfDestination) {
            Position start = Position.of(Column.B, Rank.SEVEN);
            Position end = Position.of(Column.B, Rank.FIVE);

            assertThatThrownBy(() -> blackPawn.isMovable(start, end, colorOfDestination))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("Pawn은 도착점에 기물이 있으면 앞으로 이동할 수 없습니다");
        }

        @ParameterizedTest
        @CsvSource(value = {"NONE", "BLACK"})
        void 이동하려는_방향이_대각선인경우_도착점_기물의_색깔이_WHITE이_아니면_예외(Color colorOfDestination) {
            Position start = Position.of(Column.B, Rank.SEVEN);
            Position end = Position.of(Column.C, Rank.SIX);

            assertThatThrownBy(() -> blackPawn.isMovable(start, end, colorOfDestination))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("Pawn은 대각선에 상대방이 있을 때만 이동할 수 있습니다");
        }

        @Test
        void 이동에_모든조건을_충족하면_true를_반환한다() {
            Position start = Position.of(Column.C, Rank.SEVEN);
            Position end = Position.of(Column.C, Rank.FIVE);
            Color colorOfDestination = Color.NONE;

            assertThat(blackPawn.isMovable(start, end, colorOfDestination))
                    .isTrue();
        }
    }

    @Nested
    class PawnIsWhite {
        private final Pawn whitePawn = new Pawn(Color.WHITE);

        /**
         * whitePawn은 위, 왼쪽위, 오른쪽위로만 이동 가능하다
         */
        @ParameterizedTest
        @CsvSource(value = {"C, TWO" , "B,TWO", "D,TWO"})
        void White폰이_갈수없는_방향으로_이동하려고_하면_예외 (Column endColumn, Rank endRank) {
            Position start = Position.of(Column.C, Rank.THREE);
            Position end = Position.of(endColumn, endRank);
            Color colorOfDestination = Color.NONE;

            assertThatThrownBy(() -> whitePawn.isMovable(start, end, colorOfDestination))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("Pawn이 이동할 수 있는 방향이 아닙니다");
        }

        @Test
        void 첫번째_이동이고_White폰이_한번에_이동가능한_거리가_아니면_예외() {
            Position start = Position.of(Column.C, Rank.TWO);
            Position end = Position.of(Column.C, Rank.FIVE);
            Color colorOfDestination = Color.NONE;

            assertThatThrownBy(() -> whitePawn.isMovable(start, end, colorOfDestination))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("Pawn이 한 번에 이동할 수 있는 거리가 아닙니다");
        }

        @Test
        void 첫번째_이동_이후_White폰이_한번에_이동가능한_거리가_아니면_예외() {
            Position start = Position.of(Column.B, Rank.TWO);
            Position firstEnd = Position.of(Column.B, Rank.FOUR);
            Position secondEnd = Position.of(Column.B,Rank.SIX);
            Color colorOfDestination = Color.NONE;
            whitePawn.isMovable(start, firstEnd, colorOfDestination);

            assertThatThrownBy(() -> whitePawn.isMovable(firstEnd, secondEnd, colorOfDestination))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("Pawn이 한 번에 이동할 수 있는 거리가 아닙니다");
        }

        @ParameterizedTest
        @CsvSource(value = {"WHITE", "BLACK"})
        void 이동하려는_방향이_직선인경우_도착점_기물의_색깔이_NONE이_아니면_예외(Color colorOfDestination) {
            Position start = Position.of(Column.H, Rank.TWO);
            Position end = Position.of(Column.H, Rank.THREE);

            assertThatThrownBy(() -> whitePawn.isMovable(start, end, colorOfDestination))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("Pawn은 도착점에 기물이 있으면 앞으로 이동할 수 없습니다");
        }

        @ParameterizedTest
        @CsvSource(value = {"NONE", "WHITE"})
        void 이동하려는_방향이_대각선인경우_도착점_기물의_색깔이_Black이_아니면_예외(Color colorOfDestination) {
            Position start = Position.of(Column.B, Rank.SIX);
            Position end = Position.of(Column.C, Rank.SEVEN);

            assertThatThrownBy(() -> whitePawn.isMovable(start, end, colorOfDestination))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("Pawn은 대각선에 상대방이 있을 때만 이동할 수 있습니다");
        }

        @Test
        void 이동에_모든조건을_충족하면_true를_반환한다() {
            Position start = Position.of(Column.C, Rank.FIVE);
            Position end = Position.of(Column.C, Rank.SEVEN);
            Color colorOfDestination = Color.NONE;

            assertThat(whitePawn.isMovable(start, end, colorOfDestination))
                    .isTrue();
        }
    }

}
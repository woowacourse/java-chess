package chess.domain.piece.type;

import chess.domain.piece.Color;
import chess.domain.piece.position.Condition;
import chess.domain.piece.position.PiecePosition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("Pawn 은")
class PawnTest {

    // 흰색인 경우 북쪽으로 한 칸 혹은 두 칸, 대각선 한 칸 가능
    // 검정색인 경우 남쪽으로 한 칸 혹은 두 칸, 대각선 한 칸 가능

    // 움직이지 않은 경우 한 칸, 두 칸, 대각선 한 칸 가능
    // 움직인 경우 한 칸, 대각선 한 칸 가능
    // 움직인 경우 두 칸 불가능
    // 모든 경우 세 칸 이상 불가능

    @Nested
    class 흰색_폰_움직임_테스트 {

        @ParameterizedTest
        @CsvSource({
                // 기준 b, 2
                "b,3,POSSIBLE",
                "b,4,POSSIBLE",
                "a,3,ONLY_DESTINATION_ENEMY",
                "c,3,ONLY_DESTINATION_ENEMY",
        })
        void 움직이지_않은_경우_북쪽으로_한칸_두칸_대각선한칸_이동_가능하다(final char file, final int rank, final Condition condition) {
            // given
            final PiecePosition currentPosition = PiecePosition.of(2, 'b');
            final PiecePosition destination = PiecePosition.of(rank, file);
            final Pawn pawn = new Pawn(Color.WHITE, currentPosition);

            // when & then
            assertThat(pawn.wayPointsWithCondition(destination).condition()).isEqualTo(condition);
        }

        @ParameterizedTest
        @CsvSource({
                // 기준 b, 4
                "b,5,POSSIBLE",
                "b,6,IMPOSSIBLE",
                "a,5,ONLY_DESTINATION_ENEMY",
                "c,5,ONLY_DESTINATION_ENEMY",
        })
        void 움직인_경우_북쪽으로_한칸_대각선한칸_이동_가능하다(final char file, final int rank, final Condition condition) {
            // given
            final PiecePosition currentPosition = PiecePosition.of(2, 'b');
            final PiecePosition destination = PiecePosition.of(rank, file);
            final Pawn pawn = new Pawn(Color.WHITE, currentPosition);
            pawn.move(PiecePosition.of(4, 'b'));

            // when & then
            assertThat(pawn.wayPointsWithCondition(destination).condition()).isEqualTo(condition);
        }

        @ParameterizedTest
        @CsvSource({
                // 기준 b, 2
                "b,5",
                "b,6",
                "c,4",
                "a,4",
        })
        void 모든_경우_세칸_이상은_이동할_수_없다(final char file, final int rank) {
            // given
            final PiecePosition currentPosition = PiecePosition.of(2, 'b');
            final PiecePosition destination = PiecePosition.of(rank, file);
            final Pawn pawn = new Pawn(Color.WHITE, currentPosition);

            // when & then
            assertThat(pawn.wayPointsWithCondition(destination).condition()).isEqualTo(Condition.IMPOSSIBLE);
        }

        @ParameterizedTest
        @CsvSource({
                // 기준 b, 2
                "a,2",
                "c,2",
                "b,1",
                "a,1",
                "c,1",
        })
        void 모든_경우_북쪽이_아닌_방향으로_이동할_수_없다(final char file, final int rank) {
            // given
            final PiecePosition currentPosition = PiecePosition.of(2, 'b');
            final PiecePosition destination = PiecePosition.of(rank, file);
            final Pawn pawn = new Pawn(Color.WHITE, currentPosition);

            // when & then
            assertThat(pawn.wayPointsWithCondition(destination).condition()).isEqualTo(Condition.IMPOSSIBLE);
        }
    }

    @Nested
    class 검정색_폰_움직임_테스트 {

        @ParameterizedTest
        @CsvSource({
                // 기준 b, 7
                "b,6,POSSIBLE",
                "b,5,POSSIBLE",
                "a,6,ONLY_DESTINATION_ENEMY",
                "c,6,ONLY_DESTINATION_ENEMY",
        })
        void 움직이지_않은_경우_남쪽으로_한칸_두칸_대각선한칸_이동_가능하다(final char file, final int rank, final Condition condition) {
            // given
            final PiecePosition currentPosition = PiecePosition.of(7, 'b');
            final PiecePosition destination = PiecePosition.of(rank, file);
            final Pawn pawn = new Pawn(Color.BLACK, currentPosition);

            // when & then
            assertThat(pawn.wayPointsWithCondition(destination).condition()).isEqualTo(condition);
        }

        @ParameterizedTest
        @CsvSource({
                // 기준 b, 5
                "b,4,POSSIBLE",
                "b,3,IMPOSSIBLE",
                "a,4,ONLY_DESTINATION_ENEMY",
                "c,4,ONLY_DESTINATION_ENEMY",
        })
        void 움직인_경우_남쪽으로_한칸_대각선한칸_이동_가능하다(final char file, final int rank, final Condition condition) {
            // given
            final PiecePosition currentPosition = PiecePosition.of(7, 'b');
            final PiecePosition destination = PiecePosition.of(rank, file);
            final Pawn pawn = new Pawn(Color.BLACK, currentPosition);
            pawn.move(PiecePosition.of(5, 'b'));

            // when & then
            assertThat(pawn.wayPointsWithCondition(destination).condition()).isEqualTo(condition);
        }

        @ParameterizedTest
        @CsvSource({
                // 기준 b, 7
                "b,4",
                "b,3",
                "c,5",
                "a,5",
                "d,6",
        })
        void 모든_경우_세칸_이상은_이동할_수_없다(final char file, final int rank) {
            // given
            final PiecePosition currentPosition = PiecePosition.of(7, 'b');
            final PiecePosition destination = PiecePosition.of(rank, file);
            final Pawn pawn = new Pawn(Color.BLACK, currentPosition);

            // when & then
            assertThat(pawn.wayPointsWithCondition(destination).condition()).isEqualTo(Condition.IMPOSSIBLE);
        }

        @ParameterizedTest
        @CsvSource({
                // 기준 b, 7
                "b,8",
                "a,8",
                "c,8",
                "a,7",
                "c,7",
        })
        void 모든_경우_남쪽이_아닌_방향으로_이동할_수_없다(final char file, final int rank) {
            // given
            final PiecePosition currentPosition = PiecePosition.of(7, 'b');
            final PiecePosition destination = PiecePosition.of(rank, file);
            final Pawn pawn = new Pawn(Color.BLACK, currentPosition);

            // when & then
            assertThat(pawn.wayPointsWithCondition(destination).condition()).isEqualTo(Condition.IMPOSSIBLE);
        }
    }
}

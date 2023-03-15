package chess.model.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PieceTypeTest {

    @Nested
    @DisplayName("movable()을 호출하면 움직일 수 있는 방향인지 확인한다.")
    class MovableTest {

        @ParameterizedTest(name = "PAWN이 ({0}, {1})로 이동할 때 movable 결과가 {2}로 나온다.")
        @CsvSource({"0,2,true", "1,0,false"})
        void pawn_givenRankAndFile_thenReturnIfMovable(
                final int rank,
                final int file,
                final boolean result
        ) {
            // when, then
            assertThat(PieceType.PAWN.movable(rank, file)).isEqualTo(result);
        }

        @ParameterizedTest(name = "KNIGHT가 ({0}, {1})로 이동할 때 movable 결과가 {2}로 나온다.")
        @CsvSource({
                "2,-1,true", "2,1,true", "-2,1,true", "-2,-1,true",
                "-1,2,true", "1,2,true", "1,-2,true", "-1,-2,true",
                "0,3,false", "3,0,false", "-4,-4,false", "6,-6,false", "3,3,false", "-3,3,false"
        })
        void knight_givenRankAndFile_thenReturnIfMovable(
                final int rank,
                final int file,
                final boolean result
        ) {
            // when, then
            assertThat(PieceType.KNIGHT.movable(rank, file)).isEqualTo(result);
        }

        @ParameterizedTest(name = "ROOK이 ({0}, {1})로 이동할 때 movable 결과가 {2}로 나온다.")
        @CsvSource({"0,3,true", "3,0,true", "0,-6,true", "-3,0,true", "1,3,false", "-1,3,false"})
        void rook_givenRankAndFile_thenReturnIfMovable(
                final int rank,
                final int file,
                final boolean result
        ) {
            // when, then
            assertThat(PieceType.ROOK.movable(rank, file)).isEqualTo(result);
        }

        @ParameterizedTest(name = "BISHOP이 ({0}, {1})로 이동할 때 movable 결과가 {2}로 나온다.")
        @CsvSource({"0,3,false", "3,0,false", "-4,-4,true", "6,-6,true", "3,3,true", "-3,3,true"})
        void bishop_givenRankAndFile_thenReturnIfMovable(
                final int rank,
                final int file,
                final boolean result
        ) {
            // when, then
            assertThat(PieceType.BISHOP.movable(rank, file)).isEqualTo(result);
        }

        @ParameterizedTest(name = "KING이 ({0}, {1})로 이동할 때 movable 결과가 {2}로 나온다.")
        @CsvSource({"0,1,true", "1,0,true", "-1,-1,true", "1,-1,true", "1,1,true", "-1,1,true"})
        void king_givenRankAndFile_thenReturnIfMovable(
                final int rank,
                final int file,
                final boolean result
        ) {
            // when, then
            assertThat(PieceType.KING.movable(rank, file)).isEqualTo(result);
        }

        @ParameterizedTest(name = "QUEEN이 ({0}, {1})로 이동할 때 movable 결과가 {2}로 나온다.")
        @CsvSource({"0,3,true", "3,0,true", "-4,-4,true", "6,-6,true", "3,3,true", "-3,3,true"})
        void queen_givenRankAndFile_thenReturnIfMovable(
                final int rank,
                final int file,
                final boolean result
        ) {
            // when, then
            assertThat(PieceType.QUEEN.movable(rank, file)).isEqualTo(result);
        }
    }
}

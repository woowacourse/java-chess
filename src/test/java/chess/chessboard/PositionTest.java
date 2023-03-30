package chess.chessboard;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Position 클래스")
public class PositionTest {

    @Nested
    @DisplayName("of 메서드는")
    class of {
        @Nested
        @DisplayName("File과 Rank가 주어지면")
        class given_file_and_rank {
            File file = File.A;
            Rank rank = Rank.ONE;

            @Test
            @DisplayName("해당 위치의 Square를 반환한다")
            void it_returns_position() {
                Position position = Position.of(rank, file);
                assertThat(position.getRank()).isEqualTo(Rank.ONE);
                assertThat(position.getFile()).isEqualTo(File.A);
            }
        }
    }

    @Nested
    @DisplayName("positionsOfPath 메서드는")
    class positionsOfPath {
        @Nested
        @DisplayName("직선 경로가 주어지면")
        class given_linepath {
            Position from = Position.of(Rank.FOUR, File.F);
            Position to1 = Position.of(Rank.FOUR, File.B);
            Position to2 = Position.of(Rank.ONE, File.F);

            @Test
            @DisplayName("사이에 있는 Square들을 반환한다")
            void it_returns_positions() {
                List<Position> positions1 = from.positionsOfPath(to1);
                List<Position> positions2 = from.positionsOfPath(to2);
                assertThat(positions1).containsSequence(Position.of(Rank.FOUR, File.E),
                        Position.of(Rank.FOUR, File.D),
                        Position.of(Rank.FOUR, File.C));
                assertThat(positions2).containsSequence(Position.of(Rank.THREE, File.F),
                        Position.of(Rank.TWO, File.F));
            }
        }

        @Nested
        @DisplayName("사선 경로가 주어지면")
        class given_diagonalpath {
            Position from = Position.of(Rank.FOUR, File.F);
            Position to1 = Position.of(Rank.ONE, File.C);
            Position to2 = Position.of(Rank.EIGHT, File.B);

            @Test
            @DisplayName("사이에 있는 Square들을 반환한다")
            void it_returns_positions() {
                List<Position> positions1 = from.positionsOfPath(to1);
                List<Position> positions2 = from.positionsOfPath(to2);
                assertThat(positions1).containsOnly(Position.of(Rank.THREE, File.E),
                        Position.of(Rank.TWO, File.D));
                assertThat(positions2).containsOnly(Position.of(Rank.FIVE, File.E),
                        Position.of(Rank.SIX, File.D),
                        Position.of(Rank.SEVEN, File.C));
            }
        }

        @Nested
        @DisplayName("직선 경로도 사선 경로도 아니면")
        class given_non_line_non_diagonalpath {
            Position from = Position.of(Rank.FOUR, File.F);
            Position to1 = Position.of(Rank.FIVE, File.A);
            Position to2 = Position.of(Rank.ONE, File.E);
            Position to3 = Position.of(Rank.TWO, File.E);

            @Test
            @DisplayName("아무 Square도 반환하지 않는다")
            void it_returns_empty_position() {
                List<Position> positions1 = from.positionsOfPath(to1);
                List<Position> positions2 = from.positionsOfPath(to2);
                List<Position> positions3 = from.positionsOfPath(to3);
                assertThat(positions1).isEmpty();
                assertThat(positions2).isEmpty();
                assertThat(positions3).isEmpty();
            }
        }
    }
}

package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Position;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KnightTest {
    @ParameterizedTest
    @CsvSource({"5,6", "5,2", "3,6", "3,2", "6,5", "6,3", "2,5", "2,3"})
    @DisplayName("나이트는 도착 위치가 비어있는 경우 이동할 수 있다.")
    void Given_Knight_When_CanMovePositionEmpty_Then_True(int file, int rank) {
        //given
        Piece piece = new Knight(Color.WHITE);
        //when, then
        assertThat(piece.canMove(new Position(4, 4), new Position(file, rank), Map.of())).isTrue();
    }

    @ParameterizedTest
    @CsvSource({"5,6", "5,2", "3,6", "3,2", "6,5", "6,3", "2,5", "2,3"})
    @DisplayName("나이트는 도착 위치에 상대편 말이 있는 경우 이동할 수 있다.")
    void Given_Knight_When_CanMovePositionEnemyPiece_Then_True(int file, int rank) {
        //given
        Piece piece = new Knight(Color.WHITE);
        //when, then
        assertThat(piece.canMove(new Position(4, 4), new Position(file, rank),
                Map.of(new Position(file, rank), new Queen(Color.BLACK)))).isTrue();
    }

    @ParameterizedTest
    @CsvSource({"5,6", "5,2", "3,6", "3,2", "6,5", "6,3", "2,5", "2,3"})
    @DisplayName("나이트는 도착 위치에 우리편 말이 있는 경우 이동할 수 없다.")
    void Given_Knight_When_CanNotMovePositionOurTeamPiece_Then_False(int file, int rank) {
        //given
        Piece piece = new Knight(Color.WHITE);
        //when, then
        assertThat(piece.canMove(new Position(4, 4), new Position(file, rank),
                Map.of(new Position(file, rank), new Queen(Color.WHITE)))).isFalse();
    }
}

package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Position;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KingTest {
    @ParameterizedTest
    @CsvSource({"3,3", "5,5",
            "3,5",
            "5,4", "3,4",
            "4,3", "4,5",})
    @DisplayName("킹은 도착 위치가 비어있는 경우 이동할 수 있다.")
    void Given_King_When_CanMovePositionEmpty_Then_True(int file, int rank) {
        //given
        Piece piece = new King(Color.WHITE);
        //when, then
        assertThat(piece.canMove(new Position(4, 4), new Position(file, rank), Map.of())).isTrue();
    }

    @ParameterizedTest
    @CsvSource({"3,3", "5,5",
            "3,5", "5,3",
            "5,4", "3,4",
            "4,3", "4,5",})
    @DisplayName("킹은 도착 위치에 상대편 말이 있는 경우 이동할 수 있다.")
    void Given_King_When_CanMovePositionEnemyPiece_Then_True(int file, int rank) {
        //given
        Piece piece = new King(Color.WHITE);
        //when, then
        assertThat(piece.canMove(new Position(4, 4), new Position(file, rank),
                Map.of(new Position(file, rank), new Queen(Color.BLACK)))).isTrue();
    }

    @ParameterizedTest
    @CsvSource({"3,3", "5,5",
            "3,5", "5,3",
            "5,4", "3,4",
            "4,3", "4,5",})
    @DisplayName("킹은 도착 위치에 우리편 말이 있는 경우 이동할 수 없다.")
    void Given_King_When_CanNotMovePositionOurTeamPiece_Then_False(int rank, int file) {
        //given
        Piece piece = new King(Color.WHITE);
        //when, then
        assertThat(piece.canMove(new Position(4, 4), new Position(file, rank),
                Map.of(new Position(file, rank), new Queen(Color.WHITE)))).isFalse();
    }
}

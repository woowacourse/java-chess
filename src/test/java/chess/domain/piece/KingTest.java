package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Position;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KingTest {

    private Map<Position, Piece> BOARD_MAP = new HashMap<>();

    @BeforeEach
    void beforeEach() {
        BOARD_MAP = EmptyBoardGenerator.create();
    }

    @DisplayName("킹 이동 가능")
    @ParameterizedTest(name = "(4,4)에서 ({0},{1})로 이동할 수 있다.")
    @CsvSource({"4,5", "4,3", "5,4", "3,4", "5,5", "3,5", "5,3", "3,3"})
    void canMove(int file, int rank) {
        Piece king = new King(Color.WHITE);
        Piece captured = new Knight(Color.BLACK);

        Position source = Position.of(4, 4);
        Position target = Position.of(file, rank);

        BOARD_MAP.put(source, king);
        BOARD_MAP.put(Position.of(4, 5), captured);

        assertThat(king.canMove(source, target, BOARD_MAP)).isTrue();
    }

    @DisplayName("킹 이동 불가")
    @ParameterizedTest(name = "{0}: (4,4)에서 ({1},{2})로 이동 불가")
    @CsvSource({"이동 규칙 위반,2,4", "같은 위치,4,4", "자신의 말이 위치,5,4"})
    void cannotMove(String description, int file, int rank) {
        Piece king = new King(Color.WHITE);
        Piece pawn = new Pawn(Color.WHITE);

        Position source = Position.of(4, 4);
        Position target = Position.of(file, rank);

        BOARD_MAP.put(source, king);
        BOARD_MAP.put(Position.of(5, 4), pawn);

        assertThat(king.canMove(source, target, BOARD_MAP)).isFalse();
    }
}

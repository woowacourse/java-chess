package chess.domain.piece;


import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Position;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KnightTest {

    private Map<Position, Piece> BOARD_MAP = new HashMap<>();

    @BeforeEach
    void beforeEach() {
        BOARD_MAP = EmptyBoardGenerator.create();
    }

    @DisplayName("나이트 이동 가능")
    @ParameterizedTest(name = "(4,4)에서 ({0},{1})로 이동 가능")
    @CsvSource({"5,6", "3,6", "6,5", "6,3", "5,2", "3,2", "2,3", "2,5"})
    void canMove(int file, int rank) {
        Piece knight = new Knight(Color.WHITE);
        Piece captured = new Knight(Color.BLACK);

        Position source = Position.of(4, 4);
        Position target = Position.of(file, rank);

        BOARD_MAP.put(source, knight);
        BOARD_MAP.put(Position.of(5, 6), captured);

        assertThat(knight.canMove(source, target, BOARD_MAP)).isTrue();
    }

    @DisplayName("나이트 이동 불가")
    @ParameterizedTest(name = "{0}: (4,4)에서 ({1},{2})로 이동 불가")
    @CsvSource({"이동 규칙 위반,3,3", "같은 위치,4,4", "자신의 말이 위치,5,6"})
    void cannotMove(String description, int file, int rank) {
        Piece knight = new Knight(Color.WHITE);
        Piece pawn = new Pawn(Color.WHITE);

        Position source = Position.of(4, 4);
        Position target = Position.of(file, rank);

        BOARD_MAP.put(source, knight);
        BOARD_MAP.put(Position.of(5, 6), pawn);

        assertThat(knight.canMove(source, target, BOARD_MAP)).isFalse();
    }
}

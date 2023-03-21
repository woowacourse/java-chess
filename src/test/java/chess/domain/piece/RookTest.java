package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Position;
import chess.domain.Team;
import chess.dto.BoardSnapshot;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;
import java.util.Map;

class RookTest {

    // 상,하,좌,우 직선으로 거리 상관 없이 움직일 수 있다.
    @ParameterizedTest
    @CsvSource({"3,1", "3,2", "3,4", "3,5", "1,3", "2,3", "4,3", "5,3"})
    @DisplayName("룩의 이동 조건과 일치하는 경우 true가 반환되어야 한다.")
    void canMove_Success(int x, int y) {
        // given
        Piece piece = new Rook(Team.BLACK);
        Position sourcePosition = Position.of(3, 3);
        Position targetPosition = Position.of(x, y);

        // expect
        assertThat(piece.canMove(sourcePosition, targetPosition, getBoardSnapShot()))
                .isTrue();
    }

    @ParameterizedTest
    @CsvSource({"3,1", "3,2", "3,4", "3,5", "1,3", "2,3", "4,3", "5,3"})
    @DisplayName("룩의 이동 조건과 다를경우 false가 반환되어야 한다.")
    void canMove_Fail(int x, int y) {
        // given
        Piece piece = new Rook(Team.BLACK);
        Position sourcePosition = Position.of(0, 0);
        Position targetPosition = Position.of(x, y);

        // expect
        assertThat(piece.canMove(sourcePosition, targetPosition, getBoardSnapShot()))
                .isFalse();
    }

    private BoardSnapshot getBoardSnapShot() {
        return new BoardSnapshot(getEmptySquares());
    }

    private Map<Position, Piece> getEmptySquares() {
        Map<Position, Piece> squares = new HashMap<>();
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                squares.put(Position.of(x, y), Empty.INSTANCE);
            }
        }
        return squares;
    }
}

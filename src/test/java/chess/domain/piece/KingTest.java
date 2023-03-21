package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Board;
import chess.domain.Position;
import chess.domain.Team;
import chess.dto.BoardSnapshot;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;
import java.util.Map;

class KingTest {

    @ParameterizedTest
    @CsvSource({"3,4","4,4","4,3","4,2","3,2","2,2","2,3","2,4"})
    @DisplayName("킹의 이동 조건과 일치하는 경우 true가 반환되어야 한다.")
    void canMove_Success(int x, int y) {
        // given
        Map<Position, Piece> emptySquares = getEmptySquares();
        Piece piece = new King(Team.WHITE);
        emptySquares.put(Position.of(3, 3), piece);

        Board board = new Board(emptySquares);
        Position sourcePosition = Position.of(3, 3);
        Position targetPosition = Position.of(x, y);

        // expect
        assertThat(piece.canMove(sourcePosition, targetPosition, new BoardSnapshot(board.getBoard())))
                .isTrue();
    }

    @ParameterizedTest
    @CsvSource({"4,5", "5,4", "2,5", "1,4", "1,2", "2,1", "4,1", "5,2"})
    @DisplayName("킹의 이동 조건과 다를경우 false가 반환되어야 한다.")
    void canMove_Fail(int x, int y) {
        // given
        Map<Position, Piece> emptySquares = getEmptySquares();
        Piece piece = new King(Team.WHITE);
        emptySquares.put(Position.of(3, 3), piece);

        Board board = new Board(emptySquares);
        Position sourcePosition = Position.of(3, 3);
        Position targetPosition = Position.of(x, y);

        // expect
        assertThat(piece.canMove(sourcePosition, targetPosition, new BoardSnapshot(board.getBoard())))
                .isFalse();
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

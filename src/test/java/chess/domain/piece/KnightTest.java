package chess.domain.piece;

import chess.domain.Board;
import chess.domain.Position;
import chess.domain.Team;
import chess.dto.BoardSnapshot;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class KnightTest {

    @ParameterizedTest
    @CsvSource({"4,5", "5,4", "2,5", "1,4", "1,2", "2,1", "4,1", "5,2"})
    @DisplayName("나이트의 이동 조건과 일치하는 경우 true가 반환되어야 한다.")
    void canMove_Success(int x, int y) {
        // given
        Map<Position, Piece> emptySquares = getEmptySquares();
        Piece piece = new Knight(Team.WHITE);
        emptySquares.put(Position.of(3, 3), piece);

        Board board = new Board(emptySquares);
        Position sourcePosition = Position.of(3, 3);
        Position targetPosition = Position.of(x, y);

        // expect
        assertThat(piece.canMove(sourcePosition, targetPosition, new BoardSnapshot(board.getBoard())))
                .isTrue();
    }

    @ParameterizedTest
    @CsvSource({"4,5", "5,4", "2,5", "1,4", "1,3", "2,3", "4,1", "5,2"})
    @DisplayName("나이트의 이동 조건과 다를경우 false가 반환되어야 한다.")
    void canMove_Fail(int x, int y) {
        // given
        Map<Position, Piece> emptySquares = getEmptySquares();
        Piece piece = new King(Team.WHITE);
        emptySquares.put(Position.of(0, 0), piece);

        Board board = new Board(emptySquares);
        Position sourcePosition = Position.of(0, 0);
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

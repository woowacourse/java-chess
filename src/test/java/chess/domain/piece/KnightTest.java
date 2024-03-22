package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

class KnightTest {
    @DisplayName("성공 : 나이트는 L자 모양으로 이동할 수 있다")
    @ParameterizedTest
    @CsvSource(value = {"5,2", "5,4", "1,2", "1,4", "2,5", "4,5", "2,1", "4,1"})
    void should_CanMoveLShapePosition(int destRow, int destCol) {
        ChessBoard board = new ChessBoard(new HashMap<>());
        Position start = Position.of(3, 3);
        Position dest = Position.of(destRow, destCol);
        Knight knight = new Knight(Team.BLACK);

        assertThat(knight.canMove(start, dest, board)).isTrue();
    }

    @DisplayName("실패 : 나이트가 행마법을 벗어난 목적지로 이동할 수 없다")
    @ParameterizedTest
    @CsvSource(value = {"4,4", "2,4", "2,2", "4,2"})
    void should_CanNotMoveToWrongDestinationn(int destRow, int destCol) {
        ChessBoard board = new ChessBoard(new HashMap<>());
        Position start = Position.of(3, 3);
        Position dest = Position.of(destRow, destCol);
        Knight knight = new Knight(Team.BLACK);

        assertThat(knight.canMove(start, dest, board)).isFalse();
    }

}

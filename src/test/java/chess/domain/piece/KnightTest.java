package chess.domain.piece;

import static chess.domain.piece.Color.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.ChessBoard;
import chess.domain.Position;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KnightTest {

    private Knight knight;
    private ChessBoard chessBoard;

    @BeforeEach
    void setUp() {
        knight = new Knight(Color.WHITE, new Position('b', '1'));

        Piece whietePiece = new Knight(WHITE, new Position('a', '3'));
        Piece darkPiece = new Knight(BLACK, new Position('c', '3'));
        chessBoard = new ChessBoard(Map.of(
                whietePiece.getPosition(), whietePiece,
                darkPiece.getPosition(), darkPiece));
    }

    @Test
    @DisplayName("나이트 기물 생성")
    void createPawn() {
        assertThat(knight).isInstanceOf(Knight.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"b,2", "b,3", "b,4", "c,1", "c,2", "d,3"})
    @DisplayName("나이트 위치 이동 불가 예외발생")
    void moveException(char row, char col) {
        Position movePosition = new Position(row, col);

        assertThatThrownBy(() -> knight.move(movePosition, chessBoard))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("나이트는 L자 형태로만 움직일 수 있습니다.");
    }

    @Test
    @DisplayName("해당 위치에 같은 색상의 기물 존재시 예외발생")
    void moveExceptionByExistEqualsColorPiece() {
        Position position = new Position('a', '3');

        assertThatThrownBy(() -> knight.move(position, chessBoard))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("해당 위치에 같은 색상의 기물이 존재하여 움직일 수 없습니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"c,3", "d,2"})
    @DisplayName("나이트 위치 이동")
    void move(char row, char col) {
        Position expected = new Position(row, col);

        Knight actual = knight.move(expected, chessBoard);
        assertThat(actual.getPosition()).isEqualTo(expected);
    }
}

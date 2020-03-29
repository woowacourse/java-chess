package piece;

import chess.domain.board.Position;
import chess.domain.board.Positions;
import chess.domain.piece.Pawn;
import chess.domain.piece.PieceColor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

public class PawnTest {

    @ParameterizedTest
    @DisplayName("목표 위치값으로 제자리가 들어오면 예외가 발생해야 함")
    @ValueSource(strings = {"a1", "b8", "f7"})
    void inputCurrentPositionThenThrowException(String input) {
        Pawn pawn = new Pawn(PieceColor.BLACK, Positions.of(input));

        Assertions.assertThatThrownBy(() -> pawn.getPathTo(Positions.of(input)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재 자리한 위치로는 이동할 수 없습니다.");
    }

    @Test
    @DisplayName("검은색 폰이 y좌표가 7일 때 갈 수 있는 목표 위치값이 들어오면 경로를 정상적으로 반환해야 함")
    void inputSevenLineBlackPawnMovablePositionThenReturnPath() {
        Pawn pawn = new Pawn(PieceColor.BLACK, Positions.of("e7"));

        List<Position> path1 = pawn.getPathTo(Positions.of("e5"));
        Assertions.assertThat(path1.contains(Positions.of("e6"))).isTrue();
        Assertions.assertThat(path1.contains(Positions.of("e5"))).isTrue();

        List<Position> path2 = pawn.getPathTo(Positions.of("e6"));
        Assertions.assertThat(path2.contains(Positions.of("e6"))).isTrue();
    }

    @Test
    @DisplayName("검은색 폰이 y좌표가 7이 아닐 때 갈 수 있는 목표 위치값이 들어오면 경로를 정상적으로 반환해야 함")
    void inputNotSevenLineBlackPawnMovablePositionThenReturnPath() {
        Pawn pawn = new Pawn(PieceColor.BLACK, Positions.of("f6"));

        List<Position> path1 = pawn.getPathTo(Positions.of("f5"));
        Assertions.assertThat(path1.contains(Positions.of("f5"))).isTrue();

        List<Position> path2 = pawn.getPathTo(Positions.of("e5"));
        Assertions.assertThat(path2.contains(Positions.of("e5"))).isTrue();

        List<Position> path3 = pawn.getPathTo(Positions.of("g5"));
        Assertions.assertThat(path3.contains(Positions.of("g5"))).isTrue();
    }

    @ParameterizedTest
    @DisplayName("검은색 폰이 이동할 수 없는 방향의 목표 위치값이 들어오면 예외가 발생해야 함")
    @CsvSource(value = {"a7,a8", "d7,f8", "e7,d7", "c5,h5", "e7,f8"})
    void inputBlackPawnNotMovableDirectionPositionThenThrowException(String source, String target) {
        Pawn pawn = new Pawn(PieceColor.BLACK, Positions.of(source));

        Assertions.assertThatThrownBy(() -> pawn.getPathTo(Positions.of(target)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("폰이 이동할 수 없는 방향입니다.");
    }

    @ParameterizedTest
    @DisplayName("검은색 폰이 이동할 수 없는 목표 위치값이 들어오면 예외가 발생해야 함")
    @CsvSource(value = {"a7,c6", "d7,b5", "b4,b2", "b4,b1", "h7, h4", "b4,c2"})
    void inputBlackPawnNotMovablePositionThenThrowException(String source, String target) {
        Pawn pawn = new Pawn(PieceColor.BLACK, Positions.of(source));

        Assertions.assertThatThrownBy(() -> pawn.getPathTo(Positions.of(target)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("폰이 이동할 수 없는 위치입니다.");
    }

    @Test
    @DisplayName("하얀색 폰이 y좌표가 2일 때 갈 수 있는 목표 위치값이 들어오면 경로를 정상적으로 반환해야 함")
    void inputSecondLineWhitePawnMovablePositionThenReturnPath() {
        Pawn pawn = new Pawn(PieceColor.WHITE, Positions.of("e2"));

        List<Position> path1 = pawn.getPathTo(Positions.of("e4"));
        Assertions.assertThat(path1.contains(Positions.of("e3"))).isTrue();
        Assertions.assertThat(path1.contains(Positions.of("e4"))).isTrue();

        List<Position> path2 = pawn.getPathTo(Positions.of("e3"));
        Assertions.assertThat(path2.contains(Positions.of("e3"))).isTrue();
    }

    @Test
    @DisplayName("하얀색 폰이 y좌표가 2가 아닐 때 갈 수 있는 목표 위치값이 들어오면 경로를 정상적으로 반환해야 함")
    void inputNotSecondLineWhitePawnMovablePositionThenReturnPath() {
        Pawn pawn = new Pawn(PieceColor.WHITE, Positions.of("f4"));

        List<Position> path1 = pawn.getPathTo(Positions.of("f5"));
        Assertions.assertThat(path1.contains(Positions.of("f5"))).isTrue();

        List<Position> path2 = pawn.getPathTo(Positions.of("e5"));
        Assertions.assertThat(path2.contains(Positions.of("e5"))).isTrue();

        List<Position> path3 = pawn.getPathTo(Positions.of("g5"));
        Assertions.assertThat(path3.contains(Positions.of("g5"))).isTrue();
    }

    @ParameterizedTest
    @DisplayName("하얀색 폰이 이동할 수 없는 방향의 목표 위치값이 들어오면 예외가 발생해야 함")
    @CsvSource(value = {"h2,h1", "c5,b3", "d3,a1", "f3,d3", "d6,f6", "a4,c2", "b5,e3"})
    void inputWhitePawnNotMovableDirectionPositionThenThrowException(String source, String target) {
        Pawn pawn = new Pawn(PieceColor.WHITE, Positions.of(source));

        Assertions.assertThatThrownBy(() -> pawn.getPathTo(Positions.of(target)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("폰이 이동할 수 없는 방향입니다.");
    }

    @ParameterizedTest
    @DisplayName("하얀색 폰이 이동할 수 없는 목표 위치값이 들어오면 예외가 발생해야 함")
    @CsvSource(value = {"a2,a5", "b3,b5", "c4,a6", "c4,b6", "d5,g8", "e6,h7"})
    void inputWhitePawnNotMovablePositionThenThrowException(String source, String target) {
        Pawn pawn = new Pawn(PieceColor.WHITE, Positions.of(source));

        Assertions.assertThatThrownBy(() -> pawn.getPathTo(Positions.of(target)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("폰이 이동할 수 없는 위치입니다.");
    }
}

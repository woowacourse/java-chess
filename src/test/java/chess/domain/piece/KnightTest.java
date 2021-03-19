package chess.domain.piece;

import chess.domain.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class KnightTest {
    private CurrentPieces currentPieces;

    @BeforeEach
    void setUp() {
        currentPieces = CurrentPieces.generate();
    }

    @DisplayName("Knight 객체 생성 확인")
    @Test
    void 나이트_객체_생성() {
        Knight knight = new Knight(Position.of('b', '8'), "N", Color.BLACK);

        assertThat(knight.getPosition()).isEqualTo(Position.of('b', '8'));
        assertThat(knight.getName()).isEqualTo("N");
    }

    @DisplayName("초기화된 Knight 객체들 생성 확인")
    @Test
    void 나이트_객체들_생성() {
        List<Knight> nights = Knight.generate();

        assertThat(nights.size()).isEqualTo(4);
    }

    @Test
    void 나이트_이동() {
        List<Piece> current = Arrays.asList(
                new Knight(Position.of('b', '8'), "N", Color.BLACK));
        CurrentPieces currentPieces = new CurrentPieces(current);
        Position source = Position.of('b', '8'); // 비숍 위치
        Position target = Position.of('c', '6'); // 옮기고자 하는 위치
        Piece knight = currentPieces.findByPosition(source);

        knight.move(target, currentPieces);

        assertThat(knight.getPosition()).isEqualTo(target);
    }

    @Test
    void 룩_이동_규칙에_어긋나는_경우_예() {
        List<Piece> current = Arrays.asList(
                new Knight(Position.of('b', '8'), "N", Color.BLACK));
        CurrentPieces currentPieces = new CurrentPieces(current);
        Position source = Position.of('b', '8'); // 비숍 위치
        Position target = Position.of('b', '1'); // 옮기고자 하는 위치

        Piece knight = currentPieces.findByPosition(source);

        assertThatThrownBy(() ->  knight.move(target, currentPieces))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 상대편_말을_공격한다() {
        List<Piece> current = Arrays.asList(
                new Knight(Position.of('b', '8'), "N", Color.BLACK),
                new Pawn(Position.of('d','7'),"p", Color.WHITE));
        CurrentPieces currentPieces = new CurrentPieces(current);

        Position source = Position.of('b', '8'); // 비숍 위치
        Position target = Position.of('d','7'); // 옮기고자 하는 위치
        Piece knight = currentPieces.findByPosition(source);

        knight.move(target, currentPieces);

        assertThat(currentPieces.getCurrentPieces().size()).isEqualTo(1);
    }
}

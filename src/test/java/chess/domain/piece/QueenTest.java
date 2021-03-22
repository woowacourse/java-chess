package chess.domain.piece;

import chess.domain.piece.info.Color;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QueenTest {
    @DisplayName("Queen 객체 생성 확인")
    @Test
    void 퀸_객체_생성() {
        Queen queen = new Queen(Position.of('d', '8'), Color.BLACK);

        assertThat(queen.getPosition()).isEqualTo(Position.of('d', '8'));
        assertThat(queen.getName()).isEqualTo("Q");
    }

    @DisplayName("퀸 이동 규칙에 어긋나는 경우 - 예외.")
    @Test
    void 퀸_이동_십자() {
        List<Piece> current = Arrays.asList(
                new Queen(Position.of('d', '8'), Color.BLACK));
        Pieces pieces = new Pieces(current);
        Position source = Position.of('d', '8'); // 비숍 위치
        Position target = Position.of('d', '1'); // 옮기고자 하는 위치
        Piece queen = pieces.findByPosition(source);

        queen.move(target, pieces);

        assertThat(queen.getPosition()).isEqualTo(target);
    }

    @DisplayName("퀸의 대각선 이동을 확인한다.")
    @Test
    void 퀸_이동_대각선() {
        List<Piece> current = Arrays.asList(
                new Queen(Position.of('d', '8'), Color.BLACK));
        Pieces pieces = new Pieces(current);
        Position source = Position.of('d', '8'); // 비숍 위치
        Position target = Position.of('b', '6'); // 옮기고자 하는 위치
        Piece queen = pieces.findByPosition(source);

        queen.move(target, pieces);

        assertThat(queen.getPosition()).isEqualTo(target);
    }

    @DisplayName("퀸의 대각선 이동을 확인한다. - 예외")
    @Test
    void 퀸_이동_규칙에_어긋나는_경우_이동_규칙_예외() {
        List<Piece> current = Arrays.asList(
                new Queen(Position.of('d', '8'), Color.BLACK));
        Pieces pieces = new Pieces(current);
        Position source = Position.of('d', '8'); // 비숍 위치
        Position target = Position.of('b', '1'); // 옮기고자 하는 위치

        Piece queen = pieces.findByPosition(source);

        assertThatThrownBy(() -> queen.move(target, pieces))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

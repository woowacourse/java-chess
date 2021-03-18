package chess.domain.piece;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QueenTest {
    private CurrentPieces currentPieces;

    @BeforeEach
    void setUp() {
        currentPieces = CurrentPieces.generate();
    }

    @DisplayName("Queen 객체 생성 확인")
    @Test
    void 퀸_객체_생성() {
        Queen queen = new Queen(Position.of('d', '8'), "Q");

        assertThat(queen.getPosition()).isEqualTo(Position.of('d', '8'));
        assertThat(queen.getName()).isEqualTo("Q");
    }

    @DisplayName("초기화된 퀸 객체들 생성 확인")
    @Test
    void 퀸_객체들_생성() {
        List<Queen> queens = Queen.generate();

        assertThat(queens.size()).isEqualTo(2);
    }

    @Test
    void 퀸_이동_십자() {
        List<Piece> current = Arrays.asList(
                new Queen(Position.of('d', '8'), "Q"));
        CurrentPieces currentPieces = new CurrentPieces(current);
        Position source = Position.of('d', '8'); // 비숍 위치
        Position target = Position.of('d', '1'); // 옮기고자 하는 위치
        Piece queen = currentPieces.findByPosition(source);

        queen.move(target, currentPieces);

        assertThat(queen.getPosition()).isEqualTo(target);
    }

    @Test
    void 퀸_이동_대각선() {
        List<Piece> current = Arrays.asList(
                new Queen(Position.of('d', '8'), "Q"));
        CurrentPieces currentPieces = new CurrentPieces(current);
        Position source = Position.of('d', '8'); // 비숍 위치
        Position target = Position.of('b', '6'); // 옮기고자 하는 위치
        Piece queen = currentPieces.findByPosition(source);

        queen.move(target, currentPieces);

        assertThat(queen.getPosition()).isEqualTo(target);
    }

    @Test
    void 퀸_이동_규칙에_어긋나는_경우_이동_규칙_예외() {
        List<Piece> current = Arrays.asList(
                new Queen(Position.of('d', '8'), "Q"));
        CurrentPieces currentPieces = new CurrentPieces(current);
        Position source = Position.of('d', '8'); // 비숍 위치
        Position target = Position.of('b', '1'); // 옮기고자 하는 위치

        Piece queen = currentPieces.findByPosition(source);

        assertThatThrownBy(() ->  queen.move(target, currentPieces))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 상대편_말을_공격한다_십자() {
        List<Piece> current = Arrays.asList(
                new Queen(Position.of('d', '8'), "Q"),
                new Pawn(Position.of('d','1'),"p"));
        CurrentPieces currentPieces = new CurrentPieces(current);

        Position source = Position.of('d', '8'); // 비숍 위치
        Position target = Position.of('d','1'); // 옮기고자 하는 위치
        Piece queen = currentPieces.findByPosition(source);

        queen.move(target, currentPieces);

        assertThat(currentPieces.getCurrentPieces().size()).isEqualTo(1);
    }

    @Test
    void 상대편_말을_공격한다_대각선() {
        List<Piece> current = Arrays.asList(
                new Queen(Position.of('d', '8'), "Q"),
                new Pawn(Position.of('b','6'),"p"));
        CurrentPieces currentPieces = new CurrentPieces(current);

        Position source = Position.of('d', '8'); // 비숍 위치
        Position target = Position.of('b','6'); // 옮기고자 하는 위치
        Piece queen = currentPieces.findByPosition(source);

        queen.move(target, currentPieces);

        assertThat(currentPieces.getCurrentPieces().size()).isEqualTo(1);
    }
}

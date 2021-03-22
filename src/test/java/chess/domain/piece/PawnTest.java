package chess.domain.piece;

import chess.domain.Color;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PawnTest {
    private Pieces initialPieces;

    @BeforeEach
    void setUp() {
        initialPieces = new Pieces(PieceFactory.initialPieces());
    }

    @DisplayName("Pawn 객체 생성 확인")
    @Test
    void 폰_객체_생성() {
        Pawn pawn = new Pawn(Position.of('a', '7'), Color.BLACK);

        assertThat(pawn.getPosition()).isEqualTo(Position.of('a', '7'));
        assertThat(pawn.getName()).isEqualTo("P");
    }

    @DisplayName("Pawn 규칙에 따라 처음 Pawn을 움직이는 경우 - 2칸 이동")
    @Test
    void pawn_처음으로_이동_2칸() {
        Pawn pawn = new Pawn(Position.of('a', '7'), Color.BLACK);

        pawn.move(Position.of('a', '5'), initialPieces);

        assertThat(pawn.getPosition()).isEqualTo(Position.of('a', '5'));
    }

    @DisplayName("Pawn 규칙에 따라 처음 Pawn을 움직이는 경우 예외 - 3칸 이동 ")
    @Test
    void pawn_처음으로_이동_3칸_예외() {
        Pawn pawn = new Pawn(Position.of('a', '7'), Color.BLACK);

        assertThatThrownBy(() -> pawn.move(Position.of('a', '4'), initialPieces))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("Pawn 규칙에 따라 이미 움직인 Pawn을 움직이는 경우 예외 - 2칸 이동 ")
    @Test
    void pawn_이미_이동_2칸_예외() {
        Pawn pawn = new Pawn(Position.of('a', '6'), Color.BLACK);

        assertThatThrownBy(() -> pawn.move(Position.of('a', '4'), initialPieces))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("이동 경로에 장애물이 있을 경우")
    @Test
    void 이동하는데_앞에_장애물이_있는_경우() {
        List<Piece> current = Arrays.asList(
                new Pawn(Position.of('a', '7'), Color.BLACK),
                new Pawn(Position.of('a', '6'), Color.BLACK));
        Pieces pieces = new Pieces(current);

        Position source = Position.of('a', '7'); // 비숍 위치
        Position target = Position.of('a', '5'); // 옮기고자 하는 위치
        Piece pawn = pieces.findByPosition(source);

        assertThatThrownBy(() -> pawn.move(target, pieces))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("검정 말이 흰 말을 공격한다.")
    @Test
    void 검은말이_상대편_말을_공격한다() {
        List<Piece> current = Arrays.asList(
                new Pawn(Position.of('a', '7'), Color.BLACK),
                new Pawn(Position.of('b', '6'), Color.WHITE));
        Pieces pieces = new Pieces(current);

        Position source = Position.of('a', '7'); // 비숍 위치
        Position target = Position.of('b', '6'); // 옮기고자 하는 위치
        Piece pawn = pieces.findByPosition(source);

        pawn.move(target, pieces);

        assertThat(pieces.getPieces().size()).isEqualTo(1);
    }

    @DisplayName("흰 말이 검정 말을 공격한다.")
    @Test
    void 흰말이_상대편_말을_공격한다() {
        List<Piece> current = Arrays.asList(
                new Pawn(Position.of('a', '2'), Color.WHITE),
                new Pawn(Position.of('b', '3'), Color.BLACK));
        Pieces pieces = new Pieces(current);

        Position source = Position.of('a', '2');
        Position target = Position.of('b', '3');
        Piece pawn = pieces.findByPosition(source);

        pawn.move(target, pieces);

        assertThat(pieces.getPieces().size()).isEqualTo(1);
    }

    @DisplayName("흰 말이 검정 말을 공격한다. - 말이 없을 경우")
    @Test
    void 흰말이_상대편_말을_공격한다_예외() {
        List<Piece> current = Arrays.asList(
                new Pawn(Position.of('a', '2'), Color.WHITE));
        Pieces pieces = new Pieces(current);

        Position source = Position.of('a', '2');
        Position target = Position.of('b', '3');
        Piece pawn = pieces.findByPosition(source);

        assertThatThrownBy(() -> pawn.move(target, pieces))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

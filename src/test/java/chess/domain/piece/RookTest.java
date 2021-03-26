package chess.domain.piece;

import chess.domain.piece.info.Color;
import chess.domain.piece.info.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RookTest {

    @DisplayName("Rook 객체 생성 확인")
    @Test
    void 룩_객체_생성() {
        Rook rook = new Rook(Position.of('a', '8'), "P", Color.BLACK);

        assertThat(rook.getPosition()).isEqualTo(Position.of('a', '8'));
        assertThat(rook.getName()).isEqualTo("P");
    }

    @DisplayName("초기화된 룩 객체들 생성 확인")
    @Test
    void 룩_객체들_생성() {
        List<Rook> rooks = Rook.generate();

        assertThat(rooks.size()).isEqualTo(4);
    }

    @Test
    void 룩_이동() {
        List<Piece> current = Arrays.asList(
                new Rook(Position.of('a', '8'), "R", Color.BLACK));
        CurrentPieces currentPieces = new CurrentPieces(current);
        Position source = Position.of('a', '8'); // 비숍 위치
        Position target = Position.of('a', '1'); // 옮기고자 하는 위치
        Piece rook = currentPieces.findByPosition(source);

        rook.move(target, currentPieces);

        assertThat(rook.getPosition()).isEqualTo(target);
    }

    @Test
    void 룩_이동_규칙에_어긋나는_경우_예() {
        List<Piece> current = Arrays.asList(
                new Rook(Position.of('a', '8'), "R", Color.BLACK));
        CurrentPieces currentPieces = new CurrentPieces(current);
        Position source = Position.of('a', '8'); // 비숍 위치
        Position target = Position.of('b', '1'); // 옮기고자 하는 위치

        Piece rook = currentPieces.findByPosition(source);

        assertThatThrownBy(() ->  rook.move(target, currentPieces))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 상대편_말을_공격한다() {
        List<Piece> current = Arrays.asList(
                new Rook(Position.of('a', '8'), "R", Color.BLACK),
                new Pawn(Position.of('a','5'),"p", Color.WHITE));
        CurrentPieces currentPieces = new CurrentPieces(current);

        Position source = Position.of('a', '8'); // 비숍 위치
        Position target = Position.of('a','5'); // 옮기고자 하는 위치
        Piece rook = currentPieces.findByPosition(source);

        rook.move(target, currentPieces);

        assertThat(currentPieces.getCurrentPieces().size()).isEqualTo(1);
    }
}

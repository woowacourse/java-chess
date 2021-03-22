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

public class KingTest {
    private CurrentPieces currentPieces;

    @BeforeEach
    void setUp() {
        currentPieces = CurrentPieces.generate();
    }

    @DisplayName("King 객체 생성 확인")
    @Test
    void 킹_객체_생성() {
        King king = new King(Position.of('e', '8'), "K", Color.BLACK);

        assertThat(king.getPosition()).isEqualTo(Position.of('e', '8'));
        assertThat(king.getName()).isEqualTo("K");
    }

    @DisplayName("초기화된 King 객체들 생성 확인")
    @Test
    void 킹_객체들_생성() {
        List<King> kings = King.generate();

        assertThat(kings.size()).isEqualTo(2);
    }

    @Test
    void 킹_이동_십자() {
        List<Piece> current = Arrays.asList(
                new King(Position.of('e', '8'), "K", Color.BLACK));
        CurrentPieces currentPieces = new CurrentPieces(current);
        Position source = Position.of('e', '8'); // 비숍 위치
        Position target = Position.of('e', '7'); // 옮기고자 하는 위치
        Piece king = currentPieces.findByPosition(source);

        king.move(target, currentPieces);

        assertThat(king.getPosition()).isEqualTo(target);
    }

    @Test
    void 킹_이동_대각선() {
        List<Piece> current = Arrays.asList(
                new King(Position.of('e', '8'), "K", Color.BLACK));
        CurrentPieces currentPieces = new CurrentPieces(current);
        Position source = Position.of('e', '8'); //
        Position target = Position.of('f', '7'); // 옮기고자 하는 위치
        Piece king = currentPieces.findByPosition(source);

        king.move(target, currentPieces);

        assertThat(king.getPosition()).isEqualTo(target);
    }

    @Test
    void 킹_이동_규칙에_어긋나는_경우_이동_규칙_예외() {
        List<Piece> current = Arrays.asList(
                new King(Position.of('e', '8'), "K", Color.BLACK));
        CurrentPieces currentPieces = new CurrentPieces(current);
        Position source = Position.of('e', '8'); // 비숍 위치
        Position target = Position.of('b', '1'); // 옮기고자 하는 위치

        Piece king = currentPieces.findByPosition(source);

        assertThatThrownBy(() ->  king.move(target, currentPieces))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 상대편_말을_공격한다_십자() {
        List<Piece> current = Arrays.asList(
                new King(Position.of('d', '8'), "K", Color.BLACK),
                new Pawn(Position.of('d','7'),"p", Color.WHITE));
        CurrentPieces currentPieces = new CurrentPieces(current);
        Position source = Position.of('d', '8'); // 비숍 위치

        Position target = Position.of('d','7'); // 옮기고자 하는 위치
        Piece king = currentPieces.findByPosition(source);
        king.move(target, currentPieces);

        assertThat(currentPieces.getCurrentPieces().size()).isEqualTo(1);
    }

    @Test
    void 상대편_말을_공격한다_대각선() {
        List<Piece> current = Arrays.asList(
                new King(Position.of('d', '8'), "K", Color.BLACK),
                new Pawn(Position.of('e','7'),"p", Color.WHITE));
        CurrentPieces currentPieces = new CurrentPieces(current);

        Position source = Position.of('d', '8'); // 비숍 위치
        Position target = Position.of('e','7'); // 옮기고자 하는 위치
        Piece king = currentPieces.findByPosition(source);

        king.move(target, currentPieces);

        assertThat(currentPieces.getCurrentPieces().size()).isEqualTo(1);
    }
}

package chess.domain.piece;

import chess.domain.piece.info.Diagonal;
import chess.domain.piece.info.Color;
import chess.domain.piece.info.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BishopTest {
    private CurrentPieces currentPieces;

    @BeforeEach
    void setUp() {
        currentPieces = CurrentPieces.generate();
    }

    @DisplayName("Bishop 객체 생성 확인")
    @Test
    void 비숍_객체_생성() {
        Bishop bishop = new Bishop(Position.of('c', '8'), "B", Color.BLACK);

        assertThat(bishop.getPosition()).isEqualTo(Position.of('c', '8'));
        assertThat(bishop.getName()).isEqualTo("B");
    }

    @DisplayName("초기화된 Bishop 객체들 생성 확인")
    @Test
    void 비숍_객체들_생성() {
        List<Bishop> bishops = Bishop.generate();

        assertThat(bishops.size()).isEqualTo(4);
    }

    @DisplayName("Bishop 규칙에 따른 이동")
    @Test
    void 비숍_이동_확인() {
        List<Piece> current = Arrays.asList(
                new Bishop(Position.of('c', '8'), "B", Color.BLACK));
        CurrentPieces pieces = new CurrentPieces(current);
        Position source = Position.of('c', '8'); // 비숍 위치
        Position target = Position.of('e', '6'); // 옮기고자 하는 위치
        Piece bishop = pieces.findByPosition(source);

        bishop.move(target, pieces);

        assertThat(bishop.getPosition()).isEqualTo(target);
    }

    @Test
    void 비숍_이동에_장애물() {
        CurrentPieces currentPieces = CurrentPieces.generate();
        Position source = Position.of('f', '8'); // 비숍 위치
        Position target = Position.of('h', '6'); // 옮기고자 하는 위치
        Piece bishop = currentPieces.findByPosition(source);
        Diagonal bishopD = Diagonal.findDiagonalByTwoPosition(source, target);
        assertThatThrownBy((() ->bishopD.hasPieceInPath(bishop.getPosition(), target, currentPieces)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 비숍_이동() {
        List<Piece> current = Arrays.asList(
                new Bishop(Position.of('f', '8'), "B", Color.BLACK));
        CurrentPieces currentPieces = new CurrentPieces(current);
        Position source = Position.of('f', '8'); // 비숍 위치
        Position target = Position.of('h', '6'); // 옮기고자 하는 위치
        Piece bishop = currentPieces.findByPosition(source);

        bishop.move(target, currentPieces);

        assertThat(bishop.getPosition()).isEqualTo(target);
    }

    @Test
    void 비숍_이동_규칙에_어긋나는_경우_예() {
        List<Piece> current = Arrays.asList(
                new Bishop(Position.of('f', '8'), "B", Color.BLACK));
        CurrentPieces currentPieces = new CurrentPieces(current);
        Position source = Position.of('f', '8'); // 비숍 위치
        Position target = Position.of('g', '6'); // 옮기고자 하는 위치
        Piece bishop = currentPieces.findByPosition(source);

        assertThatThrownBy(() ->  bishop.move(target, currentPieces))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("[ERROR] 올바른 방향이 아닙니다.");
    }

    @Test
    void 상대편_말을_공격한다() {
        List<Piece> current = Arrays.asList(
                new Bishop(Position.of('c', '8'), "B", Color.BLACK),
                new Pawn(Position.of('f','5'),"p", Color.WHITE));
        CurrentPieces currentPieces = new CurrentPieces(current);

        Position source = Position.of('c', '8'); // 비숍 위치
        Position target = Position.of('f','5'); // 옮기고자 하는 위치
        Piece bishop = currentPieces.findByPosition(source);

        bishop.move(target, currentPieces);

        assertThat(currentPieces.getCurrentPieces().size()).isEqualTo(1);
    }
}

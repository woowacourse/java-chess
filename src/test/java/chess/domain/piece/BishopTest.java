package chess.domain.piece;

import chess.domain.Diagonal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        Bishop bishop = new Bishop(Position.of('c', '8'), "B");

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
        Bishop bishop = new Bishop(Position.of('c', '8'), "B");

        bishop.move(Position.of('e', '6'), currentPieces);

        assertThat(bishop.getPosition()).isEqualTo(Position.of('e', '6'));
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
//
//    @Test
//    void 비숍_이동() {
//        CurrentPieces currentPieces = CurrentPieces.generate();
//        Position source = Position.of('h', '6'); // 비숍 위치
//        Position target = Position.of('g', '5'); // 옮기고자 하는 위치
//        Piece bishop = currentPieces.findByPosition(source);
//        System.out.println(bishop);
//        Diagonal bishopD = Diagonal.findDiagonalByTwoPosition(source, target);
//        System.out.println(bishopD);
//        bishopD.hasPieceInPath(bishop.getPosition(), target, currentPieces);
//
//    }
}

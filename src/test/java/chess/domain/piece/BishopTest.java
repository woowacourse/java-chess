package chess.domain.piece;

import chess.domain.Color;
import chess.domain.position.Diagonal;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BishopTest {
    private Pieces initialPieces;

    @BeforeEach
    void setUp() {
        initialPieces = new Pieces(PieceFactory.initialPieces());
    }

    @DisplayName("Bishop 객체 생성 확인")
    @Test
    void 비숍_객체_생성() {
        Bishop bishop = new Bishop(Position.of('c', '8'), Color.BLACK);

        assertThat(bishop.getPosition()).isEqualTo(Position.of('c', '8'));
        assertThat(bishop.getName()).isEqualTo("B");
    }

    @DisplayName("초기화된 Bishop 객체들 생성 확인")
    @Test
    void 비숍_객체들_생성() {
        List<Bishop> bishops = Bishop.initialBishops();

        assertThat(bishops.size()).isEqualTo(4);
    }

    @DisplayName("Bishop 규칙에 따른 이동")
    @Test
    void 비숍_이동_확인() {
        List<Piece> currentPieces = Arrays.asList(
                new Bishop(Position.of('c', '8'), Color.BLACK));
        Pieces pieces = new Pieces(currentPieces);
        Position source = Position.of('c', '8');
        Position target = Position.of('e', '6');
        Piece bishop = pieces.findByPosition(source);

        bishop.move(target, pieces);
        Position result = bishop.getPosition();

        assertThat(result).isEqualTo(target);
    }

    @DisplayName("비숍 이동 경로에 장애물이 있을 경우 예외")
    @Test
    void 비숍_이동에_장애물() {
        Position source = Position.of('f', '8');
        Position target = Position.of('h', '6');
        Piece bishop = initialPieces.findByPosition(source);
        Diagonal bishopD = Diagonal.findDiagonalByTwoPosition(source, target);

        assertThatThrownBy((() ->
                bishopD.hasPieceInPath(bishop.getPosition(), target, initialPieces)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("대각선 이동이 아닌 경우 예외")
    @Test
    void 비숍_이동_규칙에_어긋나는_경우_예외() {
        Position source = Position.of('f', '8');
        Position target = Position.of('g', '6');
        Piece bishop = initialPieces.findByPosition(source);
        initialPieces.removePieceByPosition(Position.of('g', '7'));

        assertThatThrownBy(() -> bishop.move(target, initialPieces))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

package chess.domain.piece;

import chess.domain.piece.info.Color;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        Bishop bishop = new Bishop(Position.of("c8"), Color.BLACK);

        assertThat(bishop.isSamePosition(Position.of("c8"))).isTrue();
        assertThat(bishop.getName()).isEqualTo("B");
    }

    @DisplayName("Bishop 이동")
    @Test
    void 비숍_이동_확인() {
        Position target = Position.of("e6");
        Piece bishop = new Bishop(Position.of("c8"), Color.BLACK);

        bishop.move(target);

        assertThat(bishop.isSamePosition(target)).isTrue();
    }

    @DisplayName("대각선 이동이 아닌 경우 예외")
    @Test
    void 비숍_이동_규칙에_어긋나는_경우_예외() {
        Position source = Position.of("f8");
        Position target = Position.of("g6");
        Piece bishop = initialPieces.findByPosition(source);
        Piece targetPiece = initialPieces.findByPosition(target);
        Direction direction = Direction.findDirectionByTwoPosition(source, target);

        assertThatThrownBy((() ->
                bishop.checkMovable(targetPiece, direction)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

package domain;

import static fixture.PositionFixture.A1;
import static fixture.PositionFixture.A2;
import static fixture.PositionFixture.A3;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.piece.Empty;
import domain.piece.Piece;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class PieceTest {

    private Piece piece;
    @BeforeEach
    void setUp() {
        piece = new Piece(Side.BLACK) {
            @Override
            public boolean isRuleBroken(Position current, Position target, Map<Position, Piece> pieces) {
                return false;
            }
        };
    }

    @DisplayName("source 위치와 target 위치가 같으면 이동할 수 없다.")
    @Test
    void checkDifferentPosition() {
        Position source = A1();
        Position target = A1();
        MovePath movePath = new MovePath(new PathPieces(), new Empty());

        assertThatThrownBy(() -> piece.checkValidMove(source, target, movePath))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("source 위치와 target 위치가 같으면 이동할 수 없습니다.");
    }

    @DisplayName("target 위치에 같은 색의 기물이 존재하면 이동할 수 없다.")
    @Test
    void checkNoSameColorPieceAtTarget() {
        Position source = A1();
        Position target = A2();
        MovePath movePath = new MovePath(new PathPieces(), new Pawn(Side.BLACK));

        assertThatThrownBy(() -> piece.checkValidMove(source, target, movePath))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("target 위치에 같은 색의 기물이 존재하면 이동할 수 없습니다.");
    }

    @DisplayName("source 위치에서 target 위치까지의 경로에 기물이 존재하면 이동할 수 없다.")
    @Test
    void pieceExistsOnPathTest() {
        Position source = A1();
        Position target = A3();
        MovePath movePath = MovePath.create(List.of(new Rook(Side.BLACK)), new Empty());

        assertThatThrownBy(() -> piece.checkValidMove(source, target, movePath))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("source 위치에서 target 위치까지의 경로에 기물이 존재하면 이동할 수 없습니다.");
    }
}

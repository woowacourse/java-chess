package domain;

import fixture.PositionFixture;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class PieceTest {

    @DisplayName("target 위치에 같은 팀 기물이 존재하면 예외가 발생한다.")
    @Test
    void sameSideAtTargetPositionTest() {
        Piece piece = new Piece(Side.BLACK) {
            @Override
            public boolean canMove(Position current, Position target, Map<Position, Piece> pieces) {
                return false;
            }
        };

        Position target = PositionFixture.a4();

        Map<Position, Piece> pieces = new LinkedHashMap<>() {{
            put(target, new Pawn(Side.BLACK));
        }};

        Assertions.assertThatThrownBy(() -> piece.checkBlockingPiece(target, pieces))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("target 위치로 이동하는 경로에 기물이 존재하면 예외가 발생한다.")
    @Test
    void pieceExistsOnPathTest() {
        Piece piece = new Piece(Side.BLACK) {
            @Override
            public boolean canMove(Position current, Position target, Map<Position, Piece> pieces) {
                return false;
            }
        };

        Position target = PositionFixture.a4();

        Map<Position, Piece> pieces = new LinkedHashMap<>() {{
            put(new Position(File.A, Rank.TWO), new Pawn(Side.BLACK));
        }};

        Assertions.assertThatThrownBy(() -> piece.checkBlockingPiece(target, pieces))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

package chess.domain;

import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Rook;
import chess.domain.postion.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static chess.domain.piece.PieceFixture.*;
import static chess.domain.postion.File.*;
import static chess.domain.postion.Rank.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DirectionTest {

    @DisplayName("source와 target이 어떤 방향인지 확인하는 테스트")
    @Test
    void beMoveDirection_Rook() {
        Rook rook = WHITE_ROOK;
        List<Direction> directions = Direction.getRookDirection();
        Position source = new Position(A, FOUR);
        Position target = new Position(A, EIGHT);

        assertThat(Direction.beMoveDirection(directions, source, target))
                .isEqualTo(Direction.TOP);
    }

    @DisplayName("source와 target이 어떤 방향인지 확인하는 테스트")
    @Test
    void beMoveDirection_Knight() {
        Knight knight = WHITE_KNIGHT;
        List<Direction> directions = Direction.getKnightDirection();
        Position source = new Position(G, ONE);
        Position target = new Position(H, THREE);

        assertThat(Direction.beMoveDirection(directions, source, target))
                .isEqualTo(Direction.TTR);
    }

    @DisplayName("source와 target이 어떤 방향인지 확인하는 테스트")
    @Test
    void beMoveDirection_Pawn() {
        Pawn pawn = WHITE_PAWN;
        List<Direction> directions = Direction.getWhitePawnDirection();
        Position source = new Position(G, TWO);
        Position target = new Position(G, FOUR);

        assertThat(Direction.beMoveDirection(directions, source, target))
                .isEqualTo(Direction.TOP);
    }

    @DisplayName("파라미터의 방향들 중에 방향이 없을 때 에러 테스트")
    @Test
    void beMoveDirection_exception() {
        Knight knight = WHITE_KNIGHT;
        List<Direction> directions = Direction.getBishopDirection();
        Position source = new Position(G, ONE);
        Position target = new Position(H, THREE);

        assertThatThrownBy(() -> Direction.beMoveDirection(directions, source, target))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

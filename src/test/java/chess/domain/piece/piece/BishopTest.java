package chess.domain.piece.piece;

import chess.domain.move.Move;
import chess.domain.move.MoveFactory;
import chess.domain.piece.Bishop;
import chess.domain.piece.Piece;
import chess.domain.piece.position.Position;
import chess.domain.piece.team.BlackTeam;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BishopTest {
    private Piece bishop;
    private Position source;

    @BeforeEach
    void setUp() {
        source = Position.of("c1");
        bishop = new Bishop(source, new BlackTeam());
    }

    @DisplayName("movable 의 인자가 null 일때 테스트")
    @Test
    void validateMovePatternNull() {
        assertThatThrownBy(() -> bishop.validateMovePattern(null, null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("이동 성공 테스트")
    void validateMovePattern() {
        Position target = Position.of("a3");

        Move move = MoveFactory.findMovePattern(source, target);

        bishop.validateMovePattern(move, null);
    }

    @Test
    @DisplayName("이동 실패 테스트")
    void validateMovePatternWithError() {
        Position target = Position.of("a4");
        assertThatThrownBy(() -> MoveFactory.findMovePattern(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 말이 갈 수 없는 칸입니다");
    }
}

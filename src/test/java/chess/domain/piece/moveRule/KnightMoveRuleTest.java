package chess.domain.piece.moveRule;

import static chess.domain.piece.moveRule.TestFixture.A1;
import static chess.domain.piece.moveRule.TestFixture.B2;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Color;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class KnightMoveRuleTest {
    private final MoveRule moveRule = KnightMoveRule.getInstance();
    private Piece blackPiece;
    private Piece whitePiece;
    private Map<Position, Piece> board;

    @BeforeAll
    void setUp() {
        blackPiece = Knight.from(Color.BLACK);
        whitePiece = Knight.from(Color.WHITE);
    }

    @BeforeEach
    void initBoard() {
        board = new HashMap<>();
    }

    @Test
    void 나이트_움직임_실패() {
        board.put(A1, blackPiece);
        board.put(B2, whitePiece);

        assertThatThrownBy(() -> moveRule.validateMovement(A1, B2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("나이트는 L 모양으로만 움직일 수 있습니다.");
    }
}

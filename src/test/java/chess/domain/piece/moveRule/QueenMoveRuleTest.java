package chess.domain.piece.moveRule;

import static chess.domain.piece.moveRule.TestFixture.A1;
import static chess.domain.piece.moveRule.TestFixture.B3;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class QueenMoveRuleTest {

    private final MoveRule moveRule = QueenMoveRule.getInstance();
    private Piece blackPiece;
    private Piece whitePiece;
    private Map<Position, Piece> board;

    @BeforeAll
    void setUp() {
        blackPiece = Queen.from(Color.BLACK);
        whitePiece = Queen.from(Color.WHITE);
    }

    @BeforeEach
    void initBoard() {
        board = new HashMap<>();
    }

    @Test
    void 퀸_움직임_실패() {
        board.put(A1, blackPiece);
        board.put(B3, whitePiece);

        assertThatThrownBy(() -> moveRule.validateMovement(A1, B3))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("퀸은 대각선상 또는 직선상으로만 움직일 수 있습니다.");
    }
}

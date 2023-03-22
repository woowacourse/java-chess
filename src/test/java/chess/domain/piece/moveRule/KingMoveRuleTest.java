package chess.domain.piece.moveRule;

import static chess.domain.piece.moveRule.TestFixture.A1;
import static chess.domain.piece.moveRule.TestFixture.A3;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.piece.Color;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class KingMoveRuleTest {
    private final MoveRule moveRule = KingMoveRule.getInstance();
    private Piece blackPiece;
    private Piece whitePiece;
    private Map<Position, Piece> board;

    @BeforeAll
    void setUp() {
        blackPiece = King.from(Color.BLACK);
        whitePiece = King.from(Color.WHITE);
    }

    @BeforeEach
    void initBoard() {
        board = new HashMap<>();
    }

    @Test
    void 킹_움직임_실패() {
        board.put(A1, blackPiece);
        board.put(A3, whitePiece);

        assertThatThrownBy(() -> moveRule.validateMovement(A1, A3))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("킹은 인접한 칸으로만 이동할 수 있습니다.");
    }
}

package chess.domain.piece.move_rule;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.HashMap;
import java.util.Map;

import static chess.domain.piece.move_rule.TestFixture.A1;
import static chess.domain.piece.move_rule.TestFixture.A3;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class KingMoveRuleTest {
    private Piece blackPiece;
    private Piece whitePiece;
    private MoveRule moveRule = KingMoveRule.getInstance();
    private Map<Position, Piece> board;

    @BeforeAll
    void setUp() {
        blackPiece = new Piece(moveRule, Color.BLACK);
        whitePiece = new Piece(moveRule, Color.WHITE);
    }

    @BeforeEach
    void initBoard() {
        board = new HashMap<>();
    }

    @Test
    void 킹_움직임_실패() {
        board.put(A1, blackPiece);
        board.put(A3, whitePiece);

        assertThatThrownBy(() -> moveRule.move(A1, A3))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("킹은 인접한 칸으로만 이동할 수 있습니다.");
    }
}

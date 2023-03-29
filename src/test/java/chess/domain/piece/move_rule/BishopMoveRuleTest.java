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

import static chess.domain.piece.move_rule.TestFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BishopMoveRuleTest {
    private Piece blackPiece;
    private Piece whitePiece;
    private MoveRule moveRule = BishopMoveRule.getInstance();
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
    void 비숍_움직임_실패() {
        board.put(A1, blackPiece);
        board.put(B3, whitePiece);

        assertThatThrownBy(() -> moveRule.move(A1, B3))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("비숍은 대각선상으로만 움직일 수 있습니다.");
    }


    @Test
    void 비숍_움직임_실패_중간경로에_기물_존재() {
        board.put(B2, whitePiece);
        board.put(A1, whitePiece);
        board.put(D4, blackPiece);

        assertThat(moveRule.move(A1, D4)).containsExactly(B2, C3);
    }
}

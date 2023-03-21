package chess.domain.piece.moveRule;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.HashMap;
import java.util.Map;

import static chess.domain.piece.moveRule.TestFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class QueenMoveRuleTest {

    private Piece blackPiece;
    private Piece whitePiece;
    private MoveRule moveRule = QueenMoveRule.getInstance();
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
    void 퀸_움직임_실패() {
        board.put(A1, blackPiece);
        board.put(B3, whitePiece);

        assertThatThrownBy(() -> moveRule.move(A1, B3))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("퀸은 대각선 또는 직선 상으로만 움직일 수 있습니다.");
    }

    @Test
    void 퀸_움직임_실패_중간경로에_기물_존재() {
        board.put(B1, whitePiece);
        board.put(A1, whitePiece);
        board.put(D1, blackPiece);

        assertThat(moveRule.move(A1, D1)).containsExactly(B1, C1);
    }
}

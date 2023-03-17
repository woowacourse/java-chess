package chess.domain.piece.moveRule;

import static chess.domain.piece.moveRule.TestFixture.A1;
import static chess.domain.piece.moveRule.TestFixture.A5;
import static chess.domain.piece.moveRule.TestFixture.A7;
import static chess.domain.piece.moveRule.TestFixture.B2;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PawnMoveRuleTest {
    private Piece blackPiece;
    private Piece whitePiece;
    private final MoveRule blackMoveRule = PawnMoveRule.of(Color.BLACK);
    private final MoveRule whiteMoveRule = PawnMoveRule.of(Color.WHITE);
    private Map<Position, Piece> board;

    @BeforeAll
    void setUp() {
        blackPiece = new Piece(blackMoveRule, Color.BLACK);
        whitePiece = new Piece(whiteMoveRule, Color.WHITE);
    }

    @BeforeEach
    void initBoard() {
        board = new HashMap<>();
    }

    @Test
    void 폰_대각선_움직임() {
        board.put(B2, blackPiece);
        board.put(A1, whitePiece);

        blackMoveRule.move(B2, A1, board);
        assertThat(board.get(A1)).isEqualTo(blackPiece);
    }

    @Test
    void 폰_움직임_성공_두칸이동() {
        board.put(A7, blackPiece);
        blackMoveRule.move(A7, A5, board);

        assertThat(board.get(A5)).isEqualTo(blackPiece);
    }
}

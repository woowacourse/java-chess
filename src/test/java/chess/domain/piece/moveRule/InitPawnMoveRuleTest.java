package chess.domain.piece.moveRule;

import static chess.domain.piece.moveRule.TestFixture.A1;
import static chess.domain.piece.moveRule.TestFixture.A5;
import static chess.domain.piece.moveRule.TestFixture.A7;
import static chess.domain.piece.moveRule.TestFixture.B2;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.piece.InitPawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Color;
import chess.domain.piece.moveRule.pawn.BlackPawnMoveRule;
import chess.domain.piece.moveRule.pawn.WhitePawnMoveRule;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class InitPawnMoveRuleTest {
    private final MoveRule blackMoveRule = BlackPawnMoveRule.getInstance();
    private final MoveRule whiteMoveRule = WhitePawnMoveRule.getInstance();
    private Piece blackPiece;
    private Piece whitePiece;
    private Map<Position, Piece> board;

    @BeforeAll
    void setUp() {
        blackPiece = InitPawn.from(Color.BLACK);
        whitePiece = InitPawn.from(Color.WHITE);
    }

    @BeforeEach
    void initBoard() {
        board = new HashMap<>();
    }

    @Test
    void 폰_대각선_움직임() {
        board.put(B2, blackPiece);
        board.put(A1, whitePiece);
        assertDoesNotThrow(() -> blackMoveRule.validateMovement(B2, A1));
    }

    @Test
    void 폰_움직임_성공_두칸이동() {
        board.put(A7, blackPiece);
        assertDoesNotThrow(() -> blackMoveRule.validateMovement(A7, A5));
    }
}

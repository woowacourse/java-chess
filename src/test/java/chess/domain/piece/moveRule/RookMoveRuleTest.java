package chess.domain.piece.moveRule;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.moveRule.BishopMoveRule;
import chess.domain.piece.moveRule.MoveRule;
import chess.domain.piece.moveRule.RookMoveRule;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static chess.domain.piece.moveRule.TestFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RookMoveRuleTest {
    private Piece blackPiece;
    private Piece whitePiece;
    private MoveRule moveRule = RookMoveRule.getInstance();
    private Map<Position, Piece> board;

    @BeforeAll
    void setUp(){
        blackPiece = new Piece(moveRule, Color.BLACK);
        whitePiece = new Piece(moveRule, Color.WHITE);
    }

    @BeforeEach
    void initBoard() {
        board = new HashMap<>();
    }

    @Test
    void 룩_움직임_실패() {
        board.put(A1, blackPiece);
        board.put(B3, whitePiece);

        assertThatThrownBy(() -> moveRule.move(A1, B3, board))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("룩은 직선상으로만 움직일 수 있습니다.");
    }

    @Test
    void 룩_움직임_실패_중간경로에_기물_존재() {
        board.put(B1, whitePiece);
        board.put(A1, whitePiece);
        board.put(D1, blackPiece);

        assertThatThrownBy(() -> moveRule.move(A1, D1, board))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("경로상에 다른 기물이 있어 움직일 수 없습니다.");
    }
}

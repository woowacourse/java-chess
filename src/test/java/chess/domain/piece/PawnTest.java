package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Team;
import chess.domain.exceptions.InvalidDirectionException;
import chess.domain.exceptions.InvalidDistanceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static chess.domain.utils.InputParser.position;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PawnTest {

    Piece blackPiece;
    Piece whitePiece;
    Position base;

    @BeforeEach
    void setUp() {
        blackPiece = new Pawn(Team.BLACK);
        whitePiece = new Pawn(Team.WHITE);
        base = position("b4");
    }

    @Test
    void 처음_2칸_이동_여부_테스트() {
        assertTrue(blackPiece.canMove(base, position("b2")));
        assertTrue(whitePiece.canMove(base, position("b6")));
    }

    @Test
    void 연속으로_2칸_이동하는_경우_예외_테스트() {
        Position testPostion = position("b6");
        blackPiece.canMove(testPostion, position("b4"));
        assertThrows(InvalidDistanceException.class, () -> blackPiece.canMove(testPostion, position("b2")));
    }

    @Test
    void black_Pawn_이동_예외_테스트() {
        assertThrows(InvalidDirectionException.class, () -> blackPiece.canMove(base, position("a4"))); // 좌
        assertThrows(InvalidDirectionException.class, () -> blackPiece.canMove(base, position("c4"))); // 우
        assertThrows(InvalidDirectionException.class, () -> blackPiece.canMove(base, position("b5"))); // 상
    }

    @Test
    void white_Pawn_이동_예외_테스트() {
        assertThrows(InvalidDirectionException.class, () -> whitePiece.canMove(base, position("a4"))); // 좌
        assertThrows(InvalidDirectionException.class, () -> whitePiece.canMove(base, position("c4"))); // 우
        assertThrows(InvalidDirectionException.class, () -> whitePiece.canMove(base, position("b3"))); // 하
    }

    @Test
    void 대각선_이동_여부_테스트() {
        assertThrows(InvalidDirectionException.class, () -> blackPiece.canMove(base, position("c3")));
    }

    @Test
    void 거리가_제한에_위반되는_경우_에외_테스트() {
        assertThrows(InvalidDistanceException.class, () -> {
            blackPiece.canMove(base, position("b1"));
            whitePiece.canMove(base, position("b7"));
        });
    }
}

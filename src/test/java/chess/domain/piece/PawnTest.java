package chess.domain.piece;

import chess.domain.Coordinate;
import chess.domain.Position;
import chess.domain.Team;
import chess.domain.exceptions.InvalidDirectionException;
import chess.domain.exceptions.InvalidDistanceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        base = new Position(new Coordinate('b'), new Coordinate(4));
    }

    @Test
    void 처음_2칸_이동_여부_테스트() {
        assertTrue(blackPiece.canMove(base, new Position(new Coordinate('b'), new Coordinate(2))));
        assertTrue(whitePiece.canMove(base, new Position(new Coordinate('b'), new Coordinate(6))));
    }

    @Test
    void black_Pawn_이동_예외_테스트() {
        assertThrows(InvalidDirectionException.class, () -> blackPiece.canMove(base, new Position(new Coordinate('a'), new Coordinate(4)))); // 좌
        assertThrows(InvalidDirectionException.class, () -> blackPiece.canMove(base, new Position(new Coordinate('c'), new Coordinate(4)))); // 우
        assertThrows(InvalidDirectionException.class, () -> blackPiece.canMove(base, new Position(new Coordinate('b'), new Coordinate(5)))); // 상
    }

    @Test
    void white_Pawn_이동_예외_테스트() {
        assertThrows(InvalidDirectionException.class, () -> whitePiece.canMove(base, new Position(new Coordinate('a'), new Coordinate(4)))); // 좌
        assertThrows(InvalidDirectionException.class, () -> whitePiece.canMove(base, new Position(new Coordinate('c'), new Coordinate(4)))); // 우
        assertThrows(InvalidDirectionException.class, () -> whitePiece.canMove(base, new Position(new Coordinate('b'), new Coordinate(3)))); // 하
    }

    @Test
    void 대각선_이동_여부_테스트() {
        assertThrows(InvalidDirectionException.class, () -> blackPiece.canMove(base, new Position(new Coordinate('c'), new Coordinate(3))));
    }

    @Test
    void 거리가_제한에_위반되는_경우_에외_테스트() {
        assertThrows(InvalidDistanceException.class, () -> {
            blackPiece.canMove(base, new Position(new Coordinate('b'), new Coordinate(1)));
            whitePiece.canMove(base, new Position(new Coordinate('b'), new Coordinate(7)));
        });
    }
}

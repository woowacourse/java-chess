package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Team;
import chess.domain.exceptions.InvalidDirectionException;
import chess.domain.exceptions.InvalidDistanceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static chess.domain.utils.InputParser.position;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PawnTest {

    Piece blackPiece;
    Piece whitePiece;
    Optional<Team> optionalWhiteTeam;
    Optional<Team> optionalBlackTeam;
    Position base;

    @BeforeEach
    void setUp() {
        blackPiece = new Pawn(Team.BLACK);
        optionalBlackTeam = Optional.of(Team.BLACK);
        whitePiece = new Pawn(Team.WHITE);
        optionalWhiteTeam = Optional.of(Team.WHITE);
        base = position("b4");
    }

    @Test
    void 처음_2칸_이동_여부_테스트() {
        assertTrue(blackPiece.canMove(base, position("b2"), Optional.empty()));
        assertTrue(whitePiece.canMove(base, position("b6"), Optional.empty()));
    }

    @Test
    void 연속으로_2칸_이동하는_경우_예외_테스트() {
        Position testPostion = position("b6");
        blackPiece.canMove(testPostion, position("b4"), Optional.empty());
        assertThrows(InvalidDistanceException.class, () -> blackPiece.canMove(testPostion, position("b2"), Optional.empty()));
    }

    @Test
    void black_Pawn_이동_예외_테스트() {
        assertThrows(InvalidDirectionException.class, () -> blackPiece.canMove(base, position("a4"), optionalWhiteTeam)); // 좌
        assertThrows(InvalidDirectionException.class, () -> blackPiece.canMove(base, position("c4"), optionalWhiteTeam)); // 우
        assertThrows(InvalidDirectionException.class, () -> blackPiece.canMove(base, position("b5"), optionalWhiteTeam)); // 상
    }

    @Test
    void white_Pawn_이동_예외_테스트() {
        assertThrows(InvalidDirectionException.class, () -> whitePiece.canMove(base, position("a4"), optionalBlackTeam)); // 좌
        assertThrows(InvalidDirectionException.class, () -> whitePiece.canMove(base, position("c4"), optionalBlackTeam)); // 우
        assertThrows(InvalidDirectionException.class, () -> whitePiece.canMove(base, position("b3"), optionalBlackTeam)); // 하
    }

    @Test
    void 대각선_이동_여부_테스트() {
        assertThrows(InvalidDirectionException.class, () -> blackPiece.canMove(base, position("c3"), Optional.empty()));
    }

    @Test
    void 거리가_제한에_위반되는_경우_에외_테스트() {
        assertThrows(InvalidDistanceException.class, () -> {
            blackPiece.canMove(base, position("b1"), optionalWhiteTeam);
            whitePiece.canMove(base, position("b7"), optionalBlackTeam);
        });
    }
}

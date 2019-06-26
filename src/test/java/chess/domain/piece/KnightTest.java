package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static chess.domain.utils.InputParser.position;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KnightTest {

    Piece piece;
    Optional<Team> optionalTargetPieceTeam;
    Position base;

    @BeforeEach
    void setUp() {
        piece = new Knight(Team.BLACK);
        optionalTargetPieceTeam = Optional.of(Team.WHITE);
        base = position("d4");
    }

    @Test
    void 일사분면_이동_여부_테스트() {
        assertTrue(piece.canMove(base, position("e6"), optionalTargetPieceTeam));
        assertTrue(piece.canMove(base, position("f5"), optionalTargetPieceTeam));
    }

    @Test
    void 이사분면_이동_여부_테스트() {
        assertTrue(piece.canMove(base, position("c6"), optionalTargetPieceTeam));
        assertTrue(piece.canMove(base, position("b5"), optionalTargetPieceTeam));
    }

    @Test
    void 삼사분면_이동_여부_테스트() {
        assertTrue(piece.canMove(base, position("c2"), optionalTargetPieceTeam));
        assertTrue(piece.canMove(base, position("b3"), optionalTargetPieceTeam));
    }

    @Test
    void 사사분면_이동_여부_테스트() {
        assertTrue(piece.canMove(base, position("e2"), optionalTargetPieceTeam));
        assertTrue(piece.canMove(base, position("f3"), optionalTargetPieceTeam));
    }
}
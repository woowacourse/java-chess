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

class KingTest {
    Piece piece;
    Optional<Team> optionalTargetPieceTeam;
    Position base;

    @BeforeEach
    void setUp() {
        piece = new King(Team.BLACK);
        optionalTargetPieceTeam = Optional.of(Team.WHITE);
        base = position("b2");
    }

    @Test
    void 상하좌우_이동_여부_테스트() {
        assertTrue(piece.canMove(base, position("b3"), optionalTargetPieceTeam));
        assertTrue(piece.canMove(base, position("b1"), optionalTargetPieceTeam));
        assertTrue(piece.canMove(base, position("a2"), optionalTargetPieceTeam));
        assertTrue(piece.canMove(base, position("c2"), optionalTargetPieceTeam));
    }

    @Test
    void 대각선_이동_여부_테스트() {
        assertTrue(piece.canMove(base, position("a1"), optionalTargetPieceTeam));
        assertTrue(piece.canMove(base, position("c1"), optionalTargetPieceTeam));
        assertTrue(piece.canMove(base, position("a3"), optionalTargetPieceTeam));
        assertTrue(piece.canMove(base, position("c3"), optionalTargetPieceTeam));
    }

    @Test
    void 상하거리_제한에_위반되는_경우_에외_테스트() {
        assertThrows(InvalidDistanceException.class, () -> piece.canMove(base, position("b4"), optionalTargetPieceTeam));
    }

    @Test
    void 대각선거리_제한에_위반되는_경우_에외_테스트() {
        assertThrows(InvalidDirectionException.class, () -> piece.canMove(base, position("a4"), optionalTargetPieceTeam));
    }
}
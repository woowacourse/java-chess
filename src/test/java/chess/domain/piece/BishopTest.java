package chess.domain.piece;

import chess.domain.Coordinate;
import chess.domain.Position;
import chess.domain.Team;
import chess.domain.exceptions.InvalidDirectionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.stream.IntStream;

import static chess.domain.utils.InputParser.position;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BishopTest {
    Piece piece;
    Optional<Team> optionalTargetPieceTeam;
    Position base;

    @BeforeEach
    void setUp() {
        piece = new Bishop(Team.BLACK);
        optionalTargetPieceTeam = Optional.of(Team.WHITE);
        base = position("d4");
    }

    @Test
    void 우상향_대각선_이동_여부_테스트() {
        IntStream.rangeClosed(1, 8)
                .filter(i -> i != 4)
                .forEach(i -> assertTrue(piece.canMove(base, new Position(new Coordinate(i), new Coordinate(i)), optionalTargetPieceTeam)));
    }

    @Test
    void 좌상향_대각선_이동_여부_테스트() {
        IntStream.range(1, 8)
                .filter(i -> i != 4)
                .forEach(i -> assertTrue(piece.canMove(base, new Position(new Coordinate(i), new Coordinate(8 - i)), optionalTargetPieceTeam)));
    }

    @Test
    void 상하좌우_이동_여부_예외_테스트() {
        assertThrows(InvalidDirectionException.class, () -> piece.canMove(base, position("e4"), optionalTargetPieceTeam));
    }
}
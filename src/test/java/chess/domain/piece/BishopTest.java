package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import chess.domain.Board;
import chess.domain.Color;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BishopTest {
    @DisplayName("생성 테스트")
    @Test
    void create() {
        assertThatCode(() -> new Bishop(Color.BLACK))
                .doesNotThrowAnyException();
    }

    @DisplayName("아군을 만나기 직전까지만 이동 가능하다.")
    @Test
    void givenBishopMoveWhenMeetTeamMThenStop() {
        Bishop bishop = new Bishop(Color.WHITE);
        Position currentBishopPosition = Position.from(File.A, Rank.FOUR);
        Map<Position, Piece> board = Map.of(
                currentBishopPosition, bishop,
                Position.from(File.B, Rank.THREE), new Bishop(Color.WHITE),
                Position.from(File.C, Rank.SIX), new Knight(Color.WHITE)
        );

        Set<Position> movablePositions = bishop.calculateMovablePositions(currentBishopPosition, new Board(board));

        assertThat(movablePositions).isEqualTo(
                Set.of(Position.from(File.B, Rank.FIVE)));
    }

    @DisplayName("적군을 만난 위치까지 이동 가능하다.")
    @Test
    void givenBishopMoveWhenMeetEnemyThenStopAtEnemyPosition() {
        Bishop bishop = new Bishop(Color.WHITE);
        Position currentBishopPosition = Position.from(File.A, Rank.FOUR);
        Map<Position, Piece> board = Map.of(
                currentBishopPosition, bishop,
                Position.from(File.B, Rank.THREE), new Bishop(Color.BLACK),
                Position.from(File.C, Rank.SIX), new Knight(Color.BLACK)
        );

        Set<Position> movablePositions = bishop.calculateMovablePositions(currentBishopPosition, new Board(board));

        assertThat(movablePositions).isEqualTo(
                Set.of(Position.from(File.B, Rank.FIVE), Position.from(File.C, Rank.SIX),
                        Position.from(File.B, Rank.THREE)));
    }
}

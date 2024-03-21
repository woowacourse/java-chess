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

class QueenTest {
    @DisplayName("생성 테스트")
    @Test
    void create() {
        assertThatCode(() -> new Queen(Color.BLACK))
                .doesNotThrowAnyException();
    }

    @DisplayName("아군을 만나기 직전까지만 이동 가능하다.")
    @Test
    void givenQueenMoveWhenMeetTeamMThenStop() {
        Queen queen = new Queen(Color.WHITE);
        Position currentQueenPosition = Position.of(File.G, Rank.SEVEN);
        Map<Position, Piece> board = Map.of(
                currentQueenPosition, queen,
                Position.of(File.E, Rank.SEVEN), Pawn.ofWhite(),
                Position.of(File.F, Rank.SIX), Pawn.ofWhite(),
                Position.of(File.G, Rank.FIVE), Pawn.ofWhite()
        );

        Set<Position> movablePositions = queen.calculateMovablePositions(currentQueenPosition, new Board(board));

        assertThat(movablePositions).isEqualTo(
                Set.of(Position.of(File.G, Rank.SIX),
                        Position.of(File.G, Rank.EIGHT),
                        Position.of(File.H, Rank.SIX),
                        Position.of(File.H, Rank.SEVEN),
                        Position.of(File.H, Rank.EIGHT),
                        Position.of(File.F, Rank.EIGHT),
                        Position.of(File.F, Rank.SEVEN)));
    }

    @DisplayName("적군을 만난 위치까지 이동 가능하다.")
    @Test
    void givenQueenMoveWhenMeetEnemyThenStopAtEnemyPosition() {
        Queen queen = new Queen(Color.WHITE);
        Position currentQueenPosition = Position.of(File.G, Rank.SEVEN);
        Map<Position, Piece> board = Map.of(
                currentQueenPosition, queen,
                Position.of(File.E, Rank.SEVEN), Pawn.ofBlack(),
                Position.of(File.F, Rank.SIX), Pawn.ofBlack(),
                Position.of(File.G, Rank.FIVE), Pawn.ofBlack()
        );

        Set<Position> movablePositions = queen.calculateMovablePositions(currentQueenPosition, new Board(board));

        assertThat(movablePositions).isEqualTo(
                Set.of(Position.of(File.G, Rank.SIX),
                        Position.of(File.G, Rank.EIGHT),
                        Position.of(File.H, Rank.SIX),
                        Position.of(File.H, Rank.SEVEN),
                        Position.of(File.H, Rank.EIGHT),
                        Position.of(File.F, Rank.EIGHT),
                        Position.of(File.F, Rank.SEVEN),
                        Position.of(File.E, Rank.SEVEN),
                        Position.of(File.F, Rank.SIX),
                        Position.of(File.G, Rank.FIVE)));
    }
}

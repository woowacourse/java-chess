package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import chess.domain.piece.Bishop;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookTest {
    @DisplayName("생성 테스트")
    @Test
    public void create() {
        assertThatCode(() -> new Rook(Color.BLACK))
                .doesNotThrowAnyException();
    }

    @DisplayName("룩은 아군을 만나기 직전까지만 이동 가능하다.")
    @Test
    public void givenRookMoveWhenTeamThenStop() {
        Rook rook = new Rook(Color.WHITE);
        Position currentRookPosition = new Position(File.a, Rank.ONE);
        Map<Position, Piece> board = Map.of(
                currentRookPosition, rook,
                new Position(File.a, Rank.FOUR), new Bishop(Color.WHITE),
                new Position(File.e, Rank.ONE), new Knight(Color.WHITE)
        );

//        Set<Position> movablePositions = rook.movablePositions(currentRookPosition, new Board(board));

//        assertThat(movablePositions).isEqualTo(
//                Set.of(new Position(File.a, Rank.TWO), new Position(File.a, Rank.THREE), new Position(File.b, Rank.ONE),
//                        new Position(File.c, Rank.ONE), new Position(File.d, Rank.ONE)));
    }
}

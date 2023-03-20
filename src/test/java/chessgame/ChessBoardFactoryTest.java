package chessgame;

import chessgame.domain.Board;
import chessgame.domain.Team;
import chessgame.domain.piece.Pawn;
import chessgame.domain.piece.Rook;
import chessgame.util.ChessBoardFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chessgame.point.PointFixture.A1;
import static chessgame.point.PointFixture.A7;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ChessBoardFactoryTest {
    @Test
    @DisplayName("모든 좌표값을 가진 보드를 생성한다")
    void Should_NotThrowException_When_ConstructBoard() {
        assertDoesNotThrow(() -> new Board(ChessBoardFactory.create()));
    }

    @Test
    @DisplayName("A1에 흰팀 룩이 잘 생성되었는지 확인한다")
    void Should_Same_When_ConstructBoardA1(){
        Board board = new Board(ChessBoardFactory.create());

        Assertions.assertThat(board.getBoard().get(A1)).usingRecursiveComparison().isEqualTo(Rook.from(Team.WHITE));
    }

    @Test
    @DisplayName("A6에 검은팀 폰이 잘 생성되었는지 확인한다")
    void Should_Same_When_ConstructBoardA7(){
        Board board = new Board(ChessBoardFactory.create());

        Assertions.assertThat(board.getBoard().get(A7)).usingRecursiveComparison().isEqualTo(Pawn.from(Team.BLACK));
    }

}

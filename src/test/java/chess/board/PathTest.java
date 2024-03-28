package chess.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.piece.Bishop;
import chess.piece.Color;
import chess.piece.MovedPawn;
import chess.position.UnitMovement;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PathTest {

    @Test
    @DisplayName("길이가 2보다 작은 Path를 만드는 경우, 예외를 발생한다.")
    void shortPathTest() {
        // given
        List<Square> squares = List.of(Square.empty());
        UnitMovement movement = UnitMovement.differencesOf(1, 1);
        // when, then
        assertThatThrownBy(() -> new Path(squares, movement))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("경로의 길이는 2 이상이어야 합니다.");
    }

    @Test
    @DisplayName("경로 중간에 기물이 있는 경우, 순회 시 예외를 발생한다.")
    void hasPieceOnRouteTest() {
        // given
        List<Square> squares = List.of(
                new Square(new Bishop(Color.WHITE)),
                new Square(new MovedPawn(Color.BLACK)),
                new Square(new Bishop(Color.BLACK))
        );
        UnitMovement movement = UnitMovement.differencesOf(1, 1);
        Path path = new Path(squares, movement);
        // when, then
        assertThatThrownBy(() -> path.traverse(Color.WHITE))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("경로에 기물이 있습니다.");
    }

    @Test
    @DisplayName("상대방의 말을 움직이면, 예외를 발생한다.")
    void opponentPieceTraverseTest() {
        // given
        List<Square> squares = List.of(
                new Square(new Bishop(Color.WHITE)),
                Square.empty()
        );
        UnitMovement movement = UnitMovement.differencesOf(1, 1);
        Path path = new Path(squares, movement);
        // when, then
        assertThatThrownBy(() -> path.traverse(Color.BLACK))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("상대방의 말을 움직일 수 없습니다.");
    }

    @Test
    @DisplayName("출발지에서 도착지로 이동한다.")
    void traverseTest() {
        // given
        Square source = new Square(new Bishop(Color.WHITE));
        List<Square> squares = List.of(
                source,
                Square.empty(),
                Square.empty()
        );
        UnitMovement movement = UnitMovement.differencesOf(1, 1);
        Path path = new Path(squares, movement);
        // when
        Square destination = path.traverse(Color.WHITE);
        // then
        assertThat(destination.hasPiece()).isTrue();
    }

    @Test
    @DisplayName("움직일 수 없는 경로를 순회하는 경우, 예외를 발생한다.")
    void illegalRouteTraversalTest() {
        // given
        List<Square> squares = List.of(
                new Square(new Bishop(Color.WHITE)),
                Square.empty(),
                Square.empty()
        );
        UnitMovement movement = UnitMovement.differencesOf(0, 1);
        Path path = new Path(squares, movement);
        // when, then
        assertThatThrownBy(() -> path.traverse(Color.WHITE))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("움직일 수 없는 경로입니다.");
    }

    @Test
    @DisplayName("움직일 수 없는 경로를 공격하는 경우, 예외를 발생한다.")
    void illegalRouteAttackTest() {
        // given
        List<Square> squares = List.of(
                new Square(new MovedPawn(Color.WHITE)),
                new Square(new MovedPawn(Color.BLACK))
        );
        UnitMovement movement = UnitMovement.differencesOf(0, 1);
        Path path = new Path(squares, movement);
        // when, then
        assertThatThrownBy(() -> path.traverse(Color.WHITE))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("움직일 수 없는 경로입니다.");
    }
}

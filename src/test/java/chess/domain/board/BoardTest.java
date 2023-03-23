package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.AbstractTestFixture;
import chess.domain.game.Team;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;

public class BoardTest extends AbstractTestFixture {

    @DisplayName("출발 위치에 기물이 존재하지 않으면 예외를 던진다")
    @Test
    void invalidSourcePosition_throws() {
        var board = BoardFactory.createBoard();
        var source = createPosition("C,THREE");
        var target = createPosition("D,TWO");

        assertThatThrownBy(() -> board.move(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("움직일 기물이 없습니다");
    }

    @DisplayName("목표 위치에 같은 색상의 말이 있으면 예외를 던진다")
    @Test
    void reachSameColor_throws() {
        var board = BoardFactory.createBoard();
        var source = createPosition("C,ONE");
        var target = createPosition("D,TWO");

        assertThatThrownBy(() -> board.move(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("목표 위치에 같은 색 말이 있습니다");
    }

    @DisplayName("기물이 움직일 수 있는 수가 아니면 예외를 던진다")
    @Test
    void pieceNotHasMove_throws() {
        var board = BoardFactory.createBoard();
        var source = createPosition("C,TWO");
        var target = createPosition("B,THREE");

        assertThatThrownBy(() -> board.move(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 기물이 이동할 수 없는 수입니다");
    }

    @DisplayName("목표에 도달하는중 가로막는 기물이 있으면 예외를 던진다")
    @Test
    void ObstaclePiece_throws() {
        var board = BoardFactory.createBoard();
        var source = createPosition("D,ONE");
        var target = createPosition("A,FOUR");

        board.move(createPosition("C,TWO"), createPosition("C,THREE"));
        board.move(createPosition("B,TWO"), createPosition("B,THREE"));

        assertThatThrownBy(() -> board.move(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("다른 기물을 지나칠 수 없습니다");
    }

    @DisplayName("전제조건이 모두 맞으면, 이동한다")
    @Test
    void move() {
        var board = BoardFactory.createBoard();
        var source = createPosition("D,ONE");
        var target = createPosition("A,FOUR");

        board.move(createPosition("C,TWO"), createPosition("C,THREE"));
        board.move(createPosition("B,TWO"), createPosition("B,FOUR"));
        board.move(source, target);

        assertThat(board.getPieces().get(target).getType()).isEqualTo(PieceType.QUEEN);
    }

    @DisplayName("이동이면, 해당 기물을 위치시킨다")
    @Test
    void putPiece_onMove() {
        var board = BoardFactory.createBoard();
        var source = createPosition("D,TWO");
        var target = createPosition("D,THREE");

        board.move(source, target);

        Map<Position, Piece> pieces = board.getPieces();
        assertThat(pieces.get(source))
                .isNull();
        assertThat(pieces.get(target))
                .isNotNull()
                .isInstanceOf(Pawn.class);
    }

    @DisplayName("공격이면, 기존 기물을 대체한다")
    @Test
    void replacePiece_onAttack() {
        var board = BoardFactory.createBoard();
        var source = createPosition("G,ONE");
        var target = createPosition("F,THREE");
        var target2 = createPosition("G,FIVE");
        var target3 = createPosition("F,SEVEN");

        board.move(source, target);
        board.move(target, target2);
        board.move(target2, target3);

        Map<Position, Piece> pieces = board.getPieces();
        assertThat(pieces.get(source))
                .isNull();
        assertThat(pieces.get(target3))
                .isNotNull()
                .isInstanceOf(Knight.class);
    }

    @DisplayName("어떤 위치의 기물 색을 알 수 있다")
    @Test
    void hasTeam() {
        var board = BoardFactory.createBoard();
        var position = createPosition("D,TWO");

        assertThat(board.hasPositionTeamOf(position, Team.WHITE)).isTrue();
    }
}

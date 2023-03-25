package chess.domain.board;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoardTest {

    @DisplayName("턴에 맞는 기물인지 확인한다")
    @Test
    void checkTurn_true() {
        var board = BoardFactory.createBoard();

        boolean isTurn = board.checkTurn(new Position("C2"), WHITE);

        assertThat(isTurn).isTrue();
    }

    @DisplayName("턴에 맞지 않는 기물인지 확인한다")
    @Test
    void checkTurn_false() {
        var board = BoardFactory.createBoard();

        boolean isTurn = board.checkTurn(new Position("C2"), BLACK);

        assertThat(isTurn).isFalse();
    }

    @DisplayName("출발 위치에 말이 존재하지 않으면 예외를 던진다")
    @Test
    void invalidSourcePosition_throws() {
        var board = BoardFactory.createBoard();
        var source = new Position("C3");
        var target = new Position("D2");

        assertThatThrownBy(() -> board.move(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("움직일 기물이 없습니다");
    }


    @DisplayName("목표 위치에 같은 색상의 말이 있으면 예외를 던진다")
    @Test
    void reachSameColor_throws() {
        var board = BoardFactory.createBoard();
        var source = new Position("C1");
        var target = new Position("D2");

        assertThatThrownBy(() -> board.move(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("목표 위치에 같은 색 말이 있습니다");
    }

    @DisplayName("기물의 이동 수가 아니면 예외를 던진다")
    @Test
    void pieceNotHasMove_throws() {
        var board = BoardFactory.createBoard();
        var source = new Position("C2");
        var target = new Position("B3");

        assertThatThrownBy(() -> board.move(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 기물이 이동할 수 없는 수입니다");
    }

    @DisplayName("목표에 도달하는중 다른 기물이 있으면 예외를 던진다")
    @Test
    void canNotCrossOtherPiece_throws() {
        var board = BoardFactory.createBoard();
        var source = new Position("D1");
        var target = new Position("A4");

        board.move(new Position("C2"), new Position("C3"));
        board.move(new Position("B2"), new Position("B3"));

        assertThatThrownBy(() -> board.move(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("다른 기물을 지나칠 수 없습니다");
    }

    @DisplayName("이동이면, 해당 기물을 위치시킨다")
    @Test
    void putPiece_onMove() {
        var board = BoardFactory.createBoard();
        var source = new Position("D2");
        var target = new Position("D3");

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
        var source = new Position("G1");
        var target = new Position("F3");
        var target2 = new Position("G5");
        var target3 = new Position("F7");

        board.move(source, target);
        board.move(target, target2);
        board.move(target2, target3);

        Map<Position, Piece> pieces = board.getPieces();
        assertThat(pieces.get(source))
                .isNull();
        assertThat(pieces.get(target3).getType())
                .isSameAs(PieceType.KNIGHT);
    }
}

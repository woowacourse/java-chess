package board;

import chess.domain.board.Board;
import chess.domain.board.PositionFactory;
import chess.domain.piece.*;
import chess.exception.AnotherTeamPieceException;
import chess.exception.PieceNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BoardTest {
    private static Board board;

    @BeforeAll
    static void setBoard() {
        board = new Board();
    }

    @Test
    @DisplayName("EmptyPiece가 아닌 체스말이 있는 위치값이 입력되면 해당 체스말을 반환해야 함")
    void inputPiecePositionThenReturnPiece() {
        Piece piece = board.findPiece(PositionFactory.of("a1"), PieceColor.WHITE);
        Assertions.assertThat(piece instanceof Rook).isTrue();

        piece = board.findPiece(PositionFactory.of("b8"), PieceColor.BLACK);
        Assertions.assertThat(piece instanceof Knight).isTrue();

        piece = board.findPiece(PositionFactory.of("c1"), PieceColor.WHITE);
        Assertions.assertThat(piece instanceof Bishop).isTrue();

        piece = board.findPiece(PositionFactory.of("d8"), PieceColor.BLACK);
        Assertions.assertThat(piece instanceof Queen).isTrue();

        piece = board.findPiece(PositionFactory.of("e1"), PieceColor.WHITE);
        Assertions.assertThat(piece instanceof King).isTrue();

        piece = board.findPiece(PositionFactory.of("c2"), PieceColor.WHITE);
        Assertions.assertThat(piece instanceof Pawn).isTrue();
    }

    @ParameterizedTest
    @DisplayName("체스말이 없는, 즉 EmptyPiece가 있는 위치값으로 체스말을 찾을 때 예외가 발생해야 함")
    @ValueSource(strings = {"a3", "b3", "c3", "d3", "e3", "f3", "g3", "h3",
            "a4", "b4", "c4", "d4", "e4", "f4", "g4", "h4",
            "a5", "b5", "c5", "d5", "e5", "f5", "g5", "h5",
            "a6", "b6", "c6", "d6", "e6", "f6", "g6", "h6"})
    void inputNoPiecePositionThenThrowException(String input) {
        Assertions.assertThatThrownBy(() -> board.findPiece(PositionFactory.of(input), PieceColor.NONE))
                .isInstanceOf(PieceNotFoundException.class)
                .hasMessage(String.format("위치(sourcePosition) %s에 움직일 수 있는 체스말이 없습니다.", input));
    }

    @ParameterizedTest
    @DisplayName("현재 팀의 색과 다른 색의 체스말을 찾을 때 예외가 발생해야 함")
    @ValueSource(strings = {"a8", "b8", "c8", "d8", "e8", "f8", "g8", "h8", "a7", "b7", "c7", "d7", "e7", "f7", "g7",
            "h7"})
    void findDifferentPieceColorPositionThenThrowException(String input) {
        Assertions.assertThatThrownBy(() -> board.findPiece(PositionFactory.of(input), PieceColor.WHITE))
                .isInstanceOf(AnotherTeamPieceException.class)
                .hasMessage(String.format("위치(sourcePosition) %s의 말은 현재 차례인 %s의 말이 아니므로 움직일 수 없습니다.", input,
                                          this.board.getTeam().getName()));
    }

    @Test
    @DisplayName("초기화 상태에서는 Black King, White king이 모두 살아 있으므로 false를 반환해야 함")
    void isKingKilledReturnFalse() {
        Assertions.assertThat(board.isBlackKingKilled()).isFalse();
        Assertions.assertThat(board.isWhiteKingKilled()).isFalse();
    }
}

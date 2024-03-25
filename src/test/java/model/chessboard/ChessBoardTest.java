package model.chessboard;

import model.position.File;
import model.position.Position;
import model.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class ChessBoardTest {

    @DisplayName("특정 기물이 자신의 이동방향에 부합한 목적지로 이동할 수 있다.")
    @Test
    void move() {
        ChessBoard chessBoard = new ChessBoard();
        assertAll(
                () -> assertThat(chessBoard.getChessBoard().get(Position.of(File.A, Rank.FOUR)).isOccupied()).isFalse(),
                () -> chessBoard.move(Position.of(File.A, Rank.TWO), Position.of(File.A, Rank.FOUR)),
                () -> assertThat(chessBoard.getChessBoard().get(Position.of(File.A, Rank.FOUR)).isOccupied()).isTrue()
        );
    }

    @DisplayName("특정 기물이 이동할 때 목적 지점까지의 경로에 기물이 위치하여 이동할 수 없는 경우 예외가 발생한다.")
    @Test
    void validateNotExistWayPoints() {
        ChessBoard chessBoard = new ChessBoard();
        assertThatThrownBy(() -> chessBoard.move(Position.of(File.A, Rank.ONE), Position.of(File.A, Rank.TWO)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("목적 지점까지의 경로에 기물이 위치하여 이동할 수 없습니다.");
    }

}

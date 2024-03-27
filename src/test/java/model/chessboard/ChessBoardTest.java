package model.chessboard;

import model.position.File;
import model.position.Position;
import model.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
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
}

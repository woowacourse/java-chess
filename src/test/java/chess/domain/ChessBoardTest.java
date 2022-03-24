package chess.domain;

import chess.domain.chessPiece.ChessPiece;
import chess.domain.chessPiece.Color;
import chess.domain.chessPiece.Pawn;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ChessBoardTest {

    ChessBoard chessBoard = new ChessBoard();

    @Test
    @DisplayName("체스판을 초기화하고 기물을 배치한다.")
    void init() {
        int actual = chessBoard.countPiece();
        int expected = 32;
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"a1:r", "a8:R"}, delimiter = ':')
    @DisplayName("위치를 기반으로 기물을 찾는다.")
    void findPiece(String position, String expected) {
        Optional<ChessPiece> possiblePiece = chessBoard.findPiece(new Position(position));
        ChessPiece actual = possiblePiece.get();
        assertThat(actual.getName()).isEqualTo(expected);
    }

    @Test
    @DisplayName("위치에 기물이 있는지 확인한다.")
    void findPiece_Null() {
        Optional<ChessPiece> actual = chessBoard.findPiece(new Position("a3"));
        assertThat(actual.isEmpty()).isEqualTo(true);
    }

    @Test
    @DisplayName("기물이 적인지 확인한다.")
    void enemyExist() {
        // given
        ChessPiece me = new Pawn(Color.BLACK);
        Position to = new Position("a1");

        // when
        boolean actual = chessBoard.enemyExist(me, to);

        // then
        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("이동 경로 사이에 다른 기물이 있으면 예외를 발생시킵니다.")
    void move() {
        assertThatThrownBy(() -> chessBoard.move(new Position("a1"), new Position("a3")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 경로 사이에 다른 기물이 있습니다.");

    }

    @Test
    @DisplayName("폰의 목적지에 같은색 기물이 있으면 예외를 발생시킵니다.")
    void move_Pawn_Straight1() {
        // given
        chessBoard.move(new Position("b1"), new Position("c3"));
        chessBoard.move(new Position("a7"),new Position("a6"));

        // then
        assertThatThrownBy(() -> chessBoard.move(new Position("c2"), new Position("c3")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("같은색 기물입니다.");
    }

    @Test
    @DisplayName("폰의 이동 경로 사이에 다른 기물이 있으면 예외를 발생시킵니다.")
    void move_Pawn_Straight2() {
        // given
        chessBoard.move(new Position("b1"), new Position("c3"));
        chessBoard.move(new Position("a7"),new Position("a6"));

        // then
        assertThatThrownBy(() -> chessBoard.move(new Position("c2"), new Position("c4")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 경로 사이에 다른 기물이 있습니다.");
    }

    @Test
    @DisplayName("폰의 대각선 방향에 같은색 기물이 있으면 예외를 발생시킵니다.")
    void move_Pawn_Cross() {
        // given
        chessBoard.move(new Position("a2"), new Position("a3"));
        chessBoard.move(new Position("a7"), new Position("a6")); // Dummy
        chessBoard.move(new Position("b2"), new Position("b4"));
        chessBoard.move(new Position("a6"), new Position("a5")); // Dummy

        // then
        assertThatThrownBy(() -> chessBoard.move(new Position("a3"), new Position("b4")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("같은색 기물입니다.");
    }

    @Test
    @DisplayName("폰의 목적지에 같은색 기물이 있으면 예외를 발생시킵니다.")
    void chessBoard_turn() {
        // then
        assertThatThrownBy(() ->  chessBoard.move(new Position("a7"), new Position("a6")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("WHITE의 차례입니다.");
    }
}

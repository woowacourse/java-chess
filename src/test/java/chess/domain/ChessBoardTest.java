package chess.domain;

import chess.domain.chessPiece.*;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;
import java.util.Map;
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
        chessBoard.move(new Position("a7"), new Position("a6"));

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
        chessBoard.move(new Position("a7"), new Position("a6"));

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
        assertThatThrownBy(() -> chessBoard.move(new Position("a7"), new Position("a6")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("WHITE의 차례입니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"BLACK:1.5", "WHITE:1"}, delimiter = ':')
    @DisplayName("폰이 같은 세로줄에 있는 경우의 점수를 구한다.")
    void calculateScore_pawn(Color color, double expected) {
        // given
        Map<Position, ChessPiece> pieceByPosition = new HashMap<>();
        pieceByPosition.put(new Position("a1"), new Pawn(Color.BLACK));
        pieceByPosition.put(new Position("a3"), new Pawn(Color.BLACK));
        pieceByPosition.put(new Position("a5"), new Pawn(Color.BLACK));

        pieceByPosition.put(new Position("a2"), new Pawn(Color.WHITE));

        ChessBoard chessBoard = new ChessBoard(pieceByPosition);

        // when
        Map<Color, Double> scoreByColor = chessBoard.calculateScore();
        Double actual = scoreByColor.get(color);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"BLACK:20.5", "WHITE:0"}, delimiter = ':')
    @DisplayName("체스판에 있는 기물의 총합을 색깔별로 구한다.")
    void calculateScore_combination(Color color, double expected) {
        // given
        Map<Position, ChessPiece> pieceByPosition = new HashMap<>();
        pieceByPosition.put(new Position("a1"), new King(Color.BLACK)); // 0
        pieceByPosition.put(new Position("a2"), new Queen(Color.BLACK)); // 9
        pieceByPosition.put(new Position("a3"), new Knight(Color.BLACK)); // 2.5
        pieceByPosition.put(new Position("a4"), new Rook(Color.BLACK)); // 5
        pieceByPosition.put(new Position("a5"), new Bishop(Color.BLACK)); // 3
        pieceByPosition.put(new Position("a6"), new Pawn(Color.BLACK)); // 1

        pieceByPosition.put(new Position("h2"), new King(Color.WHITE));

        ChessBoard chessBoard = new ChessBoard(pieceByPosition);

        // when
        Map<Color, Double> scoreByColor = chessBoard.calculateScore();
        Double actual = scoreByColor.get(color);

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
/*
<-------Rank---------->
 BLACK
 R  N  B  Q  K  B  N  R
A8 B8 C8 D8 E8 F8 G8 H8
A7 B7 C7 D7 E7 F7 G7 H7  ⬆️
A6 B6 C6 D6 E6 F6 G6 H6  F
A5 B5 C5 D5 E5 F5 G5 H5  I
A4 B4 C4 D4 E4 F4 G4 H4  L
A3 B3 C3 D3 E3 F3 G3 H3  E
A2 B2 C2 D2 E2 F2 G2 H2  ⬇️
A1 B1 C1 D1 E1 F1 G1 H1
 R  N  B  Q  K  B  N  R
 WHITE

Rank : a, b, c
File : 1, 2, 3
 */
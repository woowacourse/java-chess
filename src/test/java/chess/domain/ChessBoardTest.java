package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.chessPiece.Bishop;
import chess.domain.chessPiece.ChessPiece;
import chess.domain.chessPiece.Color;
import chess.domain.chessPiece.King;
import chess.domain.chessPiece.Knight;
import chess.domain.chessPiece.Pawn;
import chess.domain.chessPiece.Queen;
import chess.domain.chessPiece.Rook;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ChessBoardTest {

    final ChessBoard chessBoard = new ChessBoard();

    @ParameterizedTest
    @DisplayName("위치를 기반으로 기물을 찾는다.")
    @CsvSource(value = {"a1:r", "a8:R"}, delimiter = ':')
    void findPiece(final String position, final String expected) {
        // when
        final Optional<ChessPiece> possiblePiece = chessBoard.findPiece(new Position(position));
        final ChessPiece actual = possiblePiece.get();

        // then
        assertThat(actual.getName()).isEqualTo(expected);
    }

    @Test
    @DisplayName("위치에 기물이 있는지 확인한다.")
    void findPiece_Null() {
        // when
        final Optional<ChessPiece> actual = chessBoard.findPiece(new Position("a3"));

        // then
        assertThat(actual.isEmpty()).isEqualTo(true);
    }

    @Test
    @DisplayName("기물이 적인지 확인한다.")
    void enemyExist() {
        // given
        final ChessPiece me = new Pawn(Color.BLACK);
        final Position to = new Position("a1");

        // when
        final boolean actual = chessBoard.enemyExist(me, to);

        // then
        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("이동 경로 사이에 다른 기물이 있으면 예외를 발생시킵니다.")
    void move() {
        // given
        final Position from = new Position("a1");
        final Position to = new Position("a3");

        // then
        assertThatThrownBy(() -> chessBoard.move(from, to))
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
    @DisplayName("폰이 같은 세로줄에 있는 경우의 점수를 구한다.")
    @CsvSource(value = {"BLACK:1.5", "WHITE:1"}, delimiter = ':')
    void calculateScore_pawn(final Color color, final double expected) {
        // given
        final Map<Position, ChessPiece> pieceByPosition = new HashMap<>();
        pieceByPosition.put(new Position("a1"), new Pawn(Color.BLACK));
        pieceByPosition.put(new Position("a3"), new Pawn(Color.BLACK));
        pieceByPosition.put(new Position("a5"), new Pawn(Color.BLACK));

        pieceByPosition.put(new Position("a2"), new Pawn(Color.WHITE));

        final ChessBoard chessBoard = new ChessBoard(pieceByPosition);

        // when
        final Map<Color, Double> scoreByColor = chessBoard.calculateScore();
        final Double actual = scoreByColor.get(color);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("체스판에 있는 기물의 총합을 색깔별로 구한다.")
    @CsvSource(value = {"BLACK:20.5", "WHITE:0"}, delimiter = ':')
    void calculateScore_combination(final Color color, final double expected) {
        // given
        final Map<Position, ChessPiece> pieceByPosition = new HashMap<>();
        pieceByPosition.put(new Position("a1"), new King(Color.BLACK)); // 0
        pieceByPosition.put(new Position("a2"), new Queen(Color.BLACK)); // 9
        pieceByPosition.put(new Position("a3"), new Knight(Color.BLACK)); // 2.5
        pieceByPosition.put(new Position("a4"), new Rook(Color.BLACK)); // 5
        pieceByPosition.put(new Position("a5"), new Bishop(Color.BLACK)); // 3
        pieceByPosition.put(new Position("a6"), new Pawn(Color.BLACK)); // 1

        pieceByPosition.put(new Position("h2"), new King(Color.WHITE));

        final ChessBoard chessBoard = new ChessBoard(pieceByPosition);

        // when
        final Map<Color, Double> scoreByColor = chessBoard.calculateScore();
        final Double actual = scoreByColor.get(color);

        // then
        assertThat(actual).isEqualTo(expected);
    }
}

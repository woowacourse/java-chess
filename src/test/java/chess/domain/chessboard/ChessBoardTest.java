package chess.domain.chessboard;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.chesspiece.Bishop;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspiece.Color;
import chess.domain.chesspiece.King;
import chess.domain.chesspiece.Knight;
import chess.domain.chesspiece.Pawn;
import chess.domain.chesspiece.Queen;
import chess.domain.chesspiece.Rook;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ChessBoardTest {

    final ChessBoard chessBoard = ChessBoardFactory.createChessBoard();

    @ParameterizedTest
    @DisplayName("위치를 기반으로 기물을 찾는다.")
    @CsvSource(value = {"a1:r", "a8:R"}, delimiter = ':')
    void findPiece(final String position, final String expected) {
        // when
        final Optional<ChessPiece> possiblePiece = chessBoard.findPiece(Position.from(position));
        final ChessPiece actual = possiblePiece.get();

        // then
        assertThat(actual.name()).isEqualTo(expected);
    }

    @Test
    @DisplayName("위치에 기물이 있는지 확인한다.")
    void findPiece_Null() {
        // when
        final Optional<ChessPiece> actual = chessBoard.findPiece(Position.from("a3"));

        // then
        assertThat(actual.isEmpty()).isEqualTo(true);
    }

    @Test
    @DisplayName("기물을 빈 위치로 이동시킨다.")
    void move_to_empty() {
        // given
        final Position from = Position.from("b1");
        final Position to = Position.from("c3");

        // when
        chessBoard.move(from, to);
        final ChessPiece actual = chessBoard.findPiece(to).get();

        // then
        assertThat(actual.name()).isEqualTo("n");
    }

    @Test
    @DisplayName("기물을 다른색의 기물이 있는 위치로 이동시킨다.")
    void move_to_enemy() {
        // given
        final Position from = Position.from("d2");
        final Position to = Position.from("f4");

        final Map<Position, ChessPiece> pieceByPosition = new HashMap<>();
        pieceByPosition.put(from, Bishop.from(Color.WHITE));
        pieceByPosition.put(to, Rook.from(Color.BLACK));

        final ChessBoard chessBoard = new ChessBoard(pieceByPosition);

        // when
        chessBoard.move(from, to);
        final ChessPiece actual = chessBoard.findPiece(to).get();

        // then
        assertThat(actual.name()).isEqualTo("b");
    }

    @Test
    @DisplayName("이동 경로 사이에 다른 기물이 있으면 예외를 발생시킵니다.")
    void move_exception() {
        // given
        final Position from = Position.from("a1");
        final Position to = Position.from("a3");

        // then
        assertThatThrownBy(() -> chessBoard.move(from, to))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 경로 사이에 다른 기물이 있습니다.");
    }

    @Test
    @DisplayName("폰을 직진으로 이동 시킨다.")
    void move_pawn_straight() {
        // when

        // then
        assertThatCode(() -> chessBoard.move(Position.from("a2"), Position.from("a3")))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("폰으로 대각선에 있는 다른 색의 기물을 잡는다.")
    void move_pawn_cross() {
        // given
        final Position from = Position.from("a3");
        final Position to = Position.from("b4");

        final Map<Position, ChessPiece> pieceByPosition = new HashMap<>();
        pieceByPosition.put(from, Pawn.from(Color.WHITE));
        pieceByPosition.put(to, Pawn.from(Color.BLACK));

        final ChessBoard chessBoard = new ChessBoard(pieceByPosition);

        // then
        assertThatCode(() -> chessBoard.move(from, to))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("폰의 목적지에 같은색 기물이 있으면 예외를 발생시킵니다.")
    void move_Pawn_Straight1() {
        // given
        chessBoard.move(Position.from("b1"), Position.from("c3"));
        chessBoard.move(Position.from("a7"), Position.from("a6"));

        // then
        assertThatThrownBy(() -> chessBoard.move(Position.from("c2"), Position.from("c3")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("같은색 기물입니다.");
    }

    @Test
    @DisplayName("폰의 이동 경로 사이에 다른 기물이 있으면 예외를 발생시킵니다.")
    void move_Pawn_Straight2() {
        // given
        chessBoard.move(Position.from("b1"), Position.from("c3"));
        chessBoard.move(Position.from("a7"), Position.from("a6"));

        // then
        assertThatThrownBy(() -> chessBoard.move(Position.from("c2"), Position.from("c4")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 경로 사이에 다른 기물이 있습니다.");
    }

    @Test
    @DisplayName("폰의 목적지에 다른색의 기물이 있으면 예외를 발생시킵니다.")
    void move_Pawn_Straight3() {
        // given
        final Position from = Position.from("d5");
        final Position to = Position.from("d6");

        final Map<Position, ChessPiece> pieceByPosition = new HashMap<>();
        pieceByPosition.put(from, Pawn.from(Color.WHITE));
        pieceByPosition.put(to, Pawn.from(Color.BLACK));

        final ChessBoard chessBoard = new ChessBoard(pieceByPosition);

        // then
        assertThatThrownBy(() -> chessBoard.move(from, to))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("폰은 대각선 이동으로만 적을 잡을 수 있습니다.");
    }

    @Test
    @DisplayName("폰의 목적지에 다른색의 기물이 있으면 예외를 발생시킵니다.")
    void move_Pawn_Straight4() {
        // given
        final Position from = Position.from("a5");
        final Position to = Position.from("a4");

        final Map<Position, ChessPiece> pieceByPosition = new HashMap<>();
        pieceByPosition.put(from, Pawn.from(Color.BLACK));
        pieceByPosition.put(Position.from("a3"), Pawn.from(Color.WHITE));

        final ChessBoard chessBoard = new ChessBoard(pieceByPosition);
        chessBoard.move(Position.from("a3"), to);

        // then
        assertThatThrownBy(() -> chessBoard.move(from, to))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("폰은 대각선 이동으로만 적을 잡을 수 있습니다.");
    }

    @Test
    @DisplayName("폰의 대각선 방향에 같은색 기물이 있으면 예외를 발생시킵니다.")
    void move_Pawn_Cross1() {
        // given
        final Position from = Position.from("d5");
        final Position to = Position.from("c6");

        final Map<Position, ChessPiece> pieceByPosition = new HashMap<>();
        pieceByPosition.put(from, Pawn.from(Color.WHITE));
        pieceByPosition.put(to, Pawn.from(Color.WHITE));

        final ChessBoard chessBoard = new ChessBoard(pieceByPosition);

        // then
        assertThatThrownBy(() -> chessBoard.move(from, to))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("같은색 기물입니다.");
    }

    @Test
    @DisplayName("폰의 대각선 방향에 기물이 없으면 예외를 발생시킵니다.")
    void move_Pawn_Cross2() {
        // given
        final Position from = Position.from("d5");
        final Position to = Position.from("c6");

        final Map<Position, ChessPiece> pieceByPosition = new HashMap<>();
        pieceByPosition.put(from, Pawn.from(Color.WHITE));

        final ChessBoard chessBoard = new ChessBoard(pieceByPosition);

        // then
        assertThatThrownBy(() -> chessBoard.move(from, to))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("폰은 상대 기물이 존재할 때만 대각선으로 이동할 수 있습니다.");
    }

    @Test
    @DisplayName("순서에 맞지않는 색의 기물을 이동시키면 예외를 발생시킵니다.")
    void chessBoard_turn() {
        // then
        assertThatThrownBy(() -> chessBoard.move(Position.from("a7"), Position.from("a6")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("WHITE의 차례입니다.");
    }

    @ParameterizedTest
    @DisplayName("폰이 같은 세로줄에 있는 경우의 점수를 구한다.")
    @CsvSource(value = {"BLACK:1.5", "WHITE:1"}, delimiter = ':')
    void calculateScore_pawn(final Color color, final double expected) {
        // given
        final Map<Position, ChessPiece> pieceByPosition = new HashMap<>();
        pieceByPosition.put(Position.from("a1"), Pawn.from(Color.BLACK));
        pieceByPosition.put(Position.from("a3"), Pawn.from(Color.BLACK));
        pieceByPosition.put(Position.from("a5"), Pawn.from(Color.BLACK));

        pieceByPosition.put(Position.from("a2"), Pawn.from(Color.WHITE));

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
        pieceByPosition.put(Position.from("a1"), King.from(Color.BLACK)); // 0
        pieceByPosition.put(Position.from("a2"), Queen.from(Color.BLACK)); // 9
        pieceByPosition.put(Position.from("a3"), Knight.from(Color.BLACK)); // 2.5
        pieceByPosition.put(Position.from("a4"), Rook.from(Color.BLACK)); // 5
        pieceByPosition.put(Position.from("a5"), Bishop.from(Color.BLACK)); // 3
        pieceByPosition.put(Position.from("a6"), Pawn.from(Color.BLACK)); // 1

        pieceByPosition.put(Position.from("h2"), King.from(Color.WHITE));

        final ChessBoard chessBoard = new ChessBoard(pieceByPosition);

        // when
        final Map<Color, Double> scoreByColor = chessBoard.calculateScore();
        final Double actual = scoreByColor.get(color);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("킹을 잡으면 게임이 끝난다.")
    void game_end() {
        // given
        final Position from = Position.from("d2");
        final Position to = Position.from("f4");

        final Map<Position, ChessPiece> pieceByPosition = new HashMap<>();
        pieceByPosition.put(from, Bishop.from(Color.WHITE));
        pieceByPosition.put(to, King.from(Color.BLACK));

        final ChessBoard chessBoard = new ChessBoard(pieceByPosition);

        chessBoard.move(from, to);

        // when
        final boolean actual = chessBoard.isEnd();

        // then
        assertThat(actual).isEqualTo(true);
    }

    @Test
    @DisplayName("start 명령이 입력되기 전에는 ready 상태이다.")
    void isReady() {
        // when
        final boolean actual = chessBoard.isReady();

        // then
        assertThat(actual).isEqualTo(true);
    }

    @Test
    @DisplayName("start 명령이 입력되면 playing 상태이다.")
    void isPlaying() {
        // when
        chessBoard.start();
        final boolean actual = chessBoard.isPlaying();

        // then
        assertThat(actual).isEqualTo(true);
    }
}

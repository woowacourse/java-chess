package chess.domain.chessboard;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.chesspiece.Bishop;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspiece.Color;
import chess.domain.chesspiece.King;
import chess.domain.chesspiece.Knight;
import chess.domain.chesspiece.Rook;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ChessBoardTest {

    @ParameterizedTest
    @DisplayName("위치를 기반으로 기물을 찾는다.")
    @CsvSource(value = {"a1:WHITE", "a8:BLACK"}, delimiter = ':')
    void findPiece(final String position, final Color color) {
        // given
        final ChessBoard chessBoard = ChessBoardFactory.createChessBoard();

        // when
        final ChessPiece actual = chessBoard.findPiece(Position.from(position));

        // then
        assertThat(actual).isInstanceOf(Rook.class);
        assertThat(actual.isSameColor(color)).isTrue();
    }

    @Test
    @DisplayName("위치에 기물이 있는지 확인한다.")
    void findPiece_Null() {
        // given
        final ChessBoard chessBoard = ChessBoardFactory.createChessBoard();

        // when
        final ChessPiece actual = chessBoard.findPiece(Position.from("a3"));

        // then
        assertThat(actual).isNull();
    }

    @Test
    @DisplayName("기물을 빈 위치로 이동시킨다.")
    void move_to_empty() {
        // given
        final ChessBoard chessBoard = ChessBoardFactory.createChessBoard();
        final Position from = Position.from("b1");
        final Position to = Position.from("c3");

        // when
        chessBoard.move(from, to);
        final ChessPiece actual = chessBoard.findPiece(to);

        // then
        assertThat(actual).isInstanceOf(Knight.class);
    }

    @Test
    @DisplayName("기물을 다른색의 기물이 있는 위치로 이동시킨다.")
    void move_to_enemy() {
        // given
        final Position from = Position.from("d2");
        final Position to = Position.from("f4");

        final ChessBoard chessBoard = PieceByPosition.create()
                .add(from, Bishop.from(Color.WHITE))
                .add(to, Rook.from(Color.BLACK))
                .toChessBoard();

        // when
        chessBoard.move(from, to);
        final ChessPiece actual = chessBoard.findPiece(to);

        // then
        assertThat(actual).isInstanceOf(Bishop.class);
    }

    @Test
    @DisplayName("이동 경로 사이에 다른 기물이 있으면 예외를 발생시킵니다.")
    void move_exception1() {
        // given
        final ChessBoard chessBoard = ChessBoardFactory.createChessBoard();
        final Position from = Position.from("a1");
        final Position to = Position.from("a3");

        // then
        assertThatThrownBy(() -> chessBoard.move(from, to))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 경로 사이에 다른 기물이 있습니다.");
    }

    @Test
    @DisplayName("이동 시키려는 기물이 존재하지 않으면 예외가 터진다.")
    void move_exception2() {
        // given
        final ChessBoard chessBoard = ChessBoardFactory.createChessBoard();

        // then
        assertThatThrownBy(() -> chessBoard.move(Position.from("d5"), Position.from("d6")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치에 기물이 존재하지 않습니다.");
    }

    @Test
    @DisplayName("킹이 모두 살아있으면 게임이 끝난 상태가 아니다.")
    void isKingDie() {
        // given
        final ChessBoard chessBoard = PieceByPosition.create()
                .add(Position.from("a1"), King.from(Color.WHITE))
                .add(Position.from("a2"), King.from(Color.BLACK))
                .toChessBoard();

        // when
        final boolean actual = chessBoard.isKingDie();

        // then
        assertThat(actual).isEqualTo(false);
    }

    @Test
    @DisplayName("킹을 잡으면 게임이 끝난다.")
    void game_end() {
        // given
        final Position from = Position.from("f3");
        final Position to = Position.from("f4");

        final ChessBoard chessBoard = PieceByPosition.create()
                .add(from, King.from(Color.WHITE))
                .add(to, King.from(Color.BLACK))
                .toChessBoard();

        // when
        chessBoard.move(from, to);
        final boolean actual = chessBoard.isKingDie();

        // then
        assertThat(actual).isEqualTo(true);
    }

    private static class PieceByPosition {

        private final Map<Position, ChessPiece> value;

        private PieceByPosition() {
            this.value = new HashMap<>();
        }

        static PieceByPosition create() {
            return new PieceByPosition();
        }

        PieceByPosition add(Position position, ChessPiece chessPiece) {
            value.put(position, chessPiece);
            return this;
        }


        ChessBoard toChessBoard() {
            return new ChessBoard(value);
        }
    }
}

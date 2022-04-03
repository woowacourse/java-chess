package chess.domain;

import static chess.domain.position.Position.from;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.ChessBoardFactory;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspiece.Color;
import chess.domain.chesspiece.King;
import chess.domain.position.Position;
import chess.result.MoveResult;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameTest {

    @Test
    @DisplayName("순서에 맞지않는 색의 기물을 이동시키면 예외를 발생시킵니다.")
    void chessBoard_turn() {
        // given
        final ChessGame chessGame = new ChessGame(ChessBoardFactory.createChessBoard());
        chessGame.start();

        // then
        assertThatThrownBy(() -> chessGame.move(from("a7"), from("a6")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("WHITE의 차례입니다.");
    }

    @Test
    @DisplayName("게임 중이 아닐 때 기물을 이동시키려고 하면 예외를 던진다.")
    void move_not_in_play() {
        // given
        final ChessGame chessGame = new ChessGame(ChessBoardFactory.createChessBoard());

        // then
        assertThatThrownBy(() -> chessGame.move(Position.from("a1"), Position.from("a2")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("게임이 시작되지 않았습니다.");
    }

    @Test
    @DisplayName("킹을 잡으면 게임이 끝난다.")
    void game_end_when_king_die() {
        // given
        final Position from = from("d5");
        final Position to = from("d6");
        final Map<Position, ChessPiece> pieceByPosition = new HashMap<>();
        pieceByPosition.put(from, King.from(Color.WHITE));
        pieceByPosition.put(to, King.from(Color.BLACK));
        final ChessBoard chessBoard = new ChessBoard(pieceByPosition);
        final ChessGame chessGame = new ChessGame(chessBoard);
        chessGame.start();

        // when
        final MoveResult moveResult = chessGame.move(from, to);
        final boolean actual = moveResult.isKingDie();

        // then
        assertThat(actual).isEqualTo(true);
    }

    @Test
    @DisplayName("게임이 이미 시작한 후 start 하면 예외가 터진다.")
    void start_after_start() {
        // given
        final ChessGame chessGame = new ChessGame(ChessBoardFactory.createChessBoard());
        chessGame.start();

        // then
        assertThatThrownBy(() -> chessGame.start())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("게임이 이미 시작되었습니다.");
    }

    @Test
    @DisplayName("게임을 시작하지 않고 move 하면 예외가 터진다.")
    void move_before_start() {
        // given
        final ChessGame chessGame = new ChessGame(ChessBoardFactory.createChessBoard());

        // then
        assertThatThrownBy(() -> chessGame.move(Position.from("a1"), Position.from("a2")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("게임이 시작되지 않았습니다.");
    }

    @Test
    @DisplayName("게임을 종료된 후 move 하면 예외가 터진다.")
    void move_after_end() {
        // given
        final ChessGame chessGame = new ChessGame(ChessBoardFactory.createChessBoard());
        chessGame.end();

        // then
        assertThatThrownBy(() -> chessGame.move(Position.from("a1"), Position.from("a2")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("게임이 이미 종료되었습니다.");
    }

    @Test
    @DisplayName("게임을 시작하지 않고 status 하면 예외가 터진다.")
    void calculateScore_before_start() {
        // given
        final ChessGame chessGame = new ChessGame(ChessBoardFactory.createChessBoard());

        // then
        assertThatThrownBy(() -> chessGame.calculateScore())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("게임이 시작되지 않았습니다.");
    }

    @Test
    @DisplayName("게임을 종료된 후 status 하면 예외가 터진다.")
    void calculateScore_after_end() {
        // given
        final ChessGame chessGame = new ChessGame(ChessBoardFactory.createChessBoard());
        chessGame.end();

        // then
        assertThatThrownBy(() -> chessGame.calculateScore())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("게임이 이미 종료되었습니다.");
    }
}
package chess.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.ChessGame;
import chess.domain.board.strategy.BasicBoardStrategy;

import chess.board.boardGenerator.CheckmateBoardStrategy;
import chess.board.boardGenerator.NotCheckmateBoardStrategy;
import chess.board.boardGenerator.TestBoardStrategy;
import chess.board.boardGenerator.WhiteCheckBoardStrategy;
import chess.domain.piece.BlackPawn;
import chess.domain.piece.King;
import chess.domain.piece.Team;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameTest {

    private ChessGame game;

    @BeforeEach
    void init() {
        game = new ChessGame();
    }

    @DisplayName("이동 경로에 말이 있으면 이동 할 수 없다.")
    @Test
    void valid_path() {
        // given
        game.startGame(new BasicBoardStrategy());

        Position from = new Position(Column.D, Row.ONE);
        Position to = new Position(Column.F, Row.THREE);

        // then
        assertThatThrownBy(() -> game.move(from, to))
                .hasMessage("이동 경로에 말이 있습니다.");
    }

    @DisplayName("도착 지점에 같은 팀의 말이 있는 경우")
    @Test
    void valid_arrive() {
        // given
        game.startGame(new BasicBoardStrategy());

        Position from = new Position(Column.D, Row.ONE);
        Position to = new Position(Column.E, Row.TWO);

        // then
        assertThatThrownBy(() -> game.move(from, to))
                .hasMessage("도착 지점에 아군 말이 있어 이동이 불가능합니다.");
    }

    @DisplayName("자신의 차례인지 검증")
    @Test
    void valid_turn() {
        // given
        game.startGame(new BasicBoardStrategy());

        Position from = new Position(Column.A, Row.SEVEN);
        Position to = new Position(Column.A, Row.SIX);

        // then
        assertThatThrownBy(() -> game.move(from, to))
                .hasMessage("현재 차례는 WHITE입니다.");
    }

    @DisplayName("체크인 상황")
    @Test
    void valid_black_check() {
        // given
        game.startGame(new WhiteCheckBoardStrategy());

        // then
        assertThat(game.isCheck()).isTrue();
    }

    @DisplayName("폰이 이동할 수 없는 대각선 위치에 왕이 존재하는 경우 check 아님")
    @Test
    void valid_pawn_move_fail() {
        // given
        Position blackPawnPosition = new Position("a1");
        Position whiteKingPosition = new Position("b2");

        TestBoardStrategy boardGenerator = new TestBoardStrategy();
        boardGenerator.put(blackPawnPosition, new BlackPawn());
        boardGenerator.put(whiteKingPosition, new King(Team.WHITE));

        game.startGame(boardGenerator);

        // then
        assertThat(game.isCheck()).isFalse();
    }

    @DisplayName("폰이 이동 가능한 위치에 왕이 존재하는 경우 check")
    @Test
    void valid_pawn_move_ok() {
        // given
        Position blackPawnPosition = new Position("a3");
        Position whiteKingPosition = new Position("b2");

        TestBoardStrategy boardGenerator = new TestBoardStrategy();
        boardGenerator.put(blackPawnPosition, new BlackPawn());
        boardGenerator.put(whiteKingPosition, new King(Team.WHITE));

        game.startGame(boardGenerator);

        // then
        assertThat(game.isCheck()).isTrue();
    }

    @DisplayName("체크메이트")
    @Test
    void checkmate() {
        // given
        game.startGame(new CheckmateBoardStrategy());

        // then
        assertThat(game.isCheckmate()).isTrue();
    }

    @DisplayName("체크메이트 아닌 경우")
    @Test
    void checkmate_failed() {
        // given
        game.startGame(new NotCheckmateBoardStrategy());

        // then
        assertThat(game.isCheckmate()).isFalse();
    }

    @DisplayName("체크인 상황에서 벗어남")
    @Test
    void valid_black_check_move_false() {
        // given
        game.startGame(new WhiteCheckBoardStrategy());

        // then
        assertThatNoException()
                .isThrownBy(() -> game.move(new Position("d8"), new Position("d7")));
    }
}
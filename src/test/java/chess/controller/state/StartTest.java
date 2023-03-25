package chess.controller.state;


import static chess.helper.PositionFixture.B6;
import static chess.helper.PositionFixture.B7;
import static chess.helper.PositionFixture.E2;
import static chess.helper.PositionFixture.E3;
import static org.assertj.core.api.Assertions.assertThat;

import chess.controller.GameCommand;
import chess.dao.ChessMovementDao;
import chess.helper.FakeChessMovementDao;
import chess.model.board.ChessBoard;
import chess.model.board.ChessBoardFactory;
import chess.model.game.ChessGame;
import chess.model.piece.Piece;
import chess.model.position.Position;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StartTest {

    private static final List<Position> EMPTY = Collections.emptyList();

    private Start start;
    private ChessMovementDao dao;
    private ChessGame chessGame;

    @BeforeEach
    void beforeEach() {
        chessGame = new ChessGame();
        dao = new FakeChessMovementDao();
        start = new Start(chessGame, dao);
    }

    @Test
    @DisplayName("execute()는 dao에 저장된 내용이 없다면 ChessBoard를 초기화한 뒤 Play를 반환한다")
    void execute_whenCallWithoutHistory_thenReturnInitialChessBoard() {
        // when
        final GameState actual = start.execute(GameCommand.MOVE, EMPTY);

        // then
        assertThat(actual).isExactlyInstanceOf(Play.class);

        final ChessBoard initialChessBoard = ChessBoardFactory.create();
        final Map<Position, Piece> initialBoard = initialChessBoard.getBoard();
        final Map<Position, Piece> actualBoard = chessGame.getChessBoard().getSquares();

        for (Position position : initialBoard.keySet()) {
            assertThat(actualBoard.get(position).getClass()).isEqualTo(initialBoard.get(position).getClass());
        }
    }

    @Test
    @DisplayName("execute()는 dao에 저장된 내용이 있다면 dao의 내용을 삭제하고 ChessBoard를 초기화한 뒤 Play를 반환한다")
    void execute_whenCallWithHistory_thenReturnInitialChessBoard() {
        // given
        dao.save(E2, E3);
        dao.save(B7, B6);

        // when
        final GameState actual = start.execute(GameCommand.MOVE, EMPTY);

        // then
        assertThat(actual).isExactlyInstanceOf(Play.class);
        assertThat(dao.findAll()).hasSize(0);

        final ChessBoard initialChessBoard = ChessBoardFactory.create();
        final Map<Position, Piece> initialBoard = initialChessBoard.getBoard();
        final Map<Position, Piece> actualBoard = chessGame.getChessBoard().getSquares();

        for (Position position : initialBoard.keySet()) {
            assertThat(actualBoard.get(position).getClass()).isEqualTo(initialBoard.get(position).getClass());
        }
    }

    @Test
    @DisplayName("isContinue()는 호출하면 true를 반환한다.")
    void isContinue_whenCall_thenReturnFalse() {
        // when
        final boolean actual = start.isContinue();

        // then
        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("isPlay()는 호출하면 false를 반환한다.")
    void isPlay_whenCall_thenReturnFalse() {
        // when
        final boolean actual = start.isPlay();

        // then
        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("isPrintable()은 호출하면 false를 반환한다.")
    void isPrintable_wheCall_thenReturnTrue() {
        // when
        final boolean actual = start.isPrintable();

        // then
        assertThat(actual).isFalse();
    }
}

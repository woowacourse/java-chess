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
import chess.model.piece.PieceType;
import chess.model.position.Position;
import chess.service.ChessGameService;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LoadTest {

    private static final List<Position> EMPTY = Collections.emptyList();

    private Load load;
    private ChessMovementDao chessMovementDao;
    private ChessGameService chessGameService;

    @BeforeEach
    void beforeEach() {
        final ChessGame chessGame = new ChessGame();
        chessMovementDao = new FakeChessMovementDao();
        chessGameService = new ChessGameService(chessGame, chessMovementDao);
        load = new Load(chessGameService);
    }

    @Test
    @DisplayName("execute()는 dao에 저장된 내용이 없다면 ChessBoard를 초기화한 뒤 Play를 반환한다")
    void execute_whenCallWithoutHistory_thenReturnInitialChessBoard() {
        // when
        final GameState actual = load.execute(GameCommand.MOVE, EMPTY);

        // then
        assertThat(actual).isExactlyInstanceOf(Play.class);

        final ChessBoard initialChessBoard = ChessBoardFactory.create();
        final Map<Position, Piece> initialBoard = initialChessBoard.getBoard();
        final Map<Position, Piece> actualBoard = chessGameService.getChessBoard().getSquares();

        for (Position position : initialBoard.keySet()) {
            assertThat(actualBoard.get(position).getClass()).isEqualTo(initialBoard.get(position).getClass());
        }
    }

    @Test
    @DisplayName("execute()는 dao에 저장된 내용이 있다면 ChessBoard를 이전 상태로 초기화한 뒤 Play를 반환한다")
    void execute_whenCallWithHistory_thenReturnInitialChessBoard() {
        // given
        chessMovementDao.save(E2, E3);
        chessMovementDao.save(B7, B6);

        // when
        final GameState actual = load.execute(GameCommand.MOVE, EMPTY);

        // then
        assertThat(actual).isExactlyInstanceOf(Play.class);

        final Map<Position, Piece> actualBoard = chessGameService.getChessBoard().getSquares();

        assertThat(actualBoard.get(E3).isSameType(PieceType.PAWN)).isTrue();
        assertThat(actualBoard.get(B6).isSameType(PieceType.PAWN)).isTrue();
        assertThat(actualBoard.get(E2).isSameType(PieceType.EMPTY)).isTrue();
        assertThat(actualBoard.get(B7).isSameType(PieceType.EMPTY)).isTrue();
    }

    @Test
    @DisplayName("isContinue()는 호출하면 true를 반환한다.")
    void isContinue_whenCall_thenReturnFalse() {
        // when
        final boolean actual = load.isContinue();

        // then
        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("isPlay()는 호출하면 false를 반환한다.")
    void isPlay_whenCall_thenReturnFalse() {
        // when
        final boolean actual = load.isPlay();

        // then
        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("isPrintable()은 호출하면 false를 반환한다.")
    void isPrintable_wheCall_thenReturnTrue() {
        // when
        final boolean actual = load.isPrintable();

        // then
        assertThat(actual).isFalse();
    }
}

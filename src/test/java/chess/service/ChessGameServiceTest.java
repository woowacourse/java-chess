package chess.service;

import static chess.helper.PositionFixture.B6;
import static chess.helper.PositionFixture.B7;
import static chess.helper.PositionFixture.E2;
import static chess.helper.PositionFixture.E3;
import static org.assertj.core.api.Assertions.assertThat;

import chess.dao.ChessMovementDao;
import chess.helper.FakeChessMovementDao;
import chess.model.game.ChessGame;
import chess.model.piece.Piece;
import chess.model.piece.PieceType;
import chess.model.position.Position;
import chess.view.dto.ChessBoardResponse;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameServiceTest {

    private ChessGameService chessGameService;
    private ChessMovementDao chessMovementDao;

    @BeforeEach
    void beforeEach() {
        final ChessGame chessGame = new ChessGame();
        chessMovementDao = new FakeChessMovementDao();
        chessGameService = new ChessGameService(chessGame, chessMovementDao);
    }

    @Test
    @DisplayName("initializeChessGame()은 호출하면 DB 정보와 ChessBoard를 초기화한다.")
    void initializeChessGame_whenCall_thenClearDbAndInitializeChessBoard() {
        // when
        chessGameService.initializeChessGame();

        // then
        final ChessBoardResponse chessBoardResponse = chessGameService.getChessBoard();

        assertThat(chessBoardResponse.getSquares()).isNotNull();
        assertThat(chessMovementDao.findAll()).isEmpty();
    }

    @Test
    @DisplayName("loadGame()은 호출하면 DB에 저장된 정보를 조회해 이전 게임 진행 상태로 초기화한다.")
    void loadGame_whenCall_thenfindAllDbAndInitializeChessBoard() {
        // given
        chessMovementDao.save(E2, E3);
        chessMovementDao.save(B7, B6);

        // when
        chessGameService.loadChessGame();

        // then
        final Map<Position, Piece> squares = chessGameService.getChessBoard().getSquares();

        assertThat(squares.get(E2)).isEqualTo(Piece.EMPTY);
        assertThat(squares.get(B7)).isEqualTo(Piece.EMPTY);
        assertThat(squares.get(E3).isSameType(PieceType.PAWN)).isTrue();
        assertThat(squares.get(B6).isSameType(PieceType.PAWN)).isTrue();
        assertThat(chessMovementDao.findAll()).hasSize(2);
    }

    @Test
    @DisplayName("move()는 유효한 출발지와 목적지를 입력하면 기물을 이동한다.")
    void move_givenValidSourceAndTarget_thenSuccess() {
        // given
        chessGameService.initializeChessGame();

        // when
        chessGameService.move(E2, E3);

        // then
        final Map<Position, Piece> squares = chessGameService.getChessBoard().getSquares();

        assertThat(squares.get(E2)).isEqualTo(Piece.EMPTY);
        assertThat(squares.get(E3).isSameType(PieceType.PAWN)).isTrue();
    }

    @Test
    @DisplayName("isGameOnGoing()은 게임을 진행할 수 있는 상태에서 호출하면 true를 반환한다.")
    void isGameOnGoing_whenInitializeChessGameCall_thenReturnTrue() {
        // given
        chessGameService.initializeChessGame();

        // when
        final boolean actual = chessGameService.isGameOnGoing();

        // then
        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("clear()는 호출하면 DB의 저장되어 있는 데이터를 삭제한다.")
    void clear_whenCall_thenRemoveDB() {
        // when
        chessGameService.clear();

        // then
        assertThat(chessMovementDao.findAll()).isEmpty();
    }
}

package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import chess.domain.position.XAxis;
import chess.domain.position.YAxis;
import chess.dto.request.CreatePieceDto;
import chess.dto.request.DeletePieceDto;
import chess.dto.request.UpdatePiecePositionDto;
import chess.dto.response.BoardDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardDaoImplTest {
    private static final String GAME_ID = "test-game";
    private static final XAxis X_AXIS = XAxis.A;
    private static final YAxis Y_AXIS = YAxis.ONE;
    private static final XAxis X_AXIS_2 = XAxis.B;
    private static final YAxis Y_AXIS_2 = YAxis.TWO;
    private static final PieceType PIECE_TYPE = PieceType.PAWN;
    private static final PieceColor PIECE_COLOR = PieceColor.WHITE;

    private BoardDaoImpl boardDao;

    @BeforeEach
    void setUp() {
        boardDao = new BoardDaoImpl();

        GameDaoImpl gameDao = new GameDaoImpl();
        gameDao.createGame(GAME_ID);
    }

    @DisplayName("getBoard 는 BoardDto 를 반환한다.")
    @Test
    void getBoard() {
        // given & when
        BoardDto board = boardDao.getBoard(GAME_ID);

        // then
        assertThat(board).isInstanceOf(BoardDto.class);
    }

    @DisplayName("CreatePieceDto를 전달받아 board 테이블에 기물을 생성한다.")
    @Test
    void createPiece() {
        // given
        CreatePieceDto createPieceDto = CreatePieceDto.of(GAME_ID, Position.of(X_AXIS, Y_AXIS),
                new Piece(PIECE_TYPE, PIECE_COLOR));

        // when & then
        boardDao.createPiece(createPieceDto);
    }

    @DisplayName("DeletePieceDto를 전달받아 board 테이블에 기물을 제거한다.")
    @Test
    void deletePiece() {
        // given
        DeletePieceDto deletePieceDto = DeletePieceDto.of(GAME_ID, Position.of(X_AXIS, Y_AXIS));
        boardDao.createPiece(
                CreatePieceDto.of(GAME_ID, Position.of(X_AXIS, Y_AXIS), new Piece(PIECE_TYPE, PIECE_COLOR)));

        // when & then
        boardDao.deletePiece(deletePieceDto);
    }

    @DisplayName("UpdatePiecePositionDto를 전달받아 board 테이블의 특정 기물 위치를 변경한다.")
    @Test
    void updatePiecePosition() {
        // given
        UpdatePiecePositionDto updatePiecePositionDto = UpdatePiecePositionDto.of(GAME_ID, X_AXIS, Y_AXIS, X_AXIS_2,
                Y_AXIS_2);
        boardDao.createPiece(
                CreatePieceDto.of(GAME_ID, Position.of(X_AXIS, Y_AXIS), new Piece(PIECE_TYPE, PIECE_COLOR)));

        // then
        boardDao.updatePiecePosition(updatePiecePositionDto);
    }

    @AfterEach
    void tearDown() {
        GameDaoImpl gameDao = new GameDaoImpl();
        gameDao.deleteGame(GAME_ID);
    }
}

package dao;

import domain.game.ChessBoardGenerator;
import domain.game.GameState;
import domain.piece.*;
import dto.dao.ChessGameDaoResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class ChessDaoTest {

    private final ChessGameDao chessGameDao = new ChessGameDaoFake();

    @DisplayName("새로운 게임 방을 만든다.")
    @Test
    void newGameTest() {
        assertThatCode(() -> chessGameDao.createRoom())
                .doesNotThrowAnyException();
    }

    @DisplayName("게임 정보 저장 시 64개의 기물이 저장된다.")
    @Test
    void saveExistGameTest() {
        chessGameDao.createRoom();
        Map<Position, Piece> board = new ChessBoardGenerator().generate();

        assertThat(chessGameDao.saveChessBoard(board, Side.WHITE, 1L)).isTrue();
    }

    @DisplayName("존재하지 않는 게임 방 번호의 기물을 저장하려 하면 예외가 발생한다.")
    @Test
    void saveNonExistGameTest() {
        //given
        Map<Position, Piece> board = new ChessBoardGenerator().generate();
        //when
        assertThatCode(() -> chessGameDao.saveChessBoard(board, Side.WHITE, 1L))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("특정 게임방의 저장된 기물들의 정보, 게임 상태를 가져온다.")
    @Test
    void loadGameTest() {
        //given
        Map<Position, Piece> board = new ChessBoardGenerator().generate();
        chessGameDao.createRoom();
        chessGameDao.saveChessBoard(board, Side.WHITE, 1L);
        //when
        ChessGameDaoResponseDto chessGameDaoResponseDto = chessGameDao.loadGame(1L);
        Map<Position, Piece> boardByLoad = chessGameDaoResponseDto.getBoard();
        //then

        Assertions.assertAll(
                () -> assertThat(boardByLoad.get(Position.of("b", "2"))).isEqualTo(new Pawn(Side.WHITE)),
                () -> assertThat(chessGameDaoResponseDto.getLastTurn()).isEqualTo(Side.WHITE)
        );
    }

    @DisplayName("게임 상태를 업데이트 한다.")
    @Test
    void updateChessTest() {
        //given
        Map<Position, Piece> board = new ChessBoardGenerator().generate();
        Long roomId = chessGameDao.createRoom();
        chessGameDao.saveChessBoard(board, Side.WHITE, roomId);

        board.put(Position.of("d", "4"), new Pawn(Side.WHITE));
        board.put(Position.of("d", "2"), new EmptyPiece());
        //when
        chessGameDao.updateGameRoom(roomId, Side.BLACK, GameState.RUN);
        chessGameDao.saveChessBoard(board, Side.BLACK, roomId);
        ChessGameDaoResponseDto chessGameDaoResponseDto = chessGameDao.loadGame(roomId);
        Map<Position, Piece> boardByLoad = chessGameDaoResponseDto.getBoard();
        //then
        Assertions.assertAll(
                () -> assertThat(boardByLoad.get(Position.of("d", "2"))).isEqualTo(new EmptyPiece()),
                () -> assertThat(chessGameDaoResponseDto.getLastTurn()).isEqualTo(Side.BLACK)
        );
    }

    @DisplayName("존재하지 않는 pk인 0으로 게임 방을 조회하려고 하면 예외가 발생한다.")
    @Test
    void nonExistGameTest() {
        assertThat(chessGameDao.hasGame(0L)).isFalse();
    }

    @DisplayName("모든 게임 방을 조회한다.")
    @Test
    void findAllGameRoomsTest() {
        //given
        chessGameDao.createRoom();
        chessGameDao.createRoom();
        chessGameDao.createRoom();

        //then
        assertThat(chessGameDao.findAllGameRooms().size()).isEqualTo(3);
    }

    @DisplayName("존재하는 게임방 번호면 true를 반환한다.")
    @Test
    void hasGameFailTest() {
        assertThat(chessGameDao.hasGame(0L)).isFalse();
    }

    @DisplayName("존재하지 않는 게임방 번호면 false를 반환한다.")
    @Test
    void hasGameSuccessTest() {
        Long room = chessGameDao.createRoom();

        assertThat(chessGameDao.hasGame(room)).isTrue();
    }

    @DisplayName("게임 방을 삭제한다.")
    @Test
    void deleteTest() {
        //given
        Long room = chessGameDao.createRoom();
        Map<Position, Piece> board = new ChessBoardGenerator().generate();
        chessGameDao.saveChessBoard(board, Side.WHITE, room);

        //when
        chessGameDao.deleteGameRoom(room);
        //then
        assertThat(chessGameDao.hasGame(room)).isFalse();
    }
}

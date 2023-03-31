package service;

import dao.ChessGameDaoFake;
import domain.game.Board;
import domain.game.ChessBoardGenerator;
import domain.game.ChessGame;
import domain.game.GameState;
import domain.piece.*;
import dto.service.ChessGameCreateResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class ChessGameServiceTest {
    private ChessGameService chessGameService;

    @BeforeEach
    void setUp() {
        chessGameService = new ChessGameService(new ChessGameDaoFake());
    }

    @DisplayName("게임방을 생성한다.")
    @Test
    void gameCreateTest() {
        Map<Position, Piece> board = new ChessBoardGenerator().generate();
        ChessGame chessGame = new ChessGame(new Board(board), Side.WHITE, GameState.RUN);

        assertThatCode(() -> chessGameService.createGameRoom(chessGame))
                .doesNotThrowAnyException();

    }

    @DisplayName("체스 게임 정보를 업데이트한다.")
    @Test
    void gameUpdateTest() {
        //given
        Map<Position, Piece> board = new ChessBoardGenerator().generate();
        ChessGame chessGame = new ChessGame(new Board(board), Side.WHITE, GameState.RUN);
        ChessGameCreateResponseDto gameRoom = chessGameService.createGameRoom(chessGame);
        Long roomId = gameRoom.getRoomId();

        //when
        board.put(Position.of("e", "2"), new EmptyPiece());
        board.put(Position.of("e", "4"), new Pawn(Side.WHITE));
        ChessGame updateChessGame = new ChessGame(new Board(board), Side.BLACK, GameState.RUN);

        //then
        assertThatCode(() -> chessGameService.updateChessGame(updateChessGame, roomId))
                .doesNotThrowAnyException();
    }

    @DisplayName("체스게임 정보를 가져온다.")
    @Test
    void loadGameTest() {
        //given
        Map<Position, Piece> board = new ChessBoardGenerator().generate();
        board.put(Position.of("e", "2"), new EmptyPiece());
        board.put(Position.of("e", "4"), new Pawn(Side.WHITE));
        ChessGame chessGame = new ChessGame(new Board(board), Side.BLACK, GameState.RUN);
        ChessGameCreateResponseDto room = chessGameService.createGameRoom(chessGame);
        //when
        ChessGameCreateResponseDto chessGameDto = chessGameService.loadChessGame(room.getRoomId());
        ChessGame chessGameByLoad = chessGameDto.getChessGame();
        //then
        assertThat(chessGameByLoad.getCurrentTurn()).isEqualTo(Side.BLACK);
    }

    @DisplayName("게임 방 목록을 가져온다.")
    @Test
    void findAllRoomsTest() {
        //given
        Map<Position, Piece> board = new ChessBoardGenerator().generate();
        ChessGame chessGame = new ChessGame(new Board(board), Side.WHITE, GameState.RUN);
        chessGameService.createGameRoom(chessGame);
        chessGameService.createGameRoom(chessGame);
        //when
        List<Long> gameRooms = chessGameService.findAllRooms();
        //then
        assertThat(gameRooms.size()).isEqualTo(2);
    }

    @DisplayName("게임 방 존재 유무를 반환한다.")
    @Test
    void hasGameFailTest() {
        assertThat(chessGameService.hasGame(0L)).isFalse();
    }

    @DisplayName("")
    @Test
    void hasGameSuccessTest() {
        Map<Position, Piece> board = new ChessBoardGenerator().generate();
        ChessGame chessGame = new ChessGame(new Board(board), Side.WHITE, GameState.RUN);
        ChessGameCreateResponseDto gameRoom = chessGameService.createGameRoom(chessGame);
        Long roomId = gameRoom.getRoomId();

        assertThat(chessGameService.hasGame(roomId)).isTrue();

    }
}

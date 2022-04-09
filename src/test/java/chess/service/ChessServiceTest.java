package chess.service;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.dto.MoveDto;
import chess.dto.ResponseDto;
import chess.dto.StatusDto;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessServiceTest {

    private final FakeChessGameDao chessGameDao = new FakeChessGameDao();
    private final FakeBoardDao boardDao = new FakeBoardDao();
    private final ChessService chessService = new ChessService(chessGameDao, boardDao);

    @Test
    @DisplayName("chessGame DB에 save 했을 때, Id를 반환한다.")
    void returnGameId() {
        String gameId = chessService.start();
        assertThat(gameId).isEqualTo("1");
    }

    @Test
    @DisplayName("gameId를 이용하여 Board를 찾는다.")
    public void findBoardByGameId() {
        //given
        String gameId = chessService.start();
        //when
        Map<String, String> board = chessService.getBoard(Long.valueOf(gameId));
        //then
        Map<String, String> boardDto = new LinkedHashMap<>();
        for (Entry<Position, Piece> entry : Board.create().getBoard().entrySet()) {
            boardDto.put(entry.getKey().getPosition(), entry.getValue().getName());
        }
        assertThat(board).isEqualTo(boardDto);
    }

    @Test
    @DisplayName("Id를 이용하여 게임 시작 후, turn을 찾는다.")
    public void findTurnByIdWhenStart() {
        //given
        String id = chessService.start();
        //then
        assertThat(chessService.getTurn(Long.valueOf(id))).isEqualTo("white");
    }

    @Test
    @DisplayName("Id를 이용하여 move 후, turn을 찾는다.")
    public void findTurnByIdWhenMove() {
        //given
        String id = chessService.start();
        //when
        chessService.move(new MoveDto(id, "12", "14"));
        //then
        assertThat(chessService.getTurn(Long.valueOf(id))).isEqualTo("black");
    }

    @Test
    @DisplayName("gameId를 이용하여 Status값이 나오는지 확인한다.")
    public void findCurrentScore() {
        //given
        String gameId = chessService.start();
        //then
        assertThat(chessService.status(Long.valueOf(gameId)))
                .isEqualTo(new StatusDto(38.0, 38.0, "무승부", "무승부"));
    }

    @Test
    @DisplayName("gameId를 이용하여 game이 끝났는지 확인한다.")
    public void endByGameId() {
        //given
        String gameId = chessService.start();
        //when
        chessService.end(Long.valueOf(gameId));
        //then
        assertThat(chessGameDao.getChessGameSize()).isEqualTo(0);
        assertThat(boardDao.getBoardSize()).isEqualTo(0);
    }

    @Test
    @DisplayName("move할 때 exception이 발생하지 않고 checkmate가 아닌 경우, 다음 턴과 상태코드 200을 반환한다.")
    public void returnResponseStateCode_200_WhenNotExceptionAndCheckmate() {
        //given
        String gameId = chessService.start();
        //then
        assertThat(chessService.move(new MoveDto(gameId, "12", "14")))
                .isEqualTo(new ResponseDto("200", "성공", "black"));
    }

    @Test
    @DisplayName("move할 때 checkmate인 경우, 상태코드 300을 반환한다.")
    public void returnResponseStateCode_300_WhenCheckmate() {
        //given
        String gameId = chessService.start();
        //when
        chessService.move(new MoveDto(gameId, "12", "14"));
        chessService.move(new MoveDto(gameId, "57", "55"));
        chessService.move(new MoveDto(gameId, "11", "13"));
        chessService.move(new MoveDto(gameId, "55", "54"));
        chessService.move(new MoveDto(gameId, "13", "53"));
        chessService.move(new MoveDto(gameId, "87", "85"));
        chessService.move(new MoveDto(gameId, "53", "54"));
        chessService.move(new MoveDto(gameId, "85", "84"));

        //then
        assertThat(chessService.move(new MoveDto(gameId, "54", "58")))
                .isEqualTo(new ResponseDto("300", "끝", "white"));
    }

    @Test
    @DisplayName("move할 때 exception이 발생한 경우, 상태코드 400을 반환한다.")
    public void returnResponseStateCode_400_WhenException() {
        //given
        String gameId = chessService.start();
        //then
        assertThat(chessService.move(new MoveDto(gameId, "12", "15")))
                .isEqualTo(new ResponseDto("400", "[ERROR] 행마법에 맞지 않는 이동입니다.", "white"));
    }

}
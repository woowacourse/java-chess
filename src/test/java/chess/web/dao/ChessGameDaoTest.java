package chess.web.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.ChessGame;
import chess.domain.Command;
import chess.web.dto.ChessGameDto;
import java.sql.Connection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameDaoTest {

    @DisplayName("커넥션 테스트")
    @Test
    public void connection() {
        //given & when
        Connection connection = DBConnector.getConnection();

        //then
        assertThat(connection).isNotNull();
    }
    
    @DisplayName("체스 게임 정보 저장 테스트")
    @Test
    public void save() {
        //given
        ChessGame chessGame = new ChessGame("test");
        chessGame.progress(Command.from("start"));

        ChessGameDto chessGameDto = new ChessGameDto(chessGame);

        ChessBoardDao chessBoardDao = new ChessBoardDao();
        ChessGameDao chessGameDao = new ChessGameDao();

        //when & then
        int chessBoardId = chessBoardDao.save();

        chessGameDao.save(chessGameDto, chessBoardId);
    }

    @AfterEach
    private void rollback() {
        ChessGameDao chessGameDao = new ChessGameDao();
        chessGameDao.remove("test");
    }
}
package chess.web.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.ChessGame;
import chess.domain.Command;
import chess.domain.state.State;
import chess.web.dto.ChessGameDto;
import java.sql.Connection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameDaoTest {

    private static final ChessGameDao chessGameDao = new ChessGameDao();
    private static final PieceDao pieceDao = new PieceDao();

    @AfterEach
    private void rollback() {
        chessGameDao.remove("test");
    }

    @DisplayName("커넥션 테스트")
    @Test
    public void connection() {
        //given & when
        Connection connection = DBConnector.getConnection();

        //then
        assertThat(connection).isNotNull();
    }
    
    @DisplayName("체스 게임 저장 테스트")
    @Test
    public void save() {
        //given
        ChessGame chessGame = new ChessGame("test");
        chessGame.progress(Command.from("start"));

        ChessGameDto chessGameDto = ChessGameDto.from(chessGame);

        //when & then
        Assertions.assertDoesNotThrow(() -> chessGameDao.save(chessGameDto));
    }

    @DisplayName("체스 게임 업데이트 테스트")
    @Test
    public void update() {
        //given
        ChessGame chessGame = new ChessGame("test");
        ChessGameDto chessGameDto = ChessGameDto.from(chessGame);

        chessGameDao.save(chessGameDto);
        pieceDao.save(chessGameDto);

        //when
        chessGame.progress(Command.from("start"));
        chessGameDto = ChessGameDto.from(chessGame);

        chessGameDao.update(chessGameDto);

        //then
        chessGame = chessGameDao.findByName("test");

        State state = chessGame.getState();
        assertThat(state.getTurn()).isEqualTo("white");
    }
}

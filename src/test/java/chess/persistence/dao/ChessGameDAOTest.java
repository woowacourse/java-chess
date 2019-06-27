package chess.persistence.dao;

import chess.persistence.dto.ChessGameDTO;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ChessGameDAOTest {
    @Test
    void 게임_진행과정_데이터_데이터베이스에_잘들어가는지_테스트() {
        ChessGameDTO chessGameDTO = new ChessGameDTO();
        chessGameDTO.setGameStatus(false);
        chessGameDTO.setLastUser("white");
        assertThat(ChessGameDAO.getInstance().addGameStatus(chessGameDTO)).isEqualTo(1);
    }

    @Test
    void 게임_진행과정_최대_게임아이디_데이터_찾기_테스트() {
        ChessGameDTO chessGameDTO = new ChessGameDTO();
        chessGameDTO.setGameId(9);
        assertThat(ChessGameDAO.getInstance().findByMaxGameId()).isEqualTo(chessGameDTO);
    }
}
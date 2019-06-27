package dao;

import chess.domain.DBConnector;
import dto.NavigatorDto;
import dto.PieceDto;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PieceDaoTest {
    @Test
    void PieceCRUDTest() {
        DBConnector.setDATABASE("chess_test_db");
        GameDao gameDao = GameDaoImpl.getInstance();
        PieceDao pieceDao = PieceDaoImpl.getInstance();

        int gameId = gameDao.addGame();
        PieceDto pieceDto1 = new PieceDto(2, gameId, 1, "a4");
        PieceDto pieceDto2 = new PieceDto(1, gameId, 1, "b8");
        assertThat(pieceDao.addPiece(pieceDto1)).isEqualTo(1);
        assertThat(pieceDao.addPiece(pieceDto2)).isEqualTo(1);
        assertThat(pieceDao.findByGameId(gameId)).isEqualTo(Arrays.asList(pieceDto1, pieceDto2));
        assertThat(pieceDao.findByPosition(pieceDto1)).isEqualTo(pieceDto1);

        NavigatorDto navigatorDto = new NavigatorDto(gameId, "a4", "a5");
        assertThat(pieceDao.updatePiece(navigatorDto)).isEqualTo(1);

        PieceDto newPieceDto1 = new PieceDto(2, gameId, 1, "a5");
        assertThat(pieceDao.deletePiece(newPieceDto1)).isEqualTo(1);
        assertThat(pieceDao.deletePiece(pieceDto2)).isEqualTo(1);

        gameDao.deleteGameByid(gameId);
        DBConnector.setDATABASE("chess_db");
    }
}

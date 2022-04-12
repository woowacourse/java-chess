package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.ChessBoardInitLogic;
import chess.domain.ChessBoardPosition;
import chess.domain.ChessGame;
import chess.domain.Team;
import chess.domain.piece.ChessPiece;
import chess.dto.BoardData;
import chess.dto.GameData;
import chess.service.ChessPieceConverter;
import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class BoardDaoTest {

    @BeforeAll
    static  void setUp() {
        GameDao gameDao = new GameDao();
        gameDao.saveGame(GameData.of(1111, Team.of(Team.WHITE)));
    }

    @Test
    void updateAll() {
        final BoardDao boardDao = new BoardDao();
        ChessGame chessGame = ChessGame.create(1111);
        chessGame.initialize(Team.WHITE, ChessBoardInitLogic.initialize());
        boardDao.updateAll(chessGame.getGameId(), toBoardDatas(chessGame.getChessBoardData()));
    }

    @Test
    void findAll() {
        GameDao gameDao = new GameDao();
        final BoardDao boardDao = new BoardDao();
        ChessGame chessGame = ChessGame.create(1111);
        gameDao.saveGame(GameData.of(1111, Team.of(Team.WHITE)));
        List<BoardData> boardDatas = boardDao.findAll(chessGame.getGameId());
        assertThat(boardDatas).isEmpty();
    }

    @AfterEach
    void afterEach() {
        BoardDao boardDao = new BoardDao();
        boardDao.deleteAll(1111);

        GameDao gameDao = new GameDao();
        gameDao.deleteGameData(1111);
    }

    private List<BoardData> toBoardDatas(Map<ChessBoardPosition, ChessPiece> mapData) {
        return mapData.entrySet()
                .stream()
                .map(it -> BoardData.of(ChessPieceConverter.of(it.getValue()),
                        it.getKey().getColumn(),
                        it.getKey().getRow()))
                .collect(Collectors.toList());
    }
}

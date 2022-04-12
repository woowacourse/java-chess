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

class DbBoardDaoTest {

    @BeforeAll
    static  void setUp() {
        DbGameDao dbGameDao = new DbGameDao();
        dbGameDao.saveGame(GameData.of(1111, Team.of(Team.WHITE)));
    }

    @Test
    void connection() {
        final DbBoardDao dbBoardDao = new DbBoardDao();
        final Connection connection = dbBoardDao.getConnection();
        assertThat(connection).isNotNull();
    }

    @Test
    void updateAll() {
        final DbBoardDao dbBoardDao = new DbBoardDao();
        dbBoardDao.getConnection();
        ChessGame chessGame = ChessGame.create(1111);
        chessGame.initialize(Team.WHITE, ChessBoardInitLogic.initialize());
        dbBoardDao.updateAll(chessGame.getGameId(), toBoardDatas(chessGame.getChessBoardData()));
    }

    @Test
    void findAll() {
        DbGameDao dbGameDao = new DbGameDao();
        final DbBoardDao dbBoardDao = new DbBoardDao();
        final Connection connection = dbBoardDao.getConnection();
        ChessGame chessGame = ChessGame.create(1111);
        dbGameDao.saveGame(GameData.of(1111, Team.of(Team.WHITE)));
        List<BoardData> boardDatas = dbBoardDao.findAll(chessGame.getGameId());
        assertThat(boardDatas).isEmpty();
    }

    @AfterEach
    void afterEach() {
        DbBoardDao dbBoardDao = new DbBoardDao();
        dbBoardDao.deleteAll(1111);

        DbGameDao dbGameDao = new DbGameDao();
        dbGameDao.deleteGameData(1111);
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

package chess.service;

import chess.dao.BoardDao;
import chess.dao.ChessGameDao;
import chess.dao.entity.BoardEntity;
import chess.dao.entity.ChessGameEntity;
import chess.domain.game.ChessGame;
import chess.domain.game.GameSwitch;
import chess.domain.game.Turn;
import chess.domain.piece.Team;
import chess.service.util.BoardEntitiesToBoardConvertor;
import java.util.ArrayList;
import java.util.List;

public class Service {

    private final ChessGameDao chessGameDao = new ChessGameDao();
    private final BoardDao boardDao = new BoardDao();

    public void createChessGame(final String name) {
        ChessGame chessGame = ChessGame.createBasic(name);
        saveChessGame(chessGame);
    }

    private void saveChessGame(final ChessGame chessGame) {
        String name = chessGame.getName();
        chessGameDao.delete(name);
        chessGameDao.save(new ChessGameEntity(chessGame));
        boardDao.delete(name);
        boardDao.save(BoardEntity.generateBoardEntities(name, chessGame.getCurrentBoard()));
    }

    public ChessGame loadChessGame(final String name) {
        ChessGameEntity chessGameEntity = chessGameDao.load(name);
        return new ChessGame(
                chessGameEntity.getName(),
                BoardEntitiesToBoardConvertor.convert(boardDao.load(name)),
                new GameSwitch(chessGameEntity.isOn()),
                new Turn(Team.of(chessGameEntity.getTeamValueOfTurn()))
        );
    }

    public List<ChessGame> loadAllChessGames() {
        List<ChessGame> chessGames = new ArrayList<>();
        List<ChessGameEntity> chessGameEntities = chessGameDao.loadAll();
        for (ChessGameEntity chessGameEntity : chessGameEntities) {
            ChessGame chessGame = new ChessGame(
                    chessGameEntity.getName(),
                    BoardEntitiesToBoardConvertor.convert(boardDao.load(chessGameEntity.getName())),
                    new GameSwitch(chessGameEntity.isOn()),
                    new Turn(Team.of(chessGameEntity.getTeamValueOfTurn()))
            );
            chessGames.add(chessGame);
        }
        return chessGames;
    }

    public void deleteChessGame(final String name) {
        chessGameDao.delete(name);
        boardDao.delete(name);
    }

    public void movePiece(
            final String name,
            final char sourceColumnValue,
            final int sourceRowValue,
            final char targetColumnValue,
            final int targetRowValue
    ) {
        ChessGame chessGame = loadChessGame(name);
        chessGame.move(sourceColumnValue, sourceRowValue, targetColumnValue, targetRowValue);
        saveChessGame(chessGame);
    }
}

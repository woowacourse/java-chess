package chess.db;

import chess.db.dao.GameDao;
import chess.db.dao.PieceDao;
import chess.db.entity.FullGameEntity;
import chess.db.entity.GameEntity;
import chess.db.entity.PieceEntity;
import chess.domain.game.Game;
import chess.domain.game.GameState;
import chess.domain.game.Started;
import chess.dto.request.MoveCommandDto;
import java.util.List;

public class GameRepository {

    private static final String GAME_TABLE_NAME = "game";
    private static final String PIECE_TABLE_NAME = "piece";

    private final GameDao gameDao = new GameDao(GAME_TABLE_NAME);
    private final PieceDao pieceDao = new PieceDao(PIECE_TABLE_NAME);

    public int add(Game game) {
        int gameId = getNewGameId();

        FullGameEntity fullGameEntity = game.toEntityOf(gameId);
        GameEntity gameEntity = fullGameEntity.getGame();
        List<PieceEntity> pieces = fullGameEntity.getPieces();

        gameDao.save(gameEntity);
        pieceDao.saveAll(gameId, pieces);
        return gameId;
    }

    private int getNewGameId() {
        return gameDao.countAll() + 1;
    }

    public boolean checkById(int id) {
        return gameDao.checkById(id);
    }

    public Game findById(int id) {
        GameEntity gameEntity = gameDao.findById(id);
        GameState state = gameEntity.getState();
        List<PieceEntity> pieces = pieceDao.findAllByGameId(id);
        return Started.ofEntity(state, pieces);
    }

    public void update(FullGameEntity gameEntity, MoveCommandDto moveCommand) {
        GameEntity game = gameEntity.getGame();
        int gameId = game.getId();

        pieceDao.deleteAllByGameIdAndPositions(gameId, moveCommand.getPositions());

        PieceEntity movedPiece = gameEntity.getPieceAt(moveCommand.getTarget());
        pieceDao.saveAll(gameId, List.of(movedPiece));

        gameDao.updateState(game);
    }

    public int countAll() {
        return gameDao.countAll();
    }

    public int countByState(GameState state) {
        return gameDao.countByState(state);
    }
}

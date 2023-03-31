package chess.service;

import static chess.EntityCache.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import chess.EntityCache;
import chess.repository.dao.GameDao;
import chess.repository.dao.JdbcConnector;
import chess.domain.ChessGame;
import chess.domain.Position;
import chess.domain.Team;
import chess.domain.result.ScoreAndWinnerResult;
import chess.repository.dao.MoveDao;
import chess.repository.dao.UserDao;
import chess.repository.dao.UserGameDao;
import chess.repository.entity.Entity;
import chess.repository.entity.GameEntity;
import chess.repository.entity.MoveDto;
import chess.repository.entity.MoveEntity;
import chess.repository.entity.MoveEntityBuilder;
import chess.repository.entity.UserEntity;
import chess.repository.entity.UserEntityBuilder;
import chess.repository.entity.UserGameEntity;
import chess.repository.entity.UserGameEntityBuilder;

public class ChessService {

	private final EntityCache entityCache;
	private final boolean isDbNotConnected;
	private final ChessGame game;

	public ChessService(final EntityCache entityCache) {
		this.entityCache = entityCache;
		this.isDbNotConnected = new JdbcConnector().isDBNotConnected();
		this.game = new ChessGame();
	}

	public boolean isGameStarted() {
		return !game.isUninitialized();
	}

	// TODO: List<String> 으로 넘어오지 않게 dto 필요
	public boolean checkLastGameExists(final List<String> names) {
		if (isDbNotConnected) {
			return false;
		}

		UserDao userDao = new UserDao();
		String userName1 = names.get(0);
		UserEntity user1 = userDao.findByName(userName1);
		if (user1.getName() == null) {
			return false;
		}
		cacheEntities(Map.of(userName1, user1));

		String userName2 = names.get(1);
		UserEntity user2 = userDao.findByName(userName2);
		if (user2.getName() == null) {
			return false;
		}
		cacheEntities(Map.of(userName2, user2));
		return checkLastGameExists(user1, user2);
	}

	private boolean checkLastGameExists(final UserEntity user1, final UserEntity user2) {
		UserGameDao userGameDao = new UserGameDao();
		List<UserGameEntity> userGameEntities = userGameDao.findByUserId1OrUserId2(user1.getId(), user2.getId());
		boolean lastGameExists = (userGameEntities.size() != 0);
		if (lastGameExists) {
			cacheEntities(Map.of(USER_GAME_KEY_NAME, userGameEntities.get(userGameEntities.size() - 1)));
		}
		return lastGameExists;
	}

	private void cacheEntities(final Map<String, Entity> entityMap) {
		entityMap.forEach(entityCache::put);
	}

	public void loadLastGame() {
		UserGameEntity userGame = (UserGameEntity)entityCache.get(USER_GAME_KEY_NAME);
		long gameId = userGame.getGameId();
		MoveDao moveDao = new MoveDao();
		List<MoveEntity> moveEntities = moveDao.findByGameId(gameId);
		List<MoveDto> moves = toMoves(moveEntities);
		game.movePiece(moves);
	}

	private List<MoveDto> toMoves(final List<MoveEntity> moveEntities) {
		return moveEntities.stream()
			.map(moveEntity -> new MoveDto(
				moveEntity.getSourceColumn(),
				moveEntity.getSourceRow(),
				moveEntity.getTargetColumn(),
				moveEntity.getTargetRow()))
			.collect(Collectors.toList());
	}

	public void startGame(final List<String> names) {
		startGame();
		if (isDbNotConnected) {
			return;
		}
		List<UserEntity> userEntities = addIfNotRegistered(names);
		GameEntity gameEntity = getNewGameEntity();
		saveUserGame(gameEntity.getId(), userEntities);
	}

	public void startGame() {
		game.initialize();
	}

	private List<UserEntity> addIfNotRegistered(final List<String> names) {
		return names.stream()
			.map(this::addIfNotRegistered)
			.collect(Collectors.toList());
	}

	private UserEntity addIfNotRegistered(final String name) {
		if (!entityCache.contains(name)) {
			cacheUserEntity(name);
		}
		return (UserEntity)entityCache.get(name);
	}

	private void cacheUserEntity(final String name) {
		UserDao userDao = new UserDao();
		UserEntity userEntity = userDao.findByName(name);
		if (userDao.findByName(name).getName() == null) {
			UserEntityBuilder userEntityBuilder = new UserEntityBuilder();
			userEntity = userEntityBuilder.name(name).build();
			userDao.save(userEntity);
		}
		cacheEntities(Map.of(name, userEntity));
	}

	private GameEntity getNewGameEntity() {
		GameDao gameDao = new GameDao();
		GameEntity gameEntity =  gameDao.save();
		cacheEntities(Map.of(GAME_KEY_NAME, gameEntity));
		return gameEntity;
	}

	private void saveUserGame(final long gameId, final List<UserEntity> userEntities) {
		List<Long> userIds = userEntities.stream()
			.map(UserEntity::getId)
			.collect(Collectors.toList());
		for (Long userId : userIds) {
			saveUserGame(gameId, userId);
		}
	}

	private void saveUserGame(final long gameId, final long userId) {
		UserGameDao userGameDao = new UserGameDao();
		UserGameEntityBuilder userGameEntityBuilder = new UserGameEntityBuilder();
		UserGameEntity userGameEntity = userGameEntityBuilder.userId(userId)
			.userId(userId)
			.gameId(gameId)
			.build();
		userGameDao.save(userGameEntity);
	}

	public void movePiece(final Position source, final Position target) {
		if (game.isGameRunning()) {
			saveIfDbConnected(source, target);
		}
		game.movePiece(source, target);
	}

	private void saveIfDbConnected(final Position source, final Position target) {
		if (isDbNotConnected) {
			return;
		}
		GameEntity gameEntity = (GameEntity)entityCache.get(GAME_KEY_NAME);
		MoveEntityBuilder moveEntityBuilder = new MoveEntityBuilder();
		MoveEntity moveEntity = moveEntityBuilder.gameId(gameEntity.getId())
			.sourceColumn(source.getColumn())
			.sourceRow(source.getRow())
			.targetColumn(target.getColumn())
			.targetRow(target.getRow())
			.build();
		MoveDao moveDao = new MoveDao();
		moveDao.save(moveEntity);
	}

	public ScoreAndWinnerResult getScoreAndWinnerResult() {
		return game.getScoreAndWinnerResult();
	}

	public Team getFinalWinner() {
		return game.getFinalWinner();
	}

	public boolean isGameDone() {
		if (game != null && game.isGameDone()) {
			deleteMovesIfDbConnected();
			return true;
		}
		return false;
	}

	public boolean isGameTerminated() {
		return game != null && game.isTerminated();
	}

	private void deleteMovesIfDbConnected() {
		if (isDbNotConnected) {
			return;
		}
		UserGameEntity userGameEntity = (UserGameEntity)entityCache.get(USER_GAME_KEY_NAME);
		UserGameDao userGameDao = new UserGameDao();
		GameDao gameDao = new GameDao();
		MoveDao moveDao = new MoveDao();
		userGameDao.deleteById(userGameEntity.getId());
		gameDao.deleteById(userGameEntity.getGameId());
		moveDao.deleteByGameId(userGameEntity.getGameId());
	}

	public void terminate() {
		game.terminate();
	}

	public BoardAndTurn getBoardAndTurn() {
		return new BoardAndTurn(game.getBoard(), game.getTurn());
	}
}

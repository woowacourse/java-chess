package chess.repository;

import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import chess.converter.StringToPositionConverter;
import chess.domain.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.state.GameState;
import chess.repository.converter.BoardToStringConverter;
import chess.repository.converter.StringToPieceConverter;
import chess.repository.converter.StringToStateConverter;
import chess.repository.dao.ChessGameDao;
import chess.repository.dao.TileDao;

public class ChessGameRepository implements GameRepository {

	private static final String GAME_ID = "game_id";
	private static final String STATE = "state";

	private final ChessGameDao chessGameDao = new ChessGameDao();
	private final TileDao tileDao = new TileDao();

	@Override
	public void save(ChessGame game) {
		GameState state = game.getState();
		tileDao.insertAll(
			BoardToStringConverter.from(state.getBoard()),
			chessGameDao.insert(game.getName(), state.toString())
		);
	}

	@Override
	public Optional<ChessGame> findByName(String name) {
		Map<String, String> idAndState = chessGameDao.selectByName(name);
		if (idAndState.isEmpty()) {
			return Optional.empty();
		}

		int gameId = Integer.parseInt(idAndState.get(GAME_ID));
		Map<String, String> tiles = tileDao.selectByGameId(gameId);
		GameState gameState = StringToStateConverter.of(idAndState.get(STATE), convertToBoard(tiles));

		return Optional.of(new ChessGame(name, gameState));
	}

	private Map<Position, Piece> convertToBoard(Map<String, String> tiles) {
		return tiles.entrySet().stream()
			.collect(toMap(
				entry -> StringToPositionConverter.from(entry.getKey()),
				entry -> StringToPieceConverter.from(entry.getValue())
			));
	}

	@Override
	public void update(String name, GameState state) {
		ChessGame updatedGame = new ChessGame(name, state);
		remove(name);
		save(updatedGame);
	}

	@Override
	public void remove(String name) {
		chessGameDao.delete(name);
	}

	@Override
	public List<String> findAllNames() {
		return chessGameDao.findAllNames();
	}
}

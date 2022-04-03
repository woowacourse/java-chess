package chess.repository;

import static java.util.stream.Collectors.*;

import java.util.Map;
import java.util.Optional;

import chess.converter.File;
import chess.converter.Rank;
import chess.repository.converter.StringToPieceConverter;
import chess.converter.StringToPositionConverter;
import chess.repository.converter.StringToStateConverter;
import chess.domain.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.state.GameState;
import chess.repository.dao.ChessGameDao;
import chess.repository.dao.TileDao;

public class ChessGameRepository implements GameRepository {

	private final ChessGameDao chessGameDao = new ChessGameDao();
	private final TileDao tileDao = new TileDao();

	@Override
	public void save(ChessGame game) {
		GameState state = game.getState();
		int gamePrimaryKey = chessGameDao.insert(game.getName(), state.toString());

		Map<String, String> tiles = convertToString(state.getBoard());
		tileDao.insertAll(tiles, gamePrimaryKey);
	}

	private Map<String, String> convertToString(Map<Position, Piece> board) {
		return board.entrySet().stream()
			.collect(toMap(
				entry -> convertPosition(entry.getKey()),
				entry -> entry.getValue().toString())
			);
	}

	private String convertPosition(Position position) {
		Rank rank = Rank.from(position.getRow());
		File file = File.from(position.getColumn());
		return file.getName() + rank.getName();
	}

	@Override
	public Optional<ChessGame> findByName(String name) {
		Map<String, String> idAndState = chessGameDao.selectByName(name);
		if (idAndState.isEmpty()) {
			return Optional.empty();
		}
		Map<String, String> tiles = tileDao.selectByGame(Integer.parseInt(idAndState.get("game_id")));

		Map<Position, Piece> board = convertToBoard(tiles);
		GameState gameState = StringToStateConverter.of(idAndState.get("state"), board);

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
}

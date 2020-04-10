package chess.dao;

import static org.assertj.core.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import chess.ChessGame;
import chess.domain.piece.pawn.Pawn;
import chess.domain.position.Position;

class ChessGameDaoTest {
	@Test
	void crud() throws SQLException {
		GamesDao gamesDao = new GamesDao();
		int gameId = gamesDao.createGame("kyle", "pobi");
		MoveDao moveDao = new MoveDao();
		ChessGame game = ChessGame.createGameByMoves(moveDao.findMovesByGameId(gameId));
		game.move(Position.of("a2"),Position.of("a4"));

		assertThat(game.board().getBoard().get(Position.of("a4"))).isInstanceOf(Pawn.class);
	}
}
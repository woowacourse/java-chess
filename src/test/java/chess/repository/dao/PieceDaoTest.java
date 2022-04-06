package chess.repository.dao;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.ChessGame;
import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import chess.domain.position.Position;
import chess.domain.state.Ready;
import chess.repository.PieceDto;

class PieceDaoTest {

	private static final String TEST_NAME = "test";

	private final PieceDao pieceDao = new PieceDao();
	private final ChessGameDao chessGameDao = new ChessGameDao();
	private final ChessGame game = new ChessGame(TEST_NAME, new Ready(new HashMap<>()));

	@AfterEach
	void clear() {
		chessGameDao.delete(TEST_NAME);
	}

	@Test
	@DisplayName("피스 insert 확인")
	void insert() {
		insertChessGame();
	}

	private int insertChessGame() {
		int foreignKey = chessGameDao.insert(game);
		List<PieceDto> pieceDtos = List.of(
			new PieceDto(Pawn.createWhite(), new Position(1, 1)),
			new PieceDto(King.createBlack(), new Position(2, 2))
		);
		pieceDao.insertAll(pieceDtos, foreignKey);
		return foreignKey;
	}

	@Test
	@DisplayName("외래키로 피스 조회")
	void selectWhereForeignKey() {
		int foreignKey = insertChessGame();

		List<PieceDto> pieceDtos = pieceDao.selectByGameId(foreignKey);

		assertThat(pieceDtos)
			.containsExactly(new PieceDto(Pawn.createWhite(), new Position(1, 1)),
				new PieceDto(King.createBlack(), new Position(2, 2)));
	}

	@Test
	@DisplayName("게임 이름으로 피스 조회")
	void selectByGameName() {
		insertChessGame();

		List<PieceDto> pieceDtos = pieceDao.selectByGameName(TEST_NAME);

		assertThat(pieceDtos)
			.containsExactly(new PieceDto(Pawn.createWhite(), new Position(1, 1)),
				new PieceDto(King.createBlack(), new Position(2, 2)));
	}

	@Test
	@DisplayName("위치와 게임 이름으로 피스 삭제")
	void deleteByPosition() {
		insertChessGame();

		pieceDao.deleteByPosition(new Position(1, 1), TEST_NAME);

		List<PieceDto> pieceDtos = pieceDao.selectByGameName(TEST_NAME);
		assertThat(pieceDtos)
			.containsExactly(new PieceDto(King.createBlack(), new Position(2, 2)));
	}

	@Test
	@DisplayName("피스 위치를 수정한다.")
	void update() {
		insertChessGame();

		pieceDao.updatePositionOfPiece(Pawn.createWhite(),
			new Position(1, 1), new Position(2, 1), TEST_NAME);

		List<PieceDto> pieceDtos = pieceDao.selectByGameName(TEST_NAME);
		assertThat(pieceDtos)
			.contains(new PieceDto(Pawn.createWhite(), new Position(2, 1)));
	}
}
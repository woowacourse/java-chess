package chess.dao;

import static chess.domain.position.Fixtures.*;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import chess.domain.board.BoardFactory;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;

public class BoardDAOTest {

	private BoardDAO boardDAO;

	@BeforeEach
	void setUp() {
		boardDAO = new BoardDAO("1");
	}

	@AfterEach
	void tearDown() {
		boardDAO.truncate();
	}

	@Test
	void create() {
		assertThat(new BoardDAO("1")).isInstanceOf(BoardDAO.class);
	}

	@Test
	void addPiece() {
		Piece piece = new King(A3, Team.WHITE);
		boardDAO.addPiece(piece);

		assertThat(boardDAO.findPieceBy(A3).getSymbol()).isEqualTo(piece.getSymbol());

	}

	@Test
	void findAll() {
		List<Piece> initial = new ArrayList<>(BoardFactory.create().getBoard().values());
		for (Piece piece : initial) {
			boardDAO.addPiece(piece);
		}

		assertThat(boardDAO.findAll().size()).isEqualTo(64);
	}
}

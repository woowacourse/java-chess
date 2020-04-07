package chess.domain.factory;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.Team;
import chess.domain.chesspiece.Blank;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspiece.King;
import chess.domain.position.Position;
import chess.factory.PieceConverter;

public class PieceConverterTest {

	@DisplayName("King인 경우")
	@Test
	void convertTest() {
		ChessPiece chessPiece = PieceConverter.convert('k', "h2");

		assertThat(chessPiece).isEqualTo(new King(Position.of("h2"), Team.WHITE));
	}

	@DisplayName("BLANK인 경우")
	@Test
	void convertTest2() {
		ChessPiece chessPiece = PieceConverter.convert('.', "h2");

		assertThat(chessPiece).isEqualTo(new Blank(Position.of("h2")));
	}
}

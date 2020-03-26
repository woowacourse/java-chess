package chess.domain.chessPiece.piece;

import chess.domain.chessPiece.position.Position;
import chess.domain.chessPiece.team.BlackTeam;
import chess.domain.chessPiece.team.WhiteTeam;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class PieceTest {
	@Test
	void teamTestBlack1() {
		Piece blackPawn = new Pawn(Position.of("a3"), new BlackTeam());
		Piece WhitePawn = new Pawn(Position.of("a4"), new WhiteTeam());
		assertThat(blackPawn.isSameTeam(WhitePawn)).isFalse();
	}

	@Test
	void teamTestBlack2() {
		Piece blackPawn = new Pawn(Position.of("a3"), new BlackTeam());
		Piece blackPawn2 = new Pawn(Position.of("a4"), new BlackTeam());
		assertThat(blackPawn.isSameTeam(blackPawn2)).isTrue();
	}

	@Test
	void teamTestWhite1() {
		Piece whitePawn = new Pawn(Position.of("a3"), new WhiteTeam());
		Piece blackPawn = new Pawn(Position.of("a4"), new BlackTeam());
		assertThat(whitePawn.isSameTeam(blackPawn)).isFalse();
	}

	@Test
	void teamTestWhite2() {
		Piece whitePawn1 = new Pawn(Position.of("a3"), new WhiteTeam());
		Piece whitePawn2 = new Pawn(Position.of("a4"), new WhiteTeam());
		assertThat(whitePawn1.isSameTeam(whitePawn2)).isTrue();
	}

}

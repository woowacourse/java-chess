package chess.domain;

import chess.domain.chessPiece.piece.Bishop;
import chess.domain.chessPiece.piece.Knight;
import chess.domain.chessPiece.piece.Pawn;
import chess.domain.chessPiece.piece.Piece;
import chess.domain.chessPiece.piece.Queen;
import chess.domain.chessPiece.piece.Rook;
import chess.domain.chessPiece.position.Position;
import chess.domain.chessPiece.team.BlackTeam;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ResultTest {

	@DisplayName("점수 계산 - 폰이 하나")
	@Test
	void calculateTeamScore() {
		List<Piece> team = new ArrayList<>();

		team.add(new Queen(Position.of("a1"), new BlackTeam()));
		team.add(new Rook(Position.of("a2"), new BlackTeam()));
		team.add(new Knight(Position.of("b4"), new BlackTeam()));
		team.add(new Knight(Position.of("b5"), new BlackTeam()));
		team.add(new Bishop(Position.of("c6"), new BlackTeam()));
		//22점

		team.add(new Pawn(Position.of("a3"), new BlackTeam()));

		assertThat(Result.calculateTeamScore(team)).isEqualTo(23);
	}

	@DisplayName("점수 계산 - 폰이 둘")
	@Test
	void calculateTeamScore2() {
		List<Piece> team = new ArrayList<>();

		team.add(new Queen(Position.of("a1"), new BlackTeam()));
		team.add(new Rook(Position.of("a2"), new BlackTeam()));
		team.add(new Knight(Position.of("b4"), new BlackTeam()));
		team.add(new Knight(Position.of("b5"), new BlackTeam()));
		team.add(new Bishop(Position.of("c6"), new BlackTeam()));
		//22점

		team.add(new Pawn(Position.of("d3"), new BlackTeam()));
		team.add(new Pawn(Position.of("d4"), new BlackTeam()));

		assertThat(Result.calculateTeamScore(team)).isEqualTo(23);
	}

	@DisplayName("점수 계산 - 폰이 셋")
	@Test
	void calculateTeamScore3() {
		List<Piece> team = new ArrayList<>();

		team.add(new Queen(Position.of("a1"), new BlackTeam()));
		team.add(new Rook(Position.of("a2"), new BlackTeam()));
		team.add(new Knight(Position.of("b4"), new BlackTeam()));
		team.add(new Knight(Position.of("b5"), new BlackTeam()));
		team.add(new Bishop(Position.of("c6"), new BlackTeam()));
		//22점

		team.add(new Pawn(Position.of("d3"), new BlackTeam()));
		team.add(new Pawn(Position.of("d4"), new BlackTeam()));
		team.add(new Pawn(Position.of("d5"), new BlackTeam()));

		assertThat(Result.calculateTeamScore(team)).isEqualTo(23.5);
	}

}

package chess.domain.chessboard;

import chess.domain.chessPiece.piece.Bishop;
import chess.domain.chessPiece.piece.Knight;
import chess.domain.chessPiece.piece.Pawn;
import chess.domain.chessPiece.piece.Piece;
import chess.domain.chessPiece.piece.Queen;
import chess.domain.chessPiece.piece.Rook;
import chess.domain.chessPiece.position.File;
import chess.domain.chessPiece.position.Position;
import chess.domain.chessPiece.position.Rank;
import chess.domain.chessPiece.team.BlackTeam;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ChessBoardTest {
	private ChessBoard chessBoard;
	Position pawnInitPosition;
	Position rookInitPosition;
	Position knightInitPosition;

	@BeforeEach
	void setUp() {
		chessBoard = new ChessBoard();
		pawnInitPosition = Position.of("a2");
		rookInitPosition = Position.of("a1");
		knightInitPosition = Position.of("b1");
	}

	@Test
	@DisplayName("포지션에 맞는 피스를 찾는 기능 테스트 : QueenBlack")
	void findPieceByPositionTestQueenBlack() {
		assertThat(chessBoard.findPieceByPosition(Position.of(File.D, Rank.ONE))).isInstanceOf(Queen.class);
	}

	@Test
	@DisplayName("포지션에 맞는 피스를 찾는 기능 테스트 : null")
	void findPieceByPositionTestNull() {
		assertThat(chessBoard.findPieceByPosition(Position.of(File.D, Rank.THREE))).isEqualTo(null);
	}

	@Test
	@DisplayName("포지션에 맞는 피스를 찾는 기능 테스트 : QueenWhite")
	void findPieceByPositionTestQueenWhite() {
		assertThat(chessBoard.findPieceByPosition(Position.of(File.D, Rank.EIGHT))).isInstanceOf(Queen.class);
	}

	@Test
	@DisplayName("같은팀이 있는 위치로 체스말 이동")
	void movePieceWithError() {
		assertThatThrownBy(() -> chessBoard.movePiece(Position.of("c1"), Position.of("b2")))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("해당 칸에 같은 팀의 말이 존재 합니다.");
	}

	@DisplayName("왕이 살아있는경우")
	@Test
	void isSurviveKings1() {
		assertThat(chessBoard.isSurviveKings()).isTrue();
	}

	@DisplayName("왕이 하나 없는 경우")
	@Test
	void isSurviveKings2() {
		Piece blackKing = chessBoard.findPieceByPosition(Position.of("e1"));
		chessBoard.removeAttackedPiece(blackKing);

		assertThat(chessBoard.isSurviveKings()).isFalse();
	}

	@DisplayName("Pawn 1칸 전진 테스트")
	@Test
	void movePiecePawn() {
		Piece pawn = chessBoard.findPieceByPosition(pawnInitPosition);
		Position targetPosition = Position.of("a3");
		chessBoard.movePiece(pawnInitPosition, targetPosition);

		assertThat(pawn.isEqualPosition(targetPosition)).isTrue();
	}

	@DisplayName("Pawn 2칸 전진 테스트")
	@Test
	void movePiecePawn2() {
		Piece pawn = chessBoard.findPieceByPosition(pawnInitPosition);
		Position targetPosition = Position.of("a4");
		chessBoard.movePiece(pawnInitPosition, targetPosition);

		assertThat(pawn.isEqualPosition(targetPosition)).isTrue();
	}

	@DisplayName("Pawn 3칸 전진 테스트")
	@Test
	void movePiecePawn3() {
		Position targetPosition = Position.of("a5");

		assertThatThrownBy(() -> chessBoard.movePiece(pawnInitPosition, targetPosition))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("해당 말이 갈 수 없는 칸입니다");
	}

	@DisplayName("rook 전진 테스트, 성공")
	@Test
	void movePieceRook() {
		Piece rook = chessBoard.findPieceByPosition(rookInitPosition);
		Position targetPosition = Position.of("a2");

		chessBoard.movePiece(pawnInitPosition, Position.of("a4"));
		chessBoard.movePiece(rookInitPosition, targetPosition);

		assertThat(rook.isEqualPosition(targetPosition)).isTrue();
	}

	@DisplayName("rook 전진 테스트, 실패, 장애물")
	@Test
	void movePieceRook2() {
		Position targetPosition = Position.of("a5");

		chessBoard.movePiece(pawnInitPosition, Position.of("a4"));

		assertThatThrownBy(() -> chessBoard.movePiece(rookInitPosition, targetPosition))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("경로에 다른 말이 존재합니다.");
	}

	@DisplayName("knight 전진 테스트, 성공")
	@Test
	void movePieceKnight() {
		Piece knight = chessBoard.findPieceByPosition(knightInitPosition);
		Position targetPosition = Position.of("a3");

		chessBoard.movePiece(knightInitPosition, targetPosition);

		assertThat(knight.isEqualPosition(targetPosition)).isTrue();
	}

	@DisplayName("knight 전진 테스트, 실패")
	@Test
	void movePieceKnight2() {
		Position targetPosition = Position.of("b3");

		assertThatThrownBy(() -> chessBoard.movePiece(knightInitPosition, targetPosition))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("해당 말이 갈 수 없는 칸입니다");
	}


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

		assertThat(chessBoard.calculateTeamScore(team)).isEqualTo(23);
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

		assertThat(chessBoard.calculateTeamScore(team)).isEqualTo(23);
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

		assertThat(chessBoard.calculateTeamScore(team)).isEqualTo(23.5);
	}
}

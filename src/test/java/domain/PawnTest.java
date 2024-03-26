package domain;

import static domain.PieceMoveResult.*;
import static domain.Position.*;
import static domain.Team.*;

import domain.piece.Pawn;
import java.util.List;
import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PawnTest {

	public static Stream<Arguments> moveSuccessWhenTargetIsOtherTeamParameters() {
		return Stream.of(
			Arguments.of(WHITE, C5, BLACK),
			Arguments.of(WHITE, E5, BLACK),
			Arguments.of(BLACK, C3, WHITE),
			Arguments.of(BLACK, E3, WHITE)
		);
	}

	public static Stream<Arguments> moveFailureWhenTargetIsSameTeamParameters() {
		return Stream.of(
			Arguments.of(WHITE, C5),
			Arguments.of(WHITE, E5),
			Arguments.of(BLACK, C3),
			Arguments.of(BLACK, E3)
		);
	}

	@Test
	@DisplayName("한칸 전진 할 수 있는지 검증")
	void moveOneSuccess() {
		Pawn pawnWhite = new Pawn(A2, WHITE);
		Pawn pawnBlack = new Pawn(A7, BLACK);
		PiecesOnChessBoard piecesOnChessBoard = new PiecesOnChessBoard(List.of());
		PieceMoveResult actualWhite = pawnWhite.move(A3, piecesOnChessBoard);
		PieceMoveResult actualBlack = pawnBlack.move(A6, piecesOnChessBoard);
		org.junit.jupiter.api.Assertions.assertAll(
			() -> Assertions.assertThat(actualWhite).isEqualTo(SUCCESS),
			() -> Assertions.assertThat(actualBlack).isEqualTo(SUCCESS)
		);
	}

	@Test
	@DisplayName("처음에 두칸 전진 할 수 있는지 검증")
	void moveTwoSuccess() {
		Pawn pawnWhite = new Pawn(A2, WHITE);
		Pawn pawnBlack = new Pawn(A7, BLACK);
		PiecesOnChessBoard piecesOnChessBoard = new PiecesOnChessBoard(List.of());
		PieceMoveResult actualWhite = pawnWhite.move(A4, piecesOnChessBoard);
		PieceMoveResult actualBlack = pawnBlack.move(A5, piecesOnChessBoard);
		org.junit.jupiter.api.Assertions.assertAll(
			() -> Assertions.assertThat(actualWhite).isEqualTo(SUCCESS),
			() -> Assertions.assertThat(actualBlack).isEqualTo(SUCCESS)
		);
	}

	@Test
	@DisplayName("처음이 아닐 땐 두칸 전진 할 수 없는지 검증")
	void moveTwoFail() {
		Pawn pawnWhite = new Pawn(A2, WHITE);
		Pawn pawnBlack = new Pawn(A7, BLACK);
		PiecesOnChessBoard piecesOnChessBoard = new PiecesOnChessBoard(List.of());
		pawnWhite.move(A3, piecesOnChessBoard);
		pawnBlack.move(A6, piecesOnChessBoard);
		PieceMoveResult actualWhite = pawnWhite.move(A5, piecesOnChessBoard);
		PieceMoveResult actualBlack = pawnBlack.move(A4, piecesOnChessBoard);
		org.junit.jupiter.api.Assertions.assertAll(
			() -> Assertions.assertThat(actualWhite).isEqualTo(FAILURE),
			() -> Assertions.assertThat(actualBlack).isEqualTo(FAILURE)
		);
	}

	@Test
	@DisplayName("폰의 목적지에 다른 말이 있는 경우 이동이 불가능한지 검증")
	void moveFailureCauseTargetIsNotEmpty() {
		Pawn pawn = new Pawn(D4, WHITE);
		PiecesOnChessBoard piecesOnChessBoard = new PiecesOnChessBoard(List.of(new Pawn(D5, WHITE)));
		Assertions.assertThat(pawn.move(D5, piecesOnChessBoard))
			.isEqualTo(FAILURE);
	}

	@ParameterizedTest
	@MethodSource("moveSuccessWhenTargetIsOtherTeamParameters")
	@DisplayName("폰의 대각선 위치에 다른 팀 말이 있는 경우 이동이 가능한지 검증")
	void moveSuccessWhenTargetIsOtherTeam(Team team, Position targetPosition, Team otherTeam) {
		Pawn pawn = new Pawn(D4, team);
		PiecesOnChessBoard piecesOnChessBoard = new PiecesOnChessBoard(List.of(new Pawn(targetPosition, otherTeam)));
		Assertions.assertThat(pawn.move(targetPosition, piecesOnChessBoard))
			.isEqualTo(CATCH);
	}

	@ParameterizedTest
	@MethodSource("moveFailureWhenTargetIsSameTeamParameters")
	@DisplayName("폰의 대각선 위치에 같은 팀 말이 있는 경우 이동이 불가능한지 검증")
	void moveFailureWhenTargetIsSameTeam(Team team, Position targetPosition) {
		Pawn pawn = new Pawn(D4, team);
		PiecesOnChessBoard piecesOnChessBoard = new PiecesOnChessBoard(List.of(new Pawn(targetPosition, team)));
		Assertions.assertThat(pawn.move(targetPosition, piecesOnChessBoard))
			.isEqualTo(FAILURE);
	}

	@ParameterizedTest
	@MethodSource("moveFailureWhenTargetIsSameTeamParameters")
	@DisplayName("폰의 대각선 위치에 말이 없는 경우 이동이 불가능한지 검증")
	void moveFailureWhenTargetIsEmpty(Team team, Position targetPosition) {
		Pawn pawn = new Pawn(D4, team);
		PiecesOnChessBoard piecesOnChessBoard = new PiecesOnChessBoard(List.of());
		Assertions.assertThat(pawn.move(targetPosition, piecesOnChessBoard))
			.isEqualTo(FAILURE);
	}
}

package domain;

import static domain.PieceMoveResult.*;
import static domain.Position.*;
import static domain.Team.*;

import domain.piece.King;
import domain.piece.Pawn;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class KingTest {
	public static Stream<Arguments> moveFailureParameters() {
		Set<Position> successParameters = moveSuccessParameters().map(Arguments::get)
			.map(objects -> (Position)objects[0])
			.collect(Collectors.toSet());
		return Arrays.stream(Position.values())
			.filter(position -> !successParameters.contains(position))
			.map(Arguments::of);
	}

	public static Stream<Arguments> moveSuccessParameters() {
		return Stream.of(
			Arguments.of(C5), Arguments.of(D5), Arguments.of(E5),
			Arguments.of(C4), Arguments.of(E4),
			Arguments.of(C3), Arguments.of(D3), Arguments.of(E3)
		);
	}

	@ParameterizedTest
	@MethodSource("moveSuccessParameters")
	@DisplayName("킹의 이동 규칙대로 이동이 가능한지 검증")
	void moveSuccess(Position targetPosition) {
		King king = new King(D4, WHITE);
		PiecesOnChessBoard piecesOnChessBoard = new PiecesOnChessBoard(List.of());
		Assertions.assertThat(king.move(targetPosition, piecesOnChessBoard))
			.isEqualTo(SUCCESS);
	}

	@ParameterizedTest
	@MethodSource("moveFailureParameters")
	@DisplayName("킹의 이동 규칙을 위반한 이동이 불가능한지 검증")
	void moveFailure(Position targetPosition) {
		King king = new King(D4, WHITE);
		PiecesOnChessBoard piecesOnChessBoard = new PiecesOnChessBoard(List.of());
		Assertions.assertThat(king.move(targetPosition, piecesOnChessBoard))
			.isEqualTo(FAILURE);
	}

	@ParameterizedTest
	@MethodSource("moveSuccessParameters")
	@DisplayName("킹의 목적지에 같은 팀 말이 있는 경우 이동이 불가능한지 검증")
	void moveFailureCauseTargetIsSameTeam(Position targetPosition) {
		King king = new King(D4, WHITE);
		PiecesOnChessBoard piecesOnChessBoard = new PiecesOnChessBoard(List.of(new Pawn(targetPosition, WHITE)));
		Assertions.assertThat(king.move(targetPosition, piecesOnChessBoard))
			.isEqualTo(FAILURE);
	}

	@ParameterizedTest
	@MethodSource("moveSuccessParameters")
	@DisplayName("킹의 목적지에 다른 팀 말이 있는 경우 이동이 가능한지 검증")
	void moveSuccessWhenTargetIsOtherTeam(Position targetPosition) {
		King king = new King(D4, WHITE);
		PiecesOnChessBoard piecesOnChessBoard = new PiecesOnChessBoard(List.of(new Pawn(targetPosition, BLACK)));
		Assertions.assertThat(king.move(targetPosition, piecesOnChessBoard))
			.isEqualTo(CATCH);
	}
}

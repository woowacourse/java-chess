package chess.domain.chessPiece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.NullSource;

import chess.domain.RuleStrategy.KingRuleStrategy;
import chess.domain.RuleStrategy.KnightRuleStrategy;
import chess.domain.RuleStrategy.nonLeapableStrategy.BishopRuleStrategy;
import chess.domain.RuleStrategy.nonLeapableStrategy.RookRuleStrategy;
import chess.domain.RuleStrategy.nonLeapableStrategy.pawnRuleStrategy.PawnRuleStrategy;
import chess.domain.chessPiece.pieceState.InitialState;
import chess.domain.chessPiece.pieceState.PieceState;
import chess.domain.chessPiece.pieceType.Bishop;
import chess.domain.chessPiece.pieceType.King;
import chess.domain.chessPiece.pieceType.Knight;
import chess.domain.chessPiece.pieceType.Pawn;
import chess.domain.chessPiece.pieceType.PieceColor;
import chess.domain.chessPiece.pieceType.Rook;
import chess.domain.position.Position;

class ChessPieceTest {

	@ParameterizedTest
	@EnumSource(PieceColor.class)
	void PieceType_PieceColorAndMovableStrategy_GenerateInstance(PieceColor pieceColor) {
		PieceState chessPieceState = new InitialState(new KingRuleStrategy());
		ChessPiece chessPiece = new King(pieceColor, chessPieceState);

		assertThat(chessPiece).isInstanceOf(ChessPiece.class);
	}

	@ParameterizedTest
	@NullSource
	void PieceType_NullPieceColor_ExceptionThrown(PieceColor pieceColor) {
		PieceState chessPieceState = new InitialState(new KingRuleStrategy());

		assertThatThrownBy(() -> new King(pieceColor, chessPieceState))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("피스 색상이 null입니다.");
	}

	@ParameterizedTest
	@NullSource
	void PieceType_NullMovableStrategy_ExceptionThrown(PieceState pieceState) {
		assertThatThrownBy(() -> new King(PieceColor.BLACK, pieceState))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("피스 전략이 null입니다.");
	}

	@ParameterizedTest
	@EnumSource(PieceColor.class)
	void canLeap_CanLeapPieceState_ReturnTrue(PieceColor pieceColor) {
		PieceState canLeapState = new InitialState(new KnightRuleStrategy());
		ChessPiece canLeapChessPiece = new Knight(pieceColor, canLeapState);

		assertThat(canLeapChessPiece.canLeap()).isTrue();
	}

	@ParameterizedTest
	@EnumSource(PieceColor.class)
	void canLeap_CanNotLeapPieceState_ReturnFalse(PieceColor pieceColor) {
		PieceState canNotLeapState = new InitialState(new RookRuleStrategy());
		ChessPiece canNotLeapChessPiece = new Rook(pieceColor, canNotLeapState);

		assertThat(canNotLeapChessPiece.canLeap()).isFalse();
	}

	@ParameterizedTest
	@CsvSource(value = {"BLACK,b6,b5", "WHITE,b2,b4"})
	void canMove_CanMoveSourcePositionAndTargetPosition_ReturnTrue(PieceColor pieceColor, Position sourcePosition,
		Position targetPosition) {
		PieceState canMoveState = new InitialState(
			pieceColor.getPawnRuleStrategyBy(PawnRuleStrategy.INITIAL_STATE_MOVABLE_RANGE));
		ChessPiece canMoveChessPiece = new Pawn(pieceColor, canMoveState);

		assertThat(canMoveChessPiece.canMove(sourcePosition, targetPosition)).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"BLACK,b6,b7", "WHITE,b3,b1"})
	void canMove_CanNotMoveSourcePositionAndTargetPosition_ReturnFalse(PieceColor pieceColor, Position sourcePosition,
		Position targetPosition) {
		PieceState canNotMoveState = new InitialState(
			pieceColor.getPawnRuleStrategyBy(PawnRuleStrategy.INITIAL_STATE_MOVABLE_RANGE));
		ChessPiece canNotMoveChessPiece = new Pawn(pieceColor, canNotMoveState);

		assertThat(canNotMoveChessPiece.canMove(sourcePosition, targetPosition)).isFalse();
	}

	@ParameterizedTest
	@NullSource
	void validate_NullSourcePosition_ExceptionThrown(Position sourcePosition) {
		PieceState canMoveState = new InitialState(new RookRuleStrategy());
		ChessPiece canMoveChessPiece = new Rook(PieceColor.BLACK, canMoveState);
		Position targetPosition = Position.of("b4");

		assertThatThrownBy(() -> canMoveChessPiece.canMove(sourcePosition, targetPosition))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("소스 위치가 null입니다.");
	}

	@ParameterizedTest
	@NullSource
	void validate_NullTargetPosition_ExceptionThrown(Position targetPosition) {
		PieceState canMoveState = new InitialState(new RookRuleStrategy());
		ChessPiece canMoveChessPiece = new Rook(PieceColor.BLACK, canMoveState);
		Position sourcePosition = Position.of("b4");

		assertThatThrownBy(() -> canMoveChessPiece.canMove(sourcePosition, targetPosition))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("타겟 위치가 null입니다.");
	}

	@ParameterizedTest
	@CsvSource(value = {"BLACK,b6,a5", "WHITE,b2,a3"})
	void canCatch_CanCatchSourcePositionAndTargetPosition_ReturnTrue(PieceColor pieceColor, Position sourcePosition,
		Position targetPosition) {
		PieceState canMoveState = new InitialState(
			pieceColor.getPawnRuleStrategyBy(PawnRuleStrategy.INITIAL_STATE_MOVABLE_RANGE));
		ChessPiece canMoveChessPiece = new Pawn(pieceColor, canMoveState);

		assertThat(canMoveChessPiece.canCatch(sourcePosition, targetPosition)).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"BLACK,b6,b5", "WHITE,b2,b4"})
	void canCatch_CanNotCatchSourcePositionAndTargetPosition_ReturnFalse(PieceColor pieceColor, Position sourcePosition,
		Position targetPosition) {
		PieceState canNotMoveState = new InitialState(
			pieceColor.getPawnRuleStrategyBy(PawnRuleStrategy.INITIAL_STATE_MOVABLE_RANGE));
		ChessPiece canNotMoveChessPiece = new Pawn(pieceColor, canNotMoveState);

		assertThat(canNotMoveChessPiece.canCatch(sourcePosition, targetPosition)).isFalse();
	}

	@ParameterizedTest
	@CsvSource(value = {"BLACK,WHITE,false", "BLACK,BLACK,true"})
	void isSamePieceColorWith_ChessPiece_ReturnSameColorCompareResult(PieceColor sourcePieceColor,
		PieceColor targetPieceColor, boolean expected) {
		ChessPiece sourceChessPiece = new Bishop(sourcePieceColor, new InitialState(new BishopRuleStrategy()));
		ChessPiece targetChessPiece = new Bishop(targetPieceColor, new InitialState(new BishopRuleStrategy()));

		assertThat(sourceChessPiece.isSamePieceColorWith(targetChessPiece)).isEqualTo(expected);
	}

	@ParameterizedTest
	@NullSource
	void isSamePieceColorWith_NullChessPiece_ExceptionThrown(ChessPiece chessPiece) {
		ChessPiece sourceChessPiece = new Bishop(PieceColor.BLACK, new InitialState(new BishopRuleStrategy()));

		assertThatThrownBy(() -> sourceChessPiece.isSamePieceColorWith(chessPiece))
			.isInstanceOf(NullPointerException.class)
			.hasMessageEndingWith("체스 피스가 null입니다.");
	}

	@ParameterizedTest
	@CsvSource(value = {"BLACK,true", "WHITE,false"})
	void isSame_PieceColor_ReturnSameColorCompareResult(PieceColor pieceColor, boolean expected) {
		ChessPiece chessPiece = new Bishop(PieceColor.BLACK, new InitialState(new BishopRuleStrategy()));

		assertThat(chessPiece.isSame(pieceColor)).isEqualTo(expected);
	}

	@ParameterizedTest
	@NullSource
	void isSame_NullPieceColor_ExceptionThrown(PieceColor pieceColor) {
		ChessPiece chessPiece = new Bishop(PieceColor.BLACK, new InitialState(new BishopRuleStrategy()));

		assertThatThrownBy(() -> chessPiece.isSame(pieceColor))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("체스 색상이 null입니다.");
	}

}
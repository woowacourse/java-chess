package chess.domain;

import java.util.Objects;

import chess.domain.chesspiece.ChessPiece;

public class Turn {
	private static final String NOT_THIS_TEAM_TURN_MESSAGE = "해당 팀 차례가 아닙니다.";

	private boolean isWhiteTurn;

	public Turn(boolean isWhiteTurn) {
		this.isWhiteTurn = isWhiteTurn;
	}

	public void validateTurn(ChessPiece chessPiece) {
		if (checkWhiteTeamTurn(chessPiece) || checkBlackTeamTurn(chessPiece)) {
			throw new UnsupportedOperationException(NOT_THIS_TEAM_TURN_MESSAGE);
		}
	}

	private boolean checkWhiteTeamTurn(ChessPiece chessPiece) {
		return isWhiteTurn && chessPiece.isNotMatchTeam(Team.WHITE);
	}

	private boolean checkBlackTeamTurn(ChessPiece chessPiece) {
		return isWhiteTurn == false && chessPiece.isNotMatchTeam(Team.BLACK);
	}

	public void changeTurn() {
		this.isWhiteTurn = !this.isWhiteTurn;
	}

	public boolean isWhiteTurn() {
		return isWhiteTurn;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Turn turn = (Turn)o;
		return isWhiteTurn == turn.isWhiteTurn;
	}

	@Override
	public int hashCode() {
		return Objects.hash(isWhiteTurn);
	}
}

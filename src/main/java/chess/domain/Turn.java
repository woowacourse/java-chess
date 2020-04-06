package chess.domain;

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
}

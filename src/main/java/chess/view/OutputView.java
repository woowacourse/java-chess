package chess.view;

import chess.domain.Board;
import chess.domain.Pieces;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;

public class OutputView {
	public static void printGameStartInstruction() {
		System.out.println("체스 게임을 시작합니다.");
	}

	public static void printChessBoard(Board board) {
		Pieces pieces = board.getPieces();
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 8; i > 0; i--) {
			for (int j = 1; j <= 8; j++) {
				Piece piece = pieces.findByPosition(new Position(j, i));
				if (piece == null)
					stringBuilder.append(".");
				else
					stringBuilder.append(piece.toString());

			}
			stringBuilder.append(" ").append(i).append("\n");
		}
		stringBuilder.append("abcdefgh");
		System.out.println(stringBuilder.toString());
	}

	public static void printScore(Board board) {
		for (Team team : Team.values()) {
			System.out.println(team.getName() + " : " + board.calculateScoreByTeam(team));
		}
		System.out.println();
	}

	public static void printTeamWithHigherScore(Board board) {
		double team1Score = board.calculateScoreByTeam(Team.BLACK);
		double team2Score = board.calculateScoreByTeam(Team.WHITE);
		if (team1Score == team2Score) {
			System.out.println("무승부입니다.");
		}
		if (team1Score > team2Score) {
			System.out.println(Team.BLACK.getName() + "이 승리했습니다.");
		}
		if (team1Score < team2Score) {
			System.out.println(Team.WHITE.getName() + "이 승리했습니다.");
		}
		System.out.println();
	}

	public static void printWinner(Team team) {
		System.out.println(team.getName() + "이 승리했습니다.");
	}

	public static void printErrorMessage(String message) {
		System.out.println(message);
	}
}

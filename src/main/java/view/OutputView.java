package view;

import java.util.List;

import domain.board.Board;
import domain.board.Rank;
import domain.piece.Piece;
import domain.piece.team.Team;

public class OutputView {
	public static void printChessGameStart() {
		System.out.println("체스 게임을 시작합니다.");
		System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
	}

	public static void printChessBoard(Board board) {
		List<Rank> ranks = board.getRanks();
		for (int i = ranks.size() - 1; i >= 0; i--) {
			if (ranks.isEmpty()) {
				printEmptyRank();
				continue;
			}
			List<Piece> pieces = ranks.get(i).getPieces();
			printRank(pieces);
		}
	}

	private static void printEmptyRank() {
		for (int i = 0; i < 8; i++) {
			System.out.println(".");
		}
		System.out.println();
	}

	private static void printRank(List<Piece> pieces) {
		for (int i = 1; i <= 8; i++) {
			final int columnIndex = i;
			if (pieces.stream().anyMatch(p -> columnIndex == p.getPosition().getColumn().getNumber())) {
				pieces.stream()
					.filter(piece -> columnIndex == piece.getPosition().getColumn().getNumber())
					.map(Piece::showSymbol)
					.forEach(System.out::print);
				continue;
			}
			System.out.print(".");
		}
		System.out.println();
	}

	public static void printErrorMessage(IllegalArgumentException e) {
		System.out.println(e.getMessage());
	}

	public static void printTeamScore(double whiteTeamScore, double blackTeamScore) {
		System.out.println("White팀 점수는 " + whiteTeamScore + "점 입니다.");
		System.out.println("Black팀 점수는 " + blackTeamScore + "점 입니다.");
	}

	public static void printTurn(Team turn) {
		if (turn == Team.WHITE) {
			System.out.println("White팀 차례입니다.");
		}
		if (turn == Team.BLACK) {
			System.out.println("Black팀 차례입니다.");
		}
	}

	public static void printWinner(Team winner) {
		System.out.println(winner.getName() + "팀이 상대팀의 King을 잡았습니다!");
		System.out.println("승리한 팀은 " + winner.getName() + "팀 입니다.");
	}
}

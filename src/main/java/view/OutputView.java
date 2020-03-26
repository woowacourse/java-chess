package view;

import java.util.List;

import domain.board.Board;
import domain.board.Rank;
import domain.piece.Piece;

public class OutputView {
	public static void printChessGameStart() {
		System.out.println("체스 게임을 시작합니다.");
		System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
	}

	public static void printChessBoard(Board board) {
		List<Rank> ranks = board.getRanks();
		for (int i = ranks.size() - 1; i >= 0; i--) {
			List<Piece> pieces = ranks.get(i).getPieces();
			printRank(pieces);
		}
	}

	private static void printRank(List<Piece> pieces) {
		for (int i = 1; i <= 8; i++) {
			final int temp = i;
			if (pieces.stream().anyMatch(p -> temp == p.getPosition().getColumn().getNumber())) {
				pieces.stream()
					.filter(piece -> temp == piece.getPosition().getColumn().getNumber())
					.map(Piece::showSymbol)
					.forEach(System.out::print);
				continue;
			}
			System.out.print(".");
		}
		System.out.println();
	}
}

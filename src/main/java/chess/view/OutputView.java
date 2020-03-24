package chess.view;

import java.util.stream.Collectors;

import chess.domain.Board;
import chess.domain.Piece;
import chess.domain.Rank;

public class OutputView {
	public static void printGameStartInstruction() {
		System.out.println("체스 게임을 시작합니다.");
	}

	public static void printChessBoard(Board board) {
		for (Rank rank : board.getRanks()) {
			System.out.println(rank.getPieces().stream()
				.map(p -> p.map(Piece::toString).orElse("."))
				.collect(Collectors.joining("")));
		}
	}

}

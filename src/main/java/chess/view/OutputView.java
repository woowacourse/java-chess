package chess.view;

import java.util.Map;
import java.util.Objects;

import chess.domain.Color;
import chess.domain.PieceScore;
import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import chess.domain.piece.Piece;

/**
 *
 *    @author AnHyungJu, LeeHoBin
 */
public class OutputView {
	public static void printGameInstruction() {
		System.out.println("체스 게임을 시작합니다.");
		System.out.println("게임 시작: start");
		System.out.println("게임 종료: end");
		System.out.println("게임 이동: move source위치 target위치 - 예. move b2 b3");
	}

	public static void printException(Exception e) {
		System.out.println(e.getMessage());
	}

	public static void printGameEnd() {
		System.out.println("게임을 종료합니다.");
	}

	public static void printChessBoard(Map<Position, Piece> pieces) {
		for (Rank rank : Rank.values()) {
			for (File file : File.values()) {
				Position position = Position.of(file, rank);

				if (Objects.isNull(pieces.get(position))) {
					System.out.print(".");
					continue;
				}

				System.out.print(pieces.get(position).getSymbol());
			}
			System.out.println();
		}
	}

	public static void printStatus(Map<Position, Piece> pieces) {
		System.out.println("하얀색 진영의 점수 : " + PieceScore.calculateByColor(pieces, Color.WHITE));
		System.out.println("검정색 진영의 점수 : " + PieceScore.calculateByColor(pieces, Color.BLACK));
	}
}

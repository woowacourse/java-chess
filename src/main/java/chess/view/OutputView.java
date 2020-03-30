package chess.view;

import chess.domain.chessPiece.piece.Piece;
import chess.domain.chessPiece.position.Position;
import chess.domain.chessboard.ChessBoard;

import java.util.Optional;

public class OutputView {
	private static final String EMPTY_MARK = ".";
	private static final String MESSAGE_END_GAME = "\n게임을 종료합니다.";
	private static final String BLACK = "black";
	private static final String WHITE = "white";
	private static final String STRING_FORMAT_PRINT_SCORE = "\n블랙팀 점수 : %.1f, 화이트팀 점수 : %.1f";
	private static final String STRING_FORMAT_PRINT_WINNER = "%s 팀이 이겼습니다";

	public static void printChessBoard(ChessBoard chessBoard) {
		for (Position position : chessBoard.getChessBoard()) {
			separateLine(position);
			printPiece(chessBoard.findPieceByPosition(position));
		}
	}

	private static void printPiece(Optional<Piece> piece) {
		System.out.print(piece.map(Piece::getPieceName).orElseGet(() -> EMPTY_MARK));
	}

	private static void separateLine(Position position) {
		if (position.isFileA()) {
			System.out.println();
		}
	}

	public static void printGameResult(double blackTeamScore, double whiteTeamScore) {
		System.out.println(String.format(STRING_FORMAT_PRINT_SCORE, blackTeamScore, whiteTeamScore));
		if (blackTeamScore > whiteTeamScore) {
			System.out.println(String.format(STRING_FORMAT_PRINT_WINNER, BLACK));
			return;
		}
		System.out.println(String.format(STRING_FORMAT_PRINT_WINNER, WHITE));
	}

	public static void printGameEndMessage() {
		System.out.println(MESSAGE_END_GAME);
	}
}

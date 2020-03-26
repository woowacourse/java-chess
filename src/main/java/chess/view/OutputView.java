package chess.view;

import chess.domain.chessPiece.piece.Piece;
import chess.domain.chessPiece.position.Position;
import chess.domain.chessboard.ChessBoard;

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
			Piece piece = chessBoard.findPieceByPosition(position);
			printPiece(piece);
		}
	}

	private static void printPiece(Piece piece) {
		if (piece == null) {
			System.out.print(EMPTY_MARK);
			return;
		}
		System.out.print(piece.pieceName());
	}

	private static void separateLine(Position position) {
		if (position.isNewLine()) {
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

package chess.util;

import static java.util.stream.Collectors.*;

import java.util.Collections;
import java.util.List;

import chess.domain.chessBoard.ChessBoard;
import chess.domain.position.ChessFile;
import chess.domain.position.ChessRank;

public class ChessBoardRenderer {

	private static final String EMPTY_POSITION = ".";
	private static final String EMPTY_SPACE = "";

	public static List<String> render(ChessBoard chessBoard) {
		List<String> renderedChessBoard = renderEachChessFileFrom(chessBoard);
		Collections.reverse(renderedChessBoard);
		renderedChessBoard.add(EMPTY_SPACE);
		renderedChessBoard.add(ChessFile.values().stream()
			.map(ChessFile::toString)
			.collect(joining()));

		return renderedChessBoard;
	}

	private static List<String> renderEachChessFileFrom(ChessBoard chessBoard) {
		return ChessRank.values().stream()
			.map(chessRank -> renderChessRankFrom(chessBoard, chessRank))
			.collect(toList());
	}

	private static String renderChessRankFrom(ChessBoard chessBoard, ChessRank chessRank) {
		return ChessFile.values().stream()
			.map(chessFile -> renderChessPieceFrom(chessBoard, chessRank, chessFile))
			.collect(collectingAndThen(joining(), renderedRank -> String.format("%s	%s", renderedRank, chessRank)));
	}

	private static String renderChessPieceFrom(ChessBoard chessBoard, ChessRank chessRank, ChessFile chessFile) {
		if (chessBoard.contains(chessFile, chessRank)) {
			return chessBoard.getChessPieceNameAt(chessFile, chessRank);
		}
		return EMPTY_POSITION;
	}

}

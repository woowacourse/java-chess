package chess.util;

import static java.util.stream.Collectors.*;

import java.util.Collections;
import java.util.List;

import chess.domain.chessBoard.ChessBoard;
import chess.domain.position.ChessFile;
import chess.domain.position.ChessRank;

public class ChessBoardRenderer {

	private static final String EMPTY_POSITION = ".";

	public static List<String> render(ChessBoard chessBoard) {
		List<String> renderedChessBoard = renderEachChessFileFrom(chessBoard);
		Collections.reverse(renderedChessBoard);

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
			.collect(joining());
	}

	private static String renderChessPieceFrom(ChessBoard chessBoard, ChessRank chessRank, ChessFile chessFile) {
		if (chessBoard.contains(chessFile, chessRank)) {
			return chessBoard.getChessPieceNameAt(chessFile, chessRank);
		}
		return EMPTY_POSITION;
	}

}

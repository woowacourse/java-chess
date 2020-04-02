package chess.util;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import chess.domain.chessBoard.ChessBoard;
import chess.domain.position.ChessFile;
import chess.domain.position.ChessRank;
import chess.domain.position.Position;

public class ChessBoardRenderer {

	private static final String EMPTY_SPACE = "";
	private static final String DELIMITER = " ";
	private static final String RANK_APPEND_FORMAT = "%s	%s";
	private static final String EMPTY_POSITION = ".";

	public static List<String> render(ChessBoard chessBoard) {
		Objects.requireNonNull(chessBoard, "체스 보드가 null입니다.");
		List<String> renderedChessBoard = renderEachChessRankFrom(chessBoard);
		Collections.reverse(renderedChessBoard);
		appendChessFileName(renderedChessBoard);
		return renderedChessBoard;
	}

	private static List<String> renderEachChessRankFrom(ChessBoard chessBoard) {
		return Arrays.stream(ChessRank.values())
			.map(chessRank -> renderChessRankFrom(chessBoard, chessRank))
			.collect(toList());
	}

	private static String renderChessRankFrom(ChessBoard chessBoard, ChessRank chessRank) {
		return Arrays.stream(ChessFile.values())
			.map(chessFile -> renderChessPieceFrom(chessBoard, chessRank, chessFile))
			.collect(collectingAndThen(joining(DELIMITER),
				renderedRank -> String.format(RANK_APPEND_FORMAT, renderedRank, chessRank)));
	}

	private static String renderChessPieceFrom(ChessBoard chessBoard, ChessRank chessRank, ChessFile chessFile) {
		Position renderingPosition = Position.of(chessFile, chessRank);

		if (chessBoard.isChessPieceOn(renderingPosition)) {
			return chessBoard.getChessPieceNameOn(renderingPosition);
		}
		return EMPTY_POSITION;
	}

	private static void appendChessFileName(List<String> renderedChessBoard) {
		renderedChessBoard.add(EMPTY_SPACE);
		renderedChessBoard.add(Arrays.stream(ChessFile.values())
			.map(ChessFile::toString)
			.collect(joining(DELIMITER)));
	}

}

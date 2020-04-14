package chess.controller;

import chess.domain.Result;
import chess.domain.chessboard.ChessBoard;
import chess.domain.position.Position;
import chess.service.ChessService;
import spark.Request;

public class ChessController {
	private final ChessService chessService;

	public ChessController() {
		chessService = new ChessService();
	}

	public ChessBoard find() {
		return chessService.find();
	}

	public ChessBoard move(Request req) {
		Position startPosition = Position.of(req.queryParams("startPosition"));
		Position targetPosition = Position.of(req.queryParams("targetPosition"));
		return chessService.move(startPosition, targetPosition);
	}

	public boolean isEnd() {
		return chessService.isEnd();
	}

	public boolean isWinWhiteTeam() {
		return chessService.isWinWhiteTeam();
	}

	public boolean restart() {
		return chessService.restart();
	}

	public Result status() {
		return chessService.status();
	}
}

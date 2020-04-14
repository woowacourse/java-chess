package chess;

import chess.contoller.WebController;
import chess.domain.dao.RoomDao;
import chess.domain.service.ChessGameService;

public class WebApplication {
	public static void main(String[] args) {
		ChessGameService chessGameService = new ChessGameService(new RoomDao());
		WebController webController = new WebController(chessGameService);
		webController.run();
	}
}

package chess;

import chess.domain.service.BoardService;
import chess.contoller.WebController;
import chess.domain.dao.RoomDao;

public class WebApplication {
	public static void main(String[] args) {
		BoardService boardService = new BoardService(new RoomDao());
		WebController webController = new WebController(boardService);
		webController.run();
	}
}

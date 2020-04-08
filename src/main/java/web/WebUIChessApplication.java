package web;

import web.controller.WebController;
import web.service.BoardService;

public class WebUIChessApplication {
	public static void main(String[] args) {
		BoardService boardService = new BoardService();
		WebController webController = new WebController(boardService);
		webController.run();
	}
}

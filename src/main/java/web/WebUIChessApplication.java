package web;

import web.controller.WebController;
import web.service.ChessService;

public class WebUIChessApplication {
	public static void main(String[] args) {
		ChessService service = new ChessService();
		WebController webController = new WebController(service);
		webController.run();
	}
}

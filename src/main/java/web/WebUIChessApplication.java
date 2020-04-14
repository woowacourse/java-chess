package web;

import web.controller.WebController;
import web.service.PieceService;

public class WebUIChessApplication {
	public static void main(String[] args) {
		PieceService service = new PieceService();
		WebController webController = new WebController(service);
		webController.run();
	}
}

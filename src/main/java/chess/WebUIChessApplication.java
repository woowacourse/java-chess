package chess;

import chess.controller.WebChessController;
import chess.dao.GamesDao;
import chess.dao.MoveDao;

public class WebUIChessApplication {
	public static void main(String[] args) {
		WebChessController controller = new WebChessController(new MoveDao(), new GamesDao());
		controller.route();
	}
}

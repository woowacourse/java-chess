package chess;

import chess.controller.BoardController;
import chess.dao.GamesDao;
import chess.dao.MoveDao;

public class WebUIChessApplication {
	public static void main(String[] args) {
		BoardController controller = new BoardController(new MoveDao(), new GamesDao());
		controller.route();
	}
}

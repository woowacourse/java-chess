package chess;

import chess.controller.ChessController;
import chess.service.ChessService;
import chess.service.EntityCache;

public class Application {

	public static void main(String[] args){
		ChessController controller = new ChessController();
		ChessService service = new ChessService(new EntityCache());
		while (!(service.isGameDone() || service.isGameTerminated())) {
			controller.run(service);
		}
	}
}

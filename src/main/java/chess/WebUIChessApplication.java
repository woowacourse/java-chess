package chess;

import static spark.Spark.*;

import chess.controller.GameController;
import chess.controller.RoomController;

public class WebUIChessApplication {
	public static void main(String[] args) {
		port(4567);
		staticFiles.location("/static");
		externalStaticFileLocation("src/main/resources/templates");

		get(RoomController.BASIC_URL, RoomController.getAllRoom);
		get(RoomController.CREATE_ROOM_URL, RoomController.createRoom);
		get(RoomController.REMOVE_ROOM_URL, RoomController.removeRoom);
		get(RoomController.ENTER_ROOM_URL, RoomController.enterRoom);

		get(GameController.INIT_URL, GameController::initGame);
		post(GameController.MOVE_URL, GameController::movePiece);
		get(GameController.STATUS_URL, GameController::showStatus);
		get(GameController.LOAD_URL, GameController::loadGame);
	}
}

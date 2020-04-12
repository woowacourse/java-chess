package chess;

import controller.ChessRoomController;
import controller.ChessRoomsController;
import controller.ChessStatisticController;
import spark.Spark;

public class WebUIChessApplication {

	private static final ChessRoomsController ROOMS_CONTROLLER = ChessRoomsController.getInstance();
	private static final ChessRoomController ROOM_CONTROLLER = ChessRoomController.getInstance();
	private static final ChessStatisticController CHESS_STATISTIC_CONTROLLER
			= ChessStatisticController.getInstance();

	public static void main(String[] args) {
		Spark.port(ServerInfo.PORT);
		Spark.staticFiles.location(ServerInfo.STATIC_FILES);

		ROOMS_CONTROLLER.run();
		ROOM_CONTROLLER.run();
		CHESS_STATISTIC_CONTROLLER.run();
	}
}

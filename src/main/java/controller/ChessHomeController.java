package controller;

import spark.Spark;

import java.util.Collections;

public class ChessHomeController {
	private static final ChessHomeController CHESS_HOME_CONTROLLER;
	private static final String STATIC_PATH = "/index.html";
	public static final String PATH = "/chess/home";

	static {
		CHESS_HOME_CONTROLLER = new ChessHomeController();
	}

	private ChessHomeController() {
	}

	public static ChessHomeController getInstance() {
		return CHESS_HOME_CONTROLLER;
	}

	public void run() {
		routeGetMethod();
	}

	private void routeGetMethod() {
		Spark.get(PATH, (request, response)
				-> Renderer.getInstance().render(Collections.emptyMap(), STATIC_PATH));
	}
}

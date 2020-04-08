import static spark.Spark.*;

import java.util.Map;

import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {
	private static Gson gson = new Gson();
	private static WebController webController = new WebController();

	public static void main(String[] args) {
		port(8080);
		staticFileLocation("/public");

		get("/", ((request, response) -> { // 전체 조회
			Map<String, Object> model = webController.findRoom();
			return render(model, "index.html");
		}));

		post("/", ((request, response) -> { // 생성
			String roomID = webController.createRoom();
			response.redirect("/" + roomID);
			return null;
		}));

		delete("/:roomID", ((request, response) -> { // 삭제
			return webController.deleteRoom(request);
		}));

		get("/:roomID", (req, res) -> { // 방 조회
			Map<String, Object> model = webController.detailRoom(req);
			return render(model, "game.html");
		});

		post("/move/:roomID", (request, response) ->
				webController.move(request)
			, gson::toJson);

		get("/start/:roomID", (request, response) ->
				webController.start(request)
			, gson::toJson);

		get("/resume/:roomID", (request, response) ->
				webController.resume(request)
			, gson::toJson);

		get("/status/:roomID", (request, response) ->
				webController.status(request)
			, gson::toJson);

		get("/winner/:roomID", (request, response) ->
				webController.winner(request),
			gson::toJson);
	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}

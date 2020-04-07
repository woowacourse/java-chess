import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import dao.RoomDAO;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {
	private static Gson gson = new Gson();
	private static WebController webController = new WebController();

	public static void main(String[] args) {
		port(8080);
		staticFileLocation("/public");

		get("/", ((request, response) -> { // 전체 조회
			Map<String, Object> model = new RoomDAO().findAll();
			return render(model, "index.html");
		}));

		post("/", ((request, response) -> { // 생성
			RoomDAO roomDAO = new RoomDAO();
			String roomID = roomDAO.createRoom();

			response.redirect("/" + roomID);
			return null;
		}));

		delete("/:roomID", ((request, response) -> { // 삭제
			String roomID = request.params("roomID");
			new RoomDAO().delete(roomID);
			return true;
		}));

		get("/:roomID", (req, res) -> { // 방 조회
			Map<String, Object> model = new HashMap<>();
			model.put("roomID", req.params("roomID"));
			return render(model, "game.html");
		});

		post("/move/:roomID", (request, response) -> {
				Long roomID = Long.valueOf(request.params("roomID"));
				return webController.move(roomID, request);
			}
			, gson::toJson);

		get("/start/:roomID", (request, response) ->
				webController.start(Long.valueOf(request.params("roomID")))
			, gson::toJson);

		get("/resume/:roomID", (request, response) ->
				webController.resume(Long.valueOf(request.params("roomID")))
			, gson::toJson);

		get("/status/:roomID", (request, response) ->
				webController.status(Long.valueOf(request.params("roomID")))
			, gson::toJson);

		get("/winner/:roomID", (request, response) ->
				webController.winner(Long.valueOf(request.params("roomID"))),
			gson::toJson);
	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}

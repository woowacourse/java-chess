package chess.contoller;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

public class WebErrorController {
	public void renderStartError(Exception e, Request request, Response response) {
		Map<String, Object> model = new HashMap<>();
		model.put("error", e.getMessage());
		response.status(400);
		response.body(new HandlebarsTemplateEngine().render(new ModelAndView(model, "starterror.html")));
	}

	public void renderError(Exception e, Request request, Response response) {
		Map<String, Object> model = new HashMap<>();
		model.put("error", e.getMessage());
		model.put("game_id", request.queryParams("game_id"));
		response.status(400);
		response.body(new HandlebarsTemplateEngine().render(new ModelAndView(model, "error.html")));
	}
}

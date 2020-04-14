package web.controller;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import web.dto.ResponseDto;
import web.service.PieceService;

public class WebController {
	private static final String GAME_VIEW = "game.html";
	private PieceService service;

	public WebController(PieceService service) {
		this.service = service;
	}

	public void run() {
		port(8080);
		staticFileLocation("/templates");

		get("/", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			return render(model, GAME_VIEW);
		});

		get("/start", (req, res) -> {
			ResponseDto responseDto = service.createPieces();
			Map<String, Object> model = new HashMap<>();
			model.put("response", responseDto);
			return render(model, GAME_VIEW);
		});

		get("/move", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			try {
				ResponseDto responseDto = service.move(req.queryParams("moveCommand"));
				model.put("response", responseDto);
			} catch (IllegalArgumentException e) {
				model.put("error", e);
			}
			return render(model, GAME_VIEW);
		});
	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}

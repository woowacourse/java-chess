package controller;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Map;

public class Renderer {
	private static final Renderer renderer;

	static {
		renderer = new Renderer(new HandlebarsTemplateEngine());
	}

	private final HandlebarsTemplateEngine handlebarsTemplateEngine;

	private Renderer(final HandlebarsTemplateEngine handlebarsTemplateEngine) {
		this.handlebarsTemplateEngine = handlebarsTemplateEngine;
	}

	public static Renderer getInstance() {
		return renderer;
	}

	public String render(final Map<String, Object> model, final String templatePath) {
		return handlebarsTemplateEngine.render(new ModelAndView(model, templatePath));
	}
}

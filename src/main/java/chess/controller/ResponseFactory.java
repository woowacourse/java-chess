package chess.controller;

import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author heebg
 * @version 1.0 2019-06-27
 */
public enum ResponseFactory {
    INIT(ResponseFactory::render, ResponseFactory::toJson),
    MAIN(ResponseFactory::render, ResponseFactory::toJson),
    MOVABLE(ResponseFactory::render, ResponseFactory::toJson),
    MOVE(ResponseFactory::render, ResponseFactory::toJson),
    STATUS(ResponseFactory::render, ResponseFactory::toJson),
    END(ResponseFactory::render, ResponseFactory::toJson);

    private BiFunction<Map<String, Object>,String, String> rendFunc;
    private Function<Object, String> jsonFunc;

    ResponseFactory(BiFunction<Map<String, Object>,String, String> rendFunc, Function<Object, String> jsonFunc) {
        this.rendFunc = rendFunc;
        this.jsonFunc = jsonFunc;
    }

    public String apply(Map<String, Object> model, String template) {
        return rendFunc.apply(model, template);
    }

    public String apply(Object object) {
        return jsonFunc.apply(object);
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private static String toJson(Object object) {
        return new Gson().toJson(object);
    }
}

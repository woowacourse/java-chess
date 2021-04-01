package chess;

import static spark.Spark.staticFiles;

public class WebUIChessApplication {
    // 컨트롤러를 통해서 가져온다.


    public static void main(String[] args) {
        //Spark.port(3693);
        staticFiles.location("/static");

        WebUIChessController webUIChessController = new WebUIChessController();
        webUIChessController.run();

//        get("/", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            return render(model, "chess.html");
//        });

    }


//    private static String render(Map<String, Object> model, String templatePath) {
//        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
//    }
}

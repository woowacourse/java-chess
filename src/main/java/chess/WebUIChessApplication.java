package chess;

import domain.Board;
import domain.BoardFactory;
import view.OutputView;

public class WebUIChessApplication {
    public static void main(String[] args) {
        Board board = new Board(BoardFactory.create());

        OutputView.printBoard(board);

//        get("/", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            return render(model, "index.html");
//        });
    }

//    private static String render(Map<String, Object> model, String templatePath) {
//        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
//    }
}

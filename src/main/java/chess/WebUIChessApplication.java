package chess;

import domain.Board;
import domain.BoardFactory;
import domain.GameCommend;
import view.InputView;
import view.OutputView;

public class WebUIChessApplication {
    public static void main(String[] args) {
        OutputView.printStart();

        GameCommend gameCommend = GameCommend.answer(InputView.inputGameCommend());
        if (gameCommend == GameCommend.START) {
            play();
        }

//        get("/", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            return render(model, "index.html");
//        });
    }

    private static void play() {
        Board board = new Board(BoardFactory.create());
        OutputView.printBoard(board);

        GameCommend gameCommend = GameCommend.answer(InputView.inputGameCommend());
        if (gameCommend == GameCommend.START) {
            play();
        }
    }

//    private static String render(Map<String, Object> model, String templatePath) {
//        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
//    }
}

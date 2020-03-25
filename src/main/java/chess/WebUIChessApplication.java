package chess;

import domain.board.Board;
import domain.pieces.PiecesFactory;
import domain.commend.CommendType;
import view.InputView;
import view.OutputView;

public class WebUIChessApplication {
    public static void main(String[] args) {
        OutputView.printStart();

        CommendType commendType = CommendType.answer(InputView.inputGameCommend());
        if (commendType == CommendType.START) {
            play();
        }

//        get("/", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            return render(model, "index.html");
//        });
    }

    private static void play() {
        Board board = new Board(PiecesFactory.create());
        OutputView.printBoard(board);

        CommendType commendType = CommendType.answer(InputView.inputGameCommend());
        if (commendType == CommendType.START) {
            play();
        }
    }

//    private static String render(Map<String, Object> model, String templatePath) {
//        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
//    }
}

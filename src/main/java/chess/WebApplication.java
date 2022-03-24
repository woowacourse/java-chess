package chess;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.board.strategy.CreateCompleteBoardStrategy;
import chess.domain.piece.Piece;
import java.util.Map;
>>>>>>> refactor(Board.java): 체스판 생성을 별도의 전략으로 분리

public class WebApplication {

    public static void main(String[] args) {
        // get("/", (req, res) -> {
        //     Map<String, Object> model = new HashMap<>();
        //     return render(model, "index.html");
        // });

        final Map<Position, Piece> pieces = (new CreateCompleteBoardStrategy()).createPieces();
        Chess chess = new Chess();
        chess.run(new Board(pieces));
    }
   
	// private static String render(Map<String, Object> model, String templatePath) {
	//     return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	// }
}

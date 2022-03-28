package chess2;

import chess2.domain2.board2.Board;
import chess2.dto2.BoardViewDto;
import chess2.util2.PieceGeneratorUtil;
import chess2.view2.OutputView;

public class Application {

    private static final OutputView outputView = new OutputView();

    public static void main(String[] args) {
        outputView.printGameInstructions();
        Board board = new Board(PieceGeneratorUtil.initFullChessBoard());
        outputView.printBoard(new BoardViewDto(board));
    }
}

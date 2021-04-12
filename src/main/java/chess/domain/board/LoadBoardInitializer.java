package chess.domain.board;

import java.util.List;

public class LoadBoardInitializer implements BoardInitializer {
    private static final LoadBoardInitializer loadBoardInitializer = new LoadBoardInitializer();

    private LoadBoardInitializer() {
    }

    public static Board getBoard(List<Square> squares) {
        return loadBoardInitializer.createBoard(squares);
    }

    @Override
    public Board createBoard(List<Square> squares) {
        return Board.of(squares);
    }
}

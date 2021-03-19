package chess.service.state;

import chess.service.ChessService;
import chess.view.InputView;
import chess.view.OutputView;

public class Ready implements GameState {
    @Override
    public GameState run(ChessService chessService) {
        String command = InputView.command();
        if (command.equals("start")) {
            OutputView.printGridStatus(chessService.grid());
            return new WhiteTurn();
        }
        if (command.equals("end")) {
            return new End();
        }
        throw new IllegalArgumentException("잘못된 입력값입니다.");
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}

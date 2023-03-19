package chess.game.state;

import chess.view.InputView;
import chess.view.OutputView;

public class InitGameState extends ChessGameState {
    private static final int COMMAND_INDEX = 0;
    
    public InitGameState() {
        super(null);
    }
    
    @Override
    public ChessGameState next() {
        OutputView.noticeGameStart();
        String command = InputView.repeatAtExceptionCase(InputView::inputInitCommand);
        String[] splitedCommand = command.split(" ");
    
        if (isCommandStart(splitedCommand)){
            return new GameStartState();
        }
    
        return new GameEndState();
    }
    
    private boolean isCommandStart(String[] splitedCommand) {
        return "start".equals(splitedCommand[COMMAND_INDEX]);
    }
}

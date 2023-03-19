package chess.game.state;

import chess.domain.piece.Team;
import chess.game.Command;
import chess.view.InputView;
import chess.view.OutputView;

public class InitGameState extends ChessGameState {
    private static final String SPACE_DELIMITER = " ";
    private static final int COMMAND_INDEX = 0;
    
    public InitGameState(Team currentOrderTeam) {
        super(null, currentOrderTeam);
    }
    
    @Override
    public ChessGameState next() {
        OutputView.noticeGameStart();
        String inputtedCommand = InputView.repeatAtExceptionCase(InputView::inputInitCommand);
        String[] splitedCommand = inputtedCommand.split(SPACE_DELIMITER);
        Command command = Command.from(splitedCommand[COMMAND_INDEX]);
    
        if (command.isStart()){
            return new GameStartState(currentOrderTeam);
        }
    
        return new GameEndState();
    }
}

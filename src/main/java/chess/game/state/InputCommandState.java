package chess.game.state;

import chess.domain.board.ChessBoard;
import chess.domain.piece.Team;
import chess.game.Command;
import chess.view.InputView;

public class InputCommandState extends ChessGameState {
    private static final String SPACE_DELIMITER = " ";
    private static final int COMMAND_INDEX = 0;
    private static final int SOURCE_COORDINATE_INDEX = 1;
    private static final int DESTINATION_COORDINATE_INDEX = 2;
    
    public InputCommandState(ChessBoard chessBoard, Team currentOrderTeam) {
        super(chessBoard, currentOrderTeam);
    }
    
    @Override
    public ChessGameState next() {
        String inputtedCommand = InputView.repeatAtExceptionCase(InputView::inputCommand);
        String[] splitedCommand = inputtedCommand.split(SPACE_DELIMITER);
        Command command = Command.from(splitedCommand[COMMAND_INDEX]);
        
        if (command.isStart()){
            return new GameStartState(currentOrderTeam);
        }
        
        if (command.isMove()){
            return new PieceMoveState(
                    chessBoard,
                    currentOrderTeam,
                    splitedCommand[SOURCE_COORDINATE_INDEX],
                    splitedCommand[DESTINATION_COORDINATE_INDEX]
            );
        }
        return new GameEndState();
    }
}

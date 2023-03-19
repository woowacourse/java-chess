package chess.game.state;

import chess.domain.board.ChessBoard;
import chess.domain.piece.Team;
import chess.view.InputView;

public class InputCommandState extends ChessGameState {
    private static final String START_COMMAND = "start";
    private static final String MOVE_COMMAND = "move";
    private static final String SPACE_DELIMITER = " ";
    private static final int COMMAND_INDEX = 0;
    private static final int SOURCE_COORDINATE_INDEX = 1;
    private static final int DESTINATION_COORDINATE_INDEX = 2;
    
    public InputCommandState(ChessBoard chessBoard, Team currentOrderTeam) {
        super(chessBoard, currentOrderTeam);
    }
    
    @Override
    public ChessGameState next() {
        String command = InputView.repeatAtExceptionCase(InputView::inputCommand);
        String[] splitedCommand = command.split(SPACE_DELIMITER);
        if (isCommandStart(splitedCommand)){
            return new GameStartState(currentOrderTeam);
        }
        
        if (isCommandMove(splitedCommand)){
            return new PieceMoveState(
                    chessBoard,
                    currentOrderTeam,
                    splitedCommand[SOURCE_COORDINATE_INDEX],
                    splitedCommand[DESTINATION_COORDINATE_INDEX]
            );
        }
        return new GameEndState();
    }
    
    private boolean isCommandStart(String[] splitedCommand) {
        return START_COMMAND.equals(splitedCommand[COMMAND_INDEX]);
    }
    
    private boolean isCommandMove(String[] splitedCommand) {
        return MOVE_COMMAND.equals(splitedCommand[COMMAND_INDEX]);
    }
}

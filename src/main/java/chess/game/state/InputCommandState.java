package chess.game.state;

import chess.domain.board.ChessBoard;
import chess.view.InputView;

public class InputCommandState extends ChessGameState {
    private static final int COMMAND_INDEX = 0;
    private static final int SOURCE_COORDINATE_INDEX = 1;
    private static final int DESTINATION_COORDINATE_INDEX = 2;
    
    public InputCommandState(ChessBoard chessBoard) {
        super(chessBoard);
    }
    
    @Override
    public ChessGameState next() {
        String command = InputView.repeatAtExceptionCase(InputView::inputCommand);
        String[] splitedCommand = command.split(" ");
        if (isCommandStart(splitedCommand)){
            return new GameStartState();
        }
        
        if (isCommandMove(splitedCommand)){
            return new PieceMoveState(chessBoard, splitedCommand[SOURCE_COORDINATE_INDEX], splitedCommand[DESTINATION_COORDINATE_INDEX]);
        }
        return new GameEndState();
    }
    
    private boolean isCommandStart(String[] splitedCommand) {
        return "start".equals(splitedCommand[COMMAND_INDEX]);
    }
    
    private boolean isCommandMove(String[] splitedCommand) {
        return "move".equals(splitedCommand[COMMAND_INDEX]);
    }
}

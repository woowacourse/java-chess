package chess;

import chess.view.InputView;
import chess.view.OutputView;
import chess.view.ProcessCommand;

public class Application {
    public static void main(String[] args) {
        OutputView.noticeGameStart();
        run();
    }
    
    private static void run() {
        ProcessCommand processCommand = InputView.repeat(InputView::inputProcessCommand);
        if (processCommand.isEnd()) {
            return;
        }
        
        //출력
        run();
    }
}

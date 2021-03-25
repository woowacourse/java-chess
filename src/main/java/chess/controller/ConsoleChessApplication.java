package chess.controller;

import chess.domain.Chess;
import chess.domain.position.MovePosition;
import chess.view.InputView;
import chess.view.OutputView;

public class ConsoleChessApplication {
    public static void main(String[] args) {
        OutputView.printStart();
        
        Chess chess = Chess.createWithEmptyBoard();
        while (!chess.isTerminated()) {
            chess = execute(chess);
        }
    }
    
    private static Chess execute(Chess chess) {
        final String[] inputCommand = InputView.askCommand();
        final Command command = Command.findCommandByInputCommand(inputCommand);
        
        if (command == Command.START) {
            return start(chess);
        }
        
        if (command == Command.MOVE) {
            return move(chess, inputCommand);
        }
        
        if (command == Command.STATUS) {
            return status(chess);
        }
        
        if (command == Command.END) {
            return end(chess);
        }
        
        if (command == Command.EXIT) {
            return exit(chess);
        }
        
        throw new IllegalArgumentException("없는 커맨드입니다.");
    }
    
    private static Chess start(Chess chess) {
        return chess.start();
    }
    
    private static Chess move(Chess chess, String[] inputCommand) {
        MovePosition movePosition = MovePosition.of(inputCommand);
        return chess.move(movePosition);
    }
    
    private static Chess status(Chess chess) {
        return chess.status();
    }
    
    private static Chess end(Chess chess) {
        return chess.end();
    }
    
    private static Chess exit(Chess chess) {
        return chess.exit();
    }
}

package chess;

import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.factory.BoardFactory;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {
    public static void main(String[] args) {
        OutputView.printInitialMessage();
        run();

//        OutputView.printBoard(chessBoard);
//        System.out.println(chessBoard.getTotalScore(Team.WHITE));
//        chessBoard.move(new Position(2, 4), new Position(4, 4));
//        OutputView.printBoard(chessBoard);
//        System.out.println(chessBoard.getTotalScore(Team.WHITE));
//        chessBoard.move(new Position(2, 4), new Position(4, 4));
//        OutputView.printBoard(chessBoard);
//        System.out.println(chessBoard.getTotalScore(Team.WHITE));
//        chessBoard.move(new Position(4, 4), new Position(5, 5));
//        OutputView.printBoard(chessBoard);
//        System.out.println(chessBoard.getTotalScore(Team.WHITE));

    }

    private static void run() {
        ChessBoard chessBoard = new ChessBoard(BoardFactory.createBoard());
        String command = getCommand();

        while(!command.equals("end")){
            executeCommand(chessBoard, command);
            command = getCommand();
        }
    }

    private static String getCommand() {
        String command = InputView.inputCommand();
        if(validateCommand(command)){
            return command;
        }
        OutputView.printWrongCommandMessage();
        return getCommand();
    }

    private static void executeCommand(ChessBoard chessBoard, String command) {
        String[] splitCommand = command.split(" ");
        if(splitCommand[0].equals("move")){
            chessBoard.move(Position.of(splitCommand[1]), Position.of(splitCommand[2]) ) ;
        }
        OutputView.printBoard(chessBoard);
    }

    private static boolean validateCommand(String nowCommand) {
        boolean isStartCommand = nowCommand.equals("start");
        boolean isEndCommand = nowCommand.equals("end");
        String[] splitCommand = nowCommand.split(" ");
        boolean isMoveCommand = splitCommand[0].equals("move");
        boolean isCorrectMoveCommand = splitCommand.length == 3;

        return isStartCommand || isEndCommand || (isMoveCommand && isCorrectMoveCommand);
    }
}



//미구현
//- Input Outputview 이용해서 게임진행


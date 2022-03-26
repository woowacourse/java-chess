package controller;

import domain.chessboard.ChessBoard;
import domain.chessboard.ChessBoardGenerator;
import view.InputView;
import view.OutputView;

public class ChessController {

    public void start() {
        ChessBoard chessBoard = new ChessBoard(new ChessBoardGenerator());
        String input = InputView.startCommand();

        if (input.equals(InputView.START)) {
            OutputView.printBoard(chessBoard);
            play(chessBoard);
        }
    }

    private void play(ChessBoard chessBoard) {
        try{
            String input = InputView.playCommand();
            if(input.equals(InputView.END)){
                System.out.println("종료되었습니다.");
                return;
            }
            OutputView.printBoard(chessBoard);
            play(chessBoard);
        }catch (IllegalArgumentException exception){
            System.out.println(exception.getMessage());
            play(chessBoard);
        }
    }
}

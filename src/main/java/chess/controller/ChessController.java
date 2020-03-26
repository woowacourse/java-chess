package chess.controller;

import chess.domain.ChessBoard;
import chess.domain.GameState;
import chess.domain.Square;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChessController {

    public static void run() {
        OutputView.printStartGame();
        OutputView.printStartEndOption();
        ChessBoard chessBoard = new ChessBoard();

        start(chessBoard);
        String input;
        GameState gameState;
        do {
            input = InputView.inputStart();
            List<Square> squares = new ArrayList<>();
            Square squareBeforeMove = null;
            Square squareAfterMove = null;
            if (input.length() > 4) {
                List<String> inputs = Arrays.asList(input.split(" "));
                input = inputs.get(0);
                squares.add(Square.of(inputs.get(1)));
                squares.add(Square.of(inputs.get(2)));
            }
            gameState = GameState.of(input);
            if (gameState == GameState.START) {
                throw new IllegalArgumentException("왜 시작하세요");
            }
            if (gameState == GameState.MOVE) {
                proceed(chessBoard, squares);
            }
        } while (gameState != GameState.END);
    }

    private static void proceed(ChessBoard chessBoard, List<Square> squares) {
//        if(chessBoard){
//            //진행
//        }
        /*
        ask... (squares / turn) => 할수잇엉 못행
        => 1 - 비어잇으면 - 안댐
        => 2 - 잇는데 울팀아니면 - 안댐
        => 3 - 잇는데 움직일수잇는 곳이 없으면 - 안댐
         */

//        //말 선택 잚ㅅ
    }

    private static void start(ChessBoard chessBoard) {
        GameState gameState = GameState.of(InputView.inputStart());
        if (gameState != GameState.START) {
            throw new IllegalArgumentException("게임을 시작해야 합니다");
        }
        OutputView.printChessBoard(chessBoard);
    }
}

package chess.controller;

import chess.domain.ChessBoard;
import chess.domain.position.Positions;
import chess.views.InputView;
import chess.views.OutputView;

import java.util.Map;

public class ChessController {
    public void play() {
        OutputView.printInitialGuide();

        Map<String, String> inputs = InputView.inputCommand();
        Command command = Command.of(inputs.get("command"));

        if (!command.equals(Command.START)) {
            throw new IllegalArgumentException("start를 해야 합니다.");
        }

        // 정상적으로 들어왔을 때
        ChessBoard chessBoard = new ChessBoard();
        OutputView.printChessBoard(chessBoard.getChessBoard());

        do{
            inputs = InputView.inputCommand();
            // (to do) 입력 ~ 배열에 대한 처리
            // (to do) move a2 a4 3칸만 입력했는지 검증
            command = Command.of(inputs.get("command"));

            // (to do) state 상태 관리 필요하다.
            if (command.equals(Command.MOVE)) {
                chessBoard.move(Positions.of(inputs.get("from")), Positions.of(inputs.get("to")));
                OutputView.printChessBoard(chessBoard.getChessBoard());
            }
            if (command.equals(Command.STATUS)) {
                OutputView.printStatus(chessBoard.getResult());
            }
            if (chessBoard.isGameOver()) {
                OutputView.printGameOver();
                return;
            }
        }while (!command.equals(Command.END));
    }
}
package chess.controller;

import chess.domain.ChessBoard;
import chess.domain.position.Positions;
import chess.views.InputView;
import chess.views.OutputView;

public class ChessController {
    public void play() {
        OutputView.printInitialGuide();

        if (!Command.of(InputView.inputCommand()).equals(Command.START)) {
            throw new IllegalArgumentException("start를 해야 합니다.");
        }

        // 정상적으로 들어왔을 때
        ChessBoard chessBoard = new ChessBoard();
        OutputView.printChessBoard(chessBoard.getChessBoard());
        while (true) {
            String input = InputView.inputCommand();
            // (to do) 입력 ~ 배열에 대한 처리
            // (to do) move a2 a4 3칸만 입력했는지 검증
            Command command = Command.of(input.split(" ")[0]);

            // (to do) state 상태 관리 필요하다.
            if (command.equals(Command.MOVE)) {
                chessBoard.move(Positions.of(input.split(" ")[1]), Positions.of(input.split(" ")[2]));
            }
            if (command.equals(Command.STATUS)) {
                // do something
            }
            if (command.equals(Command.END)) {
                return;
            }
            OutputView.printChessBoard(chessBoard.getChessBoard());
        }
    }
}

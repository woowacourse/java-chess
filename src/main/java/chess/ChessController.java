package chess;

import chess.board.Board;
import chess.domain.piece.Color;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
    public void run() {
        OutputView.printStart();
        Board board = new Board();
        while (true) {
            String command = InputView.askCommand();
            // todo - 명령어 처리방식 변경
            if (command.equals("start")) {
                // todo 보드생성은 위로 빼기
                board.init();
                // todo - dto로 만들어서 뷰에 던지기
                OutputView.printBoard(board);
                // todo - 보드의 상태에 따라서 반복문 설정하기
                while (!board.isFinish()) {
                    command = InputView.askCommand();
                    // todo - 명령어 처리방식 변경
                    if ("end".equals(command)) {
                        break;
                    }
                    // todo 명령어 처리방식 변경
                    String[] commands = command.split(" ");
                    if ("move".equals(commands[0])) {
                        board.movePiece(commands[1], commands[2]);
                    }
                    if ("status".equals(command)) {
                        // todo - 결과 관련  dto 만들어서 던지기
                        OutputView.printStatus(board.score(Color.BLACK), board.score(Color.WHITE), board.winner());
                    }
                    OutputView.printBoard(board);
                }
            }
            if ("status".equals(command)) {
                // todo - 결과 관련  dto 만들어서 던지기
                OutputView.printStatus(board.score(Color.BLACK), board.score(Color.WHITE), board.winner());
            }
            if ("exit".equals(command)) {
                break;
            }
        }
        
    }
}

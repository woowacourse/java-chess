package chess;

import chess.domain.game.Chess;
import chess.domain.piece.Color;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
    
    public void run() {
        OutputView.printStart();
        Chess chess = null;
        while (true) {
            String command = InputView.askCommand();
            // todo - 명령어 처리방식 변경
            if (command.equals("start")) {
                // todo 보드생성은 위로 빼기
                chess = Chess.createWithInitializedBoard();
                // todo - dto로 만들어서 뷰에 던지기
                OutputView.printBoard(chess);
                // todo - 보드의 상태에 따라서 반복문 설정하기
                while (chess.isRunning()) {
                    command = InputView.askCommand();
                    // todo - 명령어 처리방식 변경
                    if ("end".equals(command)) {
                        break;
                    }
                    // todo 명령어 처리방식 변경
                    String[] commands = command.split(" ");
                    if ("move".equals(commands[0])) {
                        chess = chess.movePiece(commands[1], commands[2]);
                    }
                    if ("status".equals(command)) {
                        // todo - 결과 관련  dto 만들어서 던지기
                        OutputView.printStatus(chess.score(Color.BLACK), chess.score(Color.WHITE), chess.winner());
                    }
                    OutputView.printBoard(chess);
                }
                OutputView.printMessageThatKingIsDead(chess.winner());
            }
            if ("status".equals(command)) {
                if (chess == null) {
                    throw new IllegalStateException("기록된 체스 경기가 없습니다.");
                }
                // todo - 결과 관련  dto 만들어서 던지기
                OutputView.printStatus(chess.score(Color.BLACK), chess.score(Color.WHITE), chess.winner());
            }
            if ("exit".equals(command)) {
                break;
            }
        }
        
    }
}

package chess.view;

import static chess.view.command.Command.END;

import chess.view.dto.ChessboardDto;
import java.util.List;

public class ResultView {

    public void printGameStartMessage() {
        System.out.printf("체스 게임을 시작합니다.%n"
                + "> 게임 시작 : start%n"
                + "> 게임 종료 : end%n"
                + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3%n");
    }

    public void printGameEnd() {
        System.out.printf(END.getMessage());
    }

    public void printBoard(final ChessboardDto chessboardDto) {
        List<List<String>> chessboard = chessboardDto.getChessboard();
        chessboard.forEach(squares -> System.out.println(String.join("", squares)));
        System.out.println();
    }
}

package chess.view;

import static chess.view.command.CommandType.END;

import java.util.List;

import chess.view.dto.ChessboardDto;

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
        List<List<String>> chessboard = chessboardDto.get();
        chessboard.forEach(positions -> System.out.println(String.join("", positions)));
        System.out.println();
    }
}

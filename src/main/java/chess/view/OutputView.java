package chess.view;

import chess.controller.dto.ChessBoardDto;

public class OutputView {

    public void printStartMessage() {
        System.out.println("체스 게임을 시작합니다.");
    }

    public void printChessBoard(final ChessBoardDto chessBoardDto) {
        for (String line : chessBoardDto.getLines()) {
            System.out.println(line);
        }
    }
}

package chess.view;

import chess.view.dto.ChessBoardDto;
import chess.view.dto.ChessStatusDto;

public class OutputView {

    public void printStartMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 점수 확인 : status");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
        System.out.println("> 게임 종료 : end");
    }

    public void printChessBoard(final ChessBoardDto chessBoardDto) {
        for (String line : chessBoardDto.getLines()) {
            System.out.println(line);
        }
    }

    public void printChessStatus(final ChessStatusDto chessStatusDto) {
        final double blackScore = chessStatusDto.getBlackScore();
        final double whiteScore = chessStatusDto.getWhiteScore();
        System.out.println("BLACK 점수: " + blackScore);
        System.out.println("WHITE 점수: " + whiteScore);
        System.out.println(getWinOrLoseMessage(blackScore, whiteScore));
    }

    private String getWinOrLoseMessage(final Double blackScore, final Double whiteScore) {
        if (blackScore.compareTo(whiteScore) > 0) {
            return "BLACK 점수가 높습니다.";
        }
        if (blackScore.compareTo(whiteScore) < 0) {
            return "WHITE 점수가 높습니다.";
        }
        return "점수가 같습니다.";
    }

    public void printEndMessage() {
        System.out.println("게임이 종료되었습니다.");
    }

    public void printDoneMessage() {
        System.out.println("왕이 잡혀 게임이 종료되었습니다.");
    }

    public void printErrorMessage(final String message) {
        System.out.println("[ERROR] " + message);
    }
}

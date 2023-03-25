package chess.view;

import chess.view.resposne.PieceResponse;
import chess.view.resposne.StatusResponse;
import java.util.List;

public class OutputView {

    public void printInitialMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public void printBoard(List<List<PieceResponse>> piecePosition) {
        for (int i = piecePosition.size() - 1; i >= 0; i--) {
            printRank(piecePosition.get(i));
        }
        System.out.println();
    }

    public void printStatus(StatusResponse statusResponse) {
        System.out.println("현재 점수");
        System.out.println("흰색 : " + statusResponse.getWhiteScore());
        System.out.println("검정색 : " + statusResponse.getBlackScore());
    }

    private void printRank(List<PieceResponse> pieceResponses) {
        pieceResponses.forEach(this::printPiece);
        System.out.println();
    }

    private void printPiece(PieceResponse pieceResponse) {
        System.out.print(pieceResponse.getPiece());
    }

    public void printError(Exception e) {
        System.out.println(e.getMessage());
    }
}

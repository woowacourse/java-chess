package chess.view;

import chess.domain.dto.PieceResponse;

import java.util.List;

public class OutputView {

    public void printBoard(List<List<PieceResponse>> piecePosition) {
        for (int i = piecePosition.size() - 1; i >= 0; i--) {
            printRank(piecePosition.get(i));
        }
        System.out.println();
    }

    private void printRank(List<PieceResponse> pieceResponses) {
        pieceResponses.forEach(this::printPiece);
        System.out.println();
    }

    private void printPiece(PieceResponse pieceResponse) {
        System.out.print(formatByColor(pieceResponse.getPieceType(), pieceResponse.getPieceColor()));
    }

    private String formatByColor(String pieceType, String pieceColor) {
        if ("BLACK".equals(pieceColor)) {
            return pieceType.toUpperCase();
        }
        return pieceType;
    }

    public void printInitialMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public void printUnsuitableCommand() {
        System.out.println("해당 상황에서 입력할 수 없는 커맨드 입니다.");
        System.out.println();
    }

    public void printError(Exception e) {
        System.out.println(e.getMessage());
    }
}

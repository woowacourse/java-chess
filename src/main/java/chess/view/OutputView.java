package chess.view;

import chess.domain.dto.PieceResponse;

import java.util.List;

public class OutputView {

    public void printBoard(List<List<PieceResponse>> piecePosition) {
        for (int i = piecePosition.size() - 1; i >= 0; i--) {
            printBoardLine(piecePosition.get(i));
        }
    }

    private void printBoardLine(List<PieceResponse> pieceResponses) {
        pieceResponses.stream().forEach(this::printResult);
        System.out.println();
    }

    private void printResult(PieceResponse pieceResponse) {
        System.out.print(getPiecePrint(pieceResponse.getPieceType(), pieceResponse.getPieceColor()));
    }

    private String getPiecePrint(String pieceType, String pieceColor) {
        if ("BLACK".equals(pieceColor)) {
            return pieceType.toUpperCase();
        }
        return pieceType;
    }

}

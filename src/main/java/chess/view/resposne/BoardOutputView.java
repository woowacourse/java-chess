package chess.view.resposne;

import chess.controller.game.BoardOutput;
import java.util.List;

public class BoardOutputView implements BoardOutput {

    @Override
    public void printBoard(List<List<PieceResponse>> boardResponse) {
        for (int i = boardResponse.size() - 1; i >= 0; i--) {
            printRank(boardResponse.get(i));
        }
        System.out.println();
    }

    private void printRank(List<PieceResponse> pieceResponses) {
        pieceResponses.forEach(this::printPiece);
        System.out.println();
    }

    private void printPiece(PieceResponse pieceResponse) {
        System.out.print(pieceResponse.getPiece());
    }
}

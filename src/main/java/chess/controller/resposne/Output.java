package chess.controller.resposne;

import chess.view.response.PieceResponse;
import java.util.List;

public interface Output {

    void printInitialMessage();

    void printBoard(List<List<PieceResponse>> piecePosition);

    void printError(Exception e);
}

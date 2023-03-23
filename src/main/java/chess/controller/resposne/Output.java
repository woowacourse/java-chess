package chess.controller.resposne;

import java.util.List;

public interface Output {

    void printInitialMessage();

    void printBoard(List<List<PieceResponse>> piecePosition);

    void printStatus(StatusResponse statusResponse);

    void printError(Exception e);
}

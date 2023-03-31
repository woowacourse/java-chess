package chess.controller.game;

import chess.view.resposne.PieceResponse;
import java.util.List;

public interface BoardOutput {

    void printBoard(List<List<PieceResponse>> boardResponse);
}

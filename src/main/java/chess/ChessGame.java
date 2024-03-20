package chess;

import chess.domain.Board;
import chess.domain.BoardFactory;
import chess.domain.Color;
import chess.domain.File;
import chess.domain.GameCommand;
import chess.domain.Position;
import chess.domain.Rank;
import chess.domain.piece.Bishop;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.Map;
import java.util.Set;

public class ChessGame {
    public void run() {
        String inputCommand = InputView.readGameCommand();
        GameCommand command = GameCommand.from(inputCommand);
        Board board = BoardFactory.createInitialBoard();
        OutputView.printGameStartMessage();
        OutputView.printChessBoard(board);
    }
}

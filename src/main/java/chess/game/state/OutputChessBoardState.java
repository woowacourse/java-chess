package chess.game.state;

import chess.domain.board.ChessBoard;
import chess.view.OutputView;

public class OutputChessBoardState extends ChessGameState {
    public OutputChessBoardState(ChessGameState chessGameState) {
        this(chessGameState.chessBoard);
    }
    
    public OutputChessBoardState(ChessBoard chessBoard) {
        super(chessBoard);
    }
    
    @Override
    public ChessGameState next() {
        OutputView.printChessBoard(chessBoard.chessBoard());
        return new InputCommandState(chessBoard);
    }
}

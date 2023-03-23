package chess.game.state;

import chess.domain.board.ChessBoard;
import chess.domain.piece.Team;
import chess.view.OutputView;

public class OutputChessBoardState extends ChessGameState {
    public OutputChessBoardState(ChessGameState chessGameState) {
        this(chessGameState.chessBoard, chessGameState.currentOrderTeam);
    }
    
    public OutputChessBoardState(ChessBoard chessBoard, Team currentOrderTeam) {
        super(chessBoard, currentOrderTeam);
    }
    
    @Override
    public ChessGameState next() {
        OutputView.printChessBoard(chessBoard.chessBoard());
        return new InputCommandState(chessBoard, currentOrderTeam);
    }
}

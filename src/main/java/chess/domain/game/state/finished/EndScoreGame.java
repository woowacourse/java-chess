package chess.domain.game.state.finished;

import chess.domain.piece.Camp;
import chess.domain.position.ChessBoard;

public class EndScoreGame extends FinishedGame {


    EndScoreGame(ChessBoard chessBoard, Camp turnCamp) {
        super(chessBoard, turnCamp);
    }
}

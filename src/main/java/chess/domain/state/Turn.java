package chess.domain.state;

import static chess.domain.Color.BLACK;
import static chess.domain.Color.WHITE;

import chess.domain.ChessBoard;
import java.util.function.Function;

public enum Turn {

    WHITE_TURN(chessBoard -> new RunningState(chessBoard, WHITE)),
    BLACK_TURN(chessBoard -> new RunningState(chessBoard, BLACK)),
    END(EndState::new),
    ;

    private final Function<ChessBoard, ChessGameState> gameTurnCreator;

    Turn(Function<ChessBoard, ChessGameState> gameTurnCreator) {
        this.gameTurnCreator = gameTurnCreator;
    }

    public ChessGameState createGameTurn(ChessBoard chessBoard) {
        return gameTurnCreator.apply(chessBoard);
    }

    public Turn reverseTurn() {
        if (this == WHITE_TURN) {
            return BLACK_TURN;
        }
        if (this == BLACK_TURN) {
            return WHITE_TURN;
        }
        throw new IllegalStateException("END상태는 반대 턴이 없습니다.");
    }
}

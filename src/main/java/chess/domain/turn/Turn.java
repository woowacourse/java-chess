package chess.domain.turn;

import static chess.domain.Color.BLACK;
import static chess.domain.Color.WHITE;

import chess.domain.ChessBoard;
import java.util.function.Function;

public enum Turn {

    WHITE_TURN(chessBoard -> new RunningTurn(chessBoard, WHITE)),
    BLACK_TURN(chessBoard -> new RunningTurn(chessBoard, BLACK)),
    END(EndTurn::new),
    ;

    private final Function<ChessBoard, GameTurn> gameTurnCreator;

    Turn(final Function<ChessBoard, GameTurn> gameTurnCreator) {
        this.gameTurnCreator = gameTurnCreator;
    }

    public GameTurn createGameTurn(ChessBoard chessBoard) {
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

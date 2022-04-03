package chess.domain.turn;

import static chess.domain.Color.BLACK;
import static chess.domain.Color.WHITE;

import chess.domain.ChessBoard;
import java.util.function.Function;

public enum GameTurnFactory {

    WHITE_TURN(chessBoard -> new RunningTurn(chessBoard, WHITE)),
    BLACK_TURN(chessBoard -> new RunningTurn(chessBoard, BLACK)),
    END(EndTurn::new),
    ;

    private final Function<ChessBoard, GameTurn> gameTurnCreator;

    GameTurnFactory(final Function<ChessBoard, GameTurn> gameTurnCreator) {
        this.gameTurnCreator = gameTurnCreator;
    }
}

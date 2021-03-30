package chess;

import chess.domain.ChessGame;
import chess.domain.RequestDto;
import chess.domain.board.Point;

public class ChessService {
    private final ChessGame chessGame;

    public ChessService(ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    public int move(RequestDto requestDto) {
        String source = requestDto.getSource();
        String target = requestDto.getTarget();
        try {
            chessGame.playTurn(Point.of(source), Point.of(target));
            // if (chessGame.isKingDead()) {
            //     chessGame.changeGameOver();
            // }
            // chessGame.nextTurn();
            return 200;
        } catch (UnsupportedOperationException | IllegalArgumentException e) {
            return 401;
        }
    }
}

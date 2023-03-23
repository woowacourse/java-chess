package chess;

import chess.common.ChessGameConfig;

public class ChessApplication {

    public static void main(String[] args) {
        final ChessGameConfig chessGameConfig = new ChessGameConfig();
        chessGameConfig.chessGameController().start();
    }
}

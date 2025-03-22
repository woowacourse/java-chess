package chess;

import chess.response.StartPosAndEndPos;
import chess.view.GameView;

public class ChessApp {
    public static void main(String[] args) {
        StartPosAndEndPos moveData = GameView.askMove();

    }
}

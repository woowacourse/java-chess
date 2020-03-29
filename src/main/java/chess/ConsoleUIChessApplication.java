package chess;

import chess.view.GameManger;
import chess.view.InputView;
import chess.view.OutputView;

public class ConsoleUIChessApplication {
    public static void main(String[] args) {
        GameManger gameManger = new GameManger();
        gameManger.runGame();
    }
}

package model.menu;

import model.GameBoard;

import java.util.List;

public interface ChessStatus {

    ChessStatus play(List<String> command, GameBoard gameBoard);

    boolean isRunning();
}

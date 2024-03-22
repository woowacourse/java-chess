package model.menu;

import model.ChessGame;

import java.util.List;

public interface ChessStatus {

    ChessStatus play(List<String> command, ChessGame chessGame);

    boolean isRunning();
}

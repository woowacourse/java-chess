package chess.web.service;

import chess.domain.ChessGame;
import chess.domain.Command;
import java.util.List;

public class ChessService {

    private final ChessGame chessGame;

    public ChessService() {
        this.chessGame = new ChessGame();
    }

    public List<String> createChessBoard() {
        chessGame.progress(Command.from("start"));

        return chessGame.getChessBoard();
    }

    public List<String> getCurrentChessBoard() {
        return chessGame.getChessBoard();
    }

    public List<String> move(String moveCommand) {
        Command command = Command.from(moveCommand);

        chessGame.progress(command);

        return chessGame.getChessBoard();
    }
}

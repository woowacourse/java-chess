package chess.web.service;

import chess.domain.ChessGame;
import chess.domain.Command;
import chess.domain.piece.Team;
import java.util.List;
import java.util.Map;

public class ChessService {

    private ChessGame chessGame;

    public ChessService() {
    }

    public List<String> createChessBoard() {
        this.chessGame = new ChessGame();

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

    public Map<Team, Double> getScore() {
        return chessGame.calculateResult();
    }

    public String finish(Command command) {
        chessGame.progress(command);

        return chessGame.getWinTeamName();
    }
}

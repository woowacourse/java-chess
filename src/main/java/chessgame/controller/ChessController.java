package chessgame.controller;

import chessgame.domain.Board;
import chessgame.domain.ChessBoardFactory;
import chessgame.domain.Command;
import chessgame.domain.Game;
import chessgame.view.InputView;
import chessgame.view.OutputView;
import dao.BoardDao;

import java.sql.SQLException;

public class ChessController {
    private final InputView inputView;
    private final OutputView outputView;
    private final BoardDao boardDao;

    public ChessController(InputView inputView, OutputView outputView, BoardDao boardDao) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.boardDao = boardDao;
    }

    public void run() {
        Game game = setGame();
        playGame(game);
    }

    public Game setGame(){
        try {
            String gameName = inputView.readGameName();
            Game readGame = boardDao.read(gameName);
            if(readGame==null){
                return new Game(new Board(ChessBoardFactory.create()),gameName);
            }
            readGame.setDbState(boardDao.findTurnByGame(gameName));
            return readGame;
        }catch(SQLException e){
            return null;
        }
    }

    private void playGame(Game game) {
        outputView.printStartMessage();
        do {
            eachTurn(game);
            printResult(game);
        } while (!game.isEnd());
        if (game.isEndByKing()) {
            outputView.printWinner(game.winTeam());
        }

        saveGame(game);
    }

    private void saveGame(Game game) {
        try {
            boardDao.remove(game.getName());
            boardDao.save(game.board(),game.getName(),game.getTurn());
        }catch (SQLException e){
        }
    }

    private void eachTurn(Game game) {
        try {
            Command command = readCommand();
            setState(game, command);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMsg(e.getMessage());
            eachTurn(game);
        }
    }

    private Command readCommand() {
        try {
            return Command.of(inputView.readCommand());
        } catch (IllegalArgumentException e) {
            outputView.printErrorMsg(e.getMessage());
            return readCommand();
        }
    }

    private void setState(Game game, Command command) {
        try {
            game.setState(command);
            printStatusResult(game, command);
        } catch (UnsupportedOperationException e) {
            outputView.printErrorMsg(e.getMessage());
            setState(game, readCommand());
        }
    }

    private void printStatusResult(Game game, Command command) {
        if (command.isStatus()) {
            outputView.printScore(game.scoreBoard());
            outputView.printScoreWinner(game.scoreBoard());
        }
    }

    private void printResult(Game game) {
        if (!game.isEnd()) {
            outputView.printChessBoard(game.board());
        }
    }
}

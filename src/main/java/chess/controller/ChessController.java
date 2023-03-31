package chess.controller;

import static chess.view.InputView.*;

import chess.dao.ChessGameDao;
import chess.domain.PointCalculator;
import chess.domain.board.ChessBoard;
import chess.domain.piece.Team;
import chess.domain.piece.coordinate.Coordinate;
import chess.view.command.Command;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.result.Result;

import java.util.List;

public class ChessController {

    private final ChessGameDao chessGameDao;


    public ChessController(ChessGameDao chessGameDao) {
        this.chessGameDao = chessGameDao;
    }

    public void runChessGame(ChessBoard chessBoard) {
        List<List<String>> formerResults = chessGameDao.readFormerGame();
        if (formerGameIsNotFinished(formerResults)) {
            Command command = inputRestartOrStart();
            selectStartOrRestart(chessBoard, formerResults, command);
        }
        play(chessBoard);
    }

    private void selectStartOrRestart(ChessBoard chessBoard, List<List<String>> formerResults, Command command) {
        if (restartCase(command)) {
            applyFormerResults(formerResults, chessBoard);
        }
    }

    private boolean restartCase(Command command) {
        return Command.RESTART.equals(command);
    }

    public void play(ChessBoard chessBoard) {
        OutputView.noticeGameStart();
        OutputView.noticeGameCommand();
        List<String> input = repeat(InputView::inputCommand);
        Command command = extractCommand(input);
        commandMoveCase(chessBoard, input, command);
        commandStatusCase(chessBoard, command);
        if (isCommandEnd(command)) {
            return;
        }
        commandStartCase(command);
    }

    private boolean formerGameIsNotFinished(List<List<String>> formerResults) {
        return !formerResults.isEmpty();
    }

    private void applyFormerResults(List<List<String>> formerResults, ChessBoard chessBoard) {
        for (List<String> formerResult : formerResults) {
            chessBoard.move(
                    Coordinate.createCoordinate(chessGameDao.getFormerSourceRow(formerResult),
                            chessGameDao.getFormerSourceColumn(formerResult)),
                    Coordinate.createCoordinate(chessGameDao.getFormerDestinationRow(formerResult),
                            chessGameDao.getFormerDestinationColumn(formerResult)));
        }
        OutputView.noticeFormerChessBoard();
        OutputView.printChessBoard(chessBoard.chessBoard());
    }

    private void commandStatusCase(ChessBoard chessBoard, Command command) {
        if (isCommandStatus(command)) {
            status(chessBoard);
        }
    }

    private void status(ChessBoard chessBoard) {
        double whiteTeamPoint = chessBoard.calculateFinalPointsByTeam(Team.WHITE);
        double blackTeamPoint = chessBoard.calculateFinalPointsByTeam(Team.BLACK);
        Team winningTeam = PointCalculator.winnerOf(blackTeamPoint, whiteTeamPoint);
        OutputView.printPresentStatus(whiteTeamPoint, blackTeamPoint, Result.win(winningTeam));
        play(chessBoard);
    }

    private boolean isCommandStatus(Command command) {
        return Command.STATUS == command;
    }

    private void commandMoveCase(ChessBoard chessBoard, List<String> input, Command command) {
        if (isCommandMove(command)) {
            move(chessBoard, input);
        }
    }

    private boolean isCommandMove(Command command) {
        return Command.MOVE == command;
    }

    private void move(ChessBoard chessBoard, List<String> input) {
        List<String> source = extractSource(input);
        List<String> destination = extractDestination(input);
        Coordinate sourceCoordinate = Coordinate.createCoordinate(extractRow(source), extractColumn(source));
        Coordinate destinationCoordinate = Coordinate.createCoordinate(extractRow(destination), extractColumn(destination));
        moveChessBoard(chessBoard, sourceCoordinate, destinationCoordinate);
        if (checkGameIsDone(chessBoard)) {
            return;
        }
        chessGameDao.save(extractRow(source), extractColumn(source), extractRow(destination), extractColumn(destination));
        play(chessBoard);
    }

    private boolean checkGameIsDone(ChessBoard chessBoard) {
        if (checkIsKingDead(chessBoard)) {
            chessGameDao.deleteAll();
            return true;
        }
        return false;
    }

    private void moveChessBoard(ChessBoard chessBoard, Coordinate sourceCoordinate, Coordinate destinationCoordinate) {
        chessBoard.move(sourceCoordinate, destinationCoordinate);
        OutputView.printChessBoard(chessBoard.chessBoard());
    }

    private boolean checkIsKingDead(ChessBoard chessBoard) {
        if (!chessBoard.isKingAlive()) {
            OutputView.printKingIsDead();
            return true;
        }
        return false;
    }

    private boolean isCommandEnd(Command command) {
        return Command.END == command;
    }

    private void commandStartCase(Command command) {
        if (isCommandStart(command)) {
            runNewChessGame();
        }
    }

    private boolean isCommandStart(Command command) {
        return Command.START == command;
    }

    private void runNewChessGame() {
        ChessBoard newChessBoard = ChessBoard.create();
        OutputView.printChessBoard(newChessBoard.chessBoard());
        chessGameDao.deleteAll();
        play(newChessBoard);
    }

}

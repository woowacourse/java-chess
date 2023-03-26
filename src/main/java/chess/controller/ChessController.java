package chess.controller;

import static chess.view.InputView.*;

import chess.dao.ChessGameDao;
import chess.domain.board.ChessBoard;
import chess.domain.piece.Team;
import chess.domain.piece.coordinate.Coordinate;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessController {

    private final ChessGameDao chessGameDao;


    public ChessController(ChessGameDao chessGameDao) {
        this.chessGameDao = chessGameDao;
    }

    public void runChessGame(ChessBoard chessBoard) {
        List<List<String>> formerResults = chessGameDao.select();
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
        List<String> command = repeat(InputView::inputCommand);
        commandMoveCase(chessBoard, command);
        commandStatusCase(chessBoard, command);
        if (isCommandEnd(command)) {
            return;
        }
        commandStartCase(command);
    }

    private static boolean formerGameIsNotFinished(List<List<String>> formerResults) {
        return !formerResults.isEmpty();
    }

    private void applyFormerResults(List<List<String>> formerResults, ChessBoard chessBoard) {
        for (List<String> formerResult : formerResults) {
            String sourceRow = formerResult.get(0);
            String souceColumn = formerResult.get(1);
            String destinationRow = formerResult.get(2);
            String destinationColumn = formerResult.get(3);
            Coordinate sourceCoordinate = Coordinate.createCoordinate(sourceRow, souceColumn);
            Coordinate destinationCoordinate = Coordinate.createCoordinate(destinationRow, destinationColumn);
            chessBoard.move(sourceCoordinate, destinationCoordinate);
        }
        OutputView.noticeFormerChessBoard();
        OutputView.printChessBoard(chessBoard.chessBoard());
    }

    private void commandStatusCase(ChessBoard chessBoard, List<String> splitedCommand) {
        if (isCommandStatus(splitedCommand)) {
            status(chessBoard);
        }
    }

    private void status(ChessBoard chessBoard) {
        double whiteTeamPoint = chessBoard.calculateFinalPointsByTeam(Team.WHITE);
        double blackTeamPoint = chessBoard.calculateFinalPointsByTeam(Team.BLACK);
        Team winningTeam = Team.winnerOf(blackTeamPoint, whiteTeamPoint);
        OutputView.printPresentStatus(whiteTeamPoint, blackTeamPoint, winningTeam);
        play(chessBoard);
    }

    private boolean isCommandStatus(List<String> splitedCommand) {
        return Command.STATUS.equals(extractCommand(splitedCommand));
    }

    private void commandMoveCase(ChessBoard chessBoard, List<String> splitedCommand) {
        if (isCommandMove(splitedCommand)) {
            move(chessBoard, splitedCommand);
        }
    }

    private boolean isCommandMove(List<String> splitedCommand) {
        return Command.MOVE.equals(extractCommand(splitedCommand));
    }

    private void move(ChessBoard chessBoard, List<String> splitedCommand) {
        List<String> source = extractSource(splitedCommand);
        List<String> destination = extractDestination(splitedCommand);
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
            chessGameDao.delete();
            return true;
        }
        return false;
    }

    private static void moveChessBoard(ChessBoard chessBoard, Coordinate sourceCoordinate,
        Coordinate destinationCoordinate) {
        chessBoard.move(sourceCoordinate, destinationCoordinate);
        OutputView.printChessBoard(chessBoard.chessBoard());
    }

    private boolean checkIsKingDead(ChessBoard chessBoard) {
        if (isKingDead(chessBoard, Team.BLACK)) {
            OutputView.printKingIsDead(Team.BLACK);
            return true;
        }
        if (isKingDead(chessBoard, Team.WHITE)) {
            OutputView.printKingIsDead(Team.WHITE);
            return true;
        }
        return false;
    }

    private boolean isKingDead(ChessBoard chessBoard, Team team) {
        return !chessBoard.isKingAlive(team);
    }

    private boolean isCommandEnd(List<String> splitedCommand) {
        return Command.END.equals(extractCommand(splitedCommand));
    }

    private void commandStartCase(List<String> splitedCommand) {
        if (isCommandStart(splitedCommand)) {
            runNewChessGame();
        }
    }

    private boolean isCommandStart(List<String> splitedCommand) {
        return Command.START.equals(extractCommand(splitedCommand));
    }

    private void runNewChessGame() {
        ChessBoard newChessBoard = ChessBoard.create();
        OutputView.printChessBoard(newChessBoard.chessBoard());
        chessGameDao.delete();
        play(newChessBoard);
    }

}

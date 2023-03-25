package chess.controller;

import chess.domain.board.ChessBoard;
import chess.domain.piece.Team;
import chess.domain.piece.coordinate.Coordinate;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessController {

    public void runChessGame(ChessBoard chessBoard) {
        OutputView.noticeGameStart();
        List<String> command = InputView.repeat(InputView::inputCommand);
        commandMoveCase(chessBoard, command);
        commandStatusCase(chessBoard, command);
        if (isCommandEnd(command)) {
            return;
        }
        commandStartCase(command);
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
        runChessGame(chessBoard);
    }

    private boolean isCommandStatus(List<String> splitedCommand) {
        return Command.STATUS.equals(InputView.extractCommand(splitedCommand));
    }

    private void commandMoveCase(ChessBoard chessBoard, List<String> splitedCommand) {
        if (isCommandMove(splitedCommand)) {
            move(chessBoard, splitedCommand);
        }
    }

    private boolean isCommandMove(List<String> splitedCommand) {
        return Command.MOVE.equals(InputView.extractCommand(splitedCommand));
    }

    private void move(ChessBoard chessBoard, List<String> splitedCommand) {
        List<String> source = InputView.extractSource(splitedCommand);
        List<String> destination = InputView.extractDestination(splitedCommand);
        Coordinate sourceCoordinate = Coordinate.createCoordinate(InputView.extractRow(source),
            InputView.extractColumn(source));
        Coordinate destinationCoordinate = Coordinate.createCoordinate(InputView.extractRow(destination),
            InputView.extractColumn(destination));
        chessBoard.move(sourceCoordinate, destinationCoordinate);
        OutputView.printChessBoard(chessBoard.chessBoard());
        if (checkIsKingDead(chessBoard)){
            return;
        }
        runChessGame(chessBoard);
    }

    private boolean checkIsKingDead(ChessBoard chessBoard) {
        if (isKingDead(chessBoard, Team.BLACK)) {
            OutputView.printKingIsDead(Team.BLACK);
            return true;
        }
        if (isKingDead(chessBoard,Team.WHITE)){
            OutputView.printKingIsDead(Team.WHITE);
            return true;
        }
        return false;
    }

    private boolean isKingDead(ChessBoard chessBoard, Team team) {
        return !chessBoard.isKingAlive(team);
    }

    private boolean isCommandEnd(List<String> splitedCommand) {
        return Command.END.equals(InputView.extractCommand(splitedCommand));
    }

    private void commandStartCase(List<String> splitedCommand) {
        if (isCommandStart(splitedCommand)) {
            runNewChessGame();
        }
    }

    private boolean isCommandStart(List<String> splitedCommand) {
        return Command.START.equals(InputView.extractCommand(splitedCommand));
    }

    private void runNewChessGame() {
        ChessBoard newChessBoard = ChessBoard.create();
        OutputView.printChessBoard(newChessBoard.chessBoard());
        runChessGame(newChessBoard);
    }

}

package chess.controller;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

import chess.domain.ChessGame;
import chess.domain.Command;
import chess.domain.piece.Team;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ChessController {

    private static final List<Position> positions = stream(Rank.values())
            .flatMap(rank -> stream(File.values())
                    .map(file -> Position.of(file, rank)))
            .collect(toList());

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        Collections.sort(positions);
    }

    public void run() {
        ChessGame chessGame = new ChessGame();

        outputView.printAnnounce();

        if (checkEnd(chessGame)) {
            return;
        }

        progress(chessGame);

        showWinTeam(chessGame);
    }

    private boolean checkEnd(ChessGame chessGame) {
        try {
            Command command = Command.from(inputView.inputCommand());
            chessGame.progress(command);

            return chessGame.isEnd();
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());

            return checkEnd(chessGame);
        }
    }

    private void progress(ChessGame chessGame) {
        try {
            playChessGame(chessGame);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());

            progress(chessGame);
        }
    }

    private void playChessGame(ChessGame chessGame) {
        while (!chessGame.isEnd() && chessGame.isExistKing()) {
            printChessBoard(chessGame);

            Command command = Command.from(inputView.inputCommand());

            chessGame.progress(command);

            checkStatus(chessGame, command);
        }
    }

    private void checkStatus(ChessGame chessGame, Command command) {
        if (command.isStatus()) {
            showStatus(chessGame);
        }
    }

    private void showStatus(ChessGame chessGame) {
        try {
            Map<Team, Double> teamScores = chessGame.calculateResult();

            outputView.printResult(teamScores);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());

            showStatus(chessGame);
        }
    }

    private void showWinTeam(ChessGame chessGame) {
        String winTeamName = chessGame.getWinTeam();

        outputView.printWinTeam(winTeamName);

        showStatus(chessGame);
    }

    private void printChessBoard(ChessGame chessGame) {
        Set<Position> piecePositions = chessGame.getPiecePositions();

        List<String> symbols = makeSymbols(chessGame, piecePositions);

        outputView.printChessBoard(symbols);
    }

    private List<String> makeSymbols(ChessGame chessGame, Set<Position> piecePositions) {
        return positions.stream()
                .map(position -> discriminate(chessGame, piecePositions, position))
                .collect(toList());
    }

    private String discriminate(ChessGame chessGame, Set<Position> piecePositions, Position position) {
        if (piecePositions.contains(position)) {
            return chessGame.getSymbolByPosition(position);
        }

        return ".";
    }
}

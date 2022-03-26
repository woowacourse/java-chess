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

        Command initCommand = Command.from(inputView.inputCommand());

        if (!initCommand.isStart()) {
            return;
        }

        play(chessGame);

        Map<Team, Double> teamScores = chessGame.calculateResult();

        outputView.printResult(teamScores);
    }

    private void play(ChessGame chessGame) {
        while (!chessGame.isEnd()) {
            printChessBoard(chessGame);

            Command command = Command.from(inputView.inputCommand());
            
            chessGame.progress(command);
        }
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
            String type = chessGame.getSymbolByPosition(position);

            return type;
        }

        return ".";
    }
}

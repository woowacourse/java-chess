package chess.controller;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

import chess.domain.ChessGame;
import chess.domain.Command;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.ArrayList;
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
    }

    public void run() {
        ChessGame chessGame = new ChessGame();

        outputView.printAnnounce();

        play(chessGame);

        Map<Team, Double> teamScores = chessGame.calculateResult();

        outputView.printResult(teamScores);
    }

    private void play(ChessGame chessGame) {
        while (true) {
            Command command = Command.from(inputView.inputCommand());

            if (chessGame.isEnd()) {
                break;
            }

            chessGame.progress(command);

            printChessBoard(chessGame);
        }
    }

    private void printChessBoard(ChessGame chessGame) {
        List<String> symbols = new ArrayList<>();

        Map<Position, Piece> cells = chessGame.getChessBoard().getCells();

        Collections.sort(positions);

        Set<Position> cellsKeySet = cells.keySet();

        for (Position position : positions) {
            if (cellsKeySet.contains(position)) {
                Piece piece = cells.get(position);
                String type = piece.getSymbol();

                symbols.add(type);
            } else {
                symbols.add(".");
            }
        }

        outputView.printInitChessBoard(symbols);
    }
}

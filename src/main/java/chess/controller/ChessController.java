package chess.controller;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.domain.piece.File;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.piece.Rank;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printAnnounce();
        while (inputView.inputStartCommand()) {
            start();
        }
    }

    public void start() {
        ChessBoard chessBoard = new ChessBoard(PieceFactory.blackPieces(), PieceFactory.whitePieces());

        List<String> symbols = new ArrayList<>();

        Map<Position, Piece> cells = chessBoard.getCells();

        List<Position> positions = stream(File.values())
                .flatMap(file -> stream(Rank.values())
                        .map(rank -> new Position(rank, file)))
                .collect(toList());

        Collections.reverse(positions);

        Set<Position> cellsKeySet = cells.keySet();

        for (Position position : positions) {
            if (cellsKeySet.contains(position)) {
                Piece piece = cells.get(position);
                String type = piece.getType();

                symbols.add(type);
            } else {
                symbols.add(".");
            }
        }

        outputView.printInitChessBoard(symbols);
    }
}

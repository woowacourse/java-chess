package chess.controller;

import chess.domain.Position;
import chess.domain.board.Board;
import chess.view.GameCommand;
import chess.view.InputView;
import chess.view.OutputView;
import java.awt.Point;

public class MainGame implements GameStatus {
    private final InputView inputView;
    private final Board board;

    public MainGame(InputView inputView, Board board) {
        this.inputView = inputView;
        this.board = board;
    }

    @Override
    public boolean isPlayable() {
        return true;
    }

    @Override
    public GameStatus play() {
        GameCommand gameCommand = inputView.getGameCommand();

        if (gameCommand == GameCommand.START) {
            return new RestartGame(inputView);
        }

        if (gameCommand == GameCommand.MOVE) {
            return movePiece();
        }

        if (gameCommand == GameCommand.END) {
            return new TerminateGame();
        }
        
        return this;
    }

    private GameStatus movePiece() {
        Position source = createPosition(inputView.getPosition());
        Position target = createPosition(inputView.getPosition());

        board.move(source, target);
        OutputView.printBoard(board);
        return this;
    }

    private Position createPosition(final String fileAndRank) {
        Point point = PointConverter.convert(fileAndRank);
        return new Position(point.x, point.y);
    }
}

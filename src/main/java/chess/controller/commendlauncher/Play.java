package chess.controller.commendlauncher;

import static chess.domain.board.position.Position.inputToPositions;

import chess.controller.Command;
import chess.domain.board.position.Position;
import chess.domain.game.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public final class Play extends CommendLauncher {
    ChessGame chessGame;

    public Play(ChessGame chessGame) {
        this.chessGame = chessGame;
        go(chessGame);
    }

    @Override
    protected void execute(ChessGame chessGame) {
        OutputView.printChessBoard(chessGame.getBoard());

        String input = InputView.inputCommend();
        Command command = Command.from(input);

        if (command == Command.END) {
            new End(chessGame);
            return;
        }
        if (command == Command.MOVE) {
            move(input, chessGame);
            playOrEnd(chessGame);
            return;
        }
        throw new IllegalArgumentException("end, move 만 입력할 수 있습니다.");
    }

    private void move(String input, ChessGame chessGame) {
        List<Position> positions = inputToPositions(input);
        chessGame.play(positions.get(0), positions.get(1));
    }

    private void playOrEnd(ChessGame chessGame) {
        if (chessGame.isPlaying()) {
            new Play(chessGame);
            return;
        }
        new End(chessGame);
    }
}

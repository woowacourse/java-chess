package chess.controller.command;

import chess.domain.board.position.Position;
import chess.manager.ChessGame;
import chess.view.OutputView;

public class Show extends Command {
    public Show(String line) {
        super(line);
    }

    @Override
    public Command read(final String input) {
        final Menu menu = Menu.of(input);

        if (menu.isStart()) {
            throw new IllegalArgumentException("부적절한 명령어 입력입니다.");
        }

        if (menu.isMove()) {
            return new Move(input);
        }

        if (menu.isStatus()) {
            return new Status(input);
        }

        if (menu.isShow()) {
            return new Show(input);
        }

        if (menu.isEnd()) {
            return new End(input);
        }

        throw new IllegalArgumentException("부적절한 명령어 입력입니다.");
    }

    @Override
    public void execute(final ChessGame chessGame) {
//        final List<Position> reachablePositions = chessManager.getReachablePositions(super.source());
//        OutputView.printReachableBoard(chessManager.board(), reachablePositions);

        OutputView.printBoard(chessGame.board());
    }

    @Override
    public Position target() {
        throw new IllegalArgumentException("Target parameter 가 존재하지 않습니다.");
    }
}

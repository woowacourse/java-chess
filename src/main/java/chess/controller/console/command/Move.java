package chess.controller.console.command;

import chess.domain.ChessGame;
import chess.domain.board.position.Position;
import chess.view.console.OutputView;

public class Move extends Command {
    public Move(String line) {
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

    // XXX :: ChessGame.move()에 validateTurn과 changeTurn을 넣을까

    @Override
    public void execute(final ChessGame chessGame) {
        final Position source = super.source();
        final Position target = super.target();

        chessGame.validateTurn(source);
        chessGame.move(source, target);
        chessGame.changeTurn();

        OutputView.printBoard(chessGame.board());
    }
}

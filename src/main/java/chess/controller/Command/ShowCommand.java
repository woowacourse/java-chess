package chess.controller.Command;

import chess.domain.board.position.Position;
import chess.manager.ChessManager;
import chess.view.OutputView;

import java.util.List;

public class ShowCommand extends Command{
    ShowCommand(String line) {
        super(line);
    }

    @Override
    public Command read(final String input) {
        final Menu menu = Menu.of(input);

        if(menu.isStart()){
            throw new IllegalArgumentException("부적절한 명령어 입력입니다.");
        }

        if(menu.isMove()){
            return new MoveCommand(input);
        }

        if(menu.isStatus()){
            return new StatusCommand(input);
        }

        if(menu.isShow()){
            return new ShowCommand(input);
        }

        if(menu.isEnd()){
            return new EndCommand(input);
        }

        throw new IllegalArgumentException("부적절한 명령어 입력입니다.");
    }

    @Override
    public void execute(ChessManager chessManager) {
        final List<Position> reachablePositions = chessManager.getReachablePositions(super.source());
        OutputView.printReachableBoard(chessManager.board(), reachablePositions);
    }

    @Override
    public Position target() {
        throw new IllegalArgumentException("Target parameter 가 존재하지 않습니다.");
    }
}

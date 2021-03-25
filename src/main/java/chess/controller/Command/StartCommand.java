package chess.controller.Command;

import chess.domain.board.position.Position;
import chess.manager.ChessManager;

public class StartCommand extends Command{

    public StartCommand(String line) {
        super(line);
    }

    public StartCommand(){
        super("start");
    }

    public Command read(final String input){
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
        chessManager.initNewGame();
    }

    @Override
    public Position source() {
        throw new IllegalArgumentException("Source parameter 가 존재하지 않습니다.");
    }

    @Override
    public Position target() {
        throw new IllegalArgumentException("Target parameter 가 존재하지 않습니다.");
    }
}

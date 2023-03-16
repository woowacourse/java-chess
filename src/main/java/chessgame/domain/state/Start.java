package chessgame.domain.state;

import chessgame.Command;

public class Start implements State {
    @Override
    public void click(Button button, Command command) {
        if (!(command.isMove() || command.isEnd())) {
            throw new IllegalArgumentException("move와 end명령만 가능 합니다.");
        }
        if (command.isEnd()) {
            button.setState(new End());
        }
    }
}

package chessgame.domain.state;

import chessgame.domain.Command;

public class End implements State {
    @Override
    public void click(Button button, Command command) {
        if (!command.isStart()) {
            throw new IllegalArgumentException("start만 가능 합니다.");
        }
        button.setState(new Start());
    }
}

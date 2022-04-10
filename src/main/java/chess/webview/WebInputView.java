package chess.webview;

import chess.controller.ApplicationCommand;

public class WebInputView {
    private WebInputView() {}

    public static ApplicationCommand toApplicationCommand(String command) {
        return ApplicationCommand.of(command);
    }
}

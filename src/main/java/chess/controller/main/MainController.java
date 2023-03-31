package chess.controller.main;

import chess.controller.Controller;
import chess.controller.ErrorOutput;
import chess.view.InputView;

public class MainController {

    private final CommandMapper commandMapper;
    private final InputView inputView;
    private final ErrorOutput errorOutput;
    private final InitialOutput initialOutput;

    public MainController(CommandMapper commandMapper, ErrorOutput errorOutput, InputView inputView,
            InitialOutput initialOutput) {
        this.commandMapper = commandMapper;
        this.inputView = inputView;
        this.errorOutput = errorOutput;
        this.initialOutput = initialOutput;
    }

    public void run() {
        initialOutput.printInitialMessage();
        while (true) {
            try {
                Request request = inputView.inputGameCommand();
                Controller controller = commandMapper.getController(request.getActionType());
                controller.run(request);
            } catch (Exception e) {
                errorOutput.printError(e.getMessage());
            }
        }
    }
}

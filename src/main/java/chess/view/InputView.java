package chess.view;

import chess.controller.request.Input;
import chess.controller.request.RequestType;
import chess.view.request.EndRequest;
import chess.view.request.MoveRequest;
import chess.view.request.StartRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.regex.Pattern;

public class InputView implements Input {

    private static final Scanner scanner = new Scanner(System.in);

    private final Map<Pattern, Function<String, RequestType>> requests;

    public InputView() {
        requests = Map.of(
                Pattern.compile("^start$"), StartRequest::new,
                Pattern.compile("^end$"), EndRequest::new,
                Pattern.compile("^move [a-h][1-8] [a-h][1-8]$"), MoveRequest::new
        );
    }

    @Override
    public List<String> inputGameCommand() {
        return Arrays.asList(scanner.nextLine().split(" ", -1));
    }
}

package chess.view;

import chess.controller.request.Input;
import chess.controller.request.RequestType;
import chess.view.request.EndRequest;
import chess.view.request.MoveRequest;
import chess.view.request.StartRequest;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.function.Function;
import java.util.regex.Pattern;

public class InputView implements Input {

    private static final Scanner scanner = new Scanner(System.in);
    private final Map<Pattern, Function<List<String>, RequestType>> requests;

    public InputView() {
        requests = Map.of(
                Pattern.compile("^start$"), StartRequest::new,
                Pattern.compile("^end$"), EndRequest::new,
                Pattern.compile("^move [a-h][1-8] [a-h][1-8]$"), MoveRequest::new
        );
    }

    @Override
    public RequestType inputGameCommand() {
        String input = scanner.nextLine();
        return requests.entrySet().stream()
                .filter(entry -> entry.getKey().matcher(input).matches())
                .map(Entry::getValue)
                .map(value -> value.apply(List.of(input.split(" "))))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바른 명령어가 아닙니다."));
    }
}

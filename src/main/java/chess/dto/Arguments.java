package chess.dto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import spark.Request;

public class Arguments {

    private final List<String> arguments;

    public Arguments(List<String> arguments) {
        this.arguments = arguments;
    }

    public static Arguments ofArray(String[] split, int skipCount) {
        return new Arguments(Arrays.stream(split)
            .skip(skipCount)
            .collect(Collectors.toList()));
    }

    public static Arguments ofRequest(Request request, List<String> parameters) {
        if (parameters.isEmpty()) {
            return new Arguments(parameters);
        }
        return new Arguments(parameters.stream()
            .map(request::queryParams)
            .collect(Collectors.toList())
        );
    }

    public List<String> getArguments() {
        return List.copyOf(arguments);
    }

    @Override
    public String toString() {
        return "Arguments{" +
            "arguments=" + arguments +
            '}';
    }
}

package chess.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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

    public static Arguments ofRequestBody(String requestBody, List<String> parameters) {
        if (requestBody == null || requestBody.isEmpty()) {
            return new Arguments(parameters);
        }
        JsonObject jsonObject = JsonParser.parseString(requestBody).getAsJsonObject();
        return new Arguments(parameters.stream()
            .map(parameter -> jsonObject.get(parameter).getAsString())
            .collect(Collectors.toList()));
    }

    public List<String> getArguments() {
        return List.copyOf(arguments);
    }
}

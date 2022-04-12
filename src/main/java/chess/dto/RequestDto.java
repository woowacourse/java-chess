package chess.dto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RequestDto {

    private final String title;
    private final String firstMemberName;
    private final String secondMemberName;

    private RequestDto(String title, String firstMemberName, String secondMemberName) {
        this.title = title;
        this.firstMemberName = firstMemberName;
        this.secondMemberName = secondMemberName;
    }

    public static RequestDto of(String request) {
        final List<String> inputs = Arrays.stream(request.split("\n"))
                .map(s -> s.split("=")[1])
                .collect(Collectors.toList());
        validateInputSize(inputs);
        return new RequestDto(inputs.get(0), inputs.get(1), inputs.get(2));
    }

    private static void validateInputSize(List<String> inputs) {
        if (inputs.size() != 3) {
            throw new IllegalArgumentException("입력값이 올바르지 않습니다.");
        }
    }

    public String getTitle() {
        return title;
    }

    public String getFirstMemberName() {
        return firstMemberName;
    }

    public String getSecondMemberName() {
        return secondMemberName;
    }
}

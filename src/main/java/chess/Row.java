package chess;

import java.util.Map;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toMap;

public class Row {
    private final String value;
    private static final Map<String, Row> CACHE = IntStream.rangeClosed('a', 'h')
            .boxed()
            .collect(toMap(
                    i -> String.valueOf((char) i.intValue()),
                    i -> new Row(String.valueOf((char) i.intValue()))
            ));

    private Row(String value) {
        this.value = value;
    }
    public static Row valueOf(String value) {
        validate(value);
        return CACHE.get(value);
    }
    private static void validate(String value){
        validateAlphabet(value);
        validateSize(value);
    }

    private static void validateAlphabet(String value) {
        //TODO : charAt 말고 다른 방법 생각해보기
        char row = value.charAt(0);
        if(row<'a'||row>'h'){
            throw new IllegalArgumentException("a~h까지 가능합니다.");
        }
    }

    private static void validateSize(String value){
        if(value.length()!=1){
            throw new IllegalArgumentException("a~h까지 가능합니다.");
        }
    }
}

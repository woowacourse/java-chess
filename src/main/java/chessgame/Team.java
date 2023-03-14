package chessgame;

import java.util.function.Function;

public enum Team {
    BLACK(String::toUpperCase),
    WHITE(String::toLowerCase);

    private final Function<String,String> name;

    Team(Function<String,String> name) {
        this.name = name;
    }

    public String calculate(String convertedName){
        return name.apply(convertedName);
    }
}

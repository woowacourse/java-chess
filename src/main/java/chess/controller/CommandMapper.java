package chess.controller;

import java.util.HashMap;
import java.util.Map;

public class CommandMapper<K, V> {
    private final Map<K, V> commandMapper = new HashMap<>();

    public CommandMapper() {
        this(Map.of());
    }

    public CommandMapper(final Map<K, V> commandMapper) {
        this.commandMapper.putAll(commandMapper);
    }

    public V getValue(final K command) {
        return commandMapper.get(command);
    }
}

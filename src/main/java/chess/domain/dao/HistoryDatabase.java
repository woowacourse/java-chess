package chess.domain.dao;

import chess.domain.dto.CommandDto;

import java.util.ArrayList;
import java.util.List;

public class HistoryDatabase {
    private List<CommandDto> commands;

    public HistoryDatabase() {
        commands = new ArrayList<>();
    }

    public void insert(CommandDto commandDto) {
        commands.add(commandDto);
    }

    public void clear() {
        commands.clear();
    }

    public void delete(CommandDto commandDto) {
        commands.remove(commandDto);
    }

    public List<CommandDto> commands() {
        return commands;
    }

    public boolean contains(CommandDto commandDto) {
        return commands.contains(commandDto);
    }

    public String idOf(CommandDto commandDto){
        return String.valueOf(commands.indexOf(commandDto) + 1);
    }

    public boolean isEmpty() {
        return commands.isEmpty();
    }

    public int lastIndex() {
        return commands.size();
    }
}

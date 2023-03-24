package database;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.command.Command;
import chess.command.MoveCommand;
import java.sql.SQLException;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class MoveDAOTest {
    
    private final MoveDAO moveDAO = new MoveDAO("moves_test");
    
    @Test
    public void connection() {
        try (final var connection = this.moveDAO.getConnection()) {
            assertThat(connection).isNotNull();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Test
    public void addMove() {
        this.moveDAO.resetMoves();
        MoveCommand moveCommand = new MoveCommand(List.of("a2", "a3"));
        this.moveDAO.addMove(moveCommand);
        MoveCommand moveCommand2 = new MoveCommand(List.of("a3", "a4"));
        this.moveDAO.addMove(moveCommand2);
    }
    
    @Test
    public void fetchCommands() {
        List<Command> commands = this.moveDAO.fetchCommands();
        Assertions.assertThat(commands).isNotNull();
        Assertions.assertThat(commands.size()).isEqualTo(2);
    }
}
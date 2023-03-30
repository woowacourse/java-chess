package dao;

import domain.game.Game;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface QueryGameSupplier {
    Game get(PreparedStatement preparedStatement) throws SQLException;
}

package chess.dao;

import chess.domain.PieceForm;
import chess.domain.Position;
import chess.domain.TeamColor;
import chess.domain.converter.StringPositionConverter;
import chess.domain.piece.Piece;
import java.util.List;
import java.util.stream.Collectors;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

public class ChessDAOImpl implements ChessDAO {

    private final Sql2o sql2o;
    private final StringPositionConverter converter;

    public ChessDAOImpl() {
        sql2o = new Sql2o("jdbc:postgresql://localhost:5432/chess", "nabom", "1234");
        converter = new StringPositionConverter();
    }

    @Override
    public Long saveGame(List<Piece> pieces, Long gameId) {
        try (Connection connection = sql2o.beginTransaction()) {
            connection.createQuery("delete from game where gameid = :gameId")
                .addParameter("gameId", gameId)
                .executeUpdate();

            Query query = connection.createQuery(
                "insert into game(gameid, name, color, position) values(:gameid, :name, :color,:position)");
            pieces.forEach(piece -> {
                query.addParameter("gameid", gameId)
                    .addParameter("name", piece.name())
                    .addParameter("color", piece.color())
                    .addParameter("position", piece.currentPosition().columnAndRow())
                    .addToBatch();
            });
            query.executeBatch();
            connection.commit();
        }
        return null;
    }

    @Override
    public List<Piece> loadGame(Long gameId) {
        try (Connection con = sql2o.open()) {
            List<ChessDbDTO> results = con.createQuery("select * from game where gameid=:gameId")
                .addParameter("gameId", gameId)
                .executeAndFetch(ChessDbDTO.class);
            return results.stream()
                .map(result -> {
                    Position position = converter.convert(result.getPosition());
                    TeamColor teamColor = TeamColor.valueOf(result.getColor());
                    return PieceForm.create(result.getName(), teamColor, position);
                }).collect(Collectors.toList());
        }
    }

    @Override
    public void removeGame(Long gameId) {
        try (Connection con = sql2o.open()) {
            con.createQuery("delete from game where gameid=:gameId")
                .addParameter("gameId", gameId)
                .executeUpdate();
        }
    }
}

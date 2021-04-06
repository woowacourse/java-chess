package chess.dao;

import chess.domain.ChessGame;
import chess.domain.ChessGameImpl;
import chess.domain.PieceForm;
import chess.domain.Pieces;
import chess.domain.Position;
import chess.domain.TeamColor;
import chess.domain.converter.StringPositionConverter;
import chess.domain.piece.Piece;
import java.util.List;
import java.util.Optional;
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
    public Long saveGame(ChessGame chessGame, Long gameId) {
        try (Connection connection = sql2o.beginTransaction()) {
            deleteAllByGameId(gameId, connection);
            connection
                .createQuery("insert into current_color(game_id, color) values(:gameId, :color)")
                .addParameter("gameId", gameId)
                .addParameter("color", chessGame.currentColor())
                .executeUpdate();

            pieceBulkUpdate(chessGame, gameId, connection);
            connection.commit();
            return gameId;
        }
    }

    private void pieceBulkUpdate(ChessGame chessGame, Long gameId, Connection connection) {
        try (Query query = connection.createQuery(
            "insert into game(gameid, name, color, position) values(:gameid, :name, :color,:position)")) {
            chessGame.pieces().asList().forEach(piece -> {
                query.addParameter("gameid", gameId)
                    .addParameter("name", piece.name())
                    .addParameter("color", piece.color())
                    .addParameter("position", piece.currentPosition().columnAndRow())
                    .addToBatch();
            });
            query.executeBatch();
        }
    }

    @Override
    public Optional<ChessGame> loadGame(Long gameId) {
        try (Connection con = sql2o.open()) {
            List<ChessDTO> results = con.createQuery("select * from game where gameid=:gameId")
                .addParameter("gameId", gameId)
                .executeAndFetch(ChessDTO.class);

            List<String> color =
                con.createQuery("select color from current_color where game_id = :gameId")
                    .addParameter("gameId", gameId)
                    .executeAndFetch(String.class);

            if (results.isEmpty() || color.isEmpty()) {
                return Optional.empty();
            }

            List<Piece> pieces = results.stream()
                .map(result -> {
                    Position position = converter.convert(result.getPosition());
                    TeamColor teamColor = TeamColor.valueOf(result.getColor());
                    return PieceForm.create(result.getName(), teamColor, position);
                }).collect(Collectors.toList());

            return Optional
                .of(ChessGameImpl.from(Pieces.from(pieces), TeamColor.teamColor(color.get(0))));
        }
    }

    @Override
    public void removeGame(Long gameId) {
        try (Connection con = sql2o.open()) {
            deleteAllByGameId(gameId, con);
        }
    }

    private void deleteAllByGameId(Long gameId, Connection connection) {
        connection.createQuery("delete from game where gameid = :gameId")
            .addParameter("gameId", gameId)
            .executeUpdate();

        connection.createQuery("delete from current_color where game_id = :gameId")
            .addParameter("gameId", gameId)
            .executeUpdate();
    }
}

package controller;

import com.google.gson.JsonObject;
import dao.PiecesDAO;
import dao.TurnDAO;
import domain.commend.State;
import domain.pieces.DBPiecesFactory;
import domain.pieces.Pieces;
import domain.pieces.PiecesFactory;
import domain.team.Team;
import java.sql.SQLException;
import java.util.Map;

public class WebController {
    private final static String BLANK = " ";

    private State state;

    public WebController() {
    }

    public Map<String, Object> start() throws SQLException {
        Pieces pieces = Pieces.of(PiecesFactory.create());
        state = State.of(pieces);
        state.start();

        PiecesDAO piecesDAO = new PiecesDAO();
        TurnDAO turnDAO = new TurnDAO();

        turnDAO.start();

        piecesDAO.addPieces(state.getPieces());

        return piecesDAO.readPieces();
    }

    public Map<String, Object> read() throws SQLException {
        PiecesDAO piecesDAO = new PiecesDAO();
        if (state == null) {
            return getWhenStateIsNull(piecesDAO);
        }
        return piecesDAO.readPieces();
    }

    private Map<String, Object> getWhenStateIsNull(PiecesDAO piecesDAO) throws SQLException {
        if (piecesDAO.isSave()) {
            Pieces pieces = Pieces.of(DBPiecesFactory.create(piecesDAO.readPieces()));
            state = State.of(pieces);
            state.start();
            return piecesDAO.readPieces();
        }
        return start();
    }

    public boolean isSave() throws SQLException {
        PiecesDAO piecesDAO = new PiecesDAO();
        return piecesDAO.isSave();
    }

    public void move(String from, String to) throws SQLException {
        TurnDAO turnDAO = new TurnDAO();
        PiecesDAO piecesDAO = new PiecesDAO();
        state.move(turnDAO.getTurn(),"move" + " " + from + " " + to);
        turnDAO.changeTurn();
        piecesDAO.updatePieces(state.getPieces());
    }

    public JsonObject status() {
        JsonObject object = new JsonObject();
        object.addProperty("team", state.getPresentTurn().toString());
        object.addProperty("status", state.status());
        return object;
    }

    public void delete() throws SQLException {
        PiecesDAO piecesDAO = new PiecesDAO();
        TurnDAO turnDAO = new TurnDAO();
        piecesDAO.deleteAll();
        turnDAO.delete();
    }

    public boolean isFinished() {
        return state != null && state.isFinished();
    }

    public String getWhoWinner() {
        return Team.opposite(state.getPresentTurn()).toString();
    }
}

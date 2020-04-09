package service;

import dao.PiecesDAO;
import dao.TurnDAO;
import domain.commend.State;
import domain.pieces.Pieces;
import domain.pieces.PiecesFactory;
import domain.team.Team;
import dto.ScoreDTO;
import java.sql.SQLException;
import java.util.Map;

public class ChessService {

    private State state;
    private PiecesDAO piecesDAO;
    private TurnDAO turnDAO;

    public ChessService() {
        piecesDAO = PiecesDAO.getInstance();
        turnDAO = TurnDAO.getInstance();
    }

    public Map<String, Object> start() throws SQLException {
        Pieces pieces = Pieces.of(PiecesFactory.create());
        state = State.of(pieces);

        state.start();
        turnDAO.start();
        piecesDAO.addPieces(state.getPieces());

        return piecesDAO.readPieces();
    }

    public Map<String, Object> read() throws SQLException {
        if (state == null) {
            return getWhenStateIsNull(piecesDAO);
        }
        return piecesDAO.readPieces();
    }

    private Map<String, Object> getWhenStateIsNull(PiecesDAO piecesDAO) throws SQLException {
        if (piecesDAO.isSave()) {
            Pieces pieces = Pieces.of(piecesDAO);
            state = State.of(pieces);
            state.start();
            return piecesDAO.readPieces();
        }
        return start();
    }

    public void move(String from, String to) throws SQLException {
        state.move(turnDAO.getTurn(),"move" + " " + from + " " + to);
        turnDAO.changeTurn(state.getPresentTurn());
        piecesDAO.updatePieces(state.getPieces());
    }

    public ScoreDTO status() {
        return new ScoreDTO(state.getPresentTurn().toString(), state.status());
    }

    public void delete() throws SQLException {
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

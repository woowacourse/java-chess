package chess.domain.gamestate;

import chess.dao.JdbcTemplatePieceDAO;
import chess.dao.PieceDAO;
import chess.domain.Team;
import chess.domain.board.Board;
import chess.domain.piece.Placeable;
import chess.domain.position.Position;
import chess.dto.PieceDTO;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class Running extends Started {
    private static final String IMPOSSIBLE_TO_CALCULATE_SCORE_MESSAGE = "게임 진행 중에는 점수를 계산할 수 없습니다.";

    private Team teamInTurn;

    public Running() {
        this.teamInTurn = Team.WHITE;
    }

    @Override
    public GameState move(String keyFromPosition, String keyToPosition) {
        board.move(keyFromPosition, keyToPosition, teamInTurn);


        try {
            PieceDAO pieceDAO = JdbcTemplatePieceDAO.getInstance();

            Map<Position, Placeable> positionToPiece = board.getPositionToPiece();
            for (Position position : positionToPiece.keySet()) {
                Placeable piece = positionToPiece.get(position);

                PieceDTO pieceDTO = new PieceDTO();
                pieceDTO.setPosition(position);
                pieceDTO.setPieceType(piece.getPieceType());
                pieceDTO.setTeam(piece.getTeam());

                pieceDAO.updatePiece(pieceDTO);
            }
        } catch (SQLException e) {
            System.err.println("SQL 오류 : " + e.getErrorCode());
        }

        if (board.checkIfOppositeKingIsDead(teamInTurn)) {
            return finish();
        }

        teamInTurn = teamInTurn.opposite();

        return this;
    }

    @Override
    public List<List<String>> getBoardForPrint() {
        return board.getBoardForPrinting();
    }

    @Override
    public GameState finish() {
        return new Finished(board);
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public double getWhiteTeamScore() {
        throw new UnsupportedOperationException(IMPOSSIBLE_TO_CALCULATE_SCORE_MESSAGE);
    }

    @Override
    public double getBlackTeamScore() {
        throw new UnsupportedOperationException(IMPOSSIBLE_TO_CALCULATE_SCORE_MESSAGE);

    }

    @Override
    public boolean isRunning() {
        return true;
    }
}

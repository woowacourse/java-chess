package chess.domain;

import static chess.domain.piece.Team.BLACK;
import static chess.domain.piece.Team.WHITE;

import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import chess.domain.state.Ready;
import chess.domain.state.State;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ChessGame {

    private final ChessBoard chessBoard;
    private State state;

    public ChessGame() {
        state = new Ready();
        chessBoard = new ChessBoard();
    }

    public boolean isExistKing() {
        return chessBoard.isExistKing();
    }

    public String getWinTeam() {
        Map<Team, Double> teamScores = calculateResult();
        return chessBoard.findWinTeam(teamScores);
    }

    public void progress(Command command) {
        state = state.execute(command, chessBoard);
    }

    public Set<Position> getPiecePositions() {
        Map<Position, Piece> cells = chessBoard.getCells();

        return Collections.unmodifiableSet(cells.keySet());
    }

    public boolean isEnd() {
        return state.isEnd();
    }

    public String getSymbolByPosition(Position position) {
        Piece piece = chessBoard.getPieceByPosition(position);

        return piece.getSymbol();
    }

    public Map<Team, Double> calculateResult() {
        Double whiteScore = calculateScore(WHITE);
        Double blackScore = calculateScore(BLACK);

        Map<Team, Double> result = new HashMap<>();
        result.put(WHITE, whiteScore);
        result.put(BLACK, blackScore);

        return result;
    }

    private double calculateScore(Team team) {
        return chessBoard.calculateByTeam(team);
    }
}

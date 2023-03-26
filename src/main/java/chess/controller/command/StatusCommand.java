package chess.controller.command;

import chess.domain.ChessGame;
import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.score.Score;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static chess.controller.command.CommandType.INVALID_COMMAND_MESSAGE;

public class StatusCommand extends Command{

    private final Map<Team, Double> score;

    protected StatusCommand(ChessGame chessGame) {
        super(chessGame, CommandType.STATUS);
        score = new HashMap<>();
        initScore();
    }

    private Map<Team, Double> initScore() {
        score.put(Team.WHITE, 0.0);
        score.put(Team.BLACK, 0.0);
        return score;
    }

    @Override
    public Command execute(List<String> input) {
        CommandType inputCommandType = CommandType.from(input);
        if (inputCommandType == CommandType.STATUS) {
            return executeStatus();
        }
        if (inputCommandType == CommandType.END) {
            return executeEnd();
        }
        throw new IllegalArgumentException(INVALID_COMMAND_MESSAGE);
    }

    private StatusCommand executeStatus() {
        for (Position position : getChessGameBoards().keySet()) {
            calculateScore(position);
        }
        System.out.println(score);
        return this;
    }

    private void calculateScore(Position position) {
        Piece piece = chessGame.findPiece(position);
        Team team = piece.getTeam();
        if (team == Team.EMPTY) {
            return;
        }
        if (piece.getClass().equals(Pawn.class) && chessGame.pawnCountByColumnAndTeam(position, team) > 1) {
            score.put(team, score.get(team) + 0.5);
            return;
        }
        score.put(team, score.get(team) + Score.findScoreBy(piece));
    }

    private EndCommand executeEnd() {
        return new EndCommand(new ChessGame(new Board(getChessGameBoards())));
    }
}

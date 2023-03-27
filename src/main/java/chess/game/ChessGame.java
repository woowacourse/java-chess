package chess.game;

import static java.util.stream.Collectors.toList;

import chess.dao.LoadLogic;
import chess.dao.SaveLogic;
import chess.domain.Board;
import chess.domain.BoardFactory;
import chess.domain.Position;
import chess.domain.Role;
import chess.domain.Team;
import chess.domain.piece.Piece;
import chess.dto.SquareResponse;
import chess.game.state.GameState;
import chess.game.state.end.EndState;
import chess.game.state.end.NoneWinState;
import chess.game.state.running.CheckedState;
import chess.game.state.waiting.WaitingState;
import java.util.List;
import java.util.Map;

public class ChessGame {
    private static final String INVALID_TURN_EXCEPTION_MESSAGE = "[ERROR] 해당 팀의 턴이 아닙니다.";
    private static final String EMPTY_PIECE_EXCEPTION_MESSAGE = "[ERROR] 빈 칸은 움직일 수 없습니다.";
    private static final double PAWN_COUNT_MULTIPLIER = 0.5;

    private GameId gameId;
    private Board board;
    private GameState gameState;

    public ChessGame() {
        this.gameState = WaitingState.STATE;
    }

    public void start(GameId gameId, TurnStrategy turnStrategy) {
        gameState.startGame(() -> {
            this.gameId = gameId;
            this.board = new Board(BoardFactory.create());
            this.gameState = turnStrategy.create();
        });
    }

    public void end() {
        this.gameState = NoneWinState.STATE;
    }

    public boolean isEnd() {
        return gameState.isEnd();
    }

    public void movePiece(Position source, Position target) {
        gameState.movePiece(() -> {
            validate(source);
            board.move(source, target);
        });
    }

    private void validate(Position source) {
        Team pieceTeam = board.getPieceTeam(source);
        validateEmptySquare(pieceTeam);
        validateTurn(pieceTeam);
    }

    private void validateEmptySquare(Team team) {
        if (team == Team.NONE) {
            throw new IllegalArgumentException(EMPTY_PIECE_EXCEPTION_MESSAGE);
        }
    }

    private void validateTurn(Team team) {
        if (team != gameState.getTurn()) {
            throw new IllegalStateException(INVALID_TURN_EXCEPTION_MESSAGE);
        }
    }

    public List<SquareResponse> getBoard() {
        return gameState.getBoard(() -> board.getBoard().entrySet().stream()
                .map(entry -> SquareResponse.of(entry.getKey(), entry.getValue()))
                .collect(toList()));
    }

    public double getTeamScore(Team team) {
        return gameState.getTeamScore(() -> calculateScore(team));
    }

    private double calculateScore(Team team) {
        double pawnScore = getPawnScore(team);
        double withoutPawnScore = getWithoutPawnScore(team);
        return pawnScore + withoutPawnScore;
    }

    private double getPawnScore(Team team) {
        Map<Integer, Long> pawnCountByX = board.getPawnCountByX(team);
        return pawnCountByX.values().stream()
                .mapToDouble(this::calculatePawnScore)
                .sum();
    }

    private double calculatePawnScore(long pawnCount) {
        if (pawnCount == 1) {
            return Role.PAWN.getScore();
        }
        return pawnCount * Role.PAWN.getScore() * PAWN_COUNT_MULTIPLIER;
    }

    private double getWithoutPawnScore(Team team) {
        return board.getTeamSquares(team).values().stream()
                .filter(piece -> !piece.isRoleOf(Role.PAWN))
                .mapToDouble(Piece::getScore)
                .sum();
    }

    public Team getTurn() {
        return gameState.getTurn();
    }

    public void changeTurn() {
        this.gameState = gameState.changeTurn();
        if (board.isChecked(gameState.getTurn())) {
            this.gameState = CheckedState.createCheckedState(gameState.getTurn());
        }
    }

    public Team getWinner() {
        return gameState.getWinner();
    }

    public void checkCheckmate() {
        Team turn = gameState.getTurn();
        if (isChecked() && board.isChecked(turn)) {
            this.gameState = EndState.createWinState(turn.opposite());
        }
    }

    public boolean isChecked() {
        return gameState.isChecked();
    }

    public void save(SaveLogic saveLogic) {
        gameState.saveGame(() -> saveLogic.save(gameId, board, gameState));
    }

    public void load(GameId gameId, LoadLogic<Board> boardLoadLogic, LoadLogic<GameState> gameStateLoadLogic) {
        this.gameState.loadGame(() -> {
            this.gameId = gameId;
            this.board = boardLoadLogic.load(gameId);
            this.gameState = gameStateLoadLogic.load(gameId);
        });
    }

    public void leave() {
        this.gameState.leaveGame(() -> {
            this.board = null;
            this.gameState = WaitingState.STATE;
        });
    }
}

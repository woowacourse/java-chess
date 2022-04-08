package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.Position;
import chess.domain.move.MoveStrategy;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessGame {

    public static final int ONE_DEAD_KING = 1;
    public static final int FIRST_INDEX_OF_ALIVE_KINGS = 0;

    private final String name;
    private final Board board;
    private final GameSwitch gameSwitch;
    private final Turn turn;

    public ChessGame(final String name, final Board board, final GameSwitch gameSwitch, final Turn turn) {
        this.name = name;
        this.board = board;
        this.gameSwitch = gameSwitch;
        this.turn = turn;
    }

    public static ChessGame createBasic(final String name) {
        return new ChessGame(
                name,
                BoardFactory.createInitChessBoard(),
                new GameSwitch(true),
                new Turn(Team.WHITE)
        );
    }

    public void move(final char sourceColumn, final int sourceRow, final char targetColumn, final int targetRow) {
        validateGameSwitch();
        movePiece(Position.of(sourceColumn, sourceRow), Position.of(targetColumn, targetRow));
        turnOffWhenKingDie();
        turn.passTurn();
    }

    private void validateGameSwitch() {
        if (!isOn()) {
            throw new IllegalStateException("[ERROR] 게임이 종료되어 기물을 이동시킬 수 없습니다.");
        }
    }

    private void movePiece(final Position source, final Position target) {
        Piece sourcePiece = board.getPiece(source);
        validateBlank(sourcePiece);
        validateTurn(turn, sourcePiece);
        validateMove(source, target, sourcePiece.getMoveStrategy());
        board.movePiece(source, target);
    }

    private void validateBlank(Piece sourcePiece) {
        if (sourcePiece.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 체스 기물이 아닌 빈칸을 선택하였습니다.");
        }
    }

    private void validateTurn(final Turn turn, final Piece sourcePiece) {
        if (!turn.isRightTurn(sourcePiece.getTeam())) {
            throw new IllegalStateException("[ERROR] 당신의 차례가 아닙니다.");
        }
    }

    private void validateMove(final Position source, final Position target, final MoveStrategy moveStrategy) {
        if (!moveStrategy.isMovable(board, source, target)) {
            throw new IllegalStateException("[ERROR] 기물을 이동할 수 없습니다.");
        }
    }

    private void turnOffWhenKingDie() {
        if (searchTeamOfDeadKingWhenSingleKingAlive() != Team.NONE) {
            turnOff();
        }
    }

    private Team searchTeamOfDeadKingWhenSingleKingAlive() {
        List<Piece> aliveKings = board.getBoard().values().stream()
                .filter(Piece::isKing)
                .collect(Collectors.toList());
        if (aliveKings.size() == ONE_DEAD_KING) {
            return aliveKings.get(FIRST_INDEX_OF_ALIVE_KINGS).getTeam().oppositeTeam();
        }
        return Team.NONE;
    }

    public void turnOff() {
        gameSwitch.turnOff();
    }

    public boolean isOn() {
        return gameSwitch.isOn();
    }

    public Result generateResult() {
        return new Result(getCurrentBoard(), searchTeamOfDeadKingWhenSingleKingAlive());
    }

    public String getName() {
        return name;
    }

    public Map<Position, Piece> getCurrentBoard() {
        return board.getBoard();
    }

    public Turn getTurn() {
        return turn;
    }
}

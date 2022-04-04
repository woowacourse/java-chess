package chess.domain.game;

import chess.db.entity.FullGameEntity;
import chess.db.entity.GameEntity;
import chess.db.entity.PieceEntity;
import chess.domain.board.Board;
import chess.domain.board.piece.Piece;
import chess.domain.board.position.Position;
import chess.dto.response.board.ConsoleBoardViewDto;
import chess.dto.response.board.WebBoardViewDto;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class Started implements Game {

    protected final Board board;
    private final GameState state;

    protected Started(Board board, GameState state) {
        this.board = board;
        this.state = state;
    }

    public static Game ofEntity(GameState state, List<PieceEntity> pieces) {
        Board board = Board.of(pieces);
        if (state == GameState.OVER) {
            return new GameOver(board);
        }
        return Running.of(state, board);
    }

    @Override
    public final Game init() {
        return new NewGame().init();
    }

    @Override
    public final GameState getState() {
        return state;
    }

    public FullGameEntity toEntityOf(int id) {
        GameEntity gameEntity = new GameEntity(id, state);
        return new FullGameEntity(gameEntity, toPieceEntities());
    }

    private List<PieceEntity> toPieceEntities() {
        Map<Position, Piece> boardMap = board.toMap();
        return boardMap.keySet()
                .stream()
                .map(position -> toPieceEntity(position, boardMap))
                .collect(Collectors.toUnmodifiableList());
    }

    private PieceEntity toPieceEntity(Position position, Map<Position, Piece> boardMap) {
        Piece piece = boardMap.get(position);
        return piece.toEntityAt(position);
    }

    @Override
    public final ConsoleBoardViewDto toConsoleView() {
        return new ConsoleBoardViewDto(board);
    }

    @Override
    public WebBoardViewDto toBoardWebView() {
        return new WebBoardViewDto(board);
    }
}

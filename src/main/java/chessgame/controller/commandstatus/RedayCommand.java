package chessgame.controller.commandstatus;

import chessgame.domain.chessgame.BoardInitialImage;
import chessgame.domain.chessgame.Camp;
import chessgame.domain.chessgame.ChessGame;
import chessgame.domain.coordinate.Coordinate;
import chessgame.domain.piece.Piece;
import chessgame.dto.GameRoomDto;
import chessgame.service.ChessGameService;
import chessgame.view.OutputView;

import java.util.List;
import java.util.Map;

public class RedayCommand implements CommandStatus {

    private static final int ROOM_ID_INDEX = 1;
    private static final String NEW_ROOM = "new";

    private final ChessGameService chessGameService;
    private final OutputView outputView;

    public RedayCommand(final ChessGameService chessGameService, final OutputView outputView) {
        this.chessGameService = chessGameService;
        this.outputView = outputView;
        printStartMessage();
    }

    private void printStartMessage() {
        List<GameRoomDto> gameRoomDtos = chessGameService.findAllGameRoom();
        outputView.printGameStartMessage(gameRoomDtos);
    }

    @Override
    public boolean canContinue() {
        return true;
    }

    @Override
    public CommandStatus start(final List<String> commands) {
        ChessGame chessGame = makeGameRoom(commands);
        return new PlayCommand(chessGame, chessGameService, outputView);
    }

    private ChessGame makeGameRoom(final List<String> commands) {
        String roomId = commands.get(ROOM_ID_INDEX);
        if (roomId.equals(NEW_ROOM)) {
            roomId = makeNewGameRoom();
        }
        return makeAlreadyExistGameRoom(roomId);
    }

    private String makeNewGameRoom() {
        chessGameService.addNewGame(BoardInitialImage.generate(), Camp.WHITE);
        GameRoomDto gameRoomDto = chessGameService.findLeastPieces();
        return String.valueOf(gameRoomDto.getRoomId());
    }

    private ChessGame makeAlreadyExistGameRoom(final String roomId) {
        GameRoomDto gameRoomDto = chessGameService.findGameRoomById(Long.parseLong(roomId));
        Map<Coordinate, Piece> board = chessGameService.findPiecesByRoomId(gameRoomDto.getRoomId());
        return new ChessGame(board, gameRoomDto);
    }

    @Override
    public CommandStatus move(final List<String> commands) {
        throw new IllegalArgumentException("[ERROR] 아직 게임을 시작하지 않았습니다.");
    }

    @Override
    public CommandStatus status() {
        throw new IllegalArgumentException("[ERROR] 아직 게임을 시작하지 않았습니다.");
    }

    @Override
    public CommandStatus end() {
        return new EndCommand();
    }
}

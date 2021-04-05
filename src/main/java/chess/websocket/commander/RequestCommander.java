package chess.websocket.commander;

import chess.controller.ChessController;
import chess.controller.dto.PieceDTO;
import chess.controller.dto.RoundStatusDTO;
import chess.dao.ChessDAOImpl;
import chess.domain.TeamColor;
import chess.service.ChessServiceImpl;
import chess.websocket.ResponseForm;
import chess.websocket.ResponseForm.Form;
import chess.websocket.exception.FullRoomException;
import chess.websocket.util.ChatSender;
import chess.websocket.util.ResourceSender;
import java.util.List;
import java.util.Optional;
import org.eclipse.jetty.websocket.api.Session;

public class RequestCommander {

    private ChessRoom chessRoom = new ChessRoom();
    private ChatSender chatSender = new ChatSender();
    ChessController chessController = new ChessController(new ChessServiceImpl(new ChessDAOImpl()));

    public void enterRoom(String[] contents, Session player) {
        try {
            String roomId = contents[2];
            TeamColor teamColor = chessRoom.enter(Long.parseLong(roomId), player);
            ResponseForm<String> response = new ResponseForm<>(Form.COLOR, teamColor.name());
            ResourceSender.send(player, response);
        } catch (FullRoomException e) {
            ResponseForm<String> response = new ResponseForm<>(Form.ERROR, e.getMessage());
            ResourceSender.send(player, response);
        }
    }

    public void leaveRoom(Session session) {
        chessRoom.remove(session);
    }

    public void initialPieces(String[] contents, Session player) {
        Long gameId = chessRoom.keyBySession(player);
        Optional<Session> otherPlayer = chessRoom.otherPlayer(player);

        List<PieceDTO> pieces = chessController.startGame(gameId);
        ResponseForm<List<PieceDTO>> responseForm = new ResponseForm<>(Form.PIECES, pieces);

        ResourceSender.send(player, responseForm);
        otherPlayer.ifPresent(pl -> ResourceSender.send(pl, responseForm));

        roundStatus(contents, player);
    }

    public void roundStatus(String[] contents, Session player) {
        Long gameId = chessRoom.keyBySession(player);
        Optional<Session> otherPlayer = chessRoom.otherPlayer(player);

        RoundStatusDTO roundStatusDTO = chessController.roundStatus(gameId);
        ResponseForm<RoundStatusDTO> roundStatus =
            new ResponseForm<>(Form.STATUS, roundStatusDTO);

        ResourceSender.send(player, roundStatus);
        otherPlayer.ifPresent(pl -> ResourceSender.send(pl, roundStatus));
    }

    public void move(String[] contents, Session player) {
        Long gameId = chessRoom.keyBySession(player);
        Optional<Session> otherPlayer = chessRoom.otherPlayer(player);

        String currentPosition = contents[2];
        String targetPosition = contents[3];
        chessController.move(gameId, currentPosition, targetPosition);

        ResponseForm<PositionDto> positions =
            new ResponseForm<>(Form.MOVE, new PositionDto(currentPosition, targetPosition));

        otherPlayer.ifPresent(pl -> ResourceSender.send(pl, positions));
        roundStatus(contents, player);
    }


    public void sendMessage(Session user, String message) {
        chatSender.sendMessage(user, "나", message);
        Optional<Session> otherPlayer = chessRoom.otherPlayer(user);
        otherPlayer.ifPresent(pl -> chatSender.sendMessage(pl, "상대방", message));
    }
}

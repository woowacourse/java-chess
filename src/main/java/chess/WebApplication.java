package chess;

import chess.controller.WebController;
import chess.dao.MemberDao;
import chess.repository.DatabaseMemberRepository;
import chess.repository.MemoryGameRepository;
import chess.repository.MemoryMemberRepository;

public class WebApplication {
    public static void main(String[] args) {
        WebController controller = new WebController(
                new MemoryGameRepository(),
                new DatabaseMemberRepository(new MemberDao())
        );
        controller.run();
    }
}

package chess.resolver;

import chess.controller.ChessController;
import chess.view.CommandDto;
import java.util.ArrayList;
import java.util.List;

public class CommandResolverMapper {

    private static final List<Resolver> RESOLVERS;
    private static final String RESOLVER_MAPPING_ERROR = "요청을 처리할 수 없습니다.";

    static {
        final List<Resolver> list = new ArrayList<>();
        list.add(new Start());
        list.add(new Move());
        list.add(new Status());
        list.add(new End());
        RESOLVERS = list;
    }

    public static void resolve(final CommandDto dto, final ChessController controller) {
        final Resolver resolver = RESOLVERS.stream()
                .filter(it -> it.canResolve(dto.getCommand()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(RESOLVER_MAPPING_ERROR));
        resolver.execute(dto, controller);
    }
}

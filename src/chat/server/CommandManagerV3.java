package chat.server;

import chat.server.command.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CommandManagerV3 implements CommandManager{

    private static final String DELIMITER = "\\|";
    private final Map<String, Command> commands = new HashMap<>();

    // 메인 스레드에서 생성 이후 값 변경이 없음 -> 동시성 문제가 발생하지 않음
    public CommandManagerV3(SessionManager sessionManager) {
        commands.put("/join", new JoinCommand(sessionManager));
        commands.put("/message", new MessageCommand(sessionManager));
        commands.put("/change", new ChangeCommand(sessionManager));
        commands.put("/users", new UsersCommand(sessionManager));
        commands.put("/exit", new ExitCommand());
    }

    @Override
    public void execute(String totalMessage, Session session) throws IOException {
        String[] args = totalMessage.split(DELIMITER);
        String key = args[0];

        Command command = commands.get(key);

        if(command == null) {
            session.send("처리할 수 없는 명령어 입니다. : " + key);
            return;
        }

        command.execute(args, session);
    }
}

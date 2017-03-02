package mitko.data.contracts;

public interface IUserHttpData {
    Boolean register(String username, String password, String phoneNumner);

    String login(String username, String password);
}

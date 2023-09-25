import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepository {
    private static Map<String, String> users = Map.of(
            "Arian", "password",
            "Loyd", "qwerty"
    );




    public static Map<String, String> getUsers() {
        return users;
    }

    public static void setUsers(Map<String, String> users) {
        UserRepository.users = users;
    }
}

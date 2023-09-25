import java.util.ArrayList;
import java.util.List;

public class CatRepo {

    private static ArrayList<String> catList = new ArrayList<>();

    public static void addCat(Cat cat) {
        catList.add(cat.getName());
    }

    public static List<String> getCats(){

        return catList;
    }
}

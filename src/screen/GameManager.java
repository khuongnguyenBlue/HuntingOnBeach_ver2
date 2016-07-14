package screen;

import java.util.Stack;

/**
 * Created by Laptop88 on 7/14/2016.
 */
public class GameManager {
    private static GameManager sharePointer = new GameManager();
    private Stack<Screen> stackScreen;
    public static GameManager getInstance(){
        return sharePointer;
    }
    public Stack<Screen> getStackScreen(){
        return stackScreen;
    }
}

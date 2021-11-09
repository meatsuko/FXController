package meatsuko.javafx;

import javafx.stage.Stage;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class WindowManager
{
    private static Stage _windowStage = null;
    public static void deployStage(Stage primaryStage)
    {
        _windowStage = primaryStage;
    }


    private static HashMap<String, WindowAbstraction> _windowList = new HashMap<>();

    public static WindowAbstraction resolve(Class windowClass)
    {
        try
        {


            if (_windowList.containsKey(windowClass.getName())) return _windowList.get(windowClass.getName());

            Constructor constructor = windowClass.getDeclaredConstructor(new Class[] {Stage.class});
            WindowAbstraction classInstance = (WindowAbstraction) constructor.newInstance(new Object[]{_windowStage});

            _windowList.put(windowClass.getName() ,classInstance);

            return classInstance;
        }
        catch(NoSuchMethodException  exception)
        {
            exception.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}

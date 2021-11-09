package meatsuko.javafx;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.*;

import java.io.IOException;
import java.net.URL;

public abstract class FXController<T>
{
    protected Stage _currentStage;

    public T FXController(Stage windowStage, URL location)
    {
        this._currentStage = windowStage;

        FXMLLoader fxmlLoader = new FXMLLoader(location);

        fxmlLoader.setController(this);

        try
        {
            Parent parent = fxmlLoader.load();

            this._currentStage.setScene(new Scene(parent));
        }
        catch (IOException ioException)
        {
            ioException.printStackTrace();
            return null;
        }

        return (T) fxmlLoader.getController();


    }



    public T setTitle(String titleText)
    {
        this._currentStage.setTitle(titleText);

        return (T) this;
    }
    public T setResizable(boolean resizable)
    {
        this._currentStage.setResizable(resizable);

        return (T) this;
    }


    public T windowHide()
    {
        this._currentStage.hide();

        return (T) this;
    }

    public T windowShow()
    {
        this._currentStage.show();

        return (T) this;
    }

    public T windowShowModal()
    {
        this._currentStage.initModality(Modality.WINDOW_MODAL);
        this._currentStage.initStyle(StageStyle.UTILITY);
        this._currentStage.show();

        return (T) this;
    }

    public T windowShowModal(Window window)
    {
        this._currentStage.initOwner(window);

        this.windowShowModal();

        return (T) this;
    }

    public T setOnCloseRequest(EventHandler<WindowEvent> eventHandler)
    {
        this._currentStage.setOnCloseRequest(eventHandler);

        return (T) this;
    }

    @FXML protected abstract void initialize();

}

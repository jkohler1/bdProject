package be.ulb.controllers;

import be.ulb.controllers.views.*;
import be.ulb.exceptions.NavigationException;
import be.ulb.models.Query;

import java.io.IOException;

public class StatController extends BaseController implements StatViewController.ViewListener {

    private String currentTitle;

    private Query query;

    private final Listener listener;

    private StatViewController viewController;

    public StatController(String currentTitle, Query query, Listener listener) {
        this.currentTitle = currentTitle;
        this.query = query;
        this.listener = listener;
    }

    @Override
    public void show() {
        try {
            switch(Integer.parseInt(currentTitle.split("\\.")[0])) {
                case 1:
                    viewController = (Query1ViewController) ViewLoader.getInstance().loadView("Query1View.fxml");
                    break;
                case 2:
                    viewController = (Query2ViewController) ViewLoader.getInstance().loadView("Query2View.fxml");
                    break;
                case 3:
                    viewController = (Query3ViewController) ViewLoader.getInstance().loadView("Query3View.fxml");
                    break;
                case 4:
                    viewController = (Query4ViewController) ViewLoader.getInstance().loadView("Query4View.fxml");
                    break;
                case 5:
                    viewController = (Query5ViewController) ViewLoader.getInstance().loadView("Query5View.fxml");
                    break;
                case 6:
                    viewController = (Query6ViewController) ViewLoader.getInstance().loadView("Query6View.fxml");
                    break;
                default:
                    break;
            }
            viewController.setListener(this);
            viewController.initStats(query);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void goBack() throws NavigationException {
        this.listener.navigateBack();
    }


    public interface Listener {
        /**
         * Navigate back
         * @throws NavigationException nav exception
         */
        void navigateBack() throws NavigationException;
    }
}

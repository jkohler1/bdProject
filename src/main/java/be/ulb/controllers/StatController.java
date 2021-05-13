package be.ulb.controllers;

import be.ulb.controllers.views.StatViewController;
import be.ulb.controllers.views.ViewLoader;
import be.ulb.exceptions.NavigationException;
import be.ulb.models.Query;
import be.ulb.models.Vaccin;

import java.io.IOException;

public class StatController extends BaseController implements StatViewController.ViewListener {

    private Query query;

    private final Listener listener;

    private StatViewController viewController;

    public StatController(Query query, Listener listener) {
        this.query = query;
        this.listener = listener;
    }

    @Override
    public void show() throws NavigationException, IOException {
        try {
            viewController = (StatViewController) ViewLoader.getInstance().loadView("StatView.fxml");
            viewController.setListener(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void goBack() throws NavigationException {
        this.listener.navigateBack();
    }

    @Override
    public void getInfo() {
        query.getRows().forEach(elem->{
            Vaccin v = new Vaccin(elem.get(0).toString(), elem.get(1).toString().split(","));
            System.out.println(v);
        });
    }


    public interface Listener {
        /**
         * Navigate back
         * @throws NavigationException nav exception
         */
        void navigateBack() throws NavigationException;
    }
}

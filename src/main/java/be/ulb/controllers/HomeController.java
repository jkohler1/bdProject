package be.ulb.controllers;

import be.ulb.controllers.views.HomeViewController;
import be.ulb.controllers.views.ViewLoader;
import be.ulb.models.Query;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.*;

public class HomeController extends BaseController implements HomeViewController.ViewListener{

    private HomeViewController viewController;

    private Map<String, String> listOfQueries = new HashMap<>();

    @Override
    public void show() {
        try {
            viewController = (HomeViewController) ViewLoader.getInstance().loadView("HomeView.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        viewController.setListener(this);

        getListOfQueries();
        viewController.setListOfQueries(listOfQueries.keySet());
    }

    private void getListOfQueries() {
        try {
            File file = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("sol_db")).toURI());
            Scanner reader = new Scanner(file);
            StringBuilder queryBuilder = new StringBuilder();
            String title = null;
            int flag = 0;
            while(reader.hasNextLine()){
                switch (flag){
                    case 1: // After title need to ++
                        flag++;
                        reader.nextLine();
                        break;
                    case 2: // Data line, multiple allowed, we must find next \n
                        String line = reader.nextLine();
                        if(line.length() == 0){
                           listOfQueries.put(title, queryBuilder.toString());
                            queryBuilder.delete(0, queryBuilder.length());
                            flag=0;
                            break;
                        }
                        queryBuilder.append(line).append("\n");
                        break;
                    default: // Base case flag == 0 -> title of a query
                        title = reader.nextLine();
                        flag++;
                        break;
                }
            }
        } catch (URISyntaxException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void execQuery(String text) {
        Query query = new Query(text);
        try {
            query.exec();
            viewController.setTableViewData(query.getFields(), query.getRows());
        } catch (SQLException e) {
            viewController.showInformation(e.getMessage());
        }
    }

    @Override
    public void showStats() {

    }

    @Override
    public void loadQuery(String selectedQuery) {
        viewController.setQueryArea(listOfQueries.get(selectedQuery));
    }
}

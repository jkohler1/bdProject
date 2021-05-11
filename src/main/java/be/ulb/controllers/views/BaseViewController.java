package be.ulb.controllers.views;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Region;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import javafx.scene.control.Alert;

public class BaseViewController {

abstract class BaseViewController {
    /**
     * Displays notification of a success demand.
     *
     * @param title       of the alert
     * @param information content to display
     */
    public void showInformation(String title, String information) {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle(title);
        alert.setContentText(information);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.getButtonTypes().setAll(ButtonType.OK);
        alert.showAndWait();
    }
    protected BaseViewController(){}

    public void showInformation(String text){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(text);
        alert.show();
    }
}

    public void showInformation(String information) {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Information");
        alert.setContentText(information);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.getButtonTypes().setAll(ButtonType.OK);
        alert.showAndWait();
    }

    /**
     * Displays notification of a collaboration demand.
     *
     * @param message message to show on popup
     * @return boolean to indicate choice (yes or no)
     */
    public boolean showCollaborationDemand(String message) {
        ButtonType yesButton = ButtonType.YES;
        ButtonType noButton = ButtonType.NO;
        return alertCreatorAndHandler("Notification", message, yesButton, noButton, Alert.AlertType.CONFIRMATION);
    }

    /**
     * Get the choice of the user in a binary choice pop-up.
     *
     * @param alertTitle  Title of alert box
     * @param alertMsg    Message in alert box
     * @param yesButton   Ok Button
     * @param noButton    Cancel buttons
     * @param typeOfAlert Alert.AlterType : type of the alert we handle
     * @return true if choice is positive; else false
     */
    private boolean alertCreatorAndHandler(String alertTitle, String alertMsg, ButtonType yesButton, ButtonType noButton, Alert.AlertType typeOfAlert) {
        Alert alert = new Alert(typeOfAlert); //credits: https://tagmycode.com/snippet/5207/yes-no-cancel-dialog-in-javafx
        alert.setTitle(alertTitle);
        alert.setContentText(alertMsg);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

        AtomicBoolean retVal = new AtomicBoolean(false);
        alert.getButtonTypes().setAll(yesButton, noButton);
        alert.showAndWait().ifPresent(type -> {
            if ((type == yesButton)) {
                retVal.set(true);
            }
        });
        return retVal.get();
    }

    public String alertCommitMessage() {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Commit message");
        dialog.setHeaderText("Enter a commit message :");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            return result.get();
        }
        return null;
    }

    public String setPasswordPopup(String projectName) {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Password");
        dialog.setHeaderText("Enter a password for the project : " + projectName);

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            return result.get();
        }
        return null;
    }

    /**
     * Displays notification of a collaboration demand.
     *
     * @param message message to show on popup
     */
    public void showAcceptedCollaboration(String message) {
        String alertTitle = "Notification";
        Alert alert = new Alert(Alert.AlertType.NONE); //credits: https://tagmycode.com/snippet/5207/yes-no-cancel-dialog-in-javafx
        alert.setTitle(alertTitle);
        alert.setContentText(message);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.getButtonTypes().setAll(ButtonType.OK);
        alert.showAndWait();
    }

    /**
     * Displays notification of a success demand.
     *
     * @param title of the alert
     * @param msg   content to display
     */
    public void showSuccess(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.NONE); //credits: https://tagmycode.com/snippet/5207/yes-no-cancel-dialog-in-javafx
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.getButtonTypes().setAll(ButtonType.OK);
        alert.showAndWait();
    }


    /**
     * Open a Javafx error window that will block other windows until accepted
     *
     * @param errorTitle String corresponding to title of the error window
     * @param errorMsg   String corresponding to the error message shown on the window
     * @param closeApp   boolean if true, this error may close the app, otherwise do nothing
     */
    public void showError(String errorTitle, String errorMsg, boolean closeApp) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText(errorTitle);
        errorAlert.setContentText(errorMsg);
        errorAlert.showAndWait();
        if (closeApp) {
            System.exit(-1);
        }
    }

    /**
     * Open a Javafx error window that will block other windows until accepted
     *
     * @param errorTitle String corresponding to title of the error window
     * @param errorMsg   String corresponding to the error message shown on the window
     */
    public void showError(String errorTitle, String errorMsg) {
        showError(errorTitle, errorMsg, false);
    }

    /**
     * Open a Javafx confirmation window that will block other windows until accepted or rejected
     *
     * @param confirmMsg String corresponding to message shown on the confirmation window
     * @return boolean to indicate whether user has accepted or rejected the confirmation
     */
    public boolean askConfirm(String confirmMsg) {
        String alertTitle = "Confirm";
        ButtonType okButton = ButtonType.OK;
        ButtonType noButton = ButtonType.CANCEL;
        return alertCreatorAndHandler(alertTitle, confirmMsg, okButton, noButton, Alert.AlertType.CONFIRMATION);
    }

    public boolean askConfirmOverride(String confirmMsg) {
        String alertTitle = "Override";
        ButtonType okButton = ButtonType.OK;
        ButtonType noButton = ButtonType.CANCEL;
        return alertCreatorAndHandler(alertTitle, confirmMsg, okButton, noButton, Alert.AlertType.CONFIRMATION);
    }

    public boolean askConfirmCheckout(String confirmMsg) {
        String alertTitle = "Checkout";
        ButtonType okButton = ButtonType.OK;
        ButtonType noButton = ButtonType.CANCEL;
        return alertCreatorAndHandler(alertTitle, confirmMsg, okButton, noButton, Alert.AlertType.CONFIRMATION);
    }
}
package be.ulb.controllers.views;

public class HomeViewController extends BaseViewController{

    private ViewListener listener;

    public void setListener(ViewListener listener){
        this.listener = listener;
    }
    
    public interface ViewListener{
        
    }
}

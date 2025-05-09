package models;

import core.Model;
import core.View;

public class BancoModel implements Model {
    private View view;
    private String message;

    @Override
    public void attach(View view) {
        this.view = view;
    }

    @Override
    public void detach(View view) {
        if (this.view == view) {
            this.view = null;
        }
    }

    @Override
    public void notifyViews() {
        if (view != null) {
            view.update(this, message);
        }
    }

    public void log(String log) {
        message = log;
        notifyViews();
    }

}
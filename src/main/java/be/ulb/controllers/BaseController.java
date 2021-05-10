package be.ulb.controllers;

import be.ulb.exceptions.NavigationException;
import java.io.IOException;

abstract class BaseController {

    public abstract void show() throws NavigationException,IOException;

}

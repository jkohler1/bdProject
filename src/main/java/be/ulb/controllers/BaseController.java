package be.ulb.controllers;

import be.ulb.exceptions.NavigationException;

abstract class BaseController {

    public abstract void show() throws NavigationException;

}

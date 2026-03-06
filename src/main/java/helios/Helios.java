package helios;

import helios.controller.Controller;

/**
 * The main entry point for the Helios chatbot application
 * This class is responsible for initializing the controller and starting the application loop.
 */
public class Helios {
    public static void main(String[] args) {
        new Controller().run();
    }
}
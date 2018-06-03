package ru.artembotnev.mntk.model.net.events;

/**
 * Container for error message, creates when there is network error
 * <p>
 * Create by Artem Botnev 03.06.2018
 */
public class EventNetError {
    private String message;

    public EventNetError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

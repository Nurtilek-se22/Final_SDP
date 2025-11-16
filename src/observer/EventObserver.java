package observer;

import event.Event;

public interface EventObserver {
    void handleEvent(Event event);
}

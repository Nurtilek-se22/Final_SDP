package observer;

import event.GameEvent;

public interface EventObserver {
    void handleEvent(GameEvent event);
}

package observer;

import utils.Logger;
import utils.Error;
import event.Event;

public class ConsoleObserver implements EventObserver {
    @Override public void handleEvent(Event event) {
        switch (event) {
            case Event.PickUpKey:    Logger.info.printfn("OBSERVER: Player picked up key."); break;
            case Event.PickUpBoots:  Logger.info.printfn("OBSERVER: Player picked up boots."); break;
            case Event.OpenDoor:     Logger.info.printfn("OBSERVER: Player opened the door."); break;
            case Event.GameFinished: Logger.info.printfn("OBSERVER: Player finished the game!"); break;
            default: throw Error.New("UNREACHABLE: Event");
        }
    }
}

package observer;

import utils.Logger;
import utils.Error;
import event.GameEvent;

public class ConsoleObserver implements EventObserver {
    @Override public void handleEvent(GameEvent event) {
        switch (event) {
            case GameEvent.PickUpKey:    Logger.info.printfn("OBSERVER: Player picked up key."); break;
            case GameEvent.PickUpBoots:  Logger.info.printfn("OBSERVER: Player picked up boots."); break;
            case GameEvent.OpenDoor:     Logger.info.printfn("OBSERVER: Player opened the door."); break;
            case GameEvent.GameFinished: Logger.info.printfn("OBSERVER: Player finished the game!"); break;
            default: throw Error.New("UNREACHABLE: GameEvent");
        }
    }
}

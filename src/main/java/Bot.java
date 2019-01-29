import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {
    List<Person> listPerson = new ArrayList<Person>();

    public Bot(){
        listPerson.add(new Person(00000000));
    }
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        personSet(message);
        for (Person p:listPerson) {
            System.out.println(p.getID());
        }
    }

    public String getBotUsername() {
        return "@NewMyCurrentBot";
    }

    public String getBotToken() {
        return "786597984:AAErY7si5D9ZeKJW7T1Kj5cyyq2kFH7WYdM";
    }

    private void personSet(Message message) {
        int ID = message.getFrom().getId();
        for (Person person:listPerson){
            if(person.getID()==ID){
                return;
            }
        }
        listPerson.add(new Person(ID));
    }
}

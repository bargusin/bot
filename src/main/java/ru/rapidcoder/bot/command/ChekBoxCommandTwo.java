package ru.rapidcoder.bot.command;

import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ru.rapidcoder.bot.component.CheckBoxComponent;

public class ChekBoxCommandTwo extends ServiceCommand {

    public ChekBoxCommandTwo(String identifier, String description) {
        super(identifier, description);
    }

    private InlineKeyboardMarkup createKeyboard() {
        CheckBoxComponent checkBoxComponent = new CheckBoxComponent();
        /*checkBoxComponent.addItem(new ChekBoxButton("Button 1.2", "CallbackData_1.2"));
        checkBoxComponent.addItem(new ChekBoxButton("Button 2.2", "CallbackData_2.2"));

        checkBoxComponent.addSender(
                new SendCheckBoxButton("Send", "sendCallbackData_2", checkBoxComponent.getItems()),
                new CancelCheckBoxButton("Cancel", "cancelCallbackData_2", checkBoxComponent.getItems())
        );*/
        return checkBoxComponent.getKeyboardMarkup();
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        InlineKeyboardMarkup keyboardMarkup = createKeyboard();
        sendAnswer(absSender, chat.getId(), "Выбор вариантов:", keyboardMarkup);
    }

}

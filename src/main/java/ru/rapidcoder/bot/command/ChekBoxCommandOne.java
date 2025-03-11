package ru.rapidcoder.bot.command;

import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ru.rapidcoder.bot.component.CheckBoxComponent;
import ru.rapidcoder.bot.component.ChekBoxButton;

public class ChekBoxCommandOne extends ServiceCommand {

    public ChekBoxCommandOne(String identifier, String description) {
        super(identifier, description);
    }

    private InlineKeyboardMarkup createKeyboard() {
        CheckBoxComponent checkBoxComponent = new CheckBoxComponent();
        checkBoxComponent.addItem(new ChekBoxButton("[ ] Button 1.1", "[X] Button 1.1", "CallbackData_1.1"));
        checkBoxComponent.addItem(new ChekBoxButton("[ ] Button 2.1", "[X] Button 2.1", "CallbackData_2.1"));
        checkBoxComponent.addItem(new ChekBoxButton("[ ] Button 3.1", "[X] Button 3.1", "CallbackData_3.1"));
        return checkBoxComponent.getKeyboardMarkup();
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        InlineKeyboardMarkup keyboardMarkup = createKeyboard();
        keyboardManager.save(chat.getId(), keyboardMarkup);
        sendAnswer(absSender, chat.getId(), "Выбор вариантов:", keyboardMarkup);
    }

}

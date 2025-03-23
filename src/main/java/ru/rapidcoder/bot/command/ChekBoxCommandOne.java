package ru.rapidcoder.bot.command;

import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ru.rapidcoder.bot.component.CheckBoxComponent;
import ru.rapidcoder.bot.component.ChekBoxButton;
import ru.rapidcoder.bot.component.SendCheckBoxButton;
import ru.rapidcoder.bot.handler.CheckBoxHandler;
import ru.rapidcoder.bot.handler.SendCheckBoxHandler;

public class ChekBoxCommandOne extends ServiceCommand {

    public ChekBoxCommandOne(String identifier, String description) {
        super(identifier, description);
    }

    private InlineKeyboardMarkup createKeyboard() {
        CheckBoxComponent checkBoxComponent = new CheckBoxComponent();
        addCheckBoxButton(checkBoxComponent, "Button 1.1", "CallbackData_1.1");
        addCheckBoxButton(checkBoxComponent, "Button 2.1", "CallbackData_2.1");
        addCheckBoxButton(checkBoxComponent, "Button 3.1", "CallbackData_3.1");

        SendCheckBoxButton sendCheckBoxButton = new SendCheckBoxButton("Send", "sendCallbackData_1");
        SendCheckBoxHandler sendCheckBoxHandler = new SendCheckBoxHandler(sendCheckBoxButton);
        sendCheckBoxHandler.setCheckBoxButtons(checkBoxComponent.getItems());
        sendCheckBoxHandler.setKeyboardMarkup(checkBoxComponent.getKeyboardMarkup());

        checkBoxComponent.addSender(sendCheckBoxButton,sendCheckBoxHandler);

        return checkBoxComponent.getKeyboardMarkup();
    }

    private void addCheckBoxButton(CheckBoxComponent component, String name, String callbackData) {
        ChekBoxButton chekBoxButton = new ChekBoxButton(name, callbackData);
        CheckBoxHandler handler = new CheckBoxHandler(chekBoxButton);
        handler.setKeyboardMarkup(component.getKeyboardMarkup());
        component.addItem(chekBoxButton, handler);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        InlineKeyboardMarkup keyboardMarkup = createKeyboard();
        sendAnswer(absSender, chat.getId(), "Выбор вариантов:", keyboardMarkup);
    }

}

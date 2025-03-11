package ru.rapidcoder.bot.command;

import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.util.ArrayList;

public class StartCommand extends ServiceCommand {

    private ReplyKeyboardMarkup replyKeyboardMarkup;

    public StartCommand(String identifier, String description) {
        super(identifier, description);
        initKeyboard();
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        sendAnswer(absSender, chat.getId(), "Let's go! For help, push /help", replyKeyboardMarkup);
    }

    void initKeyboard() {
        replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRows.add(keyboardRow);
        keyboardRow.add(new KeyboardButton("/Test 1.0"));
        keyboardRow.add(new KeyboardButton("/Test 2.0"));
        replyKeyboardMarkup.setKeyboard(keyboardRows);
    }

}

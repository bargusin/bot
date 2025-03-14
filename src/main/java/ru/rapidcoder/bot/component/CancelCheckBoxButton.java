package ru.rapidcoder.bot.component;

import org.apache.commons.lang3.StringUtils;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

public class CancelCheckBoxButton extends InlineKeyboardButton implements Component {

    List<ChekBoxButton> checkBoxButtons;

    public CancelCheckBoxButton(String text, String callbackData, List<ChekBoxButton> checkBoxButtons) {
        setText(text);
        setCallbackData(callbackData);
        this.checkBoxButtons = checkBoxButtons;
    }

    @Override
    public String execute() {
        for (ChekBoxButton button : checkBoxButtons) {
            button.refresh();
        }
        return null;
    }
}
